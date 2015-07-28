/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 *
 * @author Britt
 */
public class Objetivo {
    private int cod;
    private String Obj;

    public Objetivo(int cod, String Obj) {
        this.cod = cod;
        this.Obj = Obj;
    }


    public String getObj() {
        return Obj;
    }

    public void setObj(String Obj) {
        this.Obj = Obj;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
    
}
