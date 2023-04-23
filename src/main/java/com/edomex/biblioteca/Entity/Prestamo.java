package com.edomex.biblioteca.Entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "prestamo")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pcvepres;
    @ManyToOne
    @JoinColumn(name = "pcvesol")
    private AppUser servidor;
    private LocalDate pfecpres;
    private LocalDate pfecadev;
    private LocalDate pfecrdev;
    private String pentrega;
    private String precibe;
    private String pdevuelve;
    @ManyToOne
    @JoinColumn(name = "pstspres")
    private CatestPrest catestPrest;
    private Integer pnlibr;
    private String precoge;
}
