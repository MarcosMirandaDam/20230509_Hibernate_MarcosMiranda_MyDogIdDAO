package com.mycompany.zhibernate.dao;

import com.mycompany.zhibernate.modelo.Monta;
import java.util.List;

/**
 *
 * @author Marcos Miranda
 */
public interface MontaDAO {
    
    // Crear una nueva monta
    void crear(Monta monta);

    // Leer un monta por ID
    Monta obtenerPorId(int id);

    // Leer todas las montas
    List<Monta> obtenerTodos();

    // Actualizar una monta
    void actualizar(Monta monta);

    // Eliminar un monta
    void eliminar(Monta monta);

    
    // Buscar monta por nombre (ignorando mayúsculas y minúsculas)
    List<Monta> findByNombreContainingIgnoreCase(String nombre);

    // Buscar monta por nombre y ordenar por numero de monta de forma descendente
    List<Monta> findByNombreOrderByNombreDesc(String  nombre);
}
