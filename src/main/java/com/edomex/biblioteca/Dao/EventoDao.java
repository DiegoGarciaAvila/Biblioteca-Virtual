package com.edomex.biblioteca.Dao;

import com.edomex.biblioteca.Entity.Catstsevent;
import com.edomex.biblioteca.Entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoDao extends JpaRepository<Evento, Integer> {
    @Query(value = "select max(ecveevent) from evento",nativeQuery = true)
    public Integer findbyEvent();


    public List<Evento> findByEstsevent(Catstsevent estsevent);

}
