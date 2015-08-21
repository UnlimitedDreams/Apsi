/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.EstadoProyecto;
import Entity.Estados;
import Entity.Persona;
import Entity.Proyectos;
import Entity.Usuario;
import Entity.UsuarioProyecto;
import Modelo.Conecion_postgres1;
import Modelo.Profesor;
import Modelo.ProyectosModelo;
import Modelo.Secuencia;
import java.io.IOException;
import java.math.BigDecimal;
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
 * @author Microinformatica
 */
@ManagedBean
@SessionScoped
public class AsiganarDirector {

    ArrayList<ProyectosModelo> pro2 = new ArrayList();
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private String NombreUsuario;

    public AsiganarDirector() {
    }

    public void cargar_Proyectos() throws IOException {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        if (httpServletRequest.getSession().getAttribute("user") != null) {
            System.out.println("Existe");
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
                    System.out.println("codigoooooo " + temp2.getCodigoProyecto());
                    pro2.add(new ProyectosModelo(temp2.getCodigoProyecto().intValue(), temp2.getNombre(), false));
                }
                System.out.println("---- " + pro2.size());
            } catch (Exception ex) {
                System.out.println("Error " + ex.toString());
            }
            Persona p = (Persona) httpServletRequest.getSession().getAttribute("persona");
            NombreUsuario = p.getNombres() + " " + p.getApellidos();
//            System.out.println("--- " + p.toString());

        } else {
            System.out.println("No existe");
            FacesContext.getCurrentInstance().getExternalContext().redirect("../logIn/index.jsp");

        }

    }

    public void pasar_Proyectos(int x) {
        ProyectosModelo temp = null;
        System.out.println("codigooo " + x);
        for (int i = 0; i < pro2.size(); i++) {
            temp = (ProyectosModelo) pro2.get(i);
            System.out.println("cod " + temp.getCod_pro());
            if (temp.getCod() == x) {
                System.out.println("entro");
                if (temp.isEstado() == false) {
                    System.out.println("quedo true");
                    temp.setEstado(true);
                } else {
                    temp.setEstado(false);
                    System.out.println("quedo false");
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
            Proyectos proyec = null;
            for (int i = 0; i < pro2.size(); i++) {
                temp2 = (ProyectosModelo) pro2.get(i);
                if (temp2.isEstado() == true) {
                    Conecion_postgres1.conectar();
                    r = Conecion_postgres1.ejecuteUpdate("update usuario_proyecto set "
                            + "director=" + p.getPege_id() + " where codigo_proyecto=" + temp2.getCod_pro());
                    Conecion_postgres1.cerrarConexion();
                    buscarProyecto(temp2.getCod_pro());
                }

            }
            t.commit();

            if (r) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("AsignarDirector.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", ""));

            }

        } catch (Exception ex) {

        }
    }

    public void buscarProyecto(int cod) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        EstadoProyecto ep = new EstadoProyecto();
        Estados e = new Estados();
        e.setCodigoEstados(new BigDecimal(1));
        e.setDescripcion("Activo");
        System.out.println("codigo Proyecto " + cod);
        try {
            ep = (EstadoProyecto) session.createQuery("select e from Proyectos p INNER JOIN p.estadoProyectos e where p.codigoProyecto=" + cod).uniqueResult();
            ep.setEstados(e);
            session.update("EstadoProyecto", ep);
            t.commit();
        } catch (Exception ex) {
            System.out.println("Error Actualizando estado " + ex.toString());
        }
    }

//    public void establecerEstado(int codi) throws ClassNotFoundException {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction t = session.beginTransaction();
//        Proyectos p = new Proyectos();
//        p = buscarProyecto(codi);
//        Estados e = new Estados();
//        e.setCodigoEstados(new BigDecimal(1));
//        e.setDescripcion("Activo");
//        EstadoProyecto ep = new EstadoProyecto();
//        int cod = Secuencia.seque("select max(cod_estadoproyec) from estado_proyecto");
//        ep.setCodEstadoproyec(new BigDecimal(cod));
//        ep.setEstados(e);
//        ep.setProyectos(p);
//        session.save(ep);
//        t.commit();
//        try {
//
//        } catch (Exception ex) {
//            System.out.println("Error estado " + ex.toString());
//        }
//    }
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

    public void mns() {
        System.out.println("entro");
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

}
