package com.edomex.biblioteca.Dao;

import com.edomex.biblioteca.Entity.AppUser;
import com.edomex.biblioteca.Entity.Cstsusr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface AppUserDao extends JpaRepository<AppUser, String> {
    //@Query("select u from appuser u where u.uastsusr=1")
    //List<AppUser> mostrarActualizado();

    List<AppUser> findByUastsusr(Cstsusr usr);

    /*@Modifying
    @Query("update appuser au set au.uastsusr=?1 where au.uacveserv=?2")
    void updaCstsUser(String id, Cstsusr usr);*/
    @Query(value = "update appuser set uastsusr=?1 where uacveserv=?2",nativeQuery = true)
    @Modifying(clearAutomatically = true)
    @Transactional
    void modiByStatus(Cstsusr user,AppUser usua);

    @Query(value = "SELECT uastsusr FROM appuser WHERE uacveserv=?1",nativeQuery = true)
    public int getEstatus(String cveserv);


}
