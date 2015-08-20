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

    public asesoria(Date fecha, String hora, boolean asiginado) {
        this.fecha = fecha;
        this.hora = hora;
        this.asiginado = asiginado;
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
    
    

}
