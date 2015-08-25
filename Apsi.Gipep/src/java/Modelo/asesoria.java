/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author usuario
 */
public class asesoria {

    private Date fecha;
    private String hora;
    private boolean asiginado;
    private int cod_dis;

    public asesoria(Date fecha, String hora, boolean asiginado, int cod_dis) {
        this.fecha = fecha;
        this.hora = hora;
        this.asiginado = asiginado;
        this.cod_dis = cod_dis;
    }

  
   

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public boolean isAsiginado() {
        return asiginado;
    }

    public void setAsiginado(boolean asiginado) {
        this.asiginado = asiginado;
    }

    @Override
    public String toString() {
        return "asesoria{" + "fecha=" + fecha + ", hora=" + hora + ", asiginado=" + asiginado + '}';
    }

    public int getCod_dis() {
        return cod_dis;
    }

    public void setCod_dis(int cod_dis) {
        this.cod_dis = cod_dis;
    }
    
    

}
