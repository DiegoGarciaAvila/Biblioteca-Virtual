package com.edomex.biblioteca.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Hector
 */
@Entity
@Data
@Table(name = "catestprest")
public class CatestPrest implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cstspre;

    private String cdespre;
}
