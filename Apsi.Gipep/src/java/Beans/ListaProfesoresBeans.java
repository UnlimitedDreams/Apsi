/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.DispoUsuario;
import Entity.Usuario;
import Modelo.Conecion_Oracle;
import Modelo.Profesor;
import dao.ProfesoresDao;
import dao.ProfesoresImple;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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

    private String año;
    private String periodo;
    ArrayList<String> b_año = new ArrayList();
    ArrayList<String> peri = new ArrayList();
    ArrayList<Profesor> profe = new ArrayList();
    ArrayList<Usuario> Pege_idProfe = new ArrayList();

    public ListaProfesoresBeans() {
    }

    public void cargar_años() {
        System.out.println("entro a cargar");
        b_año.clear();
        for (int i = 2015; i < 2030; i++) {
            b_año.add("" + i);
        }
        cargar_Periodo();
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
        FechaF = "01-12-" + año;
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
                    + " and D.fechaInicial>='" + fechaI + "' and  D.fechaFinal<='" + FechaF + "'").list();
            t.commit();
            System.out.println("profess pege_id " + Pege_idProfe.size());
            for (int i = 0; i < Pege_idProfe.size(); i++) {
                System.out.println(Pege_idProfe.get(i).getPegeId());
                traer_profesor("" + Pege_idProfe.get(i).getPegeId());
            }
            System.out.println("size profe " + profe.size());
//           
        } catch (Exception ex) {

        }
//        
    }

    public void traer_profesor(String x) throws ClassNotFoundException {
        Conecion_Oracle.conectar();
        Conecion_Oracle.ejecuteQuery("select DISTINCT pege.PEGE_ID,pege.pege_documentoidentidad,peng.peng_primernombre,peng.PENG_SEGUNDONOMBRE,\n"
                + "peng.peng_primerapellido\n"
                + "from ACADEMICO.DocenteGrupo Dg ,academico.DocenteUnidad Du ,academico.Unidad Un,\n"
                + "academico.personageneral pege, academico.PERSONANATURALGENERAL peng\n"
                + "where Dg.DOUN_ID=Du.DOUN_ID\n"
                + "and Un.unid_id=Du.UNID_ID\n"
                + "and Du.PEGE_ID=pege.PEGE_ID\n"
                + "AND pege.pege_id = peng.pege_id\n"
                + "and pege.pege_id='" + x + "'\n"
                + "group by\n"
                + "pege.PEGE_ID,pege.pege_documentoidentidad,peng.peng_primernombre,peng.PENG_SEGUNDONOMBRE,\n"
                + "peng.peng_primerapellido");
        try {
            while (Conecion_Oracle.rs.next()) {
                System.out.println("trajo");
                if (Conecion_Oracle.rs.getString(4) == null) {
                    profe.add(new Profesor(false, Conecion_Oracle.rs.getString(2), Conecion_Oracle.rs.getString(3),
                            Conecion_Oracle.rs.getString(5),Conecion_Oracle.rs.getString(1)));
                } else {
                    profe.add(new Profesor(false, Conecion_Oracle.rs.getString(2), Conecion_Oracle.rs.getString(3) + " "
                            + Conecion_Oracle.rs.getString(4),
                            Conecion_Oracle.rs.getString(5),Conecion_Oracle.rs.getString(1)));
                }

            }
        } catch (Exception ex) {

        }

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
