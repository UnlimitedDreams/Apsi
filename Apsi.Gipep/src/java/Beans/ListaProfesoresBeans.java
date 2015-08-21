/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.Asesoria;
import Entity.Dia;
import Entity.DispoUsuario;
import Entity.Disponibilidad;
import Entity.Persona;
import Entity.Usuario;
import Modelo.Conecion_Oracle;
import Modelo.Conecion_postgres;
import Modelo.Profesor;
import com.sun.faces.spi.FacesConfigResourceProvider;
import dao.ProfesoresDao;
import dao.ProfesoresImple;
import java.io.IOException;
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
public class ListaProfesoresBeans {

    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private String NombreUsuario;

    private String año;
    private String periodo;
    ArrayList<String> b_año = new ArrayList();
    ArrayList<String> peri = new ArrayList();
    ArrayList<Profesor> profe = new ArrayList();
    ArrayList<Usuario> Pege_idProfe = new ArrayList();

    public ListaProfesoresBeans() {
    }

    public void cargar_años() throws IOException {//*************************************************************
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        if (httpServletRequest.getSession().getAttribute("user") != null) {
            System.out.println("entro a cargar");
            b_año.clear();
            for (int i = 2015; i < 2030; i++) {
                b_año.add("" + i);
            }
            cargar_Periodo();
            System.out.println("Existe");

            Persona p = (Persona) httpServletRequest.getSession().getAttribute("persona");
            NombreUsuario = p.getNombres() + " " + p.getApellidos();
//            System.out.println("--- " + p.toString());

        } else {
            System.out.println("No existe");
            FacesContext.getCurrentInstance().getExternalContext().redirect("../logIn/index.jsp");

        }

    }

    public void cargar_Periodo() {
        System.out.println("entro a cargar");
        peri.clear();
        for (int i = 1; i <= 2; i++) {
            peri.add("" + i);
        }
    }

    public void cargar_profesores() {
        String fechaI = "", FechaF = "";
        fechaI = "01-01-" + año;
        FechaF = "31-12-" + año;
        ArrayList<DispoUsuario> codigos = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Pege_idProfe.clear();
        profe.clear();
        System.out.println("periodo " + periodo + " año " + año + "-fecha " + fechaI);
        try {
            Pege_idProfe = (ArrayList) session.createQuery("select UD.usuarioByProfesor from Disponibilidad D "
                    + " INNER JOIN D.dispoUsuarios UD "
                    + " INNER JOIN UD.usuarioByAdmon Usu WHERE Usu.pegeId=1 and D.periodo=" + periodo + " "
                    + " and D.fechaInicial>='" + fechaI + "' and  D.fechaFinal<='" + FechaF + "' and"
                    + " D.estado='A'").list();
            t.commit();
            System.out.println("profess pege_id " + Pege_idProfe.size());
            for (int i = 0; i < Pege_idProfe.size(); i++) {
                System.out.println(Pege_idProfe.get(i).getPegeId());
                traer_profesor("" + Pege_idProfe.get(i).getPegeId());
            }
            System.out.println("size profe " + profe.size());
            FacesContext.getCurrentInstance().getExternalContext().responseReset();
//           
        } catch (Exception ex) {
            System.out.println("Error Hiber" + ex.toString());
        }
//        
    }

    public void traer_profesor(String x) throws ClassNotFoundException, SQLException {
        Conecion_postgres.conectar();
        Conecion_postgres.ejecuteQuery("select * from profesor where pege_id=" + x);

        try {
            while (Conecion_postgres.rs.next()) {
                profe.add(new Profesor(false, Conecion_postgres.rs.getString(5), Conecion_postgres.rs.getString(2) + " "
                        + Conecion_postgres.rs.getString(2),
                        Conecion_postgres.rs.getString(3), Conecion_postgres.rs.getString(1)));
            }
            Conecion_postgres.cerrarConexion();
        } catch (Exception ex) {
            System.out.println("Error Profe" + ex.toString());
        }
    }
//
//    public void traer_profesor(String x) throws ClassNotFoundException {
//        Conecion_Oracle.conectar();
//        Conecion_Oracle.ejecuteQuery("select DISTINCT pege.PEGE_ID,pege.pege_documentoidentidad,peng.peng_primernombre,peng.PENG_SEGUNDONOMBRE,\n"
//                + "peng.peng_primerapellido\n"
//                + "from ACADEMICO.DocenteGrupo Dg ,academico.DocenteUnidad Du ,academico.Unidad Un,\n"
//                + "academico.personageneral pege, academico.PERSONANATURALGENERAL peng\n"
//                + "where Dg.DOUN_ID=Du.DOUN_ID\n"
//                + "and Un.unid_id=Du.UNID_ID\n"
//                + "and Du.PEGE_ID=pege.PEGE_ID\n"
//                + "AND pege.pege_id = peng.pege_id\n"
//                + "and pege.pege_id='" + x + "'\n"
//                + "group by\n"
//                + "pege.PEGE_ID,pege.pege_documentoidentidad,peng.peng_primernombre,peng.PENG_SEGUNDONOMBRE,\n"
//                + "peng.peng_primerapellido");
//        try {
//            while (Conecion_Oracle.rs.next()) {
//                System.out.println("trajo");
//                if (Conecion_Oracle.rs.getString(4) == null) {
//                    profe.add(new Profesor(false, Conecion_Oracle.rs.getString(2), Conecion_Oracle.rs.getString(3),
//                            Conecion_Oracle.rs.getString(5), Conecion_Oracle.rs.getString(1)));
//
//                } else {
//                    profe.add(new Profesor(false, Conecion_Oracle.rs.getString(2), Conecion_Oracle.rs.getString(3) + " "
//                            + Conecion_Oracle.rs.getString(4),
//                            Conecion_Oracle.rs.getString(5), Conecion_Oracle.rs.getString(1)));
//                }
//
//            }
//        } catch (Exception ex) {
//
//        }
//
//    }

