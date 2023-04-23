package com.edomex.biblioteca.ServDaoImpl;



import com.edomex.biblioteca.Dao.FraseDao;
import com.edomex.biblioteca.Dao.GenLiterarioDao;
import com.edomex.biblioteca.Entity.Frase;
import com.edomex.biblioteca.Entity.GenLiterario;
import com.edomex.biblioteca.Service.FraseService;
import com.edomex.biblioteca.Service.GenLiterarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroServImpl implements GenLiterarioService {
    @Autowired
    private GenLiterarioDao genLiterarioDao;
    @Override
    public List<Object> viewGenLite() {
        return genLiterarioDao.verGenLit();
    }
    @Override
    public List<GenLiterario> genLiterario() {
        return genLiterarioDao.findAll();
    }
    @Override
    public GenLiterario guardarGenLiterario(GenLiterario genLiterario) { return genLiterarioDao.save(genLiterario); }

    @Override
    public GenLiterario genLiterarioById(Integer id) { return genLiterarioDao.findById(id).get(); }

    @Override
    public void eliminarGenLiterario(Integer id) { genLiterarioDao.deleteById(id); }
}
