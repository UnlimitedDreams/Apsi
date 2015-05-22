/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Beans.FestivosBeans;
import Entity.AñoFestivo;
import Entity.Festivos;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Microinformatica
 */
public class FestivosImple implements FestivosDao {

    @Override
    public ArrayList listaFestivos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        ArrayList<Festivos> festivos = new ArrayList();
        try {
            System.out.println("1");
            festivos = (ArrayList) session.createQuery("FROM Festivos").list();
            System.out.println("2" + festivos.size());
            t.commit();
        } catch (Exception ex) {

        }
        return festivos;
    }

    @Override
    public AñoFestivo Traer_año(String año) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        AñoFestivo temp = null;
        System.out.println("--- entro");
        temp = (AñoFestivo) session.createQuery("FROM AñoFestivo WHERE año='" + año + "'").uniqueResult();
        System.out.println("---- salio " + temp.toString());
        return temp;
    }

    @Override
    public boolean CrearAñoFestivo(AñoFestivo añoFes) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        boolean r = false;
        try {
            session.save(añoFes);
            t.commit();
            r = true;
        } catch (Exception ex) {
            r = false;
        }
        return r;
    }

    @Override
    public boolean crearFestivo(Festivos fes) {
        boolean r = true;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(fes);
            t.commit();
            r = true;
        } catch (Exception ex) {
            r = false;
        }
        return r;
    }

    @Override
    public Festivos BuscarFestivo(String fecha) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Festivos temp = null;
        temp = (Festivos) session.createQuery("SELECT fe FROM Festivos fe WHERE fecha='" + fecha + "'").uniqueResult();
        t.commit();
        return temp;
    }

    @Override
    public boolean EliminarFestivo(Festivos fes) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        boolean r = false;
        try {
            System.out.println("-");
            session.createQuery("delete from Festivos where festivo=" + fes.getFestivo()).executeUpdate();
            t.commit();
            r = true;
        } catch (Exception ex) {
            r = false;
        }
        return r;
    }

}
