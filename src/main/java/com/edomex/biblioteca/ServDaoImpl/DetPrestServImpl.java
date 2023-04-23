package com.edomex.biblioteca.ServDaoImpl;


import com.edomex.biblioteca.Dao.CatestPrestDao;
import com.edomex.biblioteca.Dao.DetprestamoDao;
import com.edomex.biblioteca.Entity.CatestPrest;
import com.edomex.biblioteca.Service.CatestPrestService;
import com.edomex.biblioteca.Service.DetPrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetPrestServImpl implements DetPrestamoService {

@Autowired
private DetprestamoDao detprestamoDao;

    @Override
    public List<Integer> MasLeidos() {
        return detprestamoDao.MasLeidos();
    }

    @Override
    public void guardardtPres(Integer a, Integer b) {
        detprestamoDao.guardarDetPres(a,b);
    }

    @Override
    public List<Integer> detLibros(int cvepres) {
        return detprestamoDao.detLibros(cvepres);
    }

    @Override
    public List<Integer> listarLibrosIn(int id) {
        return detprestamoDao.listarLibrosIn(id);
    }


}
