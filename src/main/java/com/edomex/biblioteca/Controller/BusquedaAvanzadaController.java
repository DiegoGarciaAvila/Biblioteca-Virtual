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
public class BusquedaAvanzadaController {

    @Autowired
    private GenLiterarioService genLiterarioService;
    @Autowired
    private LibroService libroService;
    @Autowired
    private PrestamoService prestamoService;
    @Autowired
    private AppUserService appUserService;
    @GetMapping("/BusquedaAvanzada.xhtm")
    public String incio(@RequestParam Map<String,Object> params, Model model, @AuthenticationPrincipal User user,
        @RequestParam(name = "titulo") String titulo,@RequestParam(name = "autor") String autor, @RequestParam(name = "genero") String genero) {
        model.addAttribute("botonRegresarA",true);



        try {

            List<GenLiterario> generos=null;
            generos=genLiterarioService.genLiterario();
            List<String> listagenero = new ArrayList<String>();

            for (int gl=0;gl<generos.size();gl++){
                listagenero.add(generos.get(gl).getGDESGEN());
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




        List<Libro> libros=null;
        System.out.println("\n-------------------------------------");

        //Busca en los controladores

        if (titulo.length()>0 && autor.length()>0 && genero.length()>0){

            libros=libroService.busqueda(titulo,autor,genero);
        }


        if (titulo.length()>0 && autor.length()<1 && genero.length()<1 ){
            libros=libroService.busquedaXTitulo(titulo);
        }
        if (titulo.length()<1 && autor.length()>0 && genero.length()<1){
            libros=libroService.busquedaXAutor(autor);
        }
        if (titulo.length()<1 && autor.length()<1 && genero.length()>1){
            libros=libroService.busquedaXGenero(genero);
        }

        ///////////////////////

        if (titulo.length()>0 && autor.length()<1 && genero.length()>0 ){
            libros=libroService.busquedaXTituloGenero(titulo,genero);
        }

        if (titulo.length()>0 && autor.length()>0 && genero.length()<1 ){
            libros=libroService.busquedaXTituloAutor(titulo,autor);
        }

        if (titulo.length()<1 && autor.length()>0 && genero.length()>0 ){
            libros=libroService.busquedaXGeneroAutor(genero,autor);
        }



        //System.out.println(libros.size()+"-----------------------");

        try {
            if (libros.size()<1){
                model.addAttribute("message","message");
            }
        }catch (Exception e){
            model.addAttribute("message","message");

        }


        model.addAttribute("libros",libros);
        model.addAttribute("busqueda",1);
        return "AcervoBi";
    }
}
