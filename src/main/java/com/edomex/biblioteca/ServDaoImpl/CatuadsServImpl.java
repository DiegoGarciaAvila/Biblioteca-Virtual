package com.edomex.biblioteca.ServDaoImpl;



import com.edomex.biblioteca.Dao.CatuadsDao;
import com.edomex.biblioteca.Entity.Catuads;
import com.edomex.biblioteca.Service.CatuadsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatuadsServImpl implements CatuadsService {
    @Autowired
    private CatuadsDao catuadsDao;

    @Override
    public List<Catuads> mostrarCatAdmin() { return catuadsDao.findAll(); }

    @Override
    public Catuads guardarCatAdmin(Catuads catuads) { return catuadsDao.save(catuads); }

    @Override
    public Catuads catAdminById(String id) { return catuadsDao.findById(id).get(); }

    @Override
    public void eliminarCatAdmin(String id) { catuadsDao.deleteById(id); }
    @Override
    public List<Object> viewGraphUads() {
        return catuadsDao.graphUads();
    }
}
