/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

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

}
