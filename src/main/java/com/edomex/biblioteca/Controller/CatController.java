package com.edomex.biblioteca.Controller;

import com.edomex.biblioteca.Entity.*;
import com.edomex.biblioteca.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/Admin/Catalogo")
public class CatController {
    // GENERO LITERARIO
    @Autowired
    private GenLiterarioService genLiterarioService;

    @GetMapping("/GenerosLit.xhtm")
    public String genLiterario(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("generos", genLiterarioService.genLiterario());
        return "genLiterario";
    }
    @GetMapping("/Generos.xhtm/eliminar/{GCVEGEN}")
    public String eliminar( @PathVariable Integer GCVEGEN){
        genLiterarioService.eliminarGenLiterario(GCVEGEN);
        return "redirect:/Admin/Catalogo/GenerosLit.xhtm";
    }
    @PostMapping("/Generos.xhtm/guardar")
    public String guardar(GenLiterario genLiterario, RedirectAttributes redirectAttrs){
        genLiterarioService.guardarGenLiterario(genLiterario);
        return "redirect:/Admin/Catalogo/GenerosLit.xhtm";
    }

    //UNIDADES ADMINISTRATIVAS
    @Autowired
    CatuadsService catuadsService;

    @GetMapping("/UnidAds.xhtm")
    public String mostrarUniAdmin(Model model,@AuthenticationPrincipal User user){
        model.addAttribute("catUAdmins",catuadsService.mostrarCatAdmin());
        return "CatUAdmin";
    }

    @PostMapping("/UnidAds.xhtm/guardar")
    public String guardarUniAdmin(Catuads catuads){
        catuadsService.guardarCatAdmin(catuads);
        return "redirect:/Admin/Catalogo/UnidAds.xhtm";
    }

    @GetMapping("/UnidAds.xhtm/eliminar/{id}")
    public String eliminarUAdmin(@PathVariable String id){
        catuadsService.eliminarCatAdmin(id);
        return "redirect:/Admin/Catalogo/UnidAds.xhtm";
    }

    //CATEGORIA PRESTAMO
    @Autowired
    private CatestPrestService catestPrestService;

    @GetMapping("/EstatusPrestamo.xhtm")
    public String mostrar(Model model,@AuthenticationPrincipal User user){
        model.addAttribute("catprests",catestPrestService.mostrarPrest());
        return "catprest";
    }

    @PostMapping("/EstatusPrestamo.xhtm/guardar")
    public String guardarCatPrest(CatestPrest catestPrest){
        catestPrestService.guardarCatPrest(catestPrest);
        return "redirect:/Admin/Catalogo/EstatusPrestamo.xhtm";
    }

    @GetMapping("/EstatusPrestamo.xhtm/eliminar/{id}")
    public String eliminarCatPrest(@PathVariable Integer id){
        catestPrestService.eliminarCatPrest(id);
        return "redirect:/Admin/Catalogo/EstatusPrestamo.xhtm";
    }

    //FRASES
    @Autowired
    private FraseService fraseService;

    @GetMapping("/Frases.xhtm")
    public String mostrarFrase(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("frases",fraseService.mostrarFrase());
        return "frase";
    }
    @PostMapping("/Frases.xhtm/guardar")
    public String guardarFrase(Frase frase, @AuthenticationPrincipal User user){
        AppUser usuario=new AppUser();
        usuario.setUacveserv(user.getUsername());
        frase.setAppUser(usuario);
        fraseService.guardarFrase(frase);
        return "redirect:/Admin/Catalogo/Frases.xhtm";
    }
    @GetMapping("/Frases.xhtm/eliminar/{id}")
    public String eliminarFrase(@PathVariable Integer id){
        fraseService.eliminarFrase(id);
        return "redirect:/Admin/Catalogo/Frases.xhtm";
    }

    //EVENTO
    @Autowired
    private EventoService eventoService;

    @Autowired
    private CatStsEvService catStsEvService;

    @GetMapping("/Evento.xhtm")
    public String mostrarEvento(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("options",catStsEvService.estatusEv());
        model.addAttribute("eventos",eventoService.mostrarEventos());
        return "evento";
    }


    @PostMapping("/Evento.xhtm/guardar")
    public String guardarEvento(Evento evento,@AuthenticationPrincipal User user){
        AppUser usuario=new AppUser();
        usuario.setUacveserv(user.getUsername());
        evento.setAppUser(usuario);
        eventoService.agregarEvento(evento);
        return "redirect:/Admin/Catalogo/Evento.xhtm";
    }

    @GetMapping("/Evento.xhtm/eliminar/{id}")
    public String eliminarEvento(@PathVariable Integer id){
        eventoService.eliminarEvento(id);
        return "redirect:/Admin/Catalogo/Evento.xhtm";
    }
    @PostMapping("/Evento.xhtm/actsts")
    public String guardarSts(@RequestParam(name = "estat")Integer estat){
        System.out.println(estat);

        return estat.toString();
    }
}