    public void delete(String pege_id) {
        Disponibilidad d = new Disponibilidad();
        d = cod_dispoProfe(pege_id);
        ArrayList n = null;
        n = BuscarDispoAsesoria(d);
        System.out.println("Entroo delete  pege " + pege_id + " dispo " + d.getCodDis());
        if (n.size() > 0) {
            System.out.println("entro opcion 1");
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction t = session.beginTransaction();
            try {
                d.setEstado("I");
                session.update("Disponibilidad", d);
                t.commit();

            } catch (Exception ex) {
                System.out.println("Error " + ex.toString());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", ""));

            }
        } else if (n.size() == 0) {
            System.out.println("entro opcion 2");
            try {
                BorrarDispo(d);
                System.out.println("-- 1");
                borrarDias(d);
                System.out.println("-- 2");
                Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction t = session.beginTransaction();
                System.out.println("-- 3");
                session.delete("Disponibilidad", d);
                System.out.println("-- 4");
                t.commit();

            } catch (Exception ex) {
                System.out.println("Error hiber" + ex.toString());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", ""));

            }
        }
        FacesContext.getCurrentInstance().getExternalContext().responseReset();

    }

    public boolean borrarDias(Disponibilidad d) {
        boolean r = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        ArrayList<Dia> dias = new ArrayList();
        try {
            dias = (ArrayList) session.createQuery("select DI from Dia DI INNER JOIN "
                    + " DI.disponibilidad D where D.codDis=" + d.getCodDis()).list();
            Dia temp = null;
            for (int i = 0; i < dias.size(); i++) {
                temp = (Dia) dias.get(i);
                session.delete("Dia", temp);
            }
            t.commit();

            r = true;
            System.out.println("Borro dias " + r);
        } catch (Exception ex) {
            r = false;
            System.err.println("error Borrar Dias" + ex.toString());
        }
        return r;

    }

    public boolean BorrarDispo(Disponibilidad d) {
        boolean r = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        DispoUsuario dis = new DispoUsuario();
        try {
            dis = (DispoUsuario) session.createQuery("select DI from DispoUsuario DI INNER JOIN "
                    + " DI.disponibilidad D where D.codDis=" + d.getCodDis()).uniqueResult();
            session.delete("DispoUsuario", dis);
            t.commit();
            r = true;
//            session.getSessionFactory().close();
            System.out.println("borro dispo " + r);
        } catch (Exception ex) {
            r = false;
            System.out.println("Error Borrar Dispo" + ex.toString());
        }
        return r;
    }

    public ArrayList BuscarDispoAsesoria(Disponibilidad d) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        ArrayList<Asesoria> calen = new ArrayList();
        try {
            calen = (ArrayList) session.createQuery("select A from Disponibilidad D INNER JOIN "
                    + "D.asesorias A where D.codDis=" + d.getCodDis()).list();

            for (int i = 0; i < calen.size(); i++) {
                System.out.println("-" + calen.get(i).getCodigoProyecto());
            }
            t.commit();

        } catch (Exception ex) {

        }
        return calen;
    }

    public void update(String cod, String nombre) throws IOException {
        Disponibilidad d = new Disponibilidad();
        d = cod_dispoProfe(cod);
        String codigo = cod + "-" + nombre + "-" + año + "-" + periodo;
        System.out.println("- " + codigo + " se envio codigo " + d.getCodDis());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Pege_idProfe", codigo);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Dispo_profe", d);
        FacesContext.getCurrentInstance().getExternalContext().redirect("Lista_ProfesorUpdate.xhtml");
    }

    public Disponibilidad cod_dispoProfe(String x) {
        String fechaI = "", FechaF = "";
        fechaI = "01-01-" + año;
        FechaF = "31-12-" + año;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Disponibilidad d = new Disponibilidad();
        try {
            d = (Disponibilidad) session.createQuery("select D from Disponibilidad D "
                    + " INNER JOIN D.dispoUsuarios UD "
                    + " INNER JOIN UD.usuarioByProfesor Usu WHERE Usu.pegeId=" + x + " and D.periodo=" + periodo + " "
                    + " and D.fechaInicial>='" + fechaI + "' and  D.fechaFinal<='" + FechaF + "'").uniqueResult();
            t.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
        }

        return d;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public ArrayList<String> getB_año() {
        return b_año;
    }

    public void setB_año(ArrayList<String> b_año) {
        this.b_año = b_año;
    }

    public ArrayList<String> getPeri() {
        return peri;
    }

    public void setPeri(ArrayList<String> peri) {
        this.peri = peri;
    }

    public ArrayList<Profesor> getProfe() {
        return profe;
    }

    public void setProfe(ArrayList<Profesor> profe) {
        this.profe = profe;
    }

    public ArrayList<Usuario> getPege_idProfe() {
        return Pege_idProfe;
    }

    public void setPege_idProfe(ArrayList<Usuario> Pege_idProfe) {
        this.Pege_idProfe = Pege_idProfe;
    }

}
