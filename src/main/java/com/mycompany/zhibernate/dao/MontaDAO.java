package com.mycompany.zhibernate.dao;

import com.mycompany.zhibernate.modelo.Monta;
import java.util.List;

/**
 * interface de la clase Monta
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

    
    // Buscar monta por raza (ignorando mayúsculas y minúsculas)
    List<Monta> findByRazaContainingIgnoreCase(String raza);

    // Buscar monta por raza y ordenar por fecha de monta de forma descendente
    List<Monta> findByRazaOrderByFechaMontaDesc(String  raza);
}
