package com.edomex.biblioteca.Controller;

import com.edomex.biblioteca.Entity.*;
import com.edomex.biblioteca.Service.*;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/regis")
public class regLibr {

    @Autowired
    private PrestamoService prestamoService;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private DetPrestamoService detPrestamoService;
    @Autowired
    private LibroService libroService;
    @Autowired
    private UserGenServicee userGenService;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/prueba")
    public RedirectView prueba(@RequestParam("ine")MultipartFile file,@RequestParam("uacomdom")MultipartFile dfile, @AuthenticationPrincipal User user){
        AppUser usuario=appUserService.userById(user.getUsername());
        usuario.setUaident(user.getUsername()+"_"+file.getOriginalFilename());
        usuario.setUacomdom(user.getUsername()+"_"+ dfile.getOriginalFilename());
        appUserService.saveUsuario(usuario);
        com.edomex.biblioteca.utils.guardarImg.guardaINE(file,"C:\\Imagenes\\Archivos\\"+user.getUsername()+"\\",user.getUsername()+"_"+file.getOriginalFilename());
        com.edomex.biblioteca.utils.guardarImg.guardauacomdom(dfile,"C:\\Imagenes\\Archivos\\"+user.getUsername()+"\\",user.getUsername()+"_"+dfile.getOriginalFilename());

        return new RedirectView("/Inicio/BibliotecaGEM.xhtm");
    }


    @GetMapping("/updUser")
    public Boolean updateusuario(HttpServletRequest request, @AuthenticationPrincipal User usr) throws IOException{
        String datauser=URLDecoder.decode(request.getQueryString(), StandardCharsets.UTF_8.name());
        System.out.println(datauser);
        //JSONArray arr=new JSONArray(datauser);
        //System.out.println(arr.getJSONObject(0).getJSONObject("file"));

        appUserService.updappuser(datauser, usr.getUsername());
        return true;
    }

    @GetMapping("/addGenuser")
    public Boolean addGenUser(HttpServletRequest request, @AuthenticationPrincipal User user) throws IOException {
        String generos=URLDecoder.decode(request.getQueryString(), StandardCharsets.UTF_8.name());
        userGenService.grdUsergen(generos,user.getUsername());

        return true;
    }

