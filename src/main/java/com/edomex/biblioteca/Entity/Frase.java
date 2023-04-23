package com.edomex.biblioteca.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Hector
 */
@Entity
@Data
@Table(name = "frase")
public class Frase implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fcvefrase;

    private String fdesfrase;

    private String fautor;

    @ManyToOne
    @JoinColumn(name="fcveserv")
    private AppUser appUser;
}
