package com.mycompany.zhibernate.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "evento")
public class Evento implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "nombrePrueba")
    private String nombrePrueba;
    @Column(name = "localidad")
    private String localidad;
    @Column(name = "clase")
    private String clase;
    @Column(name = "juez")
    private String juez;
    @Column(name = "fecha")
    private Date fecha;
    
    @ManyToMany(cascade = {CascadeType.ALL},mappedBy="eventos")
    private List<Perro> listaPerros;

    @Override
    public String toString() {
        return "Evento{" + "id=" + id + ", nombrePrueba=" + nombrePrueba + ", localidad=" + localidad + ", clase=" + clase + ", juez=" + juez + ", fecha=" + fecha + ", listaPerros=" + listaPerros + '}';
    }
    
    
}
