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
public class Estudiante {

    private boolean activo;
    private String cedula;
    private String nombre;
    private String apellido;
    private String pege_id;

    public Estudiante(boolean activo, String cedula, String nombre, String apellido, String pege_id) {
        this.activo = activo;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.pege_id = pege_id;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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

    public String getPege_id() {
        return pege_id;
    }

    public void setPege_id(String pege_id) {
        this.pege_id = pege_id;
    }
    
    
}
