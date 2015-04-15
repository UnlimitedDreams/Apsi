/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.com.modelo;

/**
 *
 * @author USR_Toshiba
 */
public class Dias_Ajuste {
    private int cod;
    private int cod2;
    private int dia;
    private int mes;
    private int año;
    private boolean r;

    public Dias_Ajuste(int cod, int cod2, int dia, int mes, int año, boolean r) {
        this.cod = cod;
        this.cod2 = cod2;
        this.dia = dia;
        this.mes = mes;
        this.año = año;
        this.r = r;
    }

    public Dias_Ajuste(int dia, int mes, int año) {
        this.dia = dia;
        this.mes = mes;
        this.año = año;
    }

    

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public boolean isR() {
        return r;
    }

    public void setR(boolean r) {
        this.r = r;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getCod2() {
        return cod2;
    }

    public void setCod2(int cod2) {
        this.cod2 = cod2;
    }

    @Override
    public String toString() {
        return "Di{" + "cod=" + cod + ", cod2=" + cod2 + ", dia=" + dia + ", mes=" + mes + ", a\u00f1o=" + año + ", r=" + r + '}';
    }

   

}
