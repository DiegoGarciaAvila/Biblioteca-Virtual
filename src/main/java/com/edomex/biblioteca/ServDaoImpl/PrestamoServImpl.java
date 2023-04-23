package com.edomex.biblioteca.ServDaoImpl;

import com.edomex.biblioteca.Dao.PrestamoDao;
import com.edomex.biblioteca.Entity.AppUser;
import com.edomex.biblioteca.Entity.CatestPrest;
import com.edomex.biblioteca.Entity.Prestamo;
import com.edomex.biblioteca.Service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PrestamoServImpl implements PrestamoService {
    @Autowired
    private PrestamoDao prestamoDao;
    @Override
    public Prestamo prestamoById(Integer id) {
        return prestamoDao.findById(id).get(); }

    @Override
    public List<Prestamo> prestamosPorEstatus(Integer estatus) {
        CatestPrest catestPrest=new CatestPrest();
        catestPrest.setCstspre(estatus);
        return prestamoDao.findByCatestPrest(catestPrest);
    }

    @Override
    public Prestamo encontrarPorID(Integer id) {
        return prestamoDao.findById(id).get();
    }

    @Override
    public Date primFech() {
        return prestamoDao.fisrtDate();
    }
    @Override
    public Date ultFech() {
        return prestamoDao.lastDate();
    }
    @Override
    public List<Object> graphDia() { return prestamoDao.graphDay(); }
    @Override
    public List<Object> graphMes() { return prestamoDao.graphMonth(); }
    @Override
    public List<Object> graphAnio() { return prestamoDao.graphYear(); }

    /* GUARDADO DE Prestamo*/
    @Override
    public Prestamo guardarPrestamo(Prestamo prestamo) { return prestamoDao.save(prestamo); }

    @Override
    public Integer obtenerUltRegi(AppUser user) {
        return prestamoDao.findLastReg(user);
    }

    @Override
    public Integer prestamoxmes(String cveserv, Integer mes) { return prestamoDao.prestamoxmes(cveserv,mes); }

    @Override
    public Integer StatusPrestamo(String cveser, Integer cvepres) { return prestamoDao.StatusPrestamo(cveser,cvepres); }
    //fecha del prestamo
    @Override
    public Date fecpres(Integer id) { return prestamoDao.fecpres(id); }

    @Override
    public List<Prestamo> findServidor(AppUser user) {
        return prestamoDao.findByServidor(user);
    }

}
