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
@Table(name = "registroSalud")
public class RegistroSalud implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id; 
    
    @Column(name="nombreClinica")
    private String nombreClinica;
    @Column(name="localidad")
    private String localidad;
    @Column(name="fechaIntervencion")
    private Date fechaIntervencion;
    @Column(name="descripcion")
    private String descripcion;
    @Column(name="vacuna")
    private boolean vacuna;
    @Column(name="tratamiento")
    private boolean tratamiento;
    @Column(name="coste")
    private float coste;

    @ManyToMany(cascade = {CascadeType.ALL},mappedBy="registrosSalud")
    private List<Perro> listaPerros;
    
    @Override
    public String toString() {
        return "RegistroSalud{" + "id=" + id + ", nombreClinica=" + nombreClinica + ", localidad=" + localidad + ", fechaIntervencion=" + fechaIntervencion + ", descripcion=" + descripcion + ", vacuna=" + vacuna + ", tratamiento=" + tratamiento + ", coste=" + coste + '}';
    }
    
    
}
