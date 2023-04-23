package com.edomex.biblioteca.Service;

import com.edomex.biblioteca.Entity.GenUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GenUserService {
    public List<GenUser> mostrarGenUser();
}
