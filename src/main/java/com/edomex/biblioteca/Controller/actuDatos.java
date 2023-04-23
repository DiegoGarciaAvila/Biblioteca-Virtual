package com.edomex.biblioteca.Controller;

import com.edomex.biblioteca.Service.GenLiterarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/actDatos")
public class actuDatos {
    @Autowired
    private GenLiterarioService genlite;

    @GetMapping("/form")
    public String fomulario(Model model){
        model.addAttribute("generos",genlite.genLiterario());
        return "registroYgeneros";
    }
}
