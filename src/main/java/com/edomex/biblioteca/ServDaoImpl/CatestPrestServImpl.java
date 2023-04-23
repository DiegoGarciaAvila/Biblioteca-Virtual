package com.edomex.biblioteca.ServDaoImpl;


import com.edomex.biblioteca.Dao.CatestPrestDao;
import com.edomex.biblioteca.Entity.CatestPrest;
import com.edomex.biblioteca.Service.CatestPrestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CatestPrestServImpl implements CatestPrestService {
    @Autowired
    private CatestPrestDao catestPrestDao;

    @Override
    public List<CatestPrest> mostrarPrest() { return catestPrestDao.findAll(); }

    @Override
    public CatestPrest guardarCatPrest(CatestPrest catestPrest) { return catestPrestDao.save(catestPrest); }

    @Override
    public CatestPrest catPrestById(Integer id) { return catestPrestDao.findById(id).get(); }

    @Override
    public void eliminarCatPrest(Integer id) { catestPrestDao.deleteById(id); }
}
