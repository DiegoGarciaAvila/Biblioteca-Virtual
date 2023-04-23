package com.edomex.biblioteca.ServDaoImpl;

import com.edomex.biblioteca.Dao.EventoDao;
import com.edomex.biblioteca.Entity.Catstsevent;
import com.edomex.biblioteca.Entity.Evento;
import com.edomex.biblioteca.Service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoServImpl implements EventoService {
    @Autowired
    private EventoDao eventoDao;

    @Override
    public Integer findbyEvento() { return eventoDao.findbyEvent(); }

    @Override
    public List<Evento> mostrarEventos() { return eventoDao.findAll(); }

    @Override
    public Evento agregarEvento(Evento evento) { return eventoDao.save(evento); }

    @Override
    public Evento eventoById(Integer id) { return eventoDao.findById(id).get(); }

    @Override
    public void eliminarEvento(Integer id) { eventoDao.deleteById(id); }

    @Override
    public List<Evento> eventosPorEstattus(Integer sts) {
        Catstsevent catstsevent=new Catstsevent();
        catstsevent.setCvestsev(sts);
        return eventoDao.findByEstsevent(catstsevent);
    }
}
