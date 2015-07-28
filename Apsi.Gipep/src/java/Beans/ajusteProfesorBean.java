/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.MDias;
import Modelo.Profesor;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Microinformatica
 */
@ManagedBean
@ViewScoped
public final class ajusteProfesorBean implements Serializable {

    private String nombreProfesor;
    private Date fecha_inicial;
    private Date fecha_final;
    private String RamgoHora;
    private String Cantidad_horas;
    private String hora_inicial;
    private String hora_Final;
    ArrayList<MDias> Dias = new ArrayList();
    ArrayList<MDias> Dias_Recuerdo = new ArrayList();

    public ajusteProfesorBean() {

    }

    public ajusteProfesorBean(String nombreProfesor, Date fecha_inicial, Date fecha_final, String RamgoHora, String Cantidad_horas) {
        this.nombreProfesor = nombreProfesor;
        this.fecha_inicial = fecha_inicial;
        this.fecha_final = fecha_final;
        this.RamgoHora = RamgoHora;
        this.Cantidad_horas = Cantidad_horas;
    }

    public void Recuerpar_informacion() {
        System.out.println("Entro al recuerdo");

        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Datos_ajuste") != null) {
            ajusteProfesorBean ajuste = new ajusteProfesorBean();
            ajuste = (ajusteProfesorBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Datos_ajuste");
            nombreProfesor = ajuste.getNombreProfesor();
            fecha_inicial = ajuste.getFecha_inicial();
            fecha_final = ajuste.getFecha_final();
            RamgoHora = ajuste.getRamgoHora();
            Cantidad_horas = ajuste.getCantidad_horas();

            System.out.println(" existe");
        } else {
            System.out.println("No existe");
        }
//        

    }

    public void validarHora(String x) {
        boolean r=false;
        String hora;
        if (x.length() == 5) {

        }
    }

    public void ValidarRangoHora(String h1, String h2) {

    }

    public void inicionombreProfe() {
        Profesor temp = null;
        temp = (Profesor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("profesor");
        this.nombreProfesor = temp.getNombre() + " " + temp.getApellido();
        System.out.println("--- " + temp.toString());
        cargarDias();
        Recuerpar_informacion();
    }

    public void cargarDias() {
        Dias.clear();
        Dias.add(new MDias(false, "Lunes", "0", "0"));
        Dias.add(new MDias(false, "Martes", "0", "0"));
        Dias.add(new MDias(false, "Mieroles", "0", "0"));
        Dias.add(new MDias(false, "Jueves", "0", "0"));
        Dias.add(new MDias(false, "Viernes", "0", "0"));
        Dias.add(new MDias(false, "Sabado", "0", "0"));

    }

    public void ponerhorasinicial(String dia) {
        System.out.println("hora inicial" + dia);
        MDias temp = null;
        boolean esta = false;
        for (int i = 0; i < Dias.size(); i++) {
            temp = (MDias) Dias.get(i);
            if (temp.getDia().equalsIgnoreCase(dia)) {
                if (temp.isEstado() == true) {
                    System.out.println("Entroooo");
                    temp.setHora_inicio(hora_inicial);
                    esta = true;
                    System.out.println("estaaa 1 " + esta);
                } else {
                    esta = false;
                }
            }

        }
        System.out.println("estaaa " + esta);
        if (esta == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No ha Seleccionado el dia", ""));
        }
        hora_inicial = "";
    }

    public void ponerhorasFinal(String dia) {
        System.out.println("hora final" + dia);
        MDias temp = null;
        boolean esta = false;
        for (int i = 0; i < Dias.size(); i++) {
            temp = (MDias) Dias.get(i);
            if (temp.getDia().equalsIgnoreCase(dia)) {
                if (temp.isEstado() == true) {
                    temp.setHora_final(hora_Final);
                    System.out.println("ok");
                    esta = true;
                } else {
                    esta = false;
                }
            }
        }
        if (esta == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No ha Seleccionado el dia", ""));
        }
        hora_Final = "";
    }

    public void cargarTodo() throws IOException {
        ArrayList<MDias> d = new ArrayList();
        MDias temp = null;
        for (int i = 0; i < Dias.size(); i++) {
            temp = (MDias) Dias.get(i);
            if (temp.isEstado() == true) {
                d.add(temp);
            }
        }
        System.out.println("tamaño de d " + d.size());
        if (d.size() > 0) {
            System.out.println("entro");
            ajusteProfesorBean ajuste = new ajusteProfesorBean(nombreProfesor, fecha_inicial, fecha_final, RamgoHora, Cantidad_horas);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Dias_ajuste", d);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Datos_ajuste", ajuste);
            FacesContext.getCurrentInstance().getExternalContext().redirect("AjusteProfesor2.xhtml");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No ha seleccionado ningun dia", ""));
            System.out.println("No ha seleccionado ningun dia");
        }

    }

    public void activarDias(String dia) {
        System.out.println("Act" + dia);
        MDias temp = null;
        boolean esta = false;
        for (int i = 0; i < Dias.size(); i++) {
            temp = (MDias) Dias.get(i);
            if (temp.getDia().equalsIgnoreCase(dia)) {
                if (temp.isEstado() == true) {
                    temp.setEstado(false);
                } else {
                    temp.setEstado(true);
                }

            }
        }
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public Date getFecha_inicial() {
        return fecha_inicial;
    }

    public void setFecha_inicial(Date fecha_inicial) {
        this.fecha_inicial = fecha_inicial;
    }

    public Date getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(Date fecha_final) {
        this.fecha_final = fecha_final;
    }

    public String getRamgoHora() {
        return RamgoHora;
    }

    public void setRamgoHora(String RamgoHora) {
        this.RamgoHora = RamgoHora;
    }

    public String getCantidad_horas() {
        return Cantidad_horas;
    }

    public void setCantidad_horas(String Cantidad_horas) {
        this.Cantidad_horas = Cantidad_horas;
    }

    public String getHora_inicial() {
        return hora_inicial;
    }

    public void setHora_inicial(String hora_inicial) {
        this.hora_inicial = hora_inicial;
    }

    public String getHora_Final() {
        return hora_Final;
    }

    public void setHora_Final(String hora_Final) {
        this.hora_Final = hora_Final;
    }

    public ArrayList<MDias> getDias() {
        return Dias;
    }

    public void setDias(ArrayList<MDias> Dias) {
        this.Dias = Dias;
    }

    public ArrayList<MDias> getDias_Recuerdo() {
        return Dias_Recuerdo;
    }

    public void setDias_Recuerdo(ArrayList<MDias> Dias_Recuerdo) {
        this.Dias_Recuerdo = Dias_Recuerdo;
    }

}
