package com.edomex.biblioteca.Entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lcvelibro;

    private String ltitlibro;

    private String lautor;

    private String leditorial;

    @ManyToOne
    @JoinColumn(name = "lgeneroli")
    private GenLiterario genLiterario;

    private String lresena;

    private Integer lstock;

    @ManyToOne
    @JoinColumn(name = "lstslibro")
    private Catstslibro catstslibro;

    private String lobserv;

    private LocalDateTime lfechalta;

    private Integer lcveinventario;

}