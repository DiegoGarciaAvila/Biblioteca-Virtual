package com.edomex.biblioteca.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table (name = "cstsusr")
public class Cstsusr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ccvestsusr;

    private String cdesstsusr;
}
