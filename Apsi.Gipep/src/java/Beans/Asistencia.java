/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.Asesoria;
import Entity.DetalleAsesoria;
import Entity.Disponibilidad;
import Entity.Persona;
import Entity.Proyectos;
import Entity.Usuario;
import Modelo.Conecion_postgres;
import Modelo.Conecion_postgres1;
import Modelo.Estudiante;
import Modelo.ModeloAsesoria;
import Modelo.Profesor;
import Modelo.Secuencia;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
 * @author Britt
 */
@ManagedBean
@SessionScoped
public class Asistencia {

    ArrayList<ModeloAsesoria> lista_ase = new ArrayList();
    ArrayList<Estudiante> lista_estu = new ArrayList();
    ArrayList<ModeloAsesoria> tareas = new ArrayList();

    private String nombre;
    private String Tarea;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private String NombreUsuario;
    private String detalle;
    private String asistencia;
    Persona p = new Persona();

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
                    + " where U.pegeId=" + p.getUsuario().getPegeId() + " and D.estado='A'").uniqueResult();
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

            p = (Persona) httpServletRequest.getSession().getAttribute("persona");
            NombreUsuario = p.getNombres() + " " + p.getApellidos();
//            System.out.println("--- " + p.toString());
            int cod_dis = TraerDispo();
            System.out.println("---------------- ");
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
                    System.out.println("Nombre proyecto: " + nom + " cod_ase : " + temp.getCodAsesoria().intValue());
                    ModeloAsesoria m = new ModeloAsesoria(temp.getCodAsesoria().intValue(),
                            nom, temp.getFechaAsesoria());
//                m.setCodigo_ase(temp.getCodigoProyecto().intValue());
                    System.out.println("----> " + m.toString());
                    lista_ase.add(m);

                }
                System.out.println("size " + lista_ase.size());
                t.commit();
            } catch (Exception ex) {
                System.out.println("Error " + ex.toString());
            }
////            httpServletRequest.getAttribute("user");
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
        Date fechaHoy = new Date();
        System.out.println("fecha " + a.getFecha());
        if (fechaHoy.getMonth() == a.getFecha().getMonth() && a.getFecha().getDay() <= fechaHoy.getDay()) {
            System.out.println("entrooo recojerDato " + a.getNombre_proyecto());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ModeloASesoria", a);
            FacesContext.getCurrentInstance().getExternalContext().redirect("AsistenciaGRupoTrabajo.xhtml");
        } else {
            System.out.println("Fechas malas");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Todabia no ha pasado la fecha de la Asesoria", ""));

        }

    }

    public void cargarDatosEstudiante() {
        System.out.println("----------------------");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        ModeloAsesoria M = new ModeloAsesoria();
        ArrayList<Usuario> p = new ArrayList();
        M = (ModeloAsesoria) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ModeloASesoria");
        System.out.println("--- cod: " + M.getCodigo_ase());
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("lista_estudiante") != null) {
            lista_estu = (ArrayList) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("lista_estudiante");
            for (int i = 0; i < lista_estu.size(); i++) {
                System.out.println("-- ::: " + lista_estu.get(i));
            }
        } else {
            lista_estu.clear();

            try {
                p = (ArrayList) session.createQuery("select U from Usuario U INNER JOIN "
                        + "U.asistentes A INNER JOIN A.asesoria S"
                        + " where S.codAsesoria=" + M.getCodigo_ase()).list();
                t.commit();
                Usuario temp = null;
                for (int i = 0; i < p.size(); i++) {
                    temp = (Usuario) p.get(i);
                    System.out.println("entro pege " + temp.getPegeId());

                    lista_estu.add(new Estudiante(false, "", "", "", "" + temp.getPegeId()));
                }
                TraerDatosPersonales();
                for (int i = 0; i < lista_estu.size(); i++) {
                    System.out.println("-- " + lista_estu.get(i));
                }
            } catch (Exception ex) {
                System.out.println("Error CargarDatoEstudiante " + ex.toString());
            }
        }

    }

    public void añadir_tarea() {
        System.out.println("Entro a añadir");
        if (Tarea.equalsIgnoreCase("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No hay tarea ingresada", ""));
        } else {
            System.out.println("lista Estu :  " + lista_estu);
            tareas.add(new ModeloAsesoria(nombre, Tarea));
            if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("lista_estudiante") != null) {
                System.out.println("Existe");
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("lista_estudiante");
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("lista_estudiante", lista_estu);
            } else {
                System.out.println("No existe");
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("lista_estudiante", lista_estu);

            }
