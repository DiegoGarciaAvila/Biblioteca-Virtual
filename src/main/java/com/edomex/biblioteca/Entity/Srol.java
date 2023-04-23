package com.edomex.biblioteca.Entity;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

/**
 * @author Hector
 */
@Entity
@Data
@Table(name = "srol")
public class Srol implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scverol;
    
    private String sdesrol;
}
