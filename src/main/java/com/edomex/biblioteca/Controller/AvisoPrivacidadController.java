package com.edomex.biblioteca.Controller;

import com.edomex.biblioteca.Service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AvisoPrivacidadController {
@Autowired
private AppUserService appUserService;

    @GetMapping("/")
    public String incio(Model model, @AuthenticationPrincipal User user) {
        String path;


        int stsUsr=appUserService.getEstatus(user.getUsername());
        System.out.println("---------------------"+stsUsr);
        if (stsUsr==2){
             path="/actDatos/form";
        }else {
            path="/Inicio/BibliotecaGEM.xhtm";
        }

        model.addAttribute("path",path);
        return "AvisoPriv";
    }
}
