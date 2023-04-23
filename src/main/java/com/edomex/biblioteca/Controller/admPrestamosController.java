package com.edomex.biblioteca.Controller;

import com.edomex.biblioteca.Dao.DetprestamoDao;
import com.edomex.biblioteca.Entity.AppUser;
import com.edomex.biblioteca.Entity.CatestPrest;
import com.edomex.biblioteca.Entity.Prestamo;
import com.edomex.biblioteca.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/Admin")
public class admPrestamosController {
@Autowired
private PrestamoService prestamoService;
@Autowired
private CatestPrestService catestPrestService;
@Autowired
private AppUserService appUserService;
@Autowired
private LibroService libroService;
@Autowired
private DetPrestamoService detPrestamoService;
@Autowired
private DetprestamoDao detprestamoDao;

    @GetMapping("/Prestamos.xhtm")
    public String incio(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("concluido",prestamoService.prestamosPorEstatus(1));
        model.addAttribute("proceso",prestamoService.prestamosPorEstatus(2));
        model.addAttribute("retraso",prestamoService.prestamosPorEstatus(3));
        model.addAttribute("disponible",prestamoService.prestamosPorEstatus(4));
        return "AdmPrest";
    }

    @GetMapping("/EditaPrestamo.xhtm/{cvePres}")
    public String modifica(Model model, @AuthenticationPrincipal User user, @PathVariable Integer cvePres) {
        Prestamo prest = new Prestamo();
        prest.setPcvepres(cvePres);
        model.addAttribute("estatusprest",catestPrestService.mostrarPrest());
        model.addAttribute("prestamo",prestamoService.encontrarPorID(cvePres));
        model.addAttribute("libros", detprestamoDao.findByDetcvepres(prest));

        return "detallePrestamo";
    }

    @PostMapping("/ActualizaPrestamo.xhtm/{cvePres}")
    public String actualizaPrestamo(@PathVariable Integer cvePres,@AuthenticationPrincipal User user, int estatus,String fdev, String fechareal, String entrega, String fechaac, String precibe, String recoge){


        actualizaEstatusPrestamo(estatus,cvePres,user.getUsername(),fdev,entrega,recoge,precibe,precibe,fechareal);
        return "redirect:/Admin/Prestamos.xhtm";
    }


    public void actualizaEstatusPrestamo(int estatus,int cvePres,String user,String fecadev, String entrega,String recoge
    ,String recibe,String devuelve,String fecrdev){
        CatestPrest sts =new CatestPrest();
        sts.setCstspre(estatus);
        Prestamo prestamo=prestamoService.encontrarPorID(cvePres);
        prestamo.setCatestPrest(sts);
        AppUser usuario=appUserService.userById(user);
        LocalDate fecha= LocalDate.now();

        /*   1	"CONCLUIDO"
        2	"EN PROCESO"
        3	"CON RETRASO"
        4	"ACTIVO"
        5	"CANCELADO"*/

        switch(estatus){
            case 1:

                try{
                    prestamo.setPfecrdev(LocalDate.parse(fecrdev));
                }catch (Exception e){
                    prestamo.setPfecadev(fecha.plusDays(5));
                }

                prestamo.setPrecibe(recibe);
                prestamo.setPdevuelve(devuelve);
               // prestamo.setPfecrdev(LocalDate.parse(fecrdev));
                libroService.actualizaSts(estatus,detPrestamoService.detLibros(cvePres));
                break;
            case 2:

                break;
            case 3:
                break;
            case 4:

                try{
                    prestamo.setPfecadev(LocalDate.parse(fecadev));
                }catch (Exception e){
                    prestamo.setPfecadev(fecha.plusDays(5));
                }
               // prestamo.setPfecadev(LocalDate.parse(fecadev));
                prestamo.setPentrega(entrega);
                prestamo.setPrecoge(recoge);

                break;
            case 5:
                sts.setCstspre(1);
                try{
                    prestamo.setPfecrdev(LocalDate.parse(fecrdev));
                }catch (Exception e){
                    prestamo.setPfecadev(fecha.plusDays(5));
                }

                prestamo.setPrecibe(recibe);
                prestamo.setPdevuelve(devuelve);
                // prestamo.setPfecrdev(LocalDate.parse(fecrdev));
                libroService.actualizaSts(estatus,detPrestamoService.detLibros(cvePres));
                break;
            default:
                System.out.println("Error");
                break;
        }
        prestamoService.guardarPrestamo(prestamo);
    }


}
