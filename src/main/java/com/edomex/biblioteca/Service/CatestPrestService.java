package com.edomex.biblioteca.Service;

import com.edomex.biblioteca.Entity.CatestPrest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatestPrestService {

    public List<CatestPrest> mostrarPrest();
    public CatestPrest guardarCatPrest(CatestPrest catestPrest);
    public CatestPrest catPrestById(Integer id);
    public void eliminarCatPrest(Integer id);
}
