/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.Proyectos;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Microinformatica
 */
public class ProyectosImple implements ProyectosDao {

    @Override
    public boolean SubirProyecto(Proyectos p) {
        System.out.println("--------------------------------------------------------");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        boolean r = false;
        try {
            session.save(p);
            r = true;
            t.commit();
        } catch (Exception ex) {
            r = false;
//            System.out.println("Error - " + ex.getMessage());
        }
        return r;
    }

}
