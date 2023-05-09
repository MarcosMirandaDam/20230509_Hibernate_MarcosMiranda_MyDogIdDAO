package com.mycompany.zhibernate.dao;

import com.mycompany.zhibernate.modelo.Persona;
import java.util.List;

/**
 *
 * @author Marcos Miranda
 */
public interface PersonaDAO {
    
    // Crear una nueva persona
    void crear(Persona persona);

    // Leer una persona por ID
    Persona obtenerPorId(int id);

    // Leer todas las personas
    List<Persona> obtenerTodos();

    // Actualizar un persona
    void actualizar(Persona persona);

    // Eliminar un persona
    void eliminar(Persona persona);

    
    // Buscar persona por nombre (ignorando mayúsculas y minúsculas)
    List<Persona> findByNombreContainingIgnoreCase(String nombre);

    // Buscar persona por nombre y ordenar por numero de persona de forma descendente
    List<Persona> findByNombreOrderByNombreDesc(String  nombre);
    
}
