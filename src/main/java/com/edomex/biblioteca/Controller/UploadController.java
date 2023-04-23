package com.edomex.biblioteca.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Controller
public class UploadController {

    @GetMapping("/download/{id}/{arch}")
    public void download(HttpServletResponse response,@PathVariable String id,@PathVariable String arch) throws Exception {
        File file = new File("C:\\Imagenes\\Archivos\\"+id+"\\"+arch);
        FileInputStream fis = new FileInputStream(file);
        response.setContentType("application/force-download");
        response.addHeader("Content-disposition", "attachment;fileName=" + arch);
        OutputStream os = response.getOutputStream();
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = fis.read(buf)) != -1) {
            os.write(buf, 0, len);
        }
        fis.close();
    }

    @GetMapping("/downloadd/{idd}/{archd}")
    public void downloadd(HttpServletResponse response,@PathVariable String idd,@PathVariable String archd) throws Exception {
        File file = new File("C:\\Imagenes\\Archivos\\"+idd+"\\"+archd);
        FileInputStream fis = new FileInputStream(file);
        response.setContentType("application/force-download");
        response.addHeader("Content-disposition", "attachment;fileName=" + archd);
        OutputStream os = response.getOutputStream();
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = fis.read(buf)) != -1) {
            os.write(buf, 0, len);
        }
        fis.close();
    }
}