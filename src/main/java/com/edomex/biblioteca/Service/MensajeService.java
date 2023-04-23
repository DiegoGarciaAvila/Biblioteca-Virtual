package com.edomex.biblioteca.Service;

import com.edomex.biblioteca.Entity.CatestPrest;
import com.edomex.biblioteca.Entity.Mensaje;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MensajeService {

    public void guardaMensaje(Mensaje mensaje);

    public List<Mensaje> getMensajes(String usuario);

    public List<Mensaje> getMensajesOrd(String usuario);
}
