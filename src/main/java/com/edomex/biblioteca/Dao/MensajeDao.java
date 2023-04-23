package com.edomex.biblioteca.Dao;

import com.edomex.biblioteca.Entity.Mensaje;
import com.edomex.biblioteca.Entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MensajeDao extends JpaRepository<Mensaje, Integer> {

    public List<Mensaje> findByMdestino(String cveserv);

    @Query(value = "SELECT mcvemensaje, morigen, mdestino, masunto, mcorreo, mmensaje FROM public.mensaje order by mcvemensaje desc ",nativeQuery = true)
    List<Mensaje> mensajeOrd(String cveserv);

}
