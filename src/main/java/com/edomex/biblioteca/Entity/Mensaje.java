package com.edomex.biblioteca.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Hector
 */
@Entity
@Data
@Table(name = "mensaje")
public class Mensaje implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mcvemensaje;

    private String morigen;

    private String mdestino;

    private String masunto;

    private String mcorreo;

    private String mmensaje;
}
