package Beans;

import Entity.Persona;
import Entity.Proyectos;
import Entity.Revisiones;
import Modelo.Conecion_postgres;
import Modelo.Profesor;
import java.io.IOException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
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
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private String NombreUsuario;
    private String pege_id;

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

    public void cargar_años() throws IOException {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        if (httpServletRequest.getSession().getAttribute("user") != null) {
            System.out.println("Existe");

            Persona p = (Persona) httpServletRequest.getSession().getAttribute("persona");
            NombreUsuario = p.getNombres() + " " + p.getApellidos();
//            System.out.println("--- " + p.toString());
            pege_id=(String)httpServletRequest.getSession().getAttribute("pege_id");
            System.out.println("entro a cargar");
            b_año.clear();
            for (int i = 2015; i < 2030; i++) {
                b_año.add("" + i);
            }
////            httpServletRequest.getAttribute("user");
        } else {
            System.out.println("No existe");
            FacesContext.getCurrentInstance().getExternalContext().redirect("../logIn/index.jsp");

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
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        p.clear();
        String fechaI = "", FechaF = "";
        fechaI = "01-01-" + año;
        FechaF = "31-12-" + año;
        try {
            p = (ArrayList) session.createQuery("select distinct p from Proyectos p INNER JOIN p.usuarioProyectos "
                    + "up INNER JOIN up.usuarioByDirector u where u.pegeId="+pege_id+" and p.fechaInicio>='" + fechaI + "' "
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
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
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

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public FacesContext getFaceContext() {
        return faceContext;
    }

    public void setFaceContext(FacesContext faceContext) {
        this.faceContext = faceContext;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public String getPege_id() {
        return pege_id;
    }

    public void setPege_id(String pege_id) {
        this.pege_id = pege_id;
    }
    
}
