/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.DispoUsuario;
import Entity.Persona;
import Entity.Proyectos;
import Entity.Usuario;
import Modelo.Conecion_Oracle;
import Modelo.Conecion_postgres;
import Modelo.Conecion_postgres1;
import Modelo.Profesor;
import apsi.Security.ingresar;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
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
public class ValidarProfesor implements Serializable {

    ArrayList<Profesor> profe = new ArrayList();
    private String facultad;
    ArrayList<String> facul = new ArrayList();
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private String NombreUsuario;
    Persona p = new Persona();

    public ValidarProfesor() {

    }

    public void cargar_facultad() throws ClassNotFoundException, IOException {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        if (httpServletRequest.getSession().getAttribute("user") != null) {
            System.out.println("Existe");
            
            
////            FacesContext.getCurrentInstance().getExternalContext().responseReset();
             p = (Persona) httpServletRequest.getSession().getAttribute("persona");
            NombreUsuario = p.getNombres() + " " + p.getApellidos();
//            System.out.println("--- " + p.toString());
            facul.clear();
            profe.clear();
            Conecion_postgres.conectar();
            Conecion_postgres.ejecuteQuery("select distinct nombre from facultad");
            try {
                while (Conecion_postgres.rs.next()) {
                    facul.add(Conecion_postgres.rs.getString(1));
                }
                Conecion_postgres.cerrarConexion();

            } catch (Exception ex) {
                System.out.println("Error " + ex.toString());
            }
////            httpServletRequest.getAttribute("user");
        } else {
            System.out.println("No existe");
            FacesContext.getCurrentInstance().getExternalContext().redirect("../logIn/index.jsp");

        }
    }

    public DispoUsuario comprarProfesor(String pege) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        System.out.println("entro a comprobar pege " + pege);
        DispoUsuario temp = new DispoUsuario();
        try {
            temp = (DispoUsuario) session.createQuery("select Dis from Disponibilidad"
                    + "  D INNER JOIN D.dispoUsuarios Dis "
                    + " INNER JOIN Dis.usuarioByProfesor U where "
                    + "  U.pegeId=" + pege + " and D.estado='A'").uniqueResult();
//            System.out.println("temp " + temp.toString());
            t.commit();
        } catch (Exception ex) {
            System.out.println("Error comprobar profe" + ex.toString());
        }
        return temp;
    }

    public void cargarProfesores() throws ClassNotFoundException {
        System.out.println("----");
        profe.clear();
        Conecion_postgres.conectar();
        Conecion_postgres.ejecuteQuery("select profesor.* from profesor,facultad where profesor.tipo_carrera=facultad.cod "
                + " and facultad.nombre='" + facultad + "'");
        try {
            while (Conecion_postgres.rs.next()) {
                if (Conecion_postgres.rs.getString(4) == null) {
                    System.out.println("opcion 1");
                    profe.add(new Profesor(false, Conecion_postgres.rs.getString(2), Conecion_postgres.rs.getString(3),
                            Conecion_postgres.rs.getString(5), Conecion_postgres.rs.getString(1)));
                } else {
                    System.out.println("opcion 2");
                    profe.add(new Profesor(false, Conecion_postgres.rs.getString(2), Conecion_postgres.rs.getString(3),
                            Conecion_postgres.rs.getString(4), Conecion_postgres.rs.getString(1)));
                    System.out.println("++ " + profe.toString());
                }
            }
            if (profe.size() == 0) {
                System.out.println("no tiene profesores");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "La Facultad no Tiene Profesores", ""));
            }
            Conecion_postgres.cerrarConexion();
            TraerProyectosAsignados();
            SaberDisponibildiad();
        } catch (Exception ex) {
            System.out.println("Error " + ex.toString());
        }
    }
