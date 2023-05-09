package com.mycompany.zhibernate.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Marcos Miranda
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "monta")
public class Monta implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id; 
    
    @Column(name="raza")
    private String raza;
    @Column(name="propietarioMacho")
    private String propietarioMacho;
    @Column(name="numeroRegistroMacho")
    private int numeroRegistroMacho;
    @Column(name="propietarioHembra")
    private String propietarioHembra;
    @Column(name="numeroRegistroHembra")
    private int numeroRegistroHembra;
    @Column(name="fechaMonta")
    private Date fechaMonta;

    @Override
    public String toString() {
        return "Monta{" + "id=" + id + ", raza=" + raza + ", propietarioMacho=" + propietarioMacho + ", numeroRegistroMacho=" + numeroRegistroMacho + ", propietarioHembra=" + propietarioHembra + ", numeroRegistroHembra=" + numeroRegistroHembra + ", fechaMonta=" + fechaMonta + '}';
    }
    
    
    
}
