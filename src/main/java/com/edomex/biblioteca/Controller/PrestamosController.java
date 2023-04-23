package com.edomex.biblioteca.Controller;

import com.edomex.biblioteca.Dao.DetprestamoDao;
import com.edomex.biblioteca.Dao.PrestamoDao;
import com.edomex.biblioteca.Entity.AppUser;
import com.edomex.biblioteca.Entity.CatestPrest;
import com.edomex.biblioteca.Entity.Detprestamo;
import com.edomex.biblioteca.Entity.Prestamo;
import com.edomex.biblioteca.Service.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Historial")
public class PrestamosController {
    @Autowired
    private AppUserService appUserService;

    @Autowired
    private PrestamoDao prestamoDao;

    @Autowired
    private MensajeService mensajeService;

    @Autowired
    private DetPrestamoService detPRestamoService;

    @GetMapping("/Prestamos.xhtm")
    public String incio(Model model, @AuthenticationPrincipal User user, Prestamo prestamo) {
        System.out.println("\n ENtra en prestamo");
        model.addAttribute("user", appUserService.userById(user.getUsername()));
        AppUser usuario = new AppUser();
        usuario.setUacveserv(user.getUsername());
        model.addAttribute("attrs", prestamoDao.prestamosOrdenados(user.getUsername()));
        model.addAttribute("mensajes",mensajeService.getMensajesOrd(user.getUsername()));

        return "Prestamos";
    }

    @Autowired
    private PrestamoService prestamoService;
    @Autowired
    private DetprestamoDao detprestamoDao;

    @GetMapping("/Prestamos.xhtm/detalle/{id}")
    public String detallePrestamo(Model model, @PathVariable Integer id) {
        model.addAttribute("prestamo", prestamoService.prestamoById(id));
        Prestamo prest = new Prestamo();
        prest.setPcvepres(id);
        model.addAttribute("libros", detprestamoDao.findByDetcvepres(prest));
        return "detprestamo";
    }

    //crear pdf
    @Autowired
    private LibroService libroService;

    @GetMapping("/Prestamo.xhtm/DescargarPDf/{id}")
    public void dcrgPDF(HttpServletResponse response,@PathVariable Integer id)throws IOException{
        descarga(response,id);
    }

    public void descarga(HttpServletResponse response,@PathVariable Integer id)throws IOException{
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition",String.format("attachment; filename=\"SolicitudPrestamo.pdf\""));
        OutputStream out=response.getOutputStream();
        generateReport(getUserloged(),id,out);
        out.close();
    }
    private String getUserloged() {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    public void generateReport(String user, @PathVariable Integer id,OutputStream out)throws FileNotFoundException {
        AppUser usr=appUserService.userById(user);
        try {
            String filepath="src/main/resources/reports/ReportePrestamo.jrxml";
            JRBeanCollectionDataSource bcd=new JRBeanCollectionDataSource(libroService.reportePartLibros(id));
            Map<String, Object> reportParam = new HashMap<String,Object>();
            reportParam.put("ds", bcd);
            reportParam.put("logoestado", ClassLoader.getSystemResourceAsStream("static/styles/cssandjs/img/logoestado.png"));
            reportParam.put("Logo_GEM", ClassLoader.getSystemResourceAsStream("static/styles/cssandjs/img/Logo_GEM.png"));
            reportParam.put("foot", ClassLoader.getSystemResourceAsStream("static/styles/cssandjs/img/foot.png"));
            reportParam.put("claveServ", user);
            reportParam.put("folio",id);
            reportParam.put("NombreC", usr.getUanombre()+" "+usr.getUaappaterno()+" "+ usr.getUaamaterno());
          //  reportParam.put("fecpres", prestamoService.fecpres(id));

            InputStream input=new FileInputStream(new File(filepath));
            JasperDesign jsDesign= JRXmlLoader.load(input);
            JasperReport jasperReport=JasperCompileManager.compileReport(jsDesign);
            JasperPrint regReport = JasperFillManager.fillReport(jasperReport, reportParam, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfStream(regReport,out);

        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }
    @GetMapping("/Cancelar.xthm/{id}")
    public String CancelaCompra(Model model,@PathVariable int id){
        Prestamo prestamo=prestamoService.encontrarPorID(id);
        CatestPrest catestPrest=new CatestPrest();
        catestPrest.setCstspre(5);
        prestamo.setCatestPrest(catestPrest);
        prestamoService.guardarPrestamo(prestamo);
        List<Integer> detprestamo=detPRestamoService.listarLibrosIn(id);
        libroService.actualizaSts(1,detprestamo);

        return "redirect:/Historial/Prestamos.xhtm";
    }
}
