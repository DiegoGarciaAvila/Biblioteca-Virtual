package com.edomex.biblioteca.utils;

import com.edomex.biblioteca.Dao.FraseDao;
import com.edomex.biblioteca.Entity.Frase;
import com.edomex.biblioteca.ServDaoImpl.FraseServImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Random;

public class EncriptarPassword {
    public static void main(String[] args) {
        
        var password = "1234";
        System.out.println("password: " + password);
        System.out.println("password encriptado:" + encriptarPassword(password));
    }
    
    public static String encriptarPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
