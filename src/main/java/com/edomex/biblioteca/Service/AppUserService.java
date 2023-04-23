package com.edomex.biblioteca.Service;

import com.edomex.biblioteca.Entity.AppUser;
import com.edomex.biblioteca.Entity.Cstsusr;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AppUserService {
    public AppUser userById(String id);
    public AppUser guardarAU(AppUser appUser);
    public void modStatus(Cstsusr urs, AppUser usua);
    public int getEstatus(String cveserv);
    public void updappuser(String datauser, String user) throws IOException;

    public void saveUsuario(AppUser appUser);


}
