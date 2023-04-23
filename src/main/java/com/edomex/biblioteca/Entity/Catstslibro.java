package com.edomex.biblioteca.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "catstslibro")
public class Catstslibro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ccvestslibr;

    private String cdesstslibr;
}
