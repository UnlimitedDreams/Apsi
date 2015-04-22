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
public class Dias {

    private boolean estado;
    private String dia;
    private String hora_inicio;
    private String hora_final;
    private String fecha;
    private int numer_ase;
    private int num1;
    private int num2;
    private String fecha1;
    private String fecha2;
    private String razon;
    private int cod;
    private String cod2;

//    public Dias(boolean estado, String dia) {
//        this.estado = estado;
//        this.dia = dia;
//    }
    public Dias(boolean estado, String dia, String hora_inicio, String hora_final) {
        this.estado = estado;
        this.dia = dia;
        this.hora_inicio = hora_inicio;
        this.hora_final = hora_final;

    }

    public Dias(int cod, String fecha1, String fecha2, String hora_inicio, String hora_final) {
        this.cod = cod;
        this.fecha1 = fecha1;
        this.fecha2 = fecha2;
        this.hora_inicio = hora_inicio;
        this.hora_final = hora_final;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

//    public Dias(boolean estado, String dia, String hora_inicio, String hora_final, int numer_ase) {
//        this.estado = estado;
//        this.dia = dia;
//        this.hora_inicio = hora_inicio;
//        this.hora_final = hora_final;
//        this.numer_ase = numer_ase;
//    }
    public int getNumer_ase() {
        return numer_ase;
    }

    public void setNumer_ase(int numer_ase) {
        this.numer_ase = numer_ase;
    }

    public String getCod2() {
        return cod2;
    }

    public void setCod2(String cod2) {
        this.cod2 = cod2;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public String getFecha1() {
        return fecha1;
    }

    public void setFecha1(String fecha1) {
        this.fecha1 = fecha1;
    }

    public String getFecha2() {
        return fecha2;
    }

    public void setFecha2(String fecha2) {
        this.fecha2 = fecha2;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    @Override
    public String toString() {
        return "Dias{" + "estado=" + estado + ", dia=" + dia + ", hora_inicio=" + hora_inicio + ", hora_final=" + hora_final + ", fecha=" + fecha + ", numer_ase=" + numer_ase + ", num1=" + num1 + ", num2=" + num2 + ", fecha1=" + fecha1 + ", fecha2=" + fecha2 + ", razon=" + razon + '}';
    }

}
