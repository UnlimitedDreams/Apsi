/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import Entity.DispoUsuario;
import Entity.Disponibilidad;
import java.util.ArrayList;

/**
 *
 * @author Microinformatica
 */
public interface DisponibilidadDao {
    public boolean Insert_Dispo(Disponibilidad dis);
    public boolean InsertDispo_Usu(DispoUsuario dis_usu);
    public boolean Insert_Dias(Disponibilidad dis,ArrayList dias);
}
