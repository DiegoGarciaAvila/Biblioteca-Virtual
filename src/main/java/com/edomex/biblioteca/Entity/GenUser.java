package com.edomex.biblioteca.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "catgenero")
public class GenUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cvegenero;

    private String cdesgenero;
}
