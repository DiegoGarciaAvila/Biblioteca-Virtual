package com.edomex.biblioteca.Service;

import com.edomex.biblioteca.Entity.Prestamo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DetPrestamoService {
    public List<Integer> MasLeidos();
    public void guardardtPres(Integer a,Integer b);
    public List<Integer> detLibros(int cvepres);

    public List<Integer> listarLibrosIn(int id);
}
