package com.edomex.biblioteca.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PoliticasController {


    @GetMapping("/Politicas.xhtm")
    public String incio(Model model, @AuthenticationPrincipal User user) {
        String pathd;
        pathd="/Libros/AcervoBibliografico.xhtm";
        model.addAttribute("pathd",pathd);
        return "Politicas";
    }
}
