package com.edomex.biblioteca.Dao;

import com.edomex.biblioteca.Entity.AppUser;
import com.edomex.biblioteca.Entity.GenLiterario;
import com.edomex.biblioteca.Entity.UserGen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserGenDao extends JpaRepository<UserGen,Integer> {

    @Query(value = "insert into UserGen(ugcveserv,ugcvegen) values(?1,?2)",nativeQuery = true)
    @Modifying(clearAutomatically = true)
    @Transactional
    void grdusergen(AppUser user, GenLiterario lite);

    @Query(value = "SELECT distinct ugcvegen FROM usergen WHERE ugcveserv= ?1 LIMIT 4",nativeQuery = true)
    public List<Integer> MasLeidos(String ugcveserv);
}
