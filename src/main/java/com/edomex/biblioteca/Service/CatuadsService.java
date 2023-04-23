package com.edomex.biblioteca.Service;

import com.edomex.biblioteca.Entity.Catuads;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatuadsService {

    public List<Catuads> mostrarCatAdmin();

    public Catuads guardarCatAdmin(Catuads catuads);

    public Catuads catAdminById(String id);

    public void eliminarCatAdmin(String id);

    public List<Object> viewGraphUads();

}
