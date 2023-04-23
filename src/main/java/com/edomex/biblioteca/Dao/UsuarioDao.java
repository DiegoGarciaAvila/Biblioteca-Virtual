package com.edomex.biblioteca.Dao;

import com.edomex.biblioteca.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioDao extends JpaRepository<AppUser, String>{

    AppUser findByUacveserv(String uacveserv);
    //query methods

}
