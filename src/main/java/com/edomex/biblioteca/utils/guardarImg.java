package com.edomex.biblioteca.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class guardarImg {
    //modificar logo página
    public static String guardarImagen(MultipartFile multipartFile,String ruta){
        try{
            File archImg=new File( ruta+"Logo_GEM.png");
            multipartFile.transferTo(archImg);
            return "";
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            return null;
        }
    }

    //modificar logo jasper
    public static String grdlogoJP(MultipartFile imgfile,String ruta){
        try {
            File achImg=new File(ruta+"Logo_GEM.png");
            imgfile.transferTo(achImg);
            return "";
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            return null;
        }
    }

    //modificar logo estado jasper
    public static String grdestJP(MultipartFile imgfile,String ruta){
        try {
            File achImg=new File(ruta+"logoestado.png");
            imgfile.transferTo(achImg);
            return "";
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            return null;
        }
    }

    //modificar foot jasper
    public static String grdfootJP(MultipartFile imgfile,String ruta){
        try {
            File achImg=new File(ruta+"foot.png");
            imgfile.transferTo(achImg);
            return "";
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            return null;
        }
    }

    //modificar index imagen inicio
    public static String grdIndIni(MultipartFile imgfile, String ruta){
        try{
            File arcimg=new File(ruta+"inicio.jpg");
            imgfile.transferTo(arcimg);
            return "";
        }catch (Exception e){
            return "Error: "+e.getMessage();
        }
    }


    public static String guardaINE(MultipartFile multipartFile,String ruta,String nombre){
        try{
            File archImg=new File( ruta+nombre);
            multipartFile.transferTo(archImg);
            return "";
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            return null;
        }
    }
    public static String guardauacomdom(MultipartFile multipartFile,String ruta,String nombre){
        try{
            File archImg=new File( ruta+nombre);
            multipartFile.transferTo(archImg);
            return "";
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            return null;
        }
    }
}
