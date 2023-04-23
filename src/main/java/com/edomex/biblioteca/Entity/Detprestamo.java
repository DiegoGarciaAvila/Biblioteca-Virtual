package com.edomex.biblioteca.Entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "detprestamo")
public class Detprestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer detprcve;

    @ManyToOne
    @JoinColumn(name = "detcvepres")
    private Prestamo detcvepres;

    @ManyToOne
    @JoinColumn(name = "detcvelibro")
    private Libro libr;
}
