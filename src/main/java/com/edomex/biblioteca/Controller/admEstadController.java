package com.edomex.biblioteca.Controller;

import com.edomex.biblioteca.Service.CatuadsService;
import com.edomex.biblioteca.Service.GenLiterarioService;
import com.edomex.biblioteca.Service.LibroService;
import com.edomex.biblioteca.Service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Admin")
public class admEstadController {


    @Autowired
    private PrestamoService prestamoService;
    @Autowired
    private GenLiterarioService genLiterarioService;
    @Autowired
    private LibroService libroService;
    @Autowired
    private CatuadsService catuadsService;

    @GetMapping("/Estadisticas.xhtm")
    public String incio(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("genLite",genLiterarioService.viewGenLite());
        model.addAttribute("libros",libroService.viewGraphLibro());
        model.addAttribute("fFech",prestamoService.primFech());
        model.addAttribute("uFech",prestamoService.ultFech());
        model.addAttribute("uads",catuadsService.viewGraphUads());
        model.addAttribute("gpDia",prestamoService.graphDia());
        model.addAttribute("gpMes",prestamoService.graphMes());
        model.addAttribute("gpAnio",prestamoService.graphAnio());
        return "AdmEst";
    }


}
