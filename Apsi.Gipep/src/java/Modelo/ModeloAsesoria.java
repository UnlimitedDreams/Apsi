/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Britt
 */
public class ModeloAsesoria {

    private int codigo_ase;
    private String nombre_proyecto;
    private Date fecha;
    private int cod_proyecto;
    private String porcentaje;
    private int cantidad;

    public ModeloAsesoria() {
    }

    public ModeloAsesoria(int codigo_ase, String nombre_proyecto, Date fecha) {
        this.codigo_ase = codigo_ase;
        this.nombre_proyecto = nombre_proyecto;
        this.fecha = fecha;
    }

    public int getCodigo_ase() {
        return codigo_ase;
    }

    public void setCodigo_ase(int codigo_ase) {
        this.codigo_ase = codigo_ase;
    }

    public String getNombre_proyecto() {
        return nombre_proyecto;
    }

    public void setNombre_proyecto(String nombre_proyecto) {
        this.nombre_proyecto = nombre_proyecto;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getCod_proyecto() {
        return cod_proyecto;
    }

    public void setCod_proyecto(int cod_proyecto) {
        this.cod_proyecto = cod_proyecto;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

 

   

}
