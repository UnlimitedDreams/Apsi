/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.Proyectos;
import Modelo.Objetivo;
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
 * @author Britt
 */
@ManagedBean
@SessionScoped
public class DatosProyecto {

    private String titulo;
    private String fecha_I;
    private String fecha_f;
    ArrayList<Objetivo> list_obt = new ArrayList();
    private String porcentaje;
    private String Calificacion;

    public DatosProyecto() {
    }

    public void recuperarDatos() {
        Proyectos p = new Proyectos();
        p = (Proyectos) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("calificar");
        titulo = p.getNombre();
        fecha_I = "" + p.getFechaInicio();
        fecha_f = "" + p.getFechaFinal();
        porcentaje = p.getPorcentaje();
    }

    public void actualizar() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Proyectos p = new Proyectos();
        Proyectos Pr = new Proyectos();
        p = (Proyectos) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("calificar");
        System.out.println("proyecto " + p.getNombre());
        try {
            Pr = (Proyectos) session.createQuery("from Proyectos P where P.codigoProyecto=" + p.getCodigoProyecto()).uniqueResult();
            Pr.setCalificacion(Long.parseLong(Calificacion));
            session.update(Pr);
            t.commit();
            FacesContext.getCurrentInstance().getExternalContext().redirect("CalificarProyecto.xhtml");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error comunicarse con el administrador", ""));
            System.out.println("Error actualizar " + ex.toString());
        }

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha_I() {
        return fecha_I;
    }

    public void setFecha_I(String fecha_I) {
        this.fecha_I = fecha_I;
    }

    public String getFecha_f() {
        return fecha_f;
    }

    public void setFecha_f(String fecha_f) {
        this.fecha_f = fecha_f;
    }

    public ArrayList<Objetivo> getList_obt() {
        return list_obt;
    }

    public void setList_obt(ArrayList<Objetivo> list_obt) {
        this.list_obt = list_obt;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(String Calificacion) {
        this.Calificacion = Calificacion;
    }

}