//

    public void SaberDisponibildiad() throws ClassNotFoundException {
        Profesor temp = null;
        Conecion_postgres1.conectar();
        int cant = 0;
        String esta = "No";
        System.out.println("comenzo");
        try {
            for (int i = 0; i < profe.size(); i++) {
                temp = (Profesor) profe.get(i);
                Conecion_postgres1.ejecuteQuery("select * from dispo_usuario,usuario\n"
                        + "where \n"
                        + "dispo_usuario.profesor=usuario.pege_id and usuario.pege_id=" + temp.getPege_id());
                while (Conecion_postgres1.rs.next()) {
                    System.out.println("------------------");
                    esta = "Si";
                }
                temp.setDispo(esta);
                esta = "No";
            }
            Conecion_postgres1.cerrarConexion();

        } catch (Exception ex) {
            System.out.println("Error TraerProyectos " + ex.toString());
        }
    }

    public void TraerProyectosAsignados() throws ClassNotFoundException, SQLException {
        Profesor temp = null;
        Conecion_postgres1.conectar();
        int cant = 0;
        try {
            for (int i = 0; i < profe.size(); i++) {
                temp = (Profesor) profe.get(i);
                Conecion_postgres1.ejecuteQuery("select count(usuario_proyecto.director) from usuario_proyecto,usuario\n"
                        + "where \n"
                        + "usuario_proyecto.director=usuario.pege_id\n"
                        + "and usuario.pege_id=" + temp.getPege_id());
                while (Conecion_postgres1.rs.next()) {
                    cant = Conecion_postgres1.rs.getInt(1);
                }
                System.out.println("trajo --- " + cant);
                temp.setNum_proyectos(cant);
            }
            Conecion_postgres1.cerrarConexion();

        } catch (Exception ex) {
            System.out.println("Error TraerProyectos " + ex.toString());
        }

    }

    public String cargarProfeII(String pege_id) throws ClassNotFoundException, SQLException {
        profe.clear();
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction t = session.beginTransaction();
        String users = "";
        System.out.println("pege_id " + pege_id);
        try {
            Conecion_postgres1.conectar();
            Conecion_postgres1.ejecuteQuery("select count(usuario_proyecto.codigo_proyecto) from usuario_proyecto,usuario\n"
                    + " where\n"
                    + " usuario_proyecto.director = usuario.pege_id\n"
                    + " and usuario.pege_id=" + pege_id); //            Usuario admon2 = new Usuario();
            //            admon2.setPegeId(new BigDecimal(1));
            ////        ArrayList<Proyectos> users = new ArrayList();
            //            users = (long) session.createQuery("select count(U.proyectos)  from UsuarioProyecto U INNER JOIN U.proyectos.codigoProyecto TU\n"
            //                    + "where U.usuarioByDirector.pegeId=" + pege_id).uniqueResult();
            while (Conecion_postgres1.rs.next()) {
                System.out.println("---");
                users = Conecion_postgres1.rs.getString(1);
            }
            Conecion_postgres1.cerrarConexion();
        } catch (Exception ex) {
            System.out.println("error " + ex.toString());
        }

        return users;
    }

    public void traerInfoProfe2() throws ClassNotFoundException, SQLException {
        Conecion_postgres.conectar();
        System.out.println("1");
        Conecion_postgres.ejecuteQuery("select * from profesor");
        System.out.println("3");
        profe.clear();
        try {
            while (Conecion_postgres.rs.next()) {
                if (Conecion_postgres.rs.getString(4) == null) {
                    System.out.println("opcion 1");
                    profe.add(new Profesor(false, Conecion_postgres.rs.getString(2), Conecion_postgres.rs.getString(3),
                            Conecion_postgres.rs.getString(5), Conecion_postgres.rs.getString(1)));
                } else {
                    profe.add(new Profesor(false, Conecion_postgres.rs.getString(2), Conecion_postgres.rs.getString(3),
                            Conecion_postgres.rs.getString(4), Conecion_postgres.rs.getString(1)));
                    System.out.println("++ " + profe.toString());
                }
            }
            Conecion_postgres.cerrarConexion();
            Profesor temp = null;
            String num = "0";
            System.out.println("tam " + profe.size());
            for (int i = 0; i < profe.size(); i++) {
                System.out.println("--");
                temp = (Profesor) profe.get(i);
                num = cargarProfeII(temp.getPege_id());
                System.out.println("num " + num);
                temp.setNum_proyectos(Integer.parseInt(num));
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }

    }

    public void TraerInfoProfe(ArrayList users) throws ClassNotFoundException, SQLException {
        Conecion_Oracle.conectar();
        Usuario usu = null;
        for (int i = 0; i < users.size(); i++) {
            usu = (Usuario) users.get(i);
            System.out.println("---------------------- " + usu.getPegeId());
            Conecion_Oracle.ejecuteQuery("select DISTINCT pege.PEGE_ID,pege.pege_documentoidentidad,peng.peng_primernombre,peng.PENG_SEGUNDONOMBRE,\n"
                    + "peng.peng_primerapellido\n"
                    + "from ACADEMICO.DocenteGrupo Dg ,academico.DocenteUnidad Du ,academico.Unidad Un,\n"
                    + "academico.personageneral pege, academico.PERSONANATURALGENERAL peng\n"
                    + "where Dg.DOUN_ID=Du.DOUN_ID\n"
                    + "and Un.unid_id=Du.UNID_ID\n"
                    + "and Du.PEGE_ID=pege.PEGE_ID\n"
                    + "AND pege.pege_id = peng.pege_id\n"
                    + " and pege.pege_id=" + 31168
                    + "group by\n"
                    + "pege.PEGE_ID,pege.pege_documentoidentidad,peng.peng_primernombre,peng.PENG_SEGUNDONOMBRE,\n"
                    + "peng.peng_primerapellido"
            );

            try {
                while (Conecion_Oracle.rs.next()) {
                    if (Conecion_Oracle.rs.getString(4) == null) {
                        profe.add(new Profesor(false, Conecion_Oracle.rs.getString(2), Conecion_Oracle.rs.getString(3),
                                Conecion_Oracle.rs.getString(5), Conecion_Oracle.rs.getString(1)));
                    } else {
                        profe.add(new Profesor(false, Conecion_Oracle.rs.getString(2), Conecion_Oracle.rs.getString(3) + " "
                                + Conecion_Oracle.rs.getString(4),
                                Conecion_Oracle.rs.getString(5), Conecion_Oracle.rs.getString(1)));
                    }

                }

            } catch (Exception ex) {

            }
        }
        Conecion_Oracle.rs.close();
    }

    public void ajusteProfe(int cedula, int condi, int proyectos) throws IOException {
        System.out.println("hola " + cedula);
        Profesor temp = null;
        Profesor temp2 = null;
        for (int i = 0; i < profe.size(); i++) {
            temp = (Profesor) profe.get(i);
            if (temp.getCedula().equalsIgnoreCase("" + cedula)) {
                DispoUsuario dispo = null;
                if (condi == 1) {
                    dispo = comprarProfesor(temp.getPege_id());
                    if (dispo != null) {
                        System.out.println("Si existe Dispo");
                        break;
                    } else {
                        System.out.println("No existe Dispo");
                        temp2 = temp;
                        break;
                    }
                } else if (condi == 2) {

                    temp2 = temp;
                    break;

                }

            }
        }
//        System.out.println("- " + temp2.toString());
        if (temp2 != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("profesor", temp2);
            if (condi == 1) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("AjusteProfesor.xhtml");
            } else if (condi == 2) {
                if (proyectos > 3) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("profesor");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No puede tener asignados mas de 3 proyectos", ""));
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("AsignarDirectorProyectos.xhtml");
                }
            }
        } else {
            System.out.println("+++++");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya se encuentra Registrado", ""));
        }
    }

    public void validarProyecto(Proyectos p, int condi) throws IOException {
        System.out.println("Entro " + p.getCodigoProyecto());
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Poryecto_Revi", p);
            if (condi == 1) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("PROVistaRevision-1.2.xhtml");

            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("PROVistaVersiones.xhtml");

            }
        } catch (Exception ex) {
            System.out.println("Error ValidarProyecto " + ex.toString());
        }

    }

    public void cerrarSesion() throws IOException {
        ingresar i = new ingresar();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        httpServletRequest.getSession().invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect("../logIn/index.jsp");

    }

    public ArrayList<Profesor> getProfe() {
        return profe;
    }

    public void setProfe(ArrayList<Profesor> profe) {
        this.profe = profe;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public ArrayList<String> getFacul() {
        return facul;
    }

    public void setFacul(ArrayList<String> facul) {
        this.facul = facul;
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

    public Persona getP() {
        return p;
    }

    public void setP(Persona p) {
        this.p = p;
    }

}
