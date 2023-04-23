package com.edomex.biblioteca.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Hector
 */
@Entity
@Data
@Table(name = "catstsevent")
public class Catstsevent implements Serializable{

    @Id
    private Integer cvestsev;

    private String cdesstseven;
}