    @RequestMapping("/grdrTodo")
    public Boolean guardartodo(Prestamo prestamo,@AuthenticationPrincipal User user,HttpServletRequest request) throws IOException, MessagingException {
        System.out.println("Entra en guardar");
        List<Integer> libros = new ArrayList<Integer>();
        //obtener el usuario logeado
        AppUser usua=new AppUser();
        usua.setUacveserv(user.getUsername());

        //Modificar el usuario a deudor
        Cstsusr usr=new Cstsusr();
        usr.setCcvestsusr(4);
        usua.setUastsusr(usr);
        appUserService.modStatus(usr,usua);

        //lee el arr de obj
        String objResul= URLDecoder.decode(request.getQueryString(), StandardCharsets.UTF_8.name());
        JSONArray arr=new JSONArray(objResul);

        //guarda el prestamo
        prestamo.setServidor(usua);
        prestamo.setPfecpres(LocalDate.now());
        CatestPrest pres=new CatestPrest();
        pres.setCstspre(2);
        prestamo.setCatestPrest(pres);
        prestamo.setPnlibr(arr.length());
        prestamoService.guardarPrestamo(prestamo);
        //loop arr
        for (int i =0;i< arr.length();i++){
            int id=Integer.parseInt(arr.getJSONObject(i).getString("id"));
            //guarda en detprestamo
            detPrestamoService.guardardtPres(prestamoService.obtenerUltRegi(usua),id);
            libros.add(id);
            /*modificar el status del libro*/
            libroService.modStsLibr(2,libroService.reconoLibr(id));
        }
        System.out.println(libros+"-----------------------------------Libros-------------------------");
        System.out.println(user.getUsername()+"-----------------------------------user.getUsername()-------------------------");
        System.out.println(prestamo.getPcvepres()+"-----------------------------------prestamo.getPcvepres()-------------------------");

        sendEmail(libros,user.getUsername(),prestamo.getPcvepres());
        return true;
    }
    public void sendEmail(List<Integer> lista,String cveserv,Integer cvepres) throws MessagingException {
        AppUser usuario=appUserService.userById(cveserv);
        String filas="";
        int nlrb=0;
        for (Libro lb:libroService.masLeidos(lista)){
            nlrb=nlrb+1;
            String loopLibrToHTML=  "<tr>\n" +
                    "   <td>"+nlrb+"</td>\n" +
                    "   <td>"+lb.getLtitlibro()+"</td>\n" +
                    "   <td>"+lb.getLautor()+"</td>\n" +
                    "   <td>"+lb.getLeditorial()+"</td>\n" +
                    "</tr>";
            filas=filas+loopLibrToHTML;
        }
        //System.out.println(filas);
        String contenido="<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <title>Bootstrap demo</title>\n" +
                "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx\" crossorigin=\"anonymous\">\n" +
                "  </head>\n" +
                "  <body style=\"text-align:center\">\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"alert alert-success\" role=\"alert\">\n" +
                "            <svg xmlns=\"http://www.w3.org/2000/svg\" class=\"bi bi-exclamation-triangle-fill flex-shrink-0 me-2\" viewBox=\"0 0 16 16\" role=\"img\" aria-label=\"Success:\" width=\"2%\" height=\"2%\">\n" +
                "                <path d=\"M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z\"/>\n" +
                "            </svg>\n" +
                "                <b>Tu solicitud de prestamo de libros se ha generado con exito</b><br><b>Folio de solicitud: </b>\n" +cvepres+
                "\n" +
                "            <hr>\n" +
                "            <p class=\"mb-0\">Te recordamos imprimir tu formato de solicitud desde el sistema para acudir por los libros.<br> Detalle de la solicitud:</p>\n" +
                "        </div>\n" +
                "        <table align=\"center\" class=\"table\">\n" +
                "            <thead>\n" +
                "              <tr>\n" +
                "                <th scope=\"col\" class=\"table-primary\">#</th>\n" +
                "                <th scope=\"col\" class=\"table-primary\">Titulo</th>\n" +
                "                <th scope=\"col\" class=\"table-primary\">Autor</th>\n" +
                "                <th scope=\"col\" class=\"table-primary\">Editorial</th>\n" +
                "              </tr>\n" +
                "            </thead>\n" +
                "            <tbody>\n" +filas+

                "            </tbody>\n" +
                "          </table>\n" +
                "    </div>\n" +
                "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa\" crossorigin=\"anonymous\"></script>\n" +
                "  </body>\n" +
                "</html>";
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper email=new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        email.setTo(usuario.getUacorreo());
        email.setSubject("SOLICITUD DE PRESTAMO DE LIBROS");
        email.setText(contenido,true);
        // email.setText(contenido,true);
        mailSender.send(email.getMimeMessage());
        String filas2="";
        for (Libro lb:libroService.masLeidos(lista)){
            nlrb=nlrb+1;
            String loopLibrToHTML2=  "<tr>\n" +
                    "   <td>"+lb.getLcveinventario()+"</td>\n" +
                    "   <td>"+lb.getLtitlibro()+"</td>\n" +
                    "   <td>"+lb.getLautor()+"</td>\n" +
                    "   <td>"+lb.getLeditorial()+"</td>\n" +
                    "</tr>";
            filas2=filas2+loopLibrToHTML2;
        }
        //System.out.println(filas);
        String contenidoadm="<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <title>Bootstrap demo</title>\n" +
                "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx\" crossorigin=\"anonymous\">\n" +
                "  </head>\n" +
                "  <body style=\"text-align:center\">\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"alert alert-success\" role=\"alert\">\n" +
                "            <svg xmlns=\"http://www.w3.org/2000/svg\" class=\"bi bi-exclamation-triangle-fill flex-shrink-0 me-2\" viewBox=\"0 0 16 16\" role=\"img\" aria-label=\"Success:\" width=\"2%\" height=\"2%\">\n" +
                "                <path d=\"M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z\"/>\n" +
                "            </svg>\n" +
                "                <b>Se ha generado una solicitud de prestamo de libros</b><br><b>Folio de solicitud: </b>\n" +cvepres+"<br><b>Clave SP: </b>"+usuario.getUacveserv()+"" +
                "<br><b></b>"+
                "\n" +
                "            <hr>\n" +
                "            <p class=\"mb-0\">Detalle de la solicitud:</p>\n" +
                "        </div>\n" +
                "        <table align=\"center\" class=\"table\">\n" +
                "            <thead>\n" +
                "              <tr>\n" +
                "                <th scope=\"col\" class=\"table-primary\">No. Inventario</th>\n" +
                "                <th scope=\"col\" class=\"table-primary\">Titulo</th>\n" +
                "                <th scope=\"col\" class=\"table-primary\">Autor</th>\n" +
                "                <th scope=\"col\" class=\"table-primary\">Editorial</th>\n" +
                "              </tr>\n" +
                "            </thead>\n" +
                "            <tbody>\n" +filas2+

                "            </tbody>\n" +
                "          </table>\n" +
                "    </div>\n" +
                "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa\" crossorigin=\"anonymous\"></script>\n" +
                "  </body>\n" +
                "</html>";
        MimeMessage adm=mailSender.createMimeMessage();
        MimeMessageHelper emailadm=new MimeMessageHelper(adm,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        emailadm.setTo("IntegracionFamiliar416@gmail.com");
        emailadm.setSubject("SOLICITUD GENERADA");
        emailadm.setText(contenidoadm,true);
        mailSender.send(emailadm.getMimeMessage());
    }
}