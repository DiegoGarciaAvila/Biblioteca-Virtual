package com.edomex.biblioteca.Dao;

import com.edomex.biblioteca.Entity.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LibroDao extends JpaRepository<Libro, Integer> {

    @Query(value = "SELECT * FROM libro WHERE lgeneroli IN(?1) LIMIT 5",nativeQuery = true)
    List<Libro> personalizadoxGenero(List<Integer> lista);

    public List<Libro>findByLcvelibroIn(List<Integer> lista);
    @Query(value = "select count(dp.detcvelibro), l.ltitlibro as libro from detprestamo dp, libro l, prestamo p where dp.detcvepres=p.pcvepres and dp.detcvelibro=l.lcvelibro group by l.ltitlibro order by l.ltitlibro asc",nativeQuery = true)
    List<Object> verLibrGraph();

    //reconocer el libro y modificar estatus
    @Query(value = "select * from libro where lcvelibro=?1",nativeQuery = true)
    Integer recogLbro(Integer id);
    @Query(value = "update libro set lstslibro=?1 where lcvelibro=?2",nativeQuery = true)
    @Modifying(clearAutomatically = true)
    @Transactional
    void updateStsLibro(Integer num,Integer lib);

    //reporte
    @Query(value = "select * from libro l, detprestamo dp where l.lcvelibro=dp.detcvelibro and dp.detcvepres=?1",nativeQuery = true)
    List<Libro> repPartLibros(Integer id);

    @Query(value = "UPDATE libro SET lstslibro=?1 WHERE lcvelibro IN (?2)",nativeQuery = true)
    @Modifying(clearAutomatically = true)
    @Transactional
    void cambiaEstatusLibro(int estatus,List<Integer> lista);

    public List<Libro> findByLcveinventario(int cveinventario);
    //SELECT * FROM libro m WHERE m.ltitlibro LIKE %?1% OR m.lautor LIKE %?2%
    //   @Query(value = "SELECT * FROM libro WHERE UPPER(ltitlibro)  LIKE  ('%'||?1||'%') OR UPPER(lautor)  LIKE  UPPER('%'||?2||'%')",nativeQuery = true)
    // public List<Libro> BusquedaPersonalizada (String ltitlibro,String lautor);

    @Query(value = "select * from libro a inner join genliterario g on a.lgeneroli = g.gcvegen where  UPPER(a.ltitlibro)  LIKE  UPPER('%'||?1||'%') OR UPPER(a.lautor)  LIKE  UPPER('%'||?2||'%') OR UPPER(g.gdesgen) like UPPER('%'||?3||'%')",nativeQuery = true)
    public List<Libro> BusquedaPersonalizada (String ltitlibro,String lautor,String genero);


//    SELECT * FROM libro m WHERE m.ltitlibro LIKE %?1%
    @Query(value = "SELECT * FROM public.libro WHERE UPPER(ltitlibro) LIKE UPPER('%'||?1||'%')",nativeQuery = true)
    public List<Libro> BusquedaPersonalizadaXTitulo (String ltitlibro);
//SELECT * FROM libro m WHERE m.lautor LIKE %?1%
    @Query(value = "SELECT * FROM public.libro WHERE UPPER(lautor) LIKE UPPER('%'||?1||'%');",nativeQuery = true)
    public List<Libro> BusquedaPersonalizadaXAutor (String lautor);

    @Query(value = "select * from libro a inner join genliterario g on a.lgeneroli = g.gcvegen where  UPPER(g.gdesgen)  LIKE UPPER('%'||?1||'%')",nativeQuery = true)
    public List<Libro> BusquedaPersonalizadaXGenero (String lgenero);

    @Query(value = "select * from libro a inner join genliterario g on a.lgeneroli = g.gcvegen where  UPPER(ltitlibro) LIKE UPPER('%'||?1||'%') OR UPPER(g.gdesgen)  LIKE UPPER('%'||?2||'%')",nativeQuery = true)
    public List<Libro> BusquedaPersonalizadaXTituloGenero (String ltitulo,String lgenero);

    @Query(value = "SELECT * FROM libro WHERE UPPER(ltitlibro)  LIKE  UPPER('%'||?1||'%') OR UPPER(lautor)  LIKE  UPPER('%'||?2||'%')",nativeQuery = true)
    public List<Libro> BusquedaPersonalizadaXTituloAutor (String ltitulo,String lautor);

    @Query(value = "select * from libro a inner join genliterario g on a.lgeneroli = g.gcvegen where   UPPER(g.gdesgen) like UPPER('%'||?1||'%')  or UPPER(a.lautor)  LIKE  UPPER('%'||?2||'%');",nativeQuery = true)
    public List<Libro> BusquedaPersonalizadaXGeneroAutor (String lgenero,String lautor);



    @Query(value = "select * from libro",nativeQuery = true)
    public List<Libro> listatitulos ();













}


















