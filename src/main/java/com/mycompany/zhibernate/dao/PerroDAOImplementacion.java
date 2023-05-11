package com.mycompany.zhibernate.dao;

import com.mycompany.zhibernate.modelo.Perro;
import com.mycompany.zhibernate.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * clase que implementa la interface PerroDAO
 * @author Marcos Miranda
 */
public class PerroDAOImplementacion implements PerroDAO{
    
    private SessionFactory sessionFactory;
    
    public PerroDAOImplementacion(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * metodo que crea un perro
     * @param perro 
     */
    @Override
    public void crear(Perro perro) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(perro);
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al crear el perro: " + e.getMessage());
          
        }
    }

    /**
     * metodo que retorna un perro determinado por su id
     * @param id
     * @return 
     */
    @Override
    public Perro obtenerPorId(int id) {
        Session session = sessionFactory.openSession();
        Perro perro = session.get(Perro.class, id);
        session.close();
        return perro;
    }

    /**
     * metodo que lista los perros 
     * @return 
     */
    @Override
    public List<Perro> obtenerTodos() {
        Session session = sessionFactory.openSession();
        List<Perro> listaPerros = session.createQuery("from Perro", Perro.class).list();
        session.close();
        return listaPerros;
    }

    /**
     * metodo que actualiza un perro tras su modificacion
     * @param perro 
     */
    @Override
    public void actualizar(Perro perro) {
       Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(perro);
        transaction.commit();
        session.close();
    }

    /**
     * metodo que elimina un perro determinado por parametro
     * @param perro 
     */
    @Override
    public void eliminar(Perro perro) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(perro);
        transaction.commit();
        session.close(); 
        
    }

    /**
     * metodo que lista un perro determinado por su nombre
     * @param nombre
     * @return 
     */
    @Override
    public List<Perro> findByNombreContainingIgnoreCase(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Perro> query = session.createQuery("FROM Perro WHERE lower(nombre) LIKE :nombre", Perro.class);
            query.setParameter("nombre", "%" + nombre.toLowerCase() + "%");
            return query.list();

            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * metodo que lista un perro determinado por su nombre ordenados por numero
     * @param nombre
     * @return 
     */
    @Override
    public List<Perro> findByNombreOrderByNombreDesc(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Perro> query = session.createQuery("FROM Perro WHERE nombre = :nombre ORDER BY numero DESC", Perro.class);
            query.setParameter("nombre", nombre);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
}
