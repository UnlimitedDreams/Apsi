/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Britt
 */
public class R_profesor {
    private String cedula;
    private String nombre;
    private String apellido;
    private int horas;
    private int horas_lleba;

    public R_profesor() {
    }

    public R_profesor(int horas, int horas_lleba) {
        this.horas = horas;
        this.horas_lleba = horas_lleba;
    }

  
    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getHoras_lleba() {
        return horas_lleba;
    }

    public void setHoras_lleba(int horas_lleba) {
        this.horas_lleba = horas_lleba;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    
}
