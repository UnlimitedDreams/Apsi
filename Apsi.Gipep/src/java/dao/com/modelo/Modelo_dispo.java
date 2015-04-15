/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.com.modelo;

import java.util.ArrayList;

/**
 *
 * @author estudiante305
 */
public class Modelo_dispo {

    ArrayList<Modelo_dispo> no = new ArrayList();
    private int cod;

    public void llenar() {
        no.add(new Modelo_dispo(1));
    }

    public Modelo_dispo() {
    }

    public Modelo_dispo(int cod) {
        this.cod = cod;
    }

    public ArrayList<Modelo_dispo> getNo() {
        return no;
    }

    public void setNo(ArrayList<Modelo_dispo> no) {
        this.no = no;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

}