//            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("lista_estudiante", lista_estu);

        }
        Tarea = "";
    }

    public void activarEstudiante(String cedula) {
        System.out.println("Entro activar");
        System.out.println("entroo");
        Estudiante temp = null;
        for (int i = 0; i < lista_estu.size(); i++) {
            temp = (Estudiante) lista_estu.get(i);
            if (temp.getCedula().equalsIgnoreCase(cedula)) {
                System.out.println(asistencia);
                temp.setEstado(asistencia);
            }
        }
        for (int i = 0; i < lista_estu.size(); i++) {
            System.out.println("---  " + lista_estu.get(i).getEstado());
        }
        asistencia = "";
    }

    public void TraerDatosPersonales() throws ClassNotFoundException {
        Estudiante temp = null;
        Conecion_postgres.conectar();
        try {
            for (int i = 0; i < lista_estu.size(); i++) {
                temp = (Estudiante) lista_estu.get(i);
                System.out.println("buscando pege_id: " + temp.getPege_id());
                Conecion_postgres.ejecuteQuery("select * from estudiante where pege_id=" + temp.getPege_id());
                while (Conecion_postgres.rs.next()) {
                    temp.setCedula(Conecion_postgres.rs.getString(5));
                    temp.setNombre(Conecion_postgres.rs.getString(2));
                    temp.setApellido(Conecion_postgres.rs.getString(3));
                }

            }
            Conecion_postgres.cerrarConexion();
        } catch (Exception ex) {
            System.out.println("Error traerDatos " + ex.toString());
        }

    }

    public void guardarAsistencia() throws ClassNotFoundException {
        boolean r = false;
        System.out.println("1");
        try {
            ModeloAsesoria M = new ModeloAsesoria();
            M = (ModeloAsesoria) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ModeloASesoria");
            int codigo_dispo = traerDispo(M.getCodigo_ase());

            Estudiante temp = null;
            for (int i = 0; i < lista_estu.size(); i++) {
                temp = (Estudiante) lista_estu.get(i);
                System.out.println("1 ----- " + temp.isActivo());
//                if (temp.isActivo()) {
                System.out.println("1.1 ---");
                r = true;
                Conecion_postgres1.conectar();
                Conecion_postgres1.ejecuteUpdate("update asistente set asistencia='Asistio' where cod_asesoria=" + M.getCodigo_ase());
                Conecion_postgres1.cerrarConexion();
//                }
            }
            if (r) {
                System.out.println("+++++ detalle: " + detalle);
                if (detalle.equals("")) {
                    System.out.println("2");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite un detalle de la asesoria", ""));
                } else {
                    System.out.println("3");
                    System.out.println("Entro a guardar asistencia");

                    int cod_detalle = Secuencia.seque("select max(cod_detalle) from detalle_asesoria");
                    int cod_tarea = Secuencia.seque("select max(cod_tarea) from tareas");
                    System.out.println("cod detalle " + cod_detalle + " - cod tarea " + cod_tarea);
                    Conecion_postgres1.conectar();
                    Conecion_postgres1.ejecuteUpdate("insert into detalle_asesoria values(" + cod_detalle + ",'" + detalle + "'," + M.getCodigo_ase() + ")");
                    ModeloAsesoria temp2 = null;
                    for (int i = 0; i < tareas.size(); i++) {
                        temp2 = (ModeloAsesoria) tareas.get(i);
                        Conecion_postgres1.ejecuteUpdate("insert into tareas values(" + cod_tarea + ",'" + temp2.getResponsable() + "','" + temp2.getTarea() + "'," + M.getCodigo_ase() + ")");
                        cod_tarea++;
                    }
                    Conecion_postgres1.ejecuteUpdate("update disponibilidad set horas_cumplidas=horas_cumplidas+1 where cod_dis=" + codigo_dispo);
                }
                Conecion_postgres1.cerrarConexion();

                FacesContext.getCurrentInstance().getExternalContext().redirect("AsistenciaProyectos.xhtml");
            } else {
                System.out.println("4");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No ah selecionado ningun estudiante", ""));
            }
            r = false;
        } catch (Exception ex) {
            System.out.println("Error guardarASistencia " + ex.toString());
        }

    }

    public int traerDispo(int cod_ase) throws ClassNotFoundException {
        Conecion_postgres1.conectar();
        Conecion_postgres1.ejecuteQuery("select * from disponibilidad,asesoria "
                + "where disponibilidad.cod_dis=asesoria.cod_dis and "
                + "asesoria.cod_asesoria=" + cod_ase);
        int cod = 0;
        try {
            while (Conecion_postgres1.rs.next()) {
                cod = Conecion_postgres1.rs.getInt(1);
            }
        } catch (Exception ex) {

        } finally {
            Conecion_postgres1.cerrarConexion();
        }
        return cod;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTarea() {
        return Tarea;
    }

    public void setTarea(String Tarea) {
        this.Tarea = Tarea;
    }

    public ArrayList<ModeloAsesoria> getTareas() {
        return tareas;
    }

    public void setTareas(ArrayList<ModeloAsesoria> tareas) {
        this.tareas = tareas;
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

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

}
