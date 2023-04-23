package com.edomex.biblioteca.Controller;

import com.edomex.biblioteca.Entity.AppUser;
import com.edomex.biblioteca.Entity.GenLiterario;
import com.edomex.biblioteca.Entity.Libro;
import com.edomex.biblioteca.Entity.Prestamo;
import com.edomex.biblioteca.Service.AppUserService;
import com.edomex.biblioteca.Service.GenLiterarioService;
import com.edomex.biblioteca.Service.LibroService;
import com.edomex.biblioteca.Service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/Libros")
public class AcervoController {

    @Autowired
    private GenLiterarioService genLiterarioService;

    @Autowired
    private LibroService libroService;
    @Autowired
    private PrestamoService prestamoService;
    @Autowired
    private AppUserService appUserService;
    @GetMapping("/AcervoBibliografico.xhtm")
    public String incio(@RequestParam Map<String,Object> params, Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("botonRegresarA",false);




      try {

          List<GenLiterario> genero=null;
          genero=genLiterarioService.genLiterario();
          List<String> listagenero = new ArrayList<String>();

          for (int gl=0;gl<genero.size();gl++){
              listagenero.add(genero.get(gl).getGDESGEN());
              model.addAttribute("listagenero",listagenero);
          }

          List<Libro> titulos=null;
          titulos=libroService.listaTitulos();
          List<String> listatitulos = new ArrayList<String>();
          List<String> listaautores = new ArrayList<String>();

          for (int tl=0;tl<titulos.size();tl++){
              listatitulos.add(titulos.get(tl).getLtitlibro());
              listaautores.add(titulos.get(tl).getLautor());

              model.addAttribute("listatitulos", listatitulos);
              model.addAttribute("listaautores",listaautores);

          }

      }catch (Exception e){
          System.out.println(e);
      }





        LocalDate cal =LocalDate.now();
        int mes=cal.getMonthValue();
        AppUser usser=new AppUser();
        usser.setUacveserv(user.getUsername());
        model.addAttribute("comprasxmes",prestamoService.prestamoxmes(user.getUsername(),mes));
        var bandera=prestamoService.obtenerUltRegi(usser);
        if (bandera!=null){
            model.addAttribute("stsprest",prestamoService.StatusPrestamo(user.getUsername(), bandera));
        }else{
            model.addAttribute("stsprest","0");
        }
        int page=params.get("page")!=null ? (Integer.parseInt(params.get("page").toString())-1):0;
        PageRequest pageRequest=PageRequest.of(page,30);
        Page<Libro> pageLibro =libroService.listarLibros(pageRequest);
        int totPage= pageLibro.getTotalPages();
        if (totPage>0 && page==0){
            List<Integer> pages= IntStream.rangeClosed(1,5).boxed().collect(Collectors.toList());
            model.addAttribute("pages",pages);
        }
        if (totPage>0 && page>0){
            if (page-2<1){
                List<Integer> pages = IntStream.rangeClosed(1, page + 4).boxed().collect(Collectors.toList());
                model.addAttribute("pages", pages);
            }else {
                List<Integer> pages = IntStream.rangeClosed(page - 2, page + 2).boxed().collect(Collectors.toList());
                model.addAttribute("pages", pages);
            }
        }
        model.addAttribute("busqueda",2);

        model.addAttribute("libros",pageLibro.getContent());

        Prestamo pres;
       try {

           pres =prestamoService.prestamoById(prestamoService.obtenerUltRegi(usser));

       }catch (Exception e){
           pres=null;
       }

        //Si encuentra algun prestamo
        if (pres!=null){
            List<Prestamo> presserv=prestamoService.findServidor(usser);
            List<Prestamo> subList=new ArrayList<>();

            for (int i=0;i<presserv.size();i++){
                if(presserv.get(i).getCatestPrest().getCstspre()==2 || presserv.get(i).getCatestPrest().getCstspre()==4){
                    subList.add(presserv.get(i));
                }
            }
          /*  1	"CONCLUIDO"
            2	"EN PROCESO"
            3	"CON RETRASO"
            4	"ACTIVO"
            5	"CANCELADO"*/
//Le esta preguntando si el estado de prestamo es concluido
//EN caso de que los prestamos esten concluidos se da permiso de seleccionar 5 libros

            if(pres.getCatestPrest().getCstspre()==1 || pres.getCatestPrest().getCstspre()==5){
                model.addAttribute("nlibros",5);
//Si el prestamos esta en proceso o activo contrara el numero de libros que tiene el prestamo
            }else if(pres.getCatestPrest().getCstspre()==2 || pres.getCatestPrest().getCstspre()==4){
                int nlib=0;
                for(int i=0;i<subList.size();i++){
                    if(nlib<=5) {
                        nlib += subList.get(i).getPnlibr();
                    }
                }
//Se le descuenta el numero de libros del prestamo
                model.addAttribute("nlibros",5-nlib);
            }else{
//Si tiene un prestamo con retraso o cancelado no se le permite
                model.addAttribute("nlibros",0);
            }
        }
//Si no tiene ningun prestamo
        else {

            model.addAttribute("nlibros",5);
        }

        return "AcervoBi";
    }



    @GetMapping("/AcervoBibliografico.xhtm/resena/{id}")
    public String Mresena(Model model, @PathVariable Integer id){
        model.addAttribute("getlibro",libroService.getLibro(id));
        return "resena";
    }
    @GetMapping("/AcervoBibliografico.xhtm/prestamo")
    public  String abprestamo(Model model) {
        DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now= LocalDateTime.now();
        model.addAttribute("fec",dtf.format(now));
        return "abprestamo";
    }



}
