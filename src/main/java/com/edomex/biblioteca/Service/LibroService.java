package com.edomex.biblioteca.Service;

import com.edomex.biblioteca.Entity.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LibroService {

    public List<Libro> recomendaciones(List<Integer> lista);

    public List<Libro> masLeidos(List<Integer> list);

    public List<Libro> ltsLibros();

    public void guardaLibro(Libro libro);

    public void eliminaLibro(Integer id);

    public List<Object> viewGraphLibro();
    public List<Libro> mostLibro();
    public Libro getLibro(Integer id);

    public Integer reconoLibr(Integer id);

    public void modStsLibr(Integer num,Integer libr);
    public List<Libro> reportePartLibros(Integer id);
    public void actualizaSts(int estatis, List<Integer> lista);
    Page<Libro> listarLibros(Pageable pageable);

    public List<Libro> getBusqueda(int cveInventario);

    public List<Libro> busqueda(String titulo,String autor,String genero);

    public List<Libro> busquedaXTitulo(String titulo);

    public List<Libro> busquedaXAutor(String autor);

    public List<Libro> busquedaXGenero(String genero);

    public List<Libro> busquedaXTituloGenero(String titulo,String genero);

    public List<Libro> busquedaXTituloAutor(String titulo,String autor);

    public List<Libro> busquedaXGeneroAutor(String genero,String autor);


    public List<Libro> listaTitulos();



}
