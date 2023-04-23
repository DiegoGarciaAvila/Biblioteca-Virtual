package com.edomex.biblioteca.Dao;

import com.edomex.biblioteca.Entity.GenLiterario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenLiterarioDao extends JpaRepository<GenLiterario, Integer> {
    @Query(value = "select count(dp.detcvelibro), gl.gdesgen from detprestamo dp, libro l, prestamo p,genliterario gl where dp.detcvepres=p.pcvepres and dp.detcvelibro=l.lcvelibro and l.lgeneroli=gl.gcvegen group by gl.gdesgen order by gl.gdesgen asc", nativeQuery = true)
    List<Object> verGenLit();
}
