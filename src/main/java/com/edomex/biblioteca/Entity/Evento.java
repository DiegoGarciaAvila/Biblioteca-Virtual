package com.edomex.biblioteca.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "evento")
public class Evento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ecveevent;

    private String etitevent;
    private String edesevent;
    private String eurlevent;

    @ManyToOne
    @JoinColumn(name="ecveserv")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name="estsevent")
    private Catstsevent  estsevent;
}
