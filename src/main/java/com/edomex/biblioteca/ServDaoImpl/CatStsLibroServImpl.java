package com.edomex.biblioteca.ServDaoImpl;

import com.edomex.biblioteca.Dao.CatStsLibroDao;
import com.edomex.biblioteca.Entity.Catstslibro;
import com.edomex.biblioteca.Service.CatStsLibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatStsLibroServImpl implements CatStsLibroService {
    @Autowired
    private CatStsLibroDao catStsLibroDao;


    @Override
    public List<Catstslibro> mmuestraEstatusLibro() {
        return catStsLibroDao.findAll();
    }
}
