package com.edomex.biblioteca.Dao;

import com.edomex.biblioteca.Entity.Catstsevent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatStsEveDao extends JpaRepository<Catstsevent, Integer> {



}
