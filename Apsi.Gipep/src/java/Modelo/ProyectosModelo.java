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
    private String integrantes;
    private String fecha_I;
    private String fecha_F;
    private int procentaje;
    private String Estados;
    

    public ProyectosModelo(int cod_pro, String nombre, boolean estado) {
        this.cod_pro = cod_pro;
        this.nombre = nombre;
        this.estado = estado;
    }

    public ProyectosModelo(int codigo, String nombre, String integrantes, String fecha_I, String fecha_F, int procentaje) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.integrantes = integrantes;
        this.fecha_I = fecha_I;
        this.fecha_F = fecha_F;
        this.procentaje = procentaje;
    }

    public ProyectosModelo() {
    }

    public String getEstados() {
        return Estados;
    }

    public void setEstados(String Estados) {
        this.Estados = Estados;
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

    public int getProcentaje() {
        return procentaje;
    }

    public void setProcentaje(int procentaje) {
        this.procentaje = procentaje;
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

    public String getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(String integrantes) {
        this.integrantes = integrantes;
    }

    public String getFecha_I() {
        return fecha_I;
    }

    public void setFecha_I(String fecha_I) {
        this.fecha_I = fecha_I;
    }

    public String getFecha_F() {
        return fecha_F;
    }

    public void setFecha_F(String fecha_F) {
        this.fecha_F = fecha_F;
    }

    @Override
    public String toString() {
        return "ProyectosModelo{" + "cod_pro=" + cod_pro + ", estado=" + estado + '}';
    }

  

   


   

}
