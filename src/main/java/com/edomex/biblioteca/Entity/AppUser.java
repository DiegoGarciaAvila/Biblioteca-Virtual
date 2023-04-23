package com.edomex.biblioteca.Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

/**
 * @author Hector
 */
@Entity
@Data
@Table(name = "appuser")
public class AppUser  implements Serializable{

    @Id
    private String uacveserv;

    private String uanombre;
    private String uaappaterno;
    private String uaamaterno;

    private String uapassword;


    @ManyToOne
    @JoinColumn(name = "uagenero")
    private GenUser uagenero;
    
    private Integer uaedad;

    private String uaident;

    private String uacorreo;

    private String uadompart;

    private String uatelmov;

    @ManyToOne
    @JoinColumn(name = "uaads")
    private Catuads uaads;

    private String uadomtrab;

    private String uateltrab;

    @ManyToOne
    @JoinColumn(name="uastsusr")
    private Cstsusr uastsusr;

    private String uacomdom;
}
