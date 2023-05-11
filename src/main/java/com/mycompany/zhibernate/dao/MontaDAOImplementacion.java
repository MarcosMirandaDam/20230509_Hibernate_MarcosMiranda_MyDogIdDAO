package com.mycompany.zhibernate.dao;

import com.mycompany.zhibernate.modelo.Monta;
import com.mycompany.zhibernate.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * clase que implementa la interface MontaDAO
 * @author Marcos Miranda
 */
public class MontaDAOImplementacion implements MontaDAO{
    
    private SessionFactory sessionFactory;
    
    public MontaDAOImplementacion(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * metodo que crea una monta
     * @param monta 
     */
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

    /**
     * metodo que devuelve una monta por id determinado por el usuario
     * @param id
     * @return 
     */
    @Override
    public Monta obtenerPorId(int id) {
        Session session = sessionFactory.openSession();
        Monta monta = session.get(Monta.class, id);
        session.close();
        return monta;
    }

    /**
     * metodo que lista las montas
     * @return 
     */
    @Override
    public List<Monta> obtenerTodos() {
        Session session = sessionFactory.openSession();
        List<Monta> listaMontas = session.createQuery("from Monta", Monta.class).list();
        session.close();
        return listaMontas;
    }

    /**
     * metodo que actualiza una monta tras un cambio realizado
     * @param monta 
     */
    @Override
    public void actualizar(Monta monta) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(monta);
        transaction.commit();
        session.close();
    }

    /**
     * metodo que elimina una monta
     * @param monta 
     */
    @Override
    public void eliminar(Monta monta) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(monta);
        transaction.commit();
        session.close(); 
    }

    /**
     * metodo que lista las montas determinadas por raza
     * @param raza
     * @return 
     */
    @Override
    public List<Monta> findByRazaContainingIgnoreCase(String raza) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Monta> query = session.createQuery("FROM Monta WHERE lower(raza) LIKE :raza", Monta.class);
            query.setParameter("raza", "%" + raza.toLowerCase() + "%");
            return query.list();

            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * metodo que lista las montas determinadas por raza ordenadas por fecha
     * @param raza
     * @return 
     */
    @Override
    public List<Monta> findByRazaOrderByFechaMontaDesc(String raza) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Monta> query = session.createQuery("FROM Monta WHERE raza = :raza ORDER BY fechaMonta DESC", Monta.class);
            query.setParameter("raza", raza);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
