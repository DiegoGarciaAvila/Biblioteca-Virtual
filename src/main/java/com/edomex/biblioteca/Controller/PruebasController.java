package com.edomex.biblioteca.Controller;

import com.edomex.biblioteca.Entity.AppUser;
import com.edomex.biblioteca.Entity.Libro;
import com.edomex.biblioteca.Service.AppUserService;
import com.edomex.biblioteca.Service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.ITemplateEngine;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Email")
public class PruebasController {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private LibroService libroService;

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/Enviar")
    public  String abprestamo(Model model) throws MessagingException {
        List<Integer> lista=List.of(74,76,77);
        //sendEmail("diego99.gar.avi@gmail.com","SAT","");
        sendEmail(lista,"987654321",1);
        return "Ok";
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
                "            <p class=\"mb-0\">Te recordamos imprimir tu formato de solicitud desde el sistema al acudir por los libros.<br> Detalle de la solicitud:</p>\n" +
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
        emailadm.setTo("pruebas.informatika@gmail.com");
        emailadm.setSubject("SOLICITUD GENERADA");
        emailadm.setText(contenidoadm,true);
        mailSender.send(emailadm.getMimeMessage());
    }
}
