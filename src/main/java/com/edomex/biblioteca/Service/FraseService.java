package com.edomex.biblioteca.Service;

import com.edomex.biblioteca.Entity.Frase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FraseService {
    public Integer findbyRando();

    public List<Frase> mostrarFrase();

    public Frase guardarFrase(Frase frase);

    public Frase fraseById(Integer id);

    public void eliminarFrase(Integer id);
}
