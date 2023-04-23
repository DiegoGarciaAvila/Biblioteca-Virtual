package com.edomex.biblioteca.ServDaoImpl;


import com.edomex.biblioteca.Dao.CatestPrestDao;
import com.edomex.biblioteca.Dao.MensajeDao;
import com.edomex.biblioteca.Entity.CatestPrest;
import com.edomex.biblioteca.Entity.Mensaje;
import com.edomex.biblioteca.Service.CatestPrestService;
import com.edomex.biblioteca.Service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensajeServImpl implements MensajeService {
    @Autowired
    private MensajeDao mensajeDao;


    @Override
    public void guardaMensaje(Mensaje mensaje) {
        mensajeDao.save(mensaje);
    }

    @Override
    public List<Mensaje> getMensajes(String usuario) {
        return mensajeDao.findByMdestino(usuario);
    }

    @Override
    public List<Mensaje> getMensajesOrd(String usuario) {
        return mensajeDao.mensajeOrd(usuario);

    }
}
