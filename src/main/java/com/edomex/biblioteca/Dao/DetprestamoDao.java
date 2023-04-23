package com.edomex.biblioteca.Dao;

import com.edomex.biblioteca.Entity.Detprestamo;
import com.edomex.biblioteca.Entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface    DetprestamoDao extends JpaRepository<Detprestamo,Integer> {
    List<Detprestamo> findByDetcvepres(Prestamo id);

    @Query(value = "SELECT DETCVELIBRO FROM DETPRESTAMO GROUP BY 1 HAVING COUNT(*)>1 LIMIT 5",nativeQuery = true)
    public List<Integer> MasLeidos();

    @Query(value = "insert into detprestamo(detcvepres,detcvelibro) values(?1,?2)",nativeQuery = true)
    @Modifying(clearAutomatically = true)
    @Transactional
    void guardarDetPres(Integer a, Integer b);

    @Query(value = "SELECT detcvelibro FROM detprestamo WHERE detcvepres= ?1",nativeQuery = true)
    public List<Integer> detLibros(int cvepres);

    @Query(value = "SELECT detcvelibro FROM detprestamo WHERE detcvepres=?1",nativeQuery = true)
    public List<Integer> listarLibrosIn(int id);
}
