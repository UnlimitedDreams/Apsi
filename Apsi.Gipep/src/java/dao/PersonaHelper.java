/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.Persona;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author usuario
 */
public class PersonaHelper implements helper {

    public void limpiarSessiones() {
        s.clear();
        s.getSessionFactory().close();
    }

    @Override
    public boolean buscar(String id) {
        s.getSessionFactory().openSession();
        try {
            Persona u = (Persona) s.createQuery("from Persona where idPersona='" + id + "'").uniqueResult();
            if (u != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean agregar(Object x) {
        Persona u = (Persona) x;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            if (!buscar(u.getIdpersona().toString())) {
                session.save(u);
                t.commit();
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            if (t != null) {
                t.rollback();
                return false;
            }
            throw e;
        }
    }

    @Override
    public boolean actualizar(Object y) {
        s.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Persona u = new Persona(), x = (Persona) y;
        try {
            u = (Persona) s.get(Persona.class, x.getIdpersona());
            u = x;
            s.update(u);
            t.commit();
            return true;
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
                return false;
            }
            throw e;
        }
    }

    @Override
    public boolean borrar(String Id) {
        s.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Persona u = new Persona();
        try {
            u = (Persona) s.get(Persona.class, BigDecimal.valueOf(Integer.parseInt(Id)));
            s.delete(u);
            t.commit();
            return true;
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
                return false;
            }
            throw e;
        }
    }

    @Override
    public List listarTodo() {
        s.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        try {
            return s.createQuery("from Persona").list();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
                return null;
            }
            throw e;
        } finally {
            s.close();
        }
    }

    @Override
    public Object leer(String id) {
        s.getSessionFactory().openSession();
        try {
            Persona u = (Persona) s.createQuery("from Persona where idPersona='" + id + "'").uniqueResult();
            if (u != null) {
                return  u;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
