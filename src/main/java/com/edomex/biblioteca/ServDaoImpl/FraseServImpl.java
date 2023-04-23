package com.edomex.biblioteca.ServDaoImpl;



import com.edomex.biblioteca.Dao.FraseDao;
import com.edomex.biblioteca.Entity.Frase;
import com.edomex.biblioteca.Service.FraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FraseServImpl implements FraseService {
    @Autowired
    private FraseDao fraseDao;

    @Override
    public Integer findbyRando() { return fraseDao.findbyRand(); }


    @Override
    public List<Frase> mostrarFrase() { return fraseDao.findAll(); }

    @Override
    public Frase guardarFrase(Frase frase) { return fraseDao.save(frase); }

    @Override
    public Frase fraseById(Integer id) { return fraseDao.findById(id).get(); }

    @Override
    public void eliminarFrase(Integer id) { fraseDao.deleteById(id); }
}
