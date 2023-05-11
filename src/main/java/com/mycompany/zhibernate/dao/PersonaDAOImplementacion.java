package com.mycompany.zhibernate.dao;

import com.mycompany.zhibernate.modelo.Persona;
import com.mycompany.zhibernate.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * clase que implementa la interface PersonaDAO
 * @author Marcos Miranda
 */
public class PersonaDAOImplementacion implements PersonaDAO{
    
    private SessionFactory sessionFactory;
    
    public PersonaDAOImplementacion(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * metodo que crea una persona
     * @param persona 
     */
    @Override
    public void crear(Persona persona) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(persona);
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al crear la persona: " + e.getMessage());
          
        }
    }

    /**
     * metodo que retorna una persona determinada por su id
     * @param id
     * @return 
     */
    @Override
    public Persona obtenerPorId(int id) {
        Session session = sessionFactory.openSession();
        Persona perro = session.get(Persona.class, id);
        session.close();
        return perro;
    }

    /**
     * metodo que lista todas las personas
     * @return 
     */
    @Override
    public List<Persona> obtenerTodos() {
        Session session = sessionFactory.openSession();
        List<Persona> listaPersonas = session.createQuery("FROM Persona", Persona.class).list();
        session.close();
        return listaPersonas;
    }

    /**
     * metodo que actualiza una persona
     * @param persona 
     */
    @Override
    public void actualizar(Persona persona) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(persona);
        transaction.commit();
        session.close();
    }

    /**
     * metodo que elimina una persona
     * @param persona 
     */
    @Override
    public void eliminar(Persona persona) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(persona);
        transaction.commit();
        session.close(); 
    }

    /**
     * metodo que lista persona determinada por su nombre
     * @param nombre
     * @return 
     */
    @Override
    public List<Persona> findByNombreContainingIgnoreCase(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Persona> query = session.createQuery("FROM Persona WHERE lower(nombre) LIKE :nombre", Persona.class);
            query.setParameter("nombre", "%" + nombre.toLowerCase() + "%");
            return query.list();

            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * metodo que lista persona determinada por su nombre ordenadas por numero
     * @param nombre
     * @return 
     */
    @Override
    public List<Persona> findByNombreOrderByNombreDesc(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Persona> query = session.createQuery("FROM Persona WHERE nombre = :nombre ORDER BY numero DESC", Persona.class);
            query.setParameter("nombre", nombre);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
