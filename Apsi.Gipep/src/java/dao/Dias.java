/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;

/**
 *
 * @author Britt
 */
public class Dias {

    private int cod;
    private String dia;
    private Date fechas;

    public Dias() {
    }

    public Dias(int cod, String dia) {
        this.cod = cod;
        this.dia = dia;
    }

    public Dias( Date fechas) {
       
        this.fechas = fechas;
    }
    

    
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Date getFechas() {
        return fechas;
    }

    public void setFechas(Date fechas) {
        this.fechas = fechas;
    }
    
    
}
