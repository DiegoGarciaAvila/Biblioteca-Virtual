package com.edomex.biblioteca.ServDaoImpl;


import com.edomex.biblioteca.Dao.CatStsEveDao;
import com.edomex.biblioteca.Dao.CatestPrestDao;
import com.edomex.biblioteca.Entity.CatestPrest;
import com.edomex.biblioteca.Entity.Catstsevent;
import com.edomex.biblioteca.Service.CatStsEvService;
import com.edomex.biblioteca.Service.CatestPrestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatStsEvServImpl implements CatStsEvService {
    @Autowired
    private CatStsEveDao catStsEveDao;

    @Override
    public List<Catstsevent> estatusEv() {
        return catStsEveDao.findAll();
    }
}
