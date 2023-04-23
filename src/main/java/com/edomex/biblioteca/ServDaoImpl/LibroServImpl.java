package com.edomex.biblioteca.ServDaoImpl;

import com.edomex.biblioteca.Dao.LibroDao;
import com.edomex.biblioteca.Entity.Libro;
import com.edomex.biblioteca.Service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LibroServImpl implements LibroService {

    @Autowired
    private LibroDao libroDao;

    @Override
    public List<Libro> recomendaciones(List<Integer> lista) {
        return libroDao.personalizadoxGenero(lista);
    }

    @Override
    public List<Libro> masLeidos(List<Integer> list) {
        return libroDao.findByLcvelibroIn(list);
    }

    @Override
    public List<Libro> ltsLibros() {
        return libroDao.findAll();
    }

    @Override
    public void guardaLibro(Libro libro) {
        LocalDateTime fecha = LocalDateTime.now();
        libro.setLfechalta(fecha);

        libroDao.save(libro);
    }

    @Override
    public void eliminaLibro(Integer id) {
        libroDao.deleteById(id);
    }
    @Override
    public List<Object> viewGraphLibro() {
        return libroDao.verLibrGraph();
    }
    @Override
    public List<Libro> mostLibro() { return libroDao.findAll(); }
    @Override
    public Libro getLibro(Integer id) { return libroDao.findById(id).get(); }

    //reconocer el libro y modificar estatus
    @Override
    public Integer reconoLibr(Integer id) {
        return libroDao.recogLbro(id);
    }
    @Override
    public void modStsLibr(Integer num, Integer libr) {
        libroDao.updateStsLibro(num,libr);
    }

    @Override
    public List<Libro> reportePartLibros(Integer id) {
        return libroDao.repPartLibros(id);
    }

    @Override
    public void actualizaSts(int estatus, List<Integer> lista) {
         libroDao.cambiaEstatusLibro(estatus,lista);
    }

    @Override
    public Page<Libro> listarLibros(Pageable pageable) {
        return libroDao.findAll(pageable);
    }

    @Override
    public List<Libro> getBusqueda(int cveInventario) {
        return libroDao.findByLcveinventario(cveInventario);
    }

    @Override
    public List<Libro> busqueda(String titulo, String autor, String genero) {
        return libroDao.BusquedaPersonalizada(titulo,autor,genero);
    }

    @Override
    public List<Libro> busquedaXTitulo(String titulo) {
        return libroDao.BusquedaPersonalizadaXTitulo(titulo);
    }

    @Override
    public List<Libro> busquedaXAutor(String autor) {
        return libroDao.BusquedaPersonalizadaXAutor(autor);
    }

    @Override
    public List<Libro> busquedaXGenero(String genero) {
        return libroDao.BusquedaPersonalizadaXGenero(genero);
    }



    @Override
    public List<Libro> busquedaXTituloGenero(String titulo, String genero) {
        return libroDao.BusquedaPersonalizadaXTituloGenero(titulo,genero);
    }

    @Override
    public List<Libro> busquedaXTituloAutor(String titulo, String autor) {
        return libroDao.BusquedaPersonalizadaXTituloAutor(titulo,autor);
    }

    @Override
    public List<Libro> busquedaXGeneroAutor(String genero, String autor) {
        return libroDao.BusquedaPersonalizadaXGeneroAutor(genero,autor);
    }

    @Override
    public List<Libro> listaTitulos() {
        return libroDao.listatitulos();
    }



}
