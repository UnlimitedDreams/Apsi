/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Microinformatica
 */
public class Sequence {

    public static int GetUltimoRegistro(String sql) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        int cod = 0;
        try {
            Query query = session.createQuery(sql).setMaxResults(1);
            if (query.uniqueResult() == null) {
                cod = 1;
            } else {
                cod = Integer.parseInt(query.uniqueResult().toString()) + 1;

            }
            t.commit();
        } catch (Exception ex) {
            System.out.println("Error GetUltimoRegistro " + ex.toString());
            if (t != null) {
                t.rollback();
            }
        }

        return cod;
    }
}
