package com.edomex.biblioteca.Service;

import com.edomex.biblioteca.Entity.GenLiterario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GenLiterarioService {
    public List<Object> viewGenLite();

    public List<GenLiterario> genLiterario();

    public GenLiterario guardarGenLiterario(GenLiterario genLiterario);

    public GenLiterario genLiterarioById(Integer id);

    public void eliminarGenLiterario(Integer id);
}
