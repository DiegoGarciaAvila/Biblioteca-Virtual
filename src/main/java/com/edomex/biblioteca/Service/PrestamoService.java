package com.edomex.biblioteca.Service;

import com.edomex.biblioteca.Entity.AppUser;
import com.edomex.biblioteca.Entity.Prestamo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public interface PrestamoService {
    public Prestamo prestamoById(Integer id);
    public List<Prestamo> prestamosPorEstatus(Integer estatus);
    public Prestamo encontrarPorID(Integer id);

    public Date primFech();
    public Date ultFech();
    public List<Object> graphDia();
    public List<Object> graphMes();
    public List<Object> graphAnio();

    /* g u a r d a r  e l  p r e s t a m o*/
    public Prestamo guardarPrestamo(Prestamo prestamo);
    public Integer obtenerUltRegi(AppUser user);

    public Integer prestamoxmes(String cveserv,Integer mes);

    public Integer StatusPrestamo(String cveser,Integer cvepres);
    public Date fecpres(Integer id);

    public List<Prestamo> findServidor(AppUser user);
}
