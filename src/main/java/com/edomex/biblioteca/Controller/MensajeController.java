package com.edomex.biblioteca.Controller;

import com.edomex.biblioteca.Entity.AppUser;
import com.edomex.biblioteca.Entity.Libro;
import com.edomex.biblioteca.Entity.Mensaje;
import com.edomex.biblioteca.Service.AppUserService;
import com.edomex.biblioteca.Service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/Ajax")
public class MensajeController {
    @Autowired
    private AppUserService appUserService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    MensajeService mensajeService;


    @GetMapping("/Mensaje")
    public String ValidaCURP(@RequestParam(name = "curp") String curp,
                             @RequestParam(name = "email") String email,
                             @AuthenticationPrincipal User user,
                             @RequestParam(name = "asunto") String asunto) {
            Mensaje mensaje=new Mensaje();
            mensaje.setMdestino("Admin");
            mensaje.setMasunto(asunto);
            mensaje.setMorigen(user.getUsername());
            mensaje.setMcorreo(email);
            mensaje.setMmensaje(curp);
            mensajeService.guardaMensaje(mensaje);
        try {
            sendEmail(mensaje.getMorigen(),mensaje.getMmensaje(),mensaje.getMcorreo(),mensaje.getMasunto());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return "OK";
    }
    @GetMapping("/MensajeADM")
    public String mensajeADM(@RequestParam(name = "curp") String curp,
                             @RequestParam(name = "cveserv") String cveserv,
                             @AuthenticationPrincipal User user,
                             @RequestParam(name = "asunto") String asunto) {
        Mensaje mensaje=new Mensaje();
        mensaje.setMdestino(cveserv);
        mensaje.setMasunto(asunto);
        mensaje.setMorigen("Admin");
        mensaje.setMmensaje(curp);
        mensajeService.guardaMensaje(mensaje);
        return "OK";
    }

    public void sendEmail( String origen, String cuerpo, String correoOrigen,String asunto) throws MessagingException {



        MimeMessage mimeMessage=mailSender.createMimeMessage();


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
                " <div class=\"NuevoMsj\">" +
                "   <h2 >EL servidor publico: "+ origen  + " ha mandado un mensaje al sistema de biblioteca</h2> " +
                "   <p > Mensaje: "+ cuerpo  + "</p> " +
                "   <p >El correo del servidor publico es: "+ correoOrigen  + "</p> " +
                "</div>"+
                "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa\" crossorigin=\"anonymous\"></script>\n" +
                "  </body>\n" +
                "</html>";
        MimeMessage adm=mailSender.createMimeMessage();
        MimeMessageHelper emailadm=new MimeMessageHelper(adm,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        emailadm.setTo("IntegracionFamiliar416@gmail.com");
        emailadm.setSubject(asunto);
        emailadm.setText(contenidoadm,true);
        mailSender.send(emailadm.getMimeMessage());
    }

}
