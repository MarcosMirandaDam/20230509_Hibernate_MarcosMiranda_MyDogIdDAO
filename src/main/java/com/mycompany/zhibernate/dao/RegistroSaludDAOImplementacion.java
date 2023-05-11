package com.mycompany.zhibernate.dao;

import com.mycompany.zhibernate.modelo.RegistroSalud;
import com.mycompany.zhibernate.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * clase que implementa la interface RegistroSaludDAO
 * @author Marcos Miranda
 */
public class RegistroSaludDAOImplementacion implements RegistroSaludDAO{
    
    private SessionFactory sessionFactory;
    
    public RegistroSaludDAOImplementacion(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * metodo que crea un registro
     * @param registro 
     */
    @Override
    public void crear(RegistroSalud registro) {
       Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(registro);
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al crear la monta: " + e.getMessage());
          
        }
    }

    /**
     * metodo que muestra un resgistro determinado por id
     * @param id
     * @return 
     */
    @Override
    public RegistroSalud obtenerPorId(int id) {
        Session session = sessionFactory.openSession();
        RegistroSalud registro = session.get(RegistroSalud.class, id);
        session.close();
        return registro;
    }

    /**
     * metodo que lista todos los registros
     * @return 
     */
    @Override
    public List<RegistroSalud> obtenerTodos() {
        Session session = sessionFactory.openSession();
        List<RegistroSalud> listaRegistrosSalud = session.createQuery("from RegistroSalud", RegistroSalud.class).list();
        session.close();
        return listaRegistrosSalud;
    }

    /**
     * metodo que actualiza un registro de salud
     * @param registro 
     */
    @Override
    public void actualizar(RegistroSalud registro) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(registro);
        transaction.commit();
        session.close();
    }

    /**
     * metodo que elimina un registro
     * @param registro 
     */
    @Override
    public void eliminar(RegistroSalud registro) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(registro);
        transaction.commit();
        session.close(); 
    }

    /**
     * metodo que lista registro determinado por descripcion
     * @param descripcion
     * @return 
     */
    @Override
    public List<RegistroSalud> findByDescripionContainingIgnoreCase(String descripcion) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<RegistroSalud> query = session.createQuery("FROM RegistroSalud WHERE lower(descripcion) LIKE :descripcion", RegistroSalud.class);
            query.setParameter("descripcion", "%" + descripcion.toLowerCase() + "%");
            return query.list();

            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * metodo que lista registro determinado por descripcion ordenado por coste
     * @param descripcion
     * @return 
     */
    @Override
    public List<RegistroSalud> findByDescripionOrderByCosteDesc(String descripcion) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<RegistroSalud> query = session.createQuery("FROM RegistroSalud WHERE descripcion = :descripcion ORDER BY coste DESC", RegistroSalud.class);
            query.setParameter("descripcion", descripcion);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
