/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Conecion_Oracle;
import Modelo.Profesor;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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

    public ValidarProfesor() {
    }

    public void cargar_facultad() throws ClassNotFoundException {
        facul.clear();
        Conecion_Oracle.conectar();
        Conecion_Oracle.ejecuteQuery("select UNID_NOMBRE from academico.Unidad");
        try {
            while (Conecion_Oracle.rs.next()) {
                facul.add(Conecion_Oracle.rs.getString(1));
            }
            Conecion_Oracle.rs.close();
        } catch (Exception ex) {

        }
    }

    public void cargarProfesores() throws ClassNotFoundException {
        System.out.println("----");
        profe.clear();
        Conecion_Oracle.conectar();
        Conecion_Oracle.ejecuteQuery("select DISTINCT pege.PEGE_ID,pege.pege_documentoidentidad,peng.peng_primernombre,peng.PENG_SEGUNDONOMBRE,\n"
                + "peng.peng_primerapellido\n"
                + "from ACADEMICO.DocenteGrupo Dg ,academico.DocenteUnidad Du ,academico.Unidad Un,\n"
                + "academico.personageneral pege, academico.PERSONANATURALGENERAL peng\n"
                + "where Dg.DOUN_ID=Du.DOUN_ID\n"
                + "and Un.unid_id=Du.UNID_ID\n"
                + "and Du.PEGE_ID=pege.PEGE_ID\n"
                + "AND pege.pege_id = peng.pege_id\n"
                + "and un.unid_nombre='" + facultad + "'\n"
                + "group by\n"
                + "pege.PEGE_ID,pege.pege_documentoidentidad,peng.peng_primernombre,peng.PENG_SEGUNDONOMBRE,\n"
                + "peng.peng_primerapellido");
        try {
            while (Conecion_Oracle.rs.next()) {
                if (Conecion_Oracle.rs.getString(4) == null) {
                    profe.add(new Profesor(false, Conecion_Oracle.rs.getString(2), Conecion_Oracle.rs.getString(3),
                            Conecion_Oracle.rs.getString(5)));
                } else {
                    profe.add(new Profesor(false, Conecion_Oracle.rs.getString(2), Conecion_Oracle.rs.getString(3) + " "
                            + Conecion_Oracle.rs.getString(4),
                            Conecion_Oracle.rs.getString(5)));
                }

            }
            if (profe.size() == 0) {
                System.out.println("no tiene profesores");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "La Facultad no Tiene Profesores", ""));
            }
            Conecion_Oracle.rs.close();
        } catch (Exception ex) {

        }
    }

    public void ajusteProfe(int cedula) throws IOException {
        System.out.println("hola " + cedula);
        Profesor temp = null;
        Profesor temp2 = null;
        for (int i = 0; i < profe.size(); i++) {
            temp = (Profesor) profe.get(i);
            if (temp.getCedula().equalsIgnoreCase("" + cedula)) {
                temp2 = temp;
                break;
            }
        }
        if (temp2 != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("profesor", temp2);
            FacesContext.getCurrentInstance().getExternalContext().redirect("AjusteProfesor.xhtml");
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

}
