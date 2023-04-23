package com.edomex.biblioteca.Controller;

import com.edomex.biblioteca.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/Inicio")
public class ControladorInicio {
    @Autowired
    private FraseService fraseService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private DetPrestamoService detPrestamoService;

    @Autowired
    private LibroService libroService;

    @Autowired
    private UserGenServicee userGenService;

    @GetMapping("/BibliotecaGEM.xhtm")
    public String Principal(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("recomendaciones",libroService.recomendaciones(userGenService.recomendaciones(user.getUsername())));
        model.addAttribute("masleidos",libroService.masLeidos(detPrestamoService.MasLeidos()));
        model.addAttribute("frase", fraseService.fraseById(fraseService.findbyRando()));
        model.addAttribute("evento",eventoService.eventosPorEstattus(1));
        return "index";
    }
}
