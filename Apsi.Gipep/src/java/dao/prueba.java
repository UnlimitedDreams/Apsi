/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.Calendario;
import Entity.DetalleAsesoria;
import Entity.Disponibilidad;
import Entity.EstadoProyecto;
import Entity.Usuario;
import Entity.UsuarioProyecto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.stat.Statistics;
import util.HibernateUtil;

/**
 *
 * @author Britt
 */
public class prueba {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        DetalleAsesoria detalle = new DetalleAsesoria();
        ArrayList<DetalleAsesoria> lisD = new ArrayList();
        try {
            lisD = (ArrayList) session.createQuery("select D from Asesoria A inner join "
                    + " A.detalleAsesorias D where A.fechaAsesoria<='2015-09-07' "
                    + " and A.codigoProyecto=1").list();
            System.out.println("size : " + lisD.get(lisD.size()-1).getCodDetalle());
            t.commit();
        } catch (Exception ex) {

            System.out.println("Error " + ex.toString());
        }

    }

    public static void quer() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        ArrayList<Usuario> u = new ArrayList();

        try {
            Disponibilidad d = (Disponibilidad) session.load(Disponibilidad.class, new BigDecimal(1));
            System.out.println("size " + d.getFechaFinal());
            t.commit();
        } catch (Exception ex) {
            System.out.println("Error " + ex.toString());

        }
    }

    public static int GetUltimoRegistro(String sql) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery(sql).setMaxResults(1);
        int cod = Integer.parseInt(query.uniqueResult().toString()) + 1;
        t.commit();
        return cod;
    }

    public static void esta() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        ArrayList<Calendario> calen = new ArrayList();
        try {
            Calendario c = (Calendario) session.load(Calendario.class, new Long(1));
//            calen = (ArrayList) session.createQuery("FROM Calendario c where c.codCalendario=" + 1).list();
            System.out.println("calen 2 " + c.getFechaInicial());
            t.commit();
        } catch (Exception e) {

            throw e;
        } finally {

        }
    }

    public static void esta2() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        ArrayList<Calendario> calen = new ArrayList();
        try {
            calen = (ArrayList) session.createQuery("FROM Calendario c where c.codCalendario=" + 1).list();
            t.commit();
            System.out.println("calen 3 " + calen.size());

        } catch (Exception e) {

        } finally {

        }
    }

    public static void reportConeciones() {
        Statistics stats = HibernateUtil.getSessionFactory().getStatistics();
        stats.setStatisticsEnabled(true);

        System.out.println("recuento de conexión: " + stats.getConnectCount());

//Numero de transacciones completadas (falladas y satisfactorias)
        System.out.println("recuento Trx: " + stats.getTransactionCount());

//Numero de transacciones completadas (solo satisfactorias)
        System.out.println("Succ trx count: " + stats.getSuccessfulTransactionCount());

// Numero de sesiones que el codigo ha abierto
        System.out.println("sesiones abiertas: " + stats.getSessionOpenCount());

// Numero de sesiones que el codigo ha cerrado
        System.out.println("sesiones  cerradas: " + stats.getSessionCloseCount());

// Numero total de queries ejecutados
        System.out.println("No. queries: " + stats.getQueryExecutionCount());
//            esta();
    }

}
