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
@Table(name = "formacion")
public class Formacion implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id; 
    
    @Column(name="nombreFormacion")
    private String nombreFormacion;
    @Column(name="localidad")
    private String localidad;
    @Column(name="juez")
    private String juez;
    @Column(name="fechaFormacion")
    private Date fechaFormacion;
    
    @ManyToMany(cascade = {CascadeType.ALL},mappedBy="formaciones")
    private List<Perro> listaPerros;

    @Override
    public String toString() {
        return "Formacion{" + "id=" + id + ", nombreFormacion=" + nombreFormacion + ", localidad=" + localidad + ", juez=" + juez + ", fechaFormacion=" + fechaFormacion + ", listaPerros=" + listaPerros + '}';
    }
    
    
    
}
