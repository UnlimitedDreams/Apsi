package Beans;

import Entity.Proyectos;
import Entity.Revisiones;
import Modelo.Profesor;
import java.io.IOException;
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
 * @author Jeison
 */
@ManagedBean
@SessionScoped
public class Revision {

    ArrayList<Proyectos> p = new ArrayList();
    ArrayList<Revisiones> revi = new ArrayList();
    ArrayList<String> b_año = new ArrayList();
    private String año;
    private String mns;

    /**
     * Creates a new instance of Revision
     */
    public Revision() {
    }

    public ArrayList<Proyectos> getP() {
        return p;
    }

    public void setP(ArrayList<Proyectos> p) {
        this.p = p;
    }

    public void cargar_años() {
        System.out.println("entro a cargar");
        b_año.clear();
        for (int i = 2015; i < 2030; i++) {
            b_año.add("" + i);
        }
    }

////    public void revi(int codigoProyecto) throws IOException {
////        
////        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("codigoProyecto", codigoProyecto);//guardo un dato con nueva llave para recuperarla en la nueva vista
////        System.err.println("este es el codigo de la session PROY" + codigoProyecto);
////        FacesContext.getCurrentInstance().getExternalContext().redirect("vistaRevision.xhtml");
//////queri q muestre las revisiones q ha echo el profe a ese documento
////    }
////
////    public void reviGet(int codigoProyecto) throws IOException {
////        
////        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(codigoProyecto);//guardo un dato con nueva llave para recuperarla en la nueva vista
////        System.err.println("este es el codigo de la session PROY" + codigoProyecto);
////        FacesContext.getCurrentInstance().getExternalContext().redirect("vistaRevision.xhtml");
//////queri q muestre las revisiones q ha echo el profe a ese documento
////    }
    public void listaProfeProyect() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("Poryecto_Revi");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        p.clear();
        String fechaI = "", FechaF = "";
        fechaI = "01-01-" + año;
        FechaF = "31-12-" + año;
        try {
            p = (ArrayList) session.createQuery("select distinct p from Proyectos p INNER JOIN p.usuarioProyectos "
                    + "up INNER JOIN up.usuarioByDirector u where u.pegeId=6 and p.fechaInicio>='" + fechaI + "' "
                    + " and p.fechaInicio<='" + FechaF + "'").list();
            t.commit();
//            System.out.println("proyectos " + p.size() + " nombre " + p.get(0).getNombre());
            if (p.size() == 0) {
                System.out.println("no hay proyectos");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No tiene proyectos asignados", ""));

            }
        } catch (Exception ex) {
            System.out.println("error " + ex.toString());
        }
    }

    public void calificar(Proyectos p) {
        System.out.println("Proyecto " + p.getCodigoProyecto());
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction t = session.beginTransaction();
    }

    public void listaReviciones() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Proyectos p = new Proyectos();
        p = (Proyectos) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Poryecto_Revi");
        try {
            revi = (ArrayList) session.createQuery("select distinct u from Proyectos p INNER JOIN p.revisionProyectos "
                    + "up INNER JOIN up.revisiones u where p.codigoProyecto=" + p.getCodigoProyecto() + " "
                    + " order by u.fecha DESC").list();
            t.commit();

        } catch (Exception ex) {
            System.out.println("error listaReviciones " + ex.toString());
        }
    }

    public void atrasVista1() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("Poryecto_Revi");
        FacesContext.getCurrentInstance().getExternalContext().redirect("DireccionProyecto.xhtml");

    }

    public ArrayList<Revisiones> getRevi() {
        return revi;
    }

    public void setRevi(ArrayList<Revisiones> revi) {
        this.revi = revi;
    }

    public ArrayList<String> getB_año() {
        return b_año;
    }

    public void setB_año(ArrayList<String> b_año) {
        this.b_año = b_año;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getMns() {
        return mns;
    }

    public void setMns(String mns) {
        this.mns = mns;
    }

    public void pasar_mns(String mns) {
        System.out.println("entrooo a pasar");
        this.mns = mns;
    }
}
