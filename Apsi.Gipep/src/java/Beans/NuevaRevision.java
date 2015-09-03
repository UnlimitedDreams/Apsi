/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.Proyectos;
import Entity.RevisionProyecto;
import Entity.Revisiones;
import Modelo.Secuencia;
import dao.Sequence;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Jeison
 */
@ManagedBean
@SessionScoped

public class NuevaRevision {

    private String nueva;

    /**
     * Creates a new instance of NuevaRevision
     */
    public NuevaRevision() {
    }

    public void revi() throws ClassNotFoundException {
        System.err.println("entro al nuevo");
        int cod_Revicion = Sequence.GetUltimoRegistro("select max(r.codigoRevision) from Revisiones r");
        int revision_proye = Sequence.GetUltimoRegistro("select max(r.codRevisionproyecto) from RevisionProyecto r ");

        System.out.println("codigo revicion : " + cod_Revicion);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        try {
            String a = "";
            int codProyecto = 0;
            Revisiones re = new Revisiones();
            System.out.println("---");
            System.out.println("codigo " + cod_Revicion);
            re.setCodigoRevision(new BigDecimal(cod_Revicion));
            re.setDescripcion(nueva);
            Date fecha = new Date();
            re.setFecha(fecha);
            System.out.println("datos " + re.toString());
            session.save(re);
            t.commit();
            System.out.println("- 1");
            insertReviPro(re,revision_proye);
            FacesContext.getCurrentInstance().getExternalContext().redirect("PROVistaRevision-1.2.xhtml");
        } catch (Exception e) {
            System.err.println("Error revi" + e.toString());
        }

    }

    public void insertReviPro(Revisiones re,int revision_proye) throws ClassNotFoundException {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        try {
            System.out.println("entrooooo a insert 2");
            Proyectos pro = new Proyectos();
            pro = (Proyectos) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Poryecto_Revi");//guardo un dato con nueva llave para recuperarla en la nueva vista
            System.out.println("Proyecto +++ : " + pro.getCodigoProyecto());
            RevisionProyecto rp = new RevisionProyecto();
            rp.setCodRevisionproyecto(new BigDecimal(revision_proye));
            rp.setProyectos(pro);
            rp.setRevisiones(re);
            session.save(rp);
            t.commit();
        } catch (Exception ex) {
            System.out.println("Error ReviPro  " + ex.toString());
        }

    }

    public String getNueva() {
        return nueva;
    }

    public void setNueva(String nueva) {
        this.nueva = nueva;
    }

}
