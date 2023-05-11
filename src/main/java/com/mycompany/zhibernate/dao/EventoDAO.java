package com.mycompany.zhibernate.dao;

import com.mycompany.zhibernate.modelo.Evento;
import java.util.List;

/**
 * Interface clase Evento
 * @author Marcos Miranda
 */

public interface EventoDAO {
    
    // Crear un nuevo evento
    void crear(Evento evento);

    // Leer un evento por ID
    Evento obtenerPorId(int id);

    // Leer todos los eventos
    List<Evento> obtenerTodos();

    // Actualizar un evento
    void actualizar(Evento evento);

    // Eliminar un evento
    void eliminar(Evento evento);

    
    // Buscar evento por clase (ignorando mayúsculas y minúsculas)
    List<Evento> findByClaseContainingIgnoreCase(String clase);

    // Buscar evento por clase y ordenar por fecha de forma descendente
    List<Evento> findByClaseOrderByFechaDesc(String  clase);
}
