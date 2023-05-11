package com.mycompany.zhibernate.dao;

import com.mycompany.zhibernate.modelo.Formacion;
import com.mycompany.zhibernate.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * clase que implementa los metodos de la interface FormacionDAO
 * @author Marcos Miranda
 */
public class FormacionDAOImplementacion implements FormacionDAO{
    
    private SessionFactory sessionFactory;
    
    public FormacionDAOImplementacion(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * metodo que crea una formacion
     * @param formacion 
     */
    @Override
    public void crear(Formacion formacion) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(formacion);
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al crear la formacion: " + e.getMessage());
          
        }
    }

    /**
     * metodo que obtiene una formacion insertando el id
     * @param id
     * @return 
     */
    @Override
    public Formacion obtenerPorId(int id) {
        Session session = sessionFactory.openSession();
        Formacion formacion = session.get(Formacion.class, id);
        session.close();
        return formacion;
    }

    /**
     * metodo que devuelve una lista con todos las formaciones
     * @return 
     */
    @Override
    public List<Formacion> obtenerTodos() {
        Session session = sessionFactory.openSession();
        List<Formacion> formaciones = session.createQuery("from formacion", Formacion.class).list();
        session.close();
        return formaciones;
    }

    /**
     * metodo que actualiza una formacion tras un cambio el la misma
     * @param formacion 
     */
    @Override
    public void actualizar(Formacion formacion) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(formacion);
        transaction.commit();
        session.close();
    }

    /**
     * metodo que elimina una formacion
     * @param formacion 
     */
    @Override
    public void eliminar(Formacion formacion) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(formacion);
        transaction.commit();
        session.close(); 
    }

    /**
     * metodo que lista las formaciones determinadas por su nombre
     * @param nombreFormacion
     * @return 
     */
    @Override
    public List<Formacion> findByNombreFormacionContainingIgnoreCase(String nombreFormacion) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Formacion> query = session.createQuery("FROM Formacion WHERE lower(nombreFormacion) LIKE :nombreFormacion", Formacion.class);
            query.setParameter("nombreFormacion", "%" + nombreFormacion.toLowerCase() + "%");
            return query.list();

            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * metodo que lista las formaciones determinadas por su nombre y las ordena por localidad
     * @param nombreFormacion
     * @return 
     */
    @Override
    public List<Formacion> findByNombreFormacionOrderByLocalidadDesc(String nombreFormacion) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Formacion> query = session.createQuery("FROM Formacion WHERE nombreFormacion = :nombreFormacion ORDER BY localidad DESC", Formacion.class);
            query.setParameter("nombreFormacion", nombreFormacion);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
