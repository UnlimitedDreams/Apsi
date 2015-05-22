/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Beans.FestivosBeans;
import Entity.AñoFestivo;
import Entity.Festivos;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Microinformatica
 */
public interface FestivosDao {

    public boolean crearFestivo(Festivos fes);

    public boolean CrearAñoFestivo(AñoFestivo añoFes);

    public ArrayList<Festivos> listaFestivos();

    public AñoFestivo Traer_año(String año);
    
    public Festivos BuscarFestivo(String fecha);
    
    public boolean EliminarFestivo(Festivos fes);
}
