package com.mycompany.zhibernate.dao;

import com.mycompany.zhibernate.modelo.Formacion;
import java.util.List;

/**
 * interface de la clase Formacion
 * @author Marcos Miranda
 */
public interface FormacionDAO {
    // Crear una nueva formacion
    void crear(Formacion formacion);

    // Leer una formacion por ID
    Formacion obtenerPorId(int id);

    // Leer todas las formaciones
    List<Formacion> obtenerTodos();

    // Actualizar un formacion
    void actualizar(Formacion formacion);

    // Eliminar un formacion
    void eliminar(Formacion formacion);

    
    // Buscar formacion por nombreFormacion (ignorando mayúsculas y minúsculas)
    List<Formacion> findByNombreFormacionContainingIgnoreCase(String nombreFormacion);

    // Buscar formacion por nombreFormacion y ordenar por localidad de forma descendente
    List<Formacion> findByNombreFormacionOrderByLocalidadDesc(String  nombre);
    
}
