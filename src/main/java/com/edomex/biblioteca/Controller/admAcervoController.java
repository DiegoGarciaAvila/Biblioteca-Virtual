package com.edomex.biblioteca.Controller;

import com.edomex.biblioteca.Entity.Libro;
import com.edomex.biblioteca.Service.CatStsEvService;
import com.edomex.biblioteca.Service.CatStsLibroService;
import com.edomex.biblioteca.Service.GenLiterarioService;
import com.edomex.biblioteca.Service.LibroService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/Admin")
public class admAcervoController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private GenLiterarioService genLiterarioService;

    @Autowired
    private CatStsLibroService catStsLibroService;


    @GetMapping("/Acervo.xhtm")
    public String incio(@RequestParam Map<String,Object> params,Model model, @AuthenticationPrincipal User user) {

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
        model.addAttribute("libros",pageLibro.getContent());


        //    model.addAttribute("libros",libroService.ltsLibros());
        model.addAttribute("generoslit",genLiterarioService.genLiterario());
        model.addAttribute("estatuslibro",catStsLibroService.mmuestraEstatusLibro());
        return "AdmAcerv";
    }

    @PostMapping("GuardaLibro.xhtm")
    public String guardaLibro(Libro libro, @AuthenticationPrincipal User user){
         libroService.guardaLibro(libro);
        return "redirect:/Admin/Acervo.xhtm";
    }
@GetMapping("/EliminaLibro.xhtm/{id}")
    public String eliminaLibro(@PathVariable Integer id, RedirectAttributes redirAttrs){
        try{
            libroService.eliminaLibro(id);
        }catch (DataIntegrityViolationException exception){
            redirAttrs.addFlashAttribute("message", "El libro no puede ser eliminado, ya fue utilizado previamente");

        }
        return "redirect:/Admin/Acervo.xhtm";

}

}
