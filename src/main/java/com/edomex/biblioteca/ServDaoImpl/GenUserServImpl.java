package com.edomex.biblioteca.ServDaoImpl;

import com.edomex.biblioteca.Dao.GenUserDao;
import com.edomex.biblioteca.Entity.GenUser;
import com.edomex.biblioteca.Service.GenUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenUserServImpl implements GenUserService {
    @Autowired
    private GenUserDao genUserDao;
    @Override
    public List<GenUser> mostrarGenUser() {
        return genUserDao.findAll();
    }
}
