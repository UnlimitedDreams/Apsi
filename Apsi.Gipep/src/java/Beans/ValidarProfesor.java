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
import Modelo.Profesor;
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

    public ValidarProfesor() {

    }

    public void cargar_facultad() throws ClassNotFoundException, IOException {
//*************************************************************
         faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        if (httpServletRequest.getSession().getAttribute("user") != null) {
             faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        if (httpServletRequest.getSession().getAttribute("user") != null) {
            System.out.println("Existe");

            Persona p = (Persona) httpServletRequest.getSession().getAttribute("persona");
            NombreUsuario = p.getNombres() + " " + p.getApellidos();
//            System.out.println("--- " + p.toString());
            facul.clear();
            Conecion_postgres.conectar();
            Conecion_postgres.ejecuteQuery("select distinct facultad from profesor");
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
            System.out.println("Existe");

            Persona p = (Persona) httpServletRequest.getSession().getAttribute("persona");
            NombreUsuario = p.getNombres() + " " + p.getApellidos();
//            System.out.println("--- " + p.toString());
          
        } else {
            System.out.println("No existe");
            FacesContext.getCurrentInstance().getExternalContext().redirect("../logIn/index.jsp");

        }
        
        
       
    }

    public DispoUsuario comprarProfesor(String pege) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        System.out.println("entro a comprobar pege " + pege);
        DispoUsuario temp = new DispoUsuario();
        try {
            temp = (DispoUsuario) session.createQuery("select Dis from Disponibilidad"
                    + "  D INNER JOIN D.dispoUsuarios Dis "
                    + " INNER JOIN Dis.usuarioByProfesor U where "
                    + "  U.pegeId=" + pege + " and D.estado='A'").uniqueResult();
//            System.out.println("temp " + temp.toString());
        } catch (Exception ex) {
            System.out.println("Error comprobar profe" + ex.toString());
        }
        return temp;
    }

    public void cargarProfesores() throws ClassNotFoundException {
        System.out.println("----");
        profe.clear();
        Conecion_postgres.conectar();
        Conecion_postgres.ejecuteQuery("select * from profesor "
                + " where facultad='" + facultad + "'");
        try {
            while (Conecion_postgres.rs.next()) {
                if (Conecion_postgres.rs.getString(4) == null) {
                    System.out.println("opcion 1");
                    profe.add(new Profesor(false, Conecion_postgres.rs.getString(2), Conecion_postgres.rs.getString(3),
                            Conecion_postgres.rs.getString(5), Conecion_postgres.rs.getString(1)));
                } else {
                    System.out.println("opcion 2");
                    profe.add(new Profesor(false, Conecion_postgres.rs.getString(5), Conecion_postgres.rs.getString(2),
                            Conecion_postgres.rs.getString(3), Conecion_postgres.rs.getString(1)));
                    System.out.println("++ " + profe.toString());
                }
            }
            if (profe.size() == 0) {
                System.out.println("no tiene profesores");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "La Facultad no Tiene Profesores", ""));
            }
            Conecion_postgres.cerrarConexion();
        } catch (Exception ex) {

        }
    }

    public void cargarProfeII() throws ClassNotFoundException, SQLException, IOException {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        if (httpServletRequest.getSession().getAttribute("user") != null) {
            System.out.println("Existe");

            Persona p = (Persona) httpServletRequest.getSession().getAttribute("persona");
            NombreUsuario = p.getNombres() + " " + p.getApellidos();
//            System.out.println("--- " + p.toString());
            profe.clear();
            System.out.println("Entro a inicio");
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction t = session.beginTransaction();
            Usuario admon2 = new Usuario();
            admon2.setPegeId(new BigDecimal(1));
            ArrayList<DispoUsuario> users = new ArrayList();
            users = (ArrayList) session.createQuery("select Du from DispoUsuario Du INNER JOIN Du.usuarioByAdmon UA "
                    + "INNER JOIN Du.disponibilidad D where UA=1 and D.estado='A'").list();
            System.out.println("------------------ " + users.size());
            TraerInfoProfe2(users);
        } else {
            System.out.println("No existe");
            FacesContext.getCurrentInstance().getExternalContext().redirect("../logIn/index.jsp");

        }

    }

    public void TraerInfoProfe2(ArrayList users) throws ClassNotFoundException, SQLException {
        Conecion_postgres.conectar();
        System.out.println("entro a buscar Profe " + users.size());
        DispoUsuario usu = new DispoUsuario();
        System.out.println("1");
        for (int i = 0; i < users.size(); i++) {
            System.out.println("2");
            usu = (DispoUsuario) users.get(i);
            System.out.println("+++++++++++++ " + usu.getUsuarioByProfesor().getPegeId());
            Conecion_postgres.ejecuteQuery("select * from profesor where pege_id="
                    + usu.getUsuarioByProfesor().getPegeId());
            System.out.println("3");
            try {
                while (Conecion_postgres.rs.next()) {
                    if (Conecion_postgres.rs.getString(4) == null) {
                        System.out.println("opcion 1");
                        profe.add(new Profesor(false, Conecion_postgres.rs.getString(2), Conecion_postgres.rs.getString(3),
                                Conecion_postgres.rs.getString(5), Conecion_postgres.rs.getString(1)));
                    } else {
                        System.out.println("opcion 2");
                        profe.add(new Profesor(false, Conecion_postgres.rs.getString(5), Conecion_postgres.rs.getString(2),
                                Conecion_postgres.rs.getString(3), Conecion_postgres.rs.getString(1)));
                        System.out.println("++ " + profe.toString());
                    }
                }

            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
        Conecion_postgres.cerrarConexion();
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

    public void ajusteProfe(int cedula, int condi) throws IOException {
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
                FacesContext.getCurrentInstance().getExternalContext().redirect("AsignarDirectorProyectos.xhtml");
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

}
