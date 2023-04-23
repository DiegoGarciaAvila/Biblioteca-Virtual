package com.edomex.biblioteca.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Hector
 */
@Entity
@Data
@Table(name = "catuads")
public class Catuads implements Serializable{

    @Id
    private String ccveads;

    private String cdesads;
}
