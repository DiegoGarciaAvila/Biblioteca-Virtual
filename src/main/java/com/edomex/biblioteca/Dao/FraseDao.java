package com.edomex.biblioteca.Dao;

import com.edomex.biblioteca.Entity.Frase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface FraseDao extends JpaRepository<Frase, Integer> {

    @Query(value="select floor(random()*(max(fcvefrase)-min(fcvefrase)+1)+min(fcvefrase)) from frase limit 1",nativeQuery = true)
    public Integer findbyRand();
}
