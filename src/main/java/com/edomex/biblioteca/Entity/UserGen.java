package com.edomex.biblioteca.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "usergen")
public class UserGen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ugcve;

    @ManyToOne
    @JoinColumn(name = "ugcveserv")
    private AppUser ugcveserv;

    @ManyToOne
    @JoinColumn(name = "ugcvegen")
    private GenLiterario ugcvegen;
}
