package com.edomex.biblioteca.Service;

import com.edomex.biblioteca.Entity.Catstslibro;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatStsLibroService {

    public List<Catstslibro> mmuestraEstatusLibro();

}
