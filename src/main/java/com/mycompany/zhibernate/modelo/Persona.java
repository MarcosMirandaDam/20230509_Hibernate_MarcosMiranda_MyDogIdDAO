package com.mycompany.zhibernate.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "persona")
public class Persona implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id; 
    
    @Column(name="dni")
    private String dni;
    @Column(name="nombre")
    private String nombre;
    @Column(name="apellido")
    private String apellido;
    @Column(name="telefono")
    private String telefono;
    @Column(name="edad")
    private int edad;
    
    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)             //una persona tiene una lista de perros
    private List<Perro>listaPerros;

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + ", edad=" + edad + ", listaPerros=" + listaPerros + '}';
    }
    
    
    
    
}
