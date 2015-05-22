/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Dias;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Microinformatica
 */
public class Sequence {

    public static Query GetUltimoRegistro(String sql) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        int codigo = 0;
        Query query = session.createQuery(sql).setMaxResults(1);
      
        
        return query;
    }
}
