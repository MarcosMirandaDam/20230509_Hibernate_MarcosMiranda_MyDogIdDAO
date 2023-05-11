package com.mycompany.zhibernate.dao;

import com.mycompany.zhibernate.modelo.Evento;
import com.mycompany.zhibernate.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * Clase que implementa los metodos abstractos de la interface EventoDAO
 * @author Marcos Miranda
 */
public class EventoDAOImplementacion implements EventoDAO{
    
    private SessionFactory sessionFactory;
    
    public EventoDAOImplementacion(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * metodo que crea un evento
     * @param evento 
     */
    @Override
    public void crear(Evento evento) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(evento);
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al crear la monta: " + e.getMessage());
          
        }
    }

    /**
     * metodo que obtiene un evento insertando el id
     * @param id
     * @return 
     */
    @Override
    public Evento obtenerPorId(int id) {
        Session session = sessionFactory.openSession();
        Evento evento = session.get(Evento.class, id);
        session.close();
        return evento;
    }

    /**
     * metodo que devuelve una lista con todos los eventos 
     * @return 
     */
    @Override
    public List<Evento> obtenerTodos() {
        Session session = sessionFactory.openSession();
        List<Evento> listaEventos = session.createQuery("from Evento", Evento.class).list();
        session.close();
        return listaEventos;
    }

    /**
     * metodo que actualiza un evento tras un cambio el el mismo
     * @param evento 
     */
    @Override
    public void actualizar(Evento evento) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(evento);
        transaction.commit();
        session.close();
    }

    /**
     * metodo que elimina un evento
     * @param evento 
     */
    @Override
    public void eliminar(Evento evento) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(evento);
        transaction.commit();
        session.close(); 
    }

    /**
     * metodo que lista los eventos de una clase determinada por el usuario
     * @param clase
     * @return 
     */
    @Override
    public List<Evento> findByClaseContainingIgnoreCase(String clase) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Evento> query = session.createQuery("FROM Evento WHERE lower(clase) LIKE :clase", Evento.class);
            query.setParameter("clase", "%" + clase.toLowerCase() + "%");
            return query.list();

            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * metodo que lista los eventos de una clase determinada por el usuario y los ordena por fecha
     * @param clase
     * @return 
     */
    @Override
    public List<Evento> findByClaseOrderByFechaDesc(String clase) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Evento> query = session.createQuery("FROM Evento WHERE clase = :clase ORDER BY fecha DESC", Evento.class);
            query.setParameter("clase", clase);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
