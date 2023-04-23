package com.edomex.biblioteca.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Hector
 */
@Entity
@Data
@Table(name="genliterario")
public class GenLiterario implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gcvegen")
    private Integer GCVEGEN;

    @Column(name = "gdesgen")
    private String GDESGEN;
}
