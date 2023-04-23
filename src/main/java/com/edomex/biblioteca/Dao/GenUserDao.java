package com.edomex.biblioteca.Dao;

import com.edomex.biblioteca.Entity.GenUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenUserDao extends JpaRepository<GenUser, Integer> {
}
