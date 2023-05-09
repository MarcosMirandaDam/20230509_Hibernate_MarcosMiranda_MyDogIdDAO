package com.mycompany.zhibernate.dao;

import com.mycompany.zhibernate.modelo.Monta;
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
public class MontaDAOImplementacion implements MontaDAO{
    
    private SessionFactory sessionFactory;
    
    public MontaDAOImplementacion(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void crear(Monta monta) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(monta);
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al crear el perro: " + e.getMessage());
          
        }
    }

    @Override
    public Monta obtenerPorId(int id) {
        Session session = sessionFactory.openSession();
        Monta monta = session.get(Monta.class, id);
        session.close();
        return monta;
    }

    @Override
    public List<Monta> obtenerTodos() {
        Session session = sessionFactory.openSession();
        List<Monta> listaMontas = session.createQuery("from monta", Monta.class).list();
        session.close();
        return listaMontas;
    }

    @Override
    public void actualizar(Monta monta) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(monta);
        transaction.commit();
        session.close();
    }

    @Override
    public void eliminar(Monta monta) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(monta);
        transaction.commit();
        session.close(); 
    }

    @Override
    public List<Monta> findByNombreContainingIgnoreCase(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Monta> query = session.createQuery("FROM monta WHERE lower(nombre) LIKE :nombre", Monta.class);
            query.setParameter("nombre", "%" + nombre.toLowerCase() + "%");
            return query.list();

            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Monta> findByNombreOrderByNombreDesc(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Monta> query = session.createQuery("FROM monta WHERE nombre = :nombre ORDER BY numero DESC", Monta.class);
            query.setParameter("nombre", nombre);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
