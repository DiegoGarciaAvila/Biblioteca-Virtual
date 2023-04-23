package com.edomex.biblioteca.Service;

import com.edomex.biblioteca.Entity.Catstsevent;
import com.edomex.biblioteca.Entity.Frase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatStsEvService {

    public List<Catstsevent> estatusEv();
}
