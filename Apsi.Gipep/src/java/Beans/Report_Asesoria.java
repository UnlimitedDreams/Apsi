/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.ModeloAsesoria;
import Modelo.R_profesor;
import java.util.ArrayList;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Britt
 */
public class Report_Asesoria implements JRDataSource {

    ArrayList<ModeloAsesoria> listaParticipantes = new ArrayList();
    private int indiceParticipanteActual = -1;

    @Override
    public Object getFieldValue(JRField dato) throws JRException {
        System.out.println("Dato  ---- " + dato.getName());
        Object valor = null;

        try {
            if ("codigo".equalsIgnoreCase(dato.getName())) {
                valor = listaParticipantes.get(indiceParticipanteActual).getCodigo_ase();
            } else if ("proyecto".equalsIgnoreCase(dato.getName())) {
                valor = listaParticipantes.get(indiceParticipanteActual).getNombre_proyecto();
            } else if ("fecha".equalsIgnoreCase(dato.getName())) {
                valor = listaParticipantes.get(indiceParticipanteActual).getFecha();
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

    public void addObjecto(ModeloAsesoria p) {
        this.listaParticipantes.add(p);
    }
}