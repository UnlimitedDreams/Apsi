/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.Proyectos;
import Entity.Usuario;
import Entity.UsuarioProyecto;
import Modelo.Conecion_postgres1;
import Modelo.Profesor;
import Modelo.ProyectosModelo;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Microinformatica
 */
@ManagedBean
@SessionScoped
public class AsiganarDirector {

    ArrayList<ProyectosModelo> pro2 = new ArrayList();

    public AsiganarDirector() {
    }

    public void cargar_Proyectos() {
        ArrayList<Proyectos> pro = new ArrayList();
        pro2.clear();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            pro = (ArrayList) session.createQuery("select distinct P FROM Proyectos P"
                    + " INNER JOIN P.usuarioProyectos U INNER JOIN"
                    + " U.usuarioByDirector UD where "
                    + "UD.pegeId=0").list();
            ProyectosModelo temp = null;
            Proyectos temp2 = null;
            for (int i = 0; i < pro.size(); i++) {
                temp2 = (Proyectos) pro.get(i);
                pro2.add(new ProyectosModelo(temp2.getCodigoProyecto().intValue(), temp2.getNombre(), false));
            }
            System.out.println("---- " + pro2.size());
        } catch (Exception ex) {
            System.out.println("Error " + ex.toString());
        }
    }

    public void pasar_Proyectos(int x) {
        ProyectosModelo temp = null;
        for (int i = 0; i < pro2.size(); i++) {
            temp = (ProyectosModelo) pro2.get(i);
            if (temp.getCod() == x) {
                if (temp.isEstado() == false) {
                    temp.setEstado(true);
                } else {
                    temp.setEstado(false);
                }
            }
        }

        for (int i = 0; i < pro2.size(); i++) {

            System.out.println("- " + pro2.get(i) + "\n");
        }

    }

    public void TraerCodUsuPro() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        ArrayList<String> cod = new ArrayList();
        ArrayList<UsuarioProyecto> pro = new ArrayList();
        try {
            pro = (ArrayList) session.createQuery("from UsuarioProyecto U  where"
                    + " U.proyectos.codigoProyecto=" + 23).list();
            System.out.println("-- " + pro.size());
        } catch (Exception ex) {

        }

    }

    public void guardar() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        ArrayList<UsuarioProyecto> pro = new ArrayList();
        try {
            ProyectosModelo temp2 = null;
            Profesor p = null;
            p = (Profesor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("profesor");
            boolean r = false;
            for (int i = 0; i < pro2.size(); i++) {
                temp2 = (ProyectosModelo) pro2.get(i);
                Conecion_postgres1.conectar();
                r = Conecion_postgres1.ejecuteUpdate("update usuario_proyecto set "
                        + "director=" + p.getPege_id() + " where codigo_proyecto=" + temp2.getCod_pro());
                Conecion_postgres1.cerrarConexion();
            }
            t.commit();
            if (r) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("Menu_Admon.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", ""));

            }

        } catch (Exception ex) {

        }

    }

    public void adicionarDiretor() {
        Profesor p = new Profesor();
        p = (Profesor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("profesor");
        ProyectosModelo temp = null;
        int cod = Integer.parseInt(p.getPege_id());
        for (int i = 0; i < pro2.size(); i++) {
            temp = (ProyectosModelo) pro2.get(i);
            temp.setProfesor(cod);
        }
        for (int i = 0; i < pro2.size(); i++) {
            System.out.println("-- " + pro2.get(i) + "\n");
        }

    }

    public void traer_codigoProyec() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        ProyectosModelo temp = null;
        UsuarioProyecto temp2 = null;
        for (int i = 0; i < pro2.size(); i++) {
            temp = (ProyectosModelo) pro2.get(i);
            temp2 = (UsuarioProyecto) session.createQuery("select U from UsuarioProyecto U INNER JOIN U.proyectos P where"
                    + " P.codigoProyecto=" + temp.getCod()).uniqueResult();
            temp.setCodigo(temp2.getCodUsuproyecto().intValue());
            System.out.println("-*- " + temp2.getUsuarioByEstudiante().getPegeId());
            temp.setEstudiante(temp2.getUsuarioByEstudiante().getPegeId().intValue());
//            temp.setEstudiante(temp2.);
        }

    }

    public ArrayList<ProyectosModelo> getPro2() {
        return pro2;
    }

    public void setPro2(ArrayList<ProyectosModelo> pro2) {
        this.pro2 = pro2;
    }

}
