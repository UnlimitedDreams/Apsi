/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.com.modelo;

import java.util.ArrayList;
import java.util.Date;

public class Ajuste_Modelo {
    private String fecha_inicio;
    private String fecha_final;
    private String periodo;
    private String Rango_hora;
    private String numero_horas;
    private String hora_inicio;
    private String hora_final;
    private String Horas_porDia;
    private String hora_inicio_fincho;
    private String hora_final_fincho;
    private boolean fin_semana;
    private String Asesorias_profesor;
    private int cod;

    public Ajuste_Modelo(String fecha_inicio, String fecha_final, String periodo, String Rango_hora, String numero_horas, String hora_inicio, String hora_final, String Horas_porDia, String Asesorias_profesor) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
        this.periodo = periodo;
        this.Rango_hora = Rango_hora;
        this.numero_horas = numero_horas;
        this.hora_inicio = hora_inicio;
        this.hora_final = hora_final;
        this.Horas_porDia = Horas_porDia;
        this.Asesorias_profesor = Asesorias_profesor;
    }

    public Ajuste_Modelo(String fecha_inicio, String fecha_final, String periodo, String Rango_hora, String numero_horas, String hora_inicio, String hora_final, String Horas_porDia,String Asesorias_profesor, String hora_inicio_fincho, String hora_final_fincho ) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
        this.periodo = periodo;
        this.Rango_hora = Rango_hora;
        this.numero_horas = numero_horas;
        this.hora_inicio = hora_inicio;
        this.hora_final = hora_final;
        this.Horas_porDia = Horas_porDia;
        this.hora_inicio_fincho = hora_inicio_fincho;
        this.hora_final_fincho = hora_final_fincho;
        this.Asesorias_profesor = Asesorias_profesor;
    }
    

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(String fecha_final) {
        this.fecha_final = fecha_final;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getRango_hora() {
        return Rango_hora;
    }

    public void setRango_hora(String Rango_hora) {
        this.Rango_hora = Rango_hora;
    }

    public String getNumero_horas() {
        return numero_horas;
    }

    public void setNumero_horas(String numero_horas) {
        this.numero_horas = numero_horas;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_final() {
        return hora_final;
    }

    public void setHora_final(String hora_final) {
        this.hora_final = hora_final;
    }

    public String getHoras_porDia() {
        return Horas_porDia;
    }

    public void setHoras_porDia(String Horas_porDia) {
        this.Horas_porDia = Horas_porDia;
    }

    public String getHora_inicio_fincho() {
        return hora_inicio_fincho;
    }

    public void setHora_inicio_fincho(String hora_inicio_fincho) {
        this.hora_inicio_fincho = hora_inicio_fincho;
    }

    public String getHora_final_fincho() {
        return hora_final_fincho;
    }

    public void setHora_final_fincho(String hora_final_fincho) {
        this.hora_final_fincho = hora_final_fincho;
    }

    public boolean isFin_semana() {
        return fin_semana;
    }

    public void setFin_semana(boolean fin_semana) {
        this.fin_semana = fin_semana;
    }

    public String getAsesorias_profesor() {
        return Asesorias_profesor;
    }

    public void setAsesorias_profesor(String Asesorias_profesor) {
        this.Asesorias_profesor = Asesorias_profesor;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    @Override
    public String toString() {
        return "Ajuste_Modelo{" + "fecha_inicio=" + fecha_inicio + ", fecha_final=" + fecha_final + ", periodo=" + periodo + ", Rango_hora=" + Rango_hora + ", numero_horas=" + numero_horas + ", hora_inicio=" + hora_inicio + ", hora_final=" + hora_final + ", Horas_porDia=" + Horas_porDia + ", hora_inicio_fincho=" + hora_inicio_fincho + ", hora_final_fincho=" + hora_final_fincho + ", fin_semana=" + fin_semana + ", Asesorias_profesor=" + Asesorias_profesor + ", cod=" + cod + '}';
    }

}
