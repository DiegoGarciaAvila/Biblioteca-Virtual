package com.edomex.biblioteca.Dao;

import com.edomex.biblioteca.Entity.Catuads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CatuadsDao extends JpaRepository<Catuads, String> {
    @Query(value ="select count(dp.detcvelibro),ca.cdesads from catuads ca, appuser ap, prestamo p, detprestamo dp, libro l where dp.detcvepres=p.pcvepres and dp.detcvelibro=l.lcvelibro and ap.uaads=ca.ccveads and p.pcvesol=ap.uacveserv group by ca.cdesads",nativeQuery = true)
    List<Object> graphUads();
}
