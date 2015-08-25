/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;

public class Profesor implements Serializable {

    private boolean activo;
    private String cedula;
    private String nombre;
    private String apellido;
    private String pege_id;
    private int num_proyectos;
    private String dispo;

    public Profesor(boolean activo, String cedula, String nombre, String apellido, String pege_id) {
        this.activo = activo;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.pege_id = pege_id;
    }

    public Profesor(String pege_id, String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.pege_id = pege_id;
    }

    public Profesor() {
    }

    public int getNum_proyectos() {
        return num_proyectos;
    }

    public void setNum_proyectos(int num_proyectos) {
        this.num_proyectos = num_proyectos;
    }

    public String getDispo() {
        return dispo;
    }

    public void setDispo(String dispo) {
        this.dispo = dispo;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getPege_id() {
        return pege_id;
    }

    public void setPege_id(String pege_id) {
        this.pege_id = pege_id;
    }

    @Override
    public String toString() {
        return "Profesor{" + "activo=" + activo + ", cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", pege_id=" + pege_id + '}';
    }

}
