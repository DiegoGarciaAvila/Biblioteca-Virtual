package com.edomex.biblioteca.Controller;

import com.edomex.biblioteca.utils.guardarImg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/Imagen")
public class ChangeImgController {

    private String rutaJasper="C:\\Imagenes\\jasper\\";
    private String rutaPagina="C:\\Imagenes\\pagina\\";
    //Linux
    //private String rutaJasper="/opt/biblioteca/Imagenes/jasper/";
    //private String rutaPagina="/opt/biblioteca/Imagenes/pagina/";

    @GetMapping("/Cambiar.xhtm")
    public String cambiarImg(Model model){
        return "guardarImg";
    }

    @PostMapping("/guardarImg")
    public String saveImg(@RequestParam("archImg")MultipartFile multipart) {
        if (!multipart.isEmpty()) {
            try {
                String nombreImg = guardarImg.guardarImagen(multipart, rutaPagina);
            } catch (Exception e) {
                return "error" + e.getMessage();
            }
        }
        return "redirect:/Imagen/Cambiar.xhtm";
    }

    @PostMapping("/grdlogojp")
    public String savelogojp(@RequestParam("logjp") MultipartFile imgfile){
        if(!imgfile.isEmpty()){
            try{
                String ruta="C:\\Imagenes\\jasper\\";
                String logo=guardarImg.grdlogoJP(imgfile,rutaJasper);
            }catch (Exception e){
                return "Error: "+e.getMessage();
            }
        }
        return "redirect:/Imagen/Cambiar.xhtm";
    }

    @PostMapping("/grdestjp")
    public String saveestjp(@RequestParam("logestjp") MultipartFile imgfile){
        if(!imgfile.isEmpty()){
            try{
                String ruta="C:\\Imagenes\\jasper\\";
                String logo=guardarImg.grdestJP(imgfile,rutaJasper);
            }catch (Exception e){
                return "Error: "+e.getMessage();
            }
        }
        return "redirect:/Imagen/Cambiar.xhtm";
    }

    @PostMapping("/grdfootjp")
    public String savefootjp(@RequestParam("footjp") MultipartFile imgfile){
        if(!imgfile.isEmpty()){
            try{
                String ruta="C:\\Imagenes\\jasper\\";
                String logo=guardarImg.grdfootJP(imgfile,rutaJasper);
            }catch (Exception e){
                return "Error: "+e.getMessage();
            }
        }
        return "redirect:/Imagen/Cambiar.xhtm";
    }

    @PostMapping("/grdindIni")
    public String saveindIni(@RequestParam("indback") MultipartFile imgfile){
        if(!imgfile.isEmpty()){
            try{
                String logo=guardarImg.grdIndIni(imgfile,rutaPagina);
            }catch (Exception e){
                return "Error: "+e.getMessage();
            }
        }
        return "redirect:/Imagen/Cambiar.xhtm";
    }
}
