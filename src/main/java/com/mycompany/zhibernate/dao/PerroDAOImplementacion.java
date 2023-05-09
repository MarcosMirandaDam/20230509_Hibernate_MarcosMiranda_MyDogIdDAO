package com.mycompany.zhibernate.dao;

import com.mycompany.zhibernate.modelo.Perro;
import com.mycompany.zhibernate.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Marcos Miranda
 */
public class PerroDAOImplementacion implements PerroDAO{
    
    private SessionFactory sessionFactory;
    
    public PerroDAOImplementacion(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

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

    @Override
    public Perro obtenerPorId(int id) {
        Session session = sessionFactory.openSession();
        Perro perro = session.get(Perro.class, id);
        session.close();
        return perro;
    }

    @Override
    public List<Perro> obtenerTodos() {
        Session session = sessionFactory.openSession();
        List<Perro> listaPerros = session.createQuery("from perro", Perro.class).list();
        session.close();
        return listaPerros;
    }

    @Override
    public void actualizar(Perro perro) {
       Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(perro);
        transaction.commit();
        session.close();
    }

    @Override
    public void eliminar(Perro perro) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(perro);
        transaction.commit();
        session.close(); 
        
    }

    @Override
    public List<Perro> findByNombreContainingIgnoreCase(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Perro> query = session.createQuery("FROM perro WHERE lower(nombre) LIKE :nombre", Perro.class);
            query.setParameter("nombre", "%" + nombre.toLowerCase() + "%");
            return query.list();

            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Perro> findByNombreOrderByNombreDesc(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Perro> query = session.createQuery("FROM perro WHERE nombre = :nombre ORDER BY numero DESC", Perro.class);
            query.setParameter("nombre", nombre);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
}
