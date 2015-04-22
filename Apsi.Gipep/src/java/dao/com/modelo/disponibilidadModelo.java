/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.com.modelo;

import dao.com.control.Control;
import dao.com.control.Sequence;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author USR_Toshiba
 */
public class disponibilidadModelo {

    private boolean estado;
    private String dia;
    private String horaInicial;
    private String hora_final;

    public disponibilidadModelo(boolean estado, String dia, String horaInicial, String hora_final) {
        this.estado = estado;
        this.dia = dia;
        this.horaInicial = horaInicial;
        this.hora_final = hora_final;
    }

    public disponibilidadModelo() {
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }

    public String getHora_final() {
        return hora_final;
    }

    public void setHora_final(String hora_final) {
        this.hora_final = hora_final;
    }

    public String validarPeriodo(int mes) {
        //valida la fecha que se ingreso que sea la correcta 
        //correcta en año y fechas no menores a la de hoy
        String periodo = "";
        Date fecha2 = new Date();
        if (fecha2.getMonth() >= 0 && fecha2.getMonth() <= 5) {
            periodo = "1";
        } else {
            periodo = "2";
        }
        return periodo;
    }

    public ArrayList TraerHoras() throws ClassNotFoundException {
        Control.conectar();
        ArrayList<String> Horas1 = new ArrayList();
        Control.ejecuteQuery("select distinct hora_inicial,hora_final,rango,horasasesoria_dia from ajuste,año_festivo,dias\n"
                + "where ajuste.cod_ajuste=dias.cod_ajuste\n"
                + "and ajuste.festivo=año_festivo.cod_año\n"
                + "and año_festivo.año='2015' and \n"
                + "ajuste.periodo=1");
        String horai = "", horaF = "";
        int num_asesorias = 0, rango = 0;
        try {
            while (Control.rs.next()) {
                horai = Control.rs.getString(1);
                horaF = Control.rs.getString(2);
                rango = Control.rs.getInt(3);
                num_asesorias = Control.rs.getInt(4);
            }
            String hora = horai.substring(0, 2);
            String min = horai.substring(3, 5);
            int a = Integer.parseInt(hora);
            int b = Integer.parseInt(min);
            String hora_fi = "";
            Calendar Diadehoy = new GregorianCalendar();
            Diadehoy.set(01, 05, 2014, a, b, 00);
            for (int i = 0; i < num_asesorias; i++) {
                System.err.println("hora " + Diadehoy.get(Calendar.HOUR_OF_DAY) + " min " + Diadehoy.get(Calendar.MINUTE));
                hora_fi = Diadehoy.get(Calendar.HOUR_OF_DAY) + ":" + Diadehoy.get(Calendar.MINUTE);
                if (hora_fi.length() == 4) {
                    hora_fi = hora_fi + "0";
                }
                Horas1.add(hora_fi);
                Diadehoy.add(Calendar.MINUTE, rango);
                hora_fi = "";
            }
        } catch (Exception ex) {

        }
        String r = "";
        for (int i = 0; i < Horas1.size(); i++) {
            r = r + Horas1.get(i) + "-";
        }
        System.out.println("datos - " + r);
        return Horas1;
    }

    public ArrayList TraerHoras2() throws ClassNotFoundException {
        Control.conectar();
        ArrayList<String> Horas1 = new ArrayList();
        Control.ejecuteQuery("select distinct hora_inicial,hora_final,rango,horasasesoria_dia from ajuste,año_festivo,dias\n"
                + "where ajuste.cod_ajuste=dias.cod_ajuste\n"
                + "and ajuste.festivo=año_festivo.cod_año\n"
                + "and año_festivo.año='2015' and \n"
                + "ajuste.periodo=1");
        String horai = "", horaF = "";
        int num_asesorias = 0, rango = 0;
        try {
            while (Control.rs.next()) {
                horai = Control.rs.getString(1);
                horaF = Control.rs.getString(2);
                rango = Control.rs.getInt(3);
                num_asesorias = Control.rs.getInt(4);
            }
            String hora = horai.substring(0, 2);
            String min = horai.substring(3, 5);
            int a = Integer.parseInt(hora);
            int b = Integer.parseInt(min);
            String hora_fi = "";
            Calendar Diadehoy = new GregorianCalendar();
            Diadehoy.set(01, 05, 2014, a, b, 00);
            for (int i = 0; i < num_asesorias; i++) {
                Diadehoy.add(Calendar.MINUTE, rango);
                System.err.println("hora " + Diadehoy.get(Calendar.HOUR_OF_DAY) + " min " + Diadehoy.get(Calendar.MINUTE));
                hora_fi = Diadehoy.get(Calendar.HOUR_OF_DAY) + ":" + Diadehoy.get(Calendar.MINUTE);
                if (hora_fi.length() == 4) {
                    hora_fi = hora_fi + "0";
                }
                Horas1.add(hora_fi);
                hora_fi = "";
            }
        } catch (Exception ex) {

        }
        String r = "";
        for (int i = 0; i < Horas1.size(); i++) {
            r = r + Horas1.get(i) + "-";
        }
        System.out.println("datos - " + r);
        return Horas1;
    }

    public ArrayList Traer_disponibilidad() throws ClassNotFoundException {
        Control.conectar();
        System.out.println("Entro");
        Date fecha = new Date();
        ArrayList<disponibilidadModelo> mis_dias = new ArrayList();
        String periodo = validarPeriodo(fecha.getMonth());
        Control.ejecuteQuery("select dia from ajuste,año_festivo,dias\n"
                + "where ajuste.cod_ajuste=dias.cod_ajuste\n"
                + "and ajuste.festivo=año_festivo.cod_año\n"
                + "and año_festivo.año='" + fecha.toString().substring(fecha.toString().length() - 4, fecha.toString().length()) + "' and \n"
                + "ajuste.periodo=" + periodo);

        String dia = "", horaI = "", horaF = "";
        try {
            while (Control.rs.next()) {
                dia = Control.rs.getString(1);
                System.out.println("Trajo ");
                mis_dias.add(new disponibilidadModelo(false, dia, "1", "0"));
            }
        } catch (Exception ex) {

        }
        return mis_dias;

    }

}
