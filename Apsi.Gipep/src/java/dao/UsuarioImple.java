/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Asesoria;
import Entity.Usuario;
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
        System.out.println("entro a Buscar usuario " + usu.getPegeId());
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        boolean r = false;
        Usuario u=new Usuario();
        try {
            u = (Usuario) session.createQuery("FROM Usuario as usu where usu.pegeId="+usu.getPegeId()).uniqueResult();
            if (u == null) {
                System.out.println("no esta");
                r=false;
            } else {
                System.out.println("esta");
                r=true;
            }
            t.commit();
        } catch (Exception ex) {
            System.out.println("Error -> " + ex.getMessage());
        }
        return r;
    }

}
