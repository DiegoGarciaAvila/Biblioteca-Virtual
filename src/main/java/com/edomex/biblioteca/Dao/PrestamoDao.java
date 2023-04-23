package com.edomex.biblioteca.Dao;

import com.edomex.biblioteca.Entity.AppUser;
import com.edomex.biblioteca.Entity.CatestPrest;
import com.edomex.biblioteca.Entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PrestamoDao extends JpaRepository<Prestamo,Integer> {
    List<Prestamo> findByServidor(AppUser servidor);

    @Query(value = "SELECT * FROM Prestamo p where p.pcvesol=?1  ORDER BY pcvepres DESC ",nativeQuery = true)
    List<Prestamo> prestamosOrdenados(String servidor);

    List<Prestamo> findByCatestPrest(CatestPrest catestPrest);

    //Estadísticas del sitio
    @Query(value = "select min(pfecpres) from prestamo",nativeQuery = true)
    public Date fisrtDate();
    @Query(value = "select max(pfecpres) from prestamo",nativeQuery = true)
    public Date lastDate();
    @Query(value ="select date_trunc('day',p.pfecpres) pd,count(dp.detcvelibro) from prestamo p, detprestamo dp where p.pcvepres=dp.detcvepres group by pd order by pd asc" ,nativeQuery = true)
    List<Object> graphDay();
    @Query(value ="select date_trunc('month',p.pfecpres) pd,count(dp.detcvelibro) from prestamo p, detprestamo dp where p.pcvepres=dp.detcvepres group by pd order by pd asc",nativeQuery = true)
    List<Object> graphMonth();
    @Query(value = "select date_trunc('year',p.pfecpres) pd,count(dp.detcvelibro) from prestamo p, detprestamo dp where p.pcvepres=dp.detcvepres group by pd order by pd asc",nativeQuery = true)
    List<Object> graphYear();

    //para llevar detprestamo
    @Query("select max(p.pcvepres) from Prestamo p where p.servidor=?1")
    Integer findLastReg(AppUser user);
    //por mes
    @Query(value = "SELECT COUNT(*) FROM prestamo WHERE pcvesol=?1 AND EXTRACT(MONTH FROM pfecpres)=?2", nativeQuery = true)
    Integer prestamoxmes(String cveserv,Integer mes);
    //por estatus de prestamo
    @Query(value = "select p.pstspres from prestamo p where p.pcvesol=?1 and p.pcvepres=?2",nativeQuery = true)
    Integer StatusPrestamo(String cveserv,Integer cvepres);

    @Query("select p.pfecpres from Prestamo p where p.pcvepres=?1")
    Date fecpres(Integer id);
}
