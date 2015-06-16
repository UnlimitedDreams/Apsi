/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Microinformatica
 */
@ManagedBean
@RequestScoped
public class ajusteProfesorBeans {

    private String nombreProfesor;
    private Date fecha_inicial;
    private Date fecha_final;
    private String RamgoHora;
    private String Cantidad_horas;
    private String hora_inicial;
    private String hora_Final;
    ArrayList<String> HoraI = new ArrayList();
    ArrayList<String> HoraF = new ArrayList();

    public ajusteProfesorBeans() {
    }

    public void inicionombreProfe() {
        this.nombreProfesor = "Juan David Castrillon";
    }

    public void Cargar_horas() {
//        HoraI.clear();
//        HoraF.clear();
//        Calendar Diadehoy = new GregorianCalendar();
//        Diadehoy.set(01, 05, 2014, a, b, 00);
//        for (int i = 0; i < ase; i++) {
//            System.err.println("hora " + Diadehoy.get(Calendar.HOUR_OF_DAY) + " min " + Diadehoy.get(Calendar.MINUTE));
//            hora_fi = Diadehoy.get(Calendar.HOUR_OF_DAY) + ":" + Diadehoy.get(Calendar.MINUTE);
//            if (hora_fi.length() == 4) {
//                hora_fi = hora_fi + "0";
//            }
//            Horas h = new Horas(hora_fi);
//
//            H_dia.add(h);
//            Diadehoy.add(Calendar.MINUTE, c);
//            hora_fi = "";
//        }
//        Calendar Diadehoy2 = new GregorianCalendar();
//        Diadehoy2.set(01, 05, 2014, a, b, 00);
//        for (int i = 0; i < ase; i++) {
//            Diadehoy2.add(Calendar.MINUTE, c);
//            System.err.println("hora " + Diadehoy2.get(Calendar.HOUR_OF_DAY) + " min " + Diadehoy2.get(Calendar.MINUTE));
//            hora_fi = Diadehoy2.get(Calendar.HOUR_OF_DAY) + ":" + Diadehoy2.get(Calendar.MINUTE);
//            if (hora_fi.length() == 4) {
//                hora_fi = hora_fi + "0";
//            }
//            Horas h = new Horas(hora_fi);
//            H_dia2.add(h);
//        }
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

}
