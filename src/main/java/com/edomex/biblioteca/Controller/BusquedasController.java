package com.edomex.biblioteca.Controller;


import com.edomex.biblioteca.Entity.Catstslibro;
import com.edomex.biblioteca.Entity.Libro;
import com.edomex.biblioteca.Service.CatStsLibroService;
import com.edomex.biblioteca.Service.GenLiterarioService;
import com.edomex.biblioteca.Service.LibroService;
import net.bytebuddy.asm.Advice;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/Admin")
public class BusquedasController {
    @Autowired
    private LibroService libroService;
    @Autowired
    private GenLiterarioService genLiterarioService;
    @Autowired
    private CatStsLibroService catStsLibroService;
    @GetMapping("/Busqueda.xhtm")
    public  String abprestamo(Model model, @RequestParam(name = "ninventario") int ninventario) {
        model.addAttribute("busquedal",libroService.getBusqueda(ninventario));
        return "AdmBusqueda";
    }




    @GetMapping("/Busqueda.xhtm/{cveLibro}")
    public String detalleBusqueda(Model model, @PathVariable int cveLibro){
       // System.out.println("------------------------------------"+cveLibro+".........................");
        model.addAttribute("libro",libroService.getLibro(cveLibro));
        model.addAttribute("estatuslibro",catStsLibroService.mmuestraEstatusLibro());
        model.addAttribute("generoslit",genLiterarioService.genLiterario());
        return "detBusqueda";
    }
    @PostMapping("/ActualizaLibro.xhtm")
    public String actualizaLibro(Model model, Libro libro){
            libroService.guardaLibro(libro);
        return "redirect:/Admin/Acervo.xhtm";
    }
}
