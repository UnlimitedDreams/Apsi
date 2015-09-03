/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.EstadoProyecto;
import Entity.Estados;
import Entity.Proyectos;
import Entity.Usuario;
import Entity.UsuarioProyecto;
import Modelo.Conecion_postgres1;
import Modelo.Profesor;
import Modelo.ProyectosModelo;
import Modelo.Secuencia;
import java.math.BigDecimal;
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
    private String nombreUsuario;

    public AsiganarDirector() {
    }

    public void cargar_Proyectos() {
        ArrayList<Proyectos> pro = new ArrayList();
        pro2.clear();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
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
            t.commit();
        } catch (Exception ex) {
            if (t != null) {
                t.rollback();
            }
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
    }

    public void guardar() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        ArrayList<UsuarioProyecto> pro = new ArrayList();
        try {
            ProyectosModelo temp2 = null;
            Profesor p = null;
            p = (Profesor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("profesor");
            boolean r = false;
            Proyectos proyec = null;
            for (int i = 0; i < pro2.size(); i++) {
                temp2 = (ProyectosModelo) pro2.get(i);
                if (temp2.isEstado() == true) {
                    ArrayList<UsuarioProyecto> list_U = new ArrayList();
                    UsuarioProyecto usu = new UsuarioProyecto();
                    list_U = (ArrayList) session.createQuery("select us from UsuarioProyecto us inner join "
                            + " us.proyectos p where p.codigoProyecto=" + temp2.getCod_pro()).list();
                    usu = list_U.get(0);
                    Usuario u = (Usuario) session.load(Usuario.class, new BigDecimal(Integer.parseInt(p.getPege_id())));
                    usu.setUsuarioByDirector(u);
                    session.update(usu);
                    Estados e = (Estados) session.load(Estados.class, new BigDecimal(1));
                    EstadoProyecto ep = new EstadoProyecto();
                    ep = (EstadoProyecto) session.createQuery("select e from Proyectos p "
                            + " INNER JOIN p.estadoProyectos e where p.codigoProyecto=" + temp2.getCod_pro()).uniqueResult();
                    ep.setEstados(e);
                    session.update(ep);
                    r = true;
                }

            }
            t.commit();

            if (r) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("AsignarDirector.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", ""));

            }

        } catch (Exception ex) {
            if (t != null) {
                t.rollback();
            }
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
    

    }

    public ArrayList<ProyectosModelo> getPro2() {
        return pro2;
    }

    public void setPro2(ArrayList<ProyectosModelo> pro2) {
        this.pro2 = pro2;
    }

    public void mns() {
        System.out.println("entro");
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

}
