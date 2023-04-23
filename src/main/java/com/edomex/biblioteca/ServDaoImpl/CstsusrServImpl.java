package com.edomex.biblioteca.ServDaoImpl;

import com.edomex.biblioteca.Dao.CstsusrDao;
import com.edomex.biblioteca.Entity.Cstsusr;
import com.edomex.biblioteca.Service.CstsusrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CstsusrServImpl implements CstsusrService {
    @Autowired
    private CstsusrDao cstsusrDao;
    @Override
    public List<Cstsusr> cstslista() { return cstsusrDao.findAll(); }
}
