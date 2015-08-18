/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.ProyectosModelo;
import Modelo.R_profesor;
import java.util.ArrayList;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Britt
 */
public class Report_Proyecto implements JRDataSource {

    ArrayList<ProyectosModelo> listaProyectos = new ArrayList();
    private int indiceParticipanteActual = -1;

    @Override
    public Object getFieldValue(JRField dato) throws JRException {
        System.out.println("----- " + dato.getName());
        Object valor = null;
        if ("Codigo".equalsIgnoreCase(dato.getName())) {
            valor = listaProyectos.get(indiceParticipanteActual).getCodigo();
        } else if ("Nombre".equalsIgnoreCase(dato.getName())) {
            valor = listaProyectos.get(indiceParticipanteActual).getNombre();
        }else if ("Fecha Inicial".equalsIgnoreCase(dato.getName())) {
            valor = listaProyectos.get(indiceParticipanteActual).getFecha_I();
        }else if ("Fecha Final".equalsIgnoreCase(dato.getName())) {
            valor = listaProyectos.get(indiceParticipanteActual).getFecha_F();
        }else if ("Porcentaje".equalsIgnoreCase(dato.getName())) {
            valor = listaProyectos.get(indiceParticipanteActual).getProcentaje();
        }
        return valor;

    }

    @Override
    public boolean next() throws JRException {
        return ++indiceParticipanteActual < listaProyectos.size();
    }

    public void addObjecto(ProyectosModelo p) {
        this.listaProyectos.add(p);
    }
}
