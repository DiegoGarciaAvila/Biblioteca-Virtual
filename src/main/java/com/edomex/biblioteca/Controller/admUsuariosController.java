package com.edomex.biblioteca.Controller;

import com.edomex.biblioteca.Dao.AppUserDao;
import com.edomex.biblioteca.Dao.PrestamoDao;
import com.edomex.biblioteca.Entity.AppUser;
import com.edomex.biblioteca.Entity.Cstsusr;
import com.edomex.biblioteca.Entity.GenUser;
import com.edomex.biblioteca.Service.AppUserService;
import com.edomex.biblioteca.Service.CatuadsService;
import com.edomex.biblioteca.Service.CstsusrService;
import com.edomex.biblioteca.Service.GenUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/Admin")
public class admUsuariosController {
    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppUserDao appUserDao;

    @GetMapping("/Usuarios.xhtm")
    public String admViewUsuarios(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("admin", appUserService.userById(user.getUsername()));
        Cstsusr act=new Cstsusr();
        act.setCcvestsusr(1);
        model.addAttribute("acts",appUserDao.findByUastsusr(act));
        Cstsusr susp=new Cstsusr();
        susp.setCcvestsusr(3);
        model.addAttribute("susps",appUserDao.findByUastsusr(susp));
        Cstsusr deu=new Cstsusr();
        deu.setCcvestsusr(4);
        model.addAttribute("deuds",appUserDao.findByUastsusr(deu));
        return "AdmUsr";
    }

    @Autowired
    private PrestamoDao prestamoDao;
    @Autowired
    private CstsusrService cstsusrService;

    @GetMapping("/Usuario.xhtm/Detalles/{id}")
    public String unUsuario(Model model, @PathVariable String id){
        model.addAttribute("detalle",appUserService.userById(id));
        AppUser userr= new AppUser();
        userr.setUacveserv(id);
        model.addAttribute("prestamos",prestamoDao.findByServidor(userr));
        model.addAttribute("csts",cstsusrService.cstslista());
        return "detAdmUsr";
    }

    @Autowired
    private CatuadsService catuadsService;
    @Autowired
    private GenUserService genUserService;
    @GetMapping("/Usuario.xhtm/Detalles/Modificar/{id}")
    public String modUnUser(Model model, @PathVariable String id){
        model.addAttribute("detalle",appUserService.userById(id));
        AppUser userr= new AppUser();
        userr.setUacveserv(id);
        model.addAttribute("prestamos",prestamoDao.findByServidor(userr));
        model.addAttribute("cadu",catuadsService.mostrarCatAdmin());
        model.addAttribute("csts",cstsusrService.cstslista());
        model.addAttribute("genero",genUserService.mostrarGenUser());
        return "AdmModUsr";
    }

    @PostMapping("/Usuario.xhtm/detalles/guardar")
    public String GuarstsUsr(AppUser appUser){
        appUserService.guardarAU(appUser);

        return "redirect:/Admin/Usuarios.xhtm";
    }

}
