/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.R_profesor;
import java.util.ArrayList;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Britt
 */
public class Report_profe implements JRDataSource {

    ArrayList<R_profesor> listaParticipantes = new ArrayList();
    private int indiceParticipanteActual = -1;

    @Override
    public Object getFieldValue(JRField dato) throws JRException {
        System.out.println("Dato  ---- " + dato.getName());
        Object valor = null;

        try {
            if ("horas".equalsIgnoreCase(dato.getName())) {
                valor = listaParticipantes.get(indiceParticipanteActual).getHoras();
            } else if ("horas_lleba".equalsIgnoreCase(dato.getName())) {
                valor = listaParticipantes.get(indiceParticipanteActual).getHoras_lleba();
            } else if ("cedula".equalsIgnoreCase(dato.getName())) {
                valor = listaParticipantes.get(indiceParticipanteActual).getCedula();
            } else if ("nombre".equalsIgnoreCase(dato.getName())) {
                valor = listaParticipantes.get(indiceParticipanteActual).getNombre();
            } else if ("apellido".equalsIgnoreCase(dato.getName())) {
                valor = listaParticipantes.get(indiceParticipanteActual).getApellido();
            }
        } catch (Exception ex) {
            System.out.println("Error getFielValue " + ex.toString());
        }

        return valor;

    }

    @Override
    public boolean next() throws JRException {
        return ++indiceParticipanteActual < listaParticipantes.size();
    }

    public void addObjecto(R_profesor p) {
        this.listaParticipantes.add(p);
    }

}
