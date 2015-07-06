/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Persona;
import Entity.Usuario;
import apsi.Security.md5;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Microinformatica
 */
public class UsuarioImple implements UsuarioDao {

    @Override
    public Usuario Loguin(Usuario usu) {
        Usuario temp = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            temp = (Usuario) session.createQuery("FROM Usuario WHERE usuario='" + usu.getUsuario() + "' and contrasea='" + usu.getContrasea() + "'").uniqueResult();
            t.commit();

        } catch (Exception ex) {

        }
        return temp;
    }

    @Override
    public boolean CrearUsuario(Usuario usu) {
        System.out.println("entro a crear usuario " + usu.getPegeId());
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        boolean r = false;
        try {
            session.save(usu);
            t.commit();
            r = true;
        } catch (Exception ex) {
            r = false;
        }
        return r;
    }

    @Override
    public boolean BuscarUsuario(Usuario usu) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        boolean r = false;
        Usuario u = new Usuario();
        try {
            u = (Usuario) session.createQuery("FROM Usuario as usu where usu.pegeId=" + usu.getPegeId()).uniqueResult();
            if (u == null) {
                r = false;
            } else {
                r = true;
            }
            t.commit();
        } catch (Exception ex) {
            System.out.println("Error -> " + ex.getMessage());
        }
        return r;
    }

    public Usuario cargarUsu(String pege) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Usuario u = new Usuario();
        try {
            u = (Usuario) session.get(Usuario.class, BigDecimal.valueOf(Integer.parseInt(pege)));
            t.commit();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return u;
    }

    public void CambiarContraseña(String pege, String nPass) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Usuario u = new Usuario();
        try {
            u = (Usuario) session.get(Usuario.class, BigDecimal.valueOf(Integer.parseInt(pege)));
            u.setContrasea(md5.getMD5(nPass));
            session.update(u);
            t.commit();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }
    
    public Persona verPersona(String id){
         Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Persona p = new Persona();
        try {
            p = (Persona) session.get(Persona.class, BigDecimal.valueOf(Integer.parseInt(id)));
            t.commit();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return p;
    }
}
