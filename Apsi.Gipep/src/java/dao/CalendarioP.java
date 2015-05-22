/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Asesoria;
import Entity.Asistente;
import Entity.Calendario;
import Entity.Usuario;
import java.util.ArrayList;

public interface CalendarioP {

    public void buscarAsesorias();

    public boolean CrearCalendario(Calendario c);

    public boolean BorrarCalendario(Calendario c);

    public boolean BorrarASesoria(Asesoria S, Asistente A);

    public ArrayList TraerCalendario(Usuario usu);

    public ArrayList TraerAsesorias(int cod);

    public Calendario BuscarCalendario(String cod);

    public boolean ActualizarCalendario(Calendario c);
}
