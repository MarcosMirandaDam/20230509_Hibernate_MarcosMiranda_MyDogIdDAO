package com.mycompany.zhibernate.dao;

import com.mycompany.zhibernate.modelo.RegistroSalud;
import java.util.List;

/**
 * interface de la clase RegistroSalud
 * @author Marcos Miranda
 */
public interface RegistroSaludDAO {
    
    // Crear un nuevo RegistroSalud
    void crear(RegistroSalud registro);

    // Leer un RegistroSalud por ID
    RegistroSalud obtenerPorId(int id);

    // Leer todos los RegistroSalud
    List<RegistroSalud> obtenerTodos();

    // Actualizar un RegistroSalud
    void actualizar(RegistroSalud registro);

    // Eliminar un RegistroSalud
    void eliminar(RegistroSalud registro);

    
    // Buscar RegistroSalud por descripcion (ignorando mayúsculas y minúsculas)
    List<RegistroSalud> findByDescripionContainingIgnoreCase(String descripcion);

    // Buscar RegistroSalud por descripcion y ordenar por coste de RegistroSalud de forma descendente
    List<RegistroSalud> findByDescripionOrderByCosteDesc(String  descripcion);
}
