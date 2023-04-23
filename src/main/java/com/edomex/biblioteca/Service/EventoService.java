package com.edomex.biblioteca.Service;

import com.edomex.biblioteca.Entity.Catstsevent;
import com.edomex.biblioteca.Entity.Evento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventoService {
    public Integer findbyEvento();

    public List<Evento> mostrarEventos();
    public Evento agregarEvento(Evento evento);
    public Evento eventoById(Integer id);
    public void eliminarEvento(Integer id);
    public List<Evento> eventosPorEstattus(Integer catstsevent);
}
