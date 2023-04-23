package com.edomex.biblioteca.ServDaoImpl;

import com.edomex.biblioteca.Dao.AppUserDao;
import com.edomex.biblioteca.Entity.AppUser;
import com.edomex.biblioteca.Entity.Cstsusr;
import com.edomex.biblioteca.Entity.GenUser;
import com.edomex.biblioteca.Service.AppUserService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class AppUserServImpl implements AppUserService {
    @Autowired
    private AppUserDao appUserDao;

    @Override
    public AppUser userById(String od) {
        return appUserDao.findById(od).get();
    }

    @Override
    public AppUser guardarAU(AppUser appUser) { return appUserDao.save(appUser); }
    @Override
    public void modStatus(Cstsusr urs, AppUser usua) {
        appUserDao.modiByStatus(urs,usua);
    }

    @Override
    public int getEstatus(String cveserv) {
        return appUserDao.getEstatus(cveserv);
    }

    @Override
    public void updappuser(String datauser, String user) throws IOException {
        AppUser usr=userById(user);
        Cstsusr stsrs=new Cstsusr();
        stsrs.setCcvestsusr(1);
        usr.setUastsusr(stsrs);

        JSONArray arr=new JSONArray(datauser);
        GenUser gener= new GenUser();
        gener.setCvegenero(Integer.parseInt(arr.getJSONObject(0).getString("genero")));
        usr.setUagenero(gener);
       // usr.setUaedad(Integer.parseInt(arr.getJSONObject(0).getString("edad")));
        usr.setUacorreo(arr.getJSONObject(0).getString("correoE"));
        usr.setUadompart(arr.getJSONObject(0).getString("domicilioP"));
        usr.setUatelmov(arr.getJSONObject(0).getString("telefonoP"));
        usr.setUadomtrab(arr.getJSONObject(1).getString("domicilioU"));
        usr.setUateltrab(arr.getJSONObject(1).getString("telefonoU"));
        CreaDirectorio(user);
        appUserDao.save(usr);
    }

    public boolean CreaDirectorio(String cve) {
        boolean va=true  ;
        File directorios = new File("C:\\Imagenes\\Archivos\\" + cve);
        if (!directorios.exists()) {
            va=false;
            directorios.mkdir();
            System.out.println("Directorio creado");
        } else {

            System.out.println("El directorio ya existe");
        }
        System.out.println(va);
        return va;
    }
    public void GuardaArchivos( MultipartFile ident, String serv) throws IOException {
        byte[] identi = ident.getBytes();
        InputStream inid = ident.getInputStream();
        String FILESYSTEM = "C:\\Imagenes\\Archivos\\"+serv+"\\";
        Path pathide = Paths.get(FILESYSTEM + serv+"_"+ ident.getOriginalFilename());
        Files.write(pathide, identi);
    }

    public void saveUsuario(AppUser appUser){
        appUserDao.save(appUser);
    }
}
