/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import Entity.Aofestivo;
import Entity.Aofestivo;
import Entity.Festivos;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Microinformatica
 */
public interface FestivosDao {

    public boolean crearFestivo(Festivos fes);

    public boolean CrearAñoFestivo(Aofestivo añoFes);

    public ArrayList<Festivos> listaFestivos();

    public Aofestivo Traer_año(String año);
    
    public Festivos BuscarFestivo(String fecha);
    
    public boolean EliminarFestivo(Festivos fes);
}
