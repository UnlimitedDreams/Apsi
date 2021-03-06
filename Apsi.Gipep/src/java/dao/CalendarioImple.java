    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.Asesoria;
import Entity.Asistente;
import Entity.Calendario;
import Entity.Usuario;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Microinformatica
 */
public class CalendarioImple implements CalendarioP {

    @Override
    public void buscarAsesorias() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();;
        Transaction t = session.beginTransaction();
        Asesoria ase = new Asesoria();
        try {
            ase = (Asesoria) session.createQuery("FROM Asesoria as aseso where aseso.asistentes.usuario.pegeId=1").uniqueResult();
            if (ase == null) {
                System.out.println("no esta");
            } else {
                System.out.println("esta");
            }
        } catch (Exception ex) {
            if (t != null) {
                t.rollback();
            }
            throw ex;
        }

    }

    @Override
    public boolean CrearCalendario(Calendario c) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        boolean r = false;
        try {
            session.save(c);
            t.commit();
            r = true;
        } catch (Exception ex) {
            r = false;
            if (t != null) {
                t.rollback();
            }
            throw ex;
        }
        return r;
    }

    @Override
    public boolean BorrarCalendario(Calendario c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        boolean r = false;
        try {
            session.createQuery("delete from Calendario where codCalendario=" + c.getCodCalendario()).executeUpdate();
            t.commit();
            r = true;
        } catch (Exception ex) {
            r = false;
        }
        return r;
    }

    @Override
    public boolean BorrarASesoria(Asesoria S, Asistente A) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        boolean r = false;
        try {
            session.createQuery("delete from Asistente where asesoria=" + S.getCodAsesoria()).executeUpdate();
            session.createQuery("delete from Asesoria where codAsesoria=" + S.getCodAsesoria()).executeUpdate();
            t.commit();
            r = true;
        } catch (Exception ex) {
            r = false;
        }
        return r;
    }

    @Override
    public ArrayList TraerCalendario(Usuario usu) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        ArrayList<Calendario> calen = new ArrayList();
        try {
            calen = (ArrayList) session.createQuery("FROM Calendario c where c.usuario=" + usu.getPegeId()).list();
            t.commit();
        } catch (Exception ex) {

        }
        return calen;
    }

    @Override
    public ArrayList TraerAsesorias(int cod) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        ArrayList<Asesoria> calen = new ArrayList();
        try {
            calen = (ArrayList) session.createQuery("select A from Asesoria A INNER JOIN A.disponibilidad D "
                    + " INNER JOIN D.dispoUsuarios UD "
                    + " INNER JOIN UD.usuarioByProfesor Usu "
                    + " INNER JOIN A.estadosAsesorias ES "
                    + " INNER JOIN ES.estados E "
                    + "WHERE Usu.pegeId=" + cod + " and E.codigoEstados=1").list();
            t.commit();
        } catch (Exception ex) {

        }
        return calen;

    }

    @Override
    public Calendario BuscarCalendario(String cod) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Calendario temp = new Calendario();
        try {
            temp = (Calendario) session.createQuery("FROM Calendario c WHERE c.codCalendario=" + cod).uniqueResult();
            t.commit();
        } catch (Exception ex) {

        }
        return temp;
    }

    @Override
    public boolean ActualizarCalendario(Calendario c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        boolean r = false;
        try {
            session.update(c);
            t.commit();
            r = true;
        } catch (Exception ex) {
            r = false;

        }
        return r;
    }

}
