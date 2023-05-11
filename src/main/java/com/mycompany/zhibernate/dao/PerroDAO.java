package com.mycompany.zhibernate.dao;

import com.mycompany.zhibernate.modelo.Perro;
import java.util.List;

/**
 * interface de la clase Perro
 * @author Marcos Miranda
 */
public interface PerroDAO {
    
    // Crear un nuevo perro
    void crear(Perro perro);

    // Leer un perro por ID
    Perro obtenerPorId(int id);

    // Leer todos los perros
    List<Perro> obtenerTodos();

    // Actualizar un perro
    void actualizar(Perro perro);

    // Eliminar un perro
    void eliminar(Perro perro);

    
    // Buscar perros por nombre (ignorando mayúsculas y minúsculas)
    List<Perro> findByNombreContainingIgnoreCase(String nombre);

    // Buscar perros por nombre y ordenar por numero de perro de forma descendente
    List<Perro> findByNombreOrderByNombreDesc(String  nombre);
}
