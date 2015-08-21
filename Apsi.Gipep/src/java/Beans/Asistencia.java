/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.Asesoria;
import Entity.Disponibilidad;
import Entity.Persona;
import Entity.Proyectos;
import Entity.Usuario;
import Modelo.Conecion_postgres;
import Modelo.Conecion_postgres1;
import Modelo.Estudiante;
import Modelo.ModeloAsesoria;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Britt
 */
@ManagedBean
@SessionScoped
public class Asistencia {

    ArrayList<ModeloAsesoria> lista_ase = new ArrayList();
    ArrayList<Estudiante> lista_estu = new ArrayList();
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private String NombreUsuario;

    public Asistencia() {
    }

    public int TraerDispo() {
        //recuperar session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Disponibilidad d = new Disponibilidad();
        try {
            d = (Disponibilidad) session.createQuery("select D from Disponibilidad D INNER JOIN "
                    + " D.dispoUsuarios DS INNER JOIN DS.usuarioByProfesor U "
                    + " where U.pegeId=6 and D.estado='A'").uniqueResult();
        } catch (Exception ex) {
            System.out.println("Error TraerDispo " + ex.toString());
        }
        return d.getCodDis().intValue();

    }

    public void cargarDatos() throws IOException {
         faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        if (httpServletRequest.getSession().getAttribute("user") != null) {
            System.out.println("Existe");

            Persona p = (Persona) httpServletRequest.getSession().getAttribute("persona");
            NombreUsuario = p.getNombres() + " " + p.getApellidos();
//            System.out.println("--- " + p.toString());
             int cod_dis = TraerDispo();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        ArrayList<Asesoria> ase = new ArrayList();
        lista_ase.clear();
        Date fecha_hoy = new Date();
        System.out.println("codigo de dispo " + cod_dis);
        try {
            ase = (ArrayList) session.createQuery("select A from Asesoria A "
                    + "  INNER JOIN A.asistentes P INNER JOIN A.disponibilidad D"
                    + " where P.asistencia='Pendiente' and D.codDis=" + cod_dis + " "
                    + " order by A.fechaAsesoria DESC").list();

            Asesoria temp = null;
            for (int i = 0; i < ase.size(); i++) {
                System.out.println("entro");
                temp = (Asesoria) ase.get(i);
                String nom = NombreProyecto(temp.getCodigoProyecto().intValue());
                ModeloAsesoria m = new ModeloAsesoria(temp.getCodAsesoria().intValue(),
                        nom, temp.getFechaAsesoria());
                m.setCodigo_ase(temp.getCodigoProyecto().intValue());
                lista_ase.add(m);
            }
            t.commit();
        } catch (Exception ex) {
            System.out.println("Error " + ex.toString());
        }
          
        } else {
            System.out.println("No existe");
            FacesContext.getCurrentInstance().getExternalContext().redirect("../logIn/index.jsp");

        }
       
    }

    public String NombreProyecto(int cod) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Proyectos p = new Proyectos();
        try {
            p = (Proyectos) session.createQuery("select p from Proyectos p where"
                    + " p.codigoProyecto=" + cod).uniqueResult();
        } catch (Exception ex) {
            System.out.println("Error NombreProyecto " + ex.toString());
        }
        return p.getNombre();
    }

    public void recojerDato(ModeloAsesoria a) throws IOException {
        System.out.println("entrooo recojerDato");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ModeloASesoria", a);
        FacesContext.getCurrentInstance().getExternalContext().redirect("AsistenciaGRupoTrabajo.xhtml");
    }

    public void mns() {
        System.out.println("entroo");
    }

    public void cargarDatosEstudiante() throws IOException {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        if (httpServletRequest.getSession().getAttribute("user") != null) {
            System.out.println("Existe");
             Persona per = (Persona) httpServletRequest.getSession().getAttribute("persona");
            NombreUsuario = per.getNombres() + " " + per.getApellidos();
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction t = session.beginTransaction();
            ModeloAsesoria M = new ModeloAsesoria();
            ArrayList<Usuario> p = new ArrayList();

            M = (ModeloAsesoria) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ModeloASesoria");
            try {
                p = (ArrayList) session.createQuery("select U from Usuario U INNER JOIN "
                        + "U.asistentes A INNER JOIN A.asesoria AS"
                        + " where AS.codAsesoria=" + M.getCodigo_ase()).list();
                t.commit();
                Usuario temp = null;
                for (int i = 0; i < p.size(); i++) {
                    temp = (Usuario) p.get(i);
                    lista_estu.add(new Estudiante(false, "", "", "", "" + temp.getPegeId()));
                }
                TraerDatosPersonales();
            } catch (Exception ex) {
                System.out.println("Error CargarDatoEstudiante " + ex.toString());
            }
           
//            System.out.println("--- " + p.toString());

        } else {
            System.out.println("No existe");
            FacesContext.getCurrentInstance().getExternalContext().redirect("../logIn/index.jsp");

        }

    }

    public void TraerDatosPersonales() throws ClassNotFoundException {

        Estudiante temp = null;
        Conecion_postgres.conectar();
        try {
            for (int i = 0; i < lista_estu.size(); i++) {
                temp = (Estudiante) lista_estu.get(i);
                Conecion_postgres.ejecuteQuery("select * from estudiante where pege_id=" + temp.getPege_id());
                temp.setCedula(Conecion_postgres.rs.getString(2));
                temp.setNombre(Conecion_postgres.rs.getString(3));
                temp.setApellido(Conecion_postgres.rs.getString(4));
            }
            Conecion_postgres.cerrarConexion();
        } catch (Exception ex) {
            System.out.println("Error traerDatos " + ex.toString());
        }

    }

    public ArrayList<ModeloAsesoria> getLista_ase() {
        return lista_ase;
    }

    public void setLista_ase(ArrayList<ModeloAsesoria> lista_ase) {
        this.lista_ase = lista_ase;
    }

    public ArrayList<Estudiante> getLista_estu() {
        return lista_estu;
    }

    public void setLista_estu(ArrayList<Estudiante> lista_estu) {
        this.lista_estu = lista_estu;
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
