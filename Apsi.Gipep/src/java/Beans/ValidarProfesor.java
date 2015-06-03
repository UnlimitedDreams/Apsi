/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Profesor;
import java.io.IOException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Microinformatica
 */
@ManagedBean
@SessionScoped
public class ValidarProfesor {

    ArrayList<Profesor> profe = new ArrayList();

    public ValidarProfesor() {
    }

    public void cargarProfesores() {
        profe.clear();
        Profesor p = new Profesor(false, 1, "juandavid", "castrillon");
        Profesor p2 = new Profesor(false, 1, "jos", "galvis");
        profe.add(p);
        profe.add(p2);
    }

    public void ajusteProfe(int cedula) throws IOException {
        System.out.println("hola " + cedula);
        Profesor temp = null;
        Profesor temp2 = null;
        for (int i = 0; i < profe.size(); i++) {
            temp = (Profesor) profe.get(i);
            if (temp.getCedula() == cedula) {
                temp2 = temp;
                break;
            }
        }
        if (temp2 != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("profesor", temp2);
            FacesContext.getCurrentInstance().getExternalContext().redirect("Ajuste_profesor.html");
        }

    }

    public ArrayList<Profesor> getProfe() {
        return profe;
    }

    public void setProfe(ArrayList<Profesor> profe) {
        this.profe = profe;
    }

}
