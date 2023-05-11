package com.mycompany.zhibernate.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "perro")
public class Perro implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id; 
    
    @Column(name="nombre")
    private String nombre;
    @Column(name="afijo")
    private String afijo;
    @Column(name="chip")
    private String chip;
    @Column(name="fechaNacim")
    private Date fechaNacimiento;
    @Column(name="sexo")
    private String sexo;
    @Column(name="precio")
    private float precio;

    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY)             //un perro tiene una lista de montas
    private List<Monta>listaMontas;
    
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="perro_RegistroSalud", joinColumns={@JoinColumn(name="IdPerro")}, inverseJoinColumns={@JoinColumn(name="IdRegistroSalud")})
    private List<RegistroSalud> registrosSalud;
    
    
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="Perro_Evento", joinColumns={@JoinColumn(name="IdPerro")}, inverseJoinColumns={@JoinColumn(name="IdEvento")})
    private List<Evento> eventos;
    
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="Perro_Formacion", joinColumns={@JoinColumn(name="IdPerro")}, inverseJoinColumns={@JoinColumn(name="IdFormacion")})
    private List<Formacion> formaciones;
    
    
    @Override
    public String toString() {
        return "Perro{" + "id=" + id + ", nombre=" + nombre + ", afijo=" + afijo + ", chip=" + chip + ", fechaNacimiento=" + fechaNacimiento + ", sexo=" + sexo + ", precio=" + precio + '}';
    }
    
       
          
    
    
}
