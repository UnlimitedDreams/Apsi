/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Microinformatica
 */
public class ProyectosModelo {
    private int codigo;
    private int cod_pro;
    private String nombre;
    private String descripcion;
    private boolean estado;
    private int Estudiante;
    private int Profesor;
    
    

    public ProyectosModelo(int cod_pro, String nombre, boolean estado) {
        this.cod_pro = cod_pro;
        this.nombre = nombre;
        this.estado = estado;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getCod() {
        return cod_pro;
    }

    public int getEstudiante() {
        return Estudiante;
    }

    public void setEstudiante(int Estudiante) {
        this.Estudiante = Estudiante;
    }

    public int getProfesor() {
        return Profesor;
    }

    public void setProfesor(int Profesor) {
        this.Profesor = Profesor;
    }

   

    public void setCod(int cod) {
        this.cod_pro = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCod_pro() {
        return cod_pro;
    }

    public void setCod_pro(int cod_pro) {
        this.cod_pro = cod_pro;
    }

    @Override
    public String toString() {
        return "ProyectosModelo{" + "codigo=" + codigo + ", cod_pro=" + cod_pro + ", nombre=" + nombre + ", descripcion=" + descripcion + ", estado=" + estado + ", Estudiante=" + Estudiante + ", Profesor=" + Profesor + '}';
    }



   

}
