/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.com.control;

import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jeison
 */
@Named(value = "calendarioProfe_update")
@Dependent
public class CalendarioProfe_update {

    private Date fecha_final;
    private Date fecha_inicio;
    private String hora1;
    private String hora2;

    public CalendarioProfe_update() {
    }

    public void traer_informacion() throws ClassNotFoundException {
        Control.conectar();
        String codigo = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Codigo_asesoria");
        System.out.println("select * from calendario where cod_calemndario=" + codigo);
        Control.ejecuteQuery("select * from calendario where cod_calemndario=" + codigo);
        try {
            while (Control.rs.next()) {
                 System.out.println("trajo fecha");
                fecha_inicio = Control.rs.getDate(2);
                fecha_final = Control.rs.getDate(3);
                hora1 = Control.rs.getString(4);
                hora2 = Control.rs.getString(5);
            }
            System.out.println("tra");
        } catch (Exception ex) {

        }
    }

    public Date getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(Date fecha_final) {
        this.fecha_final = fecha_final;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getHora1() {
        return hora1;
    }

    public void setHora1(String hora1) {
        this.hora1 = hora1;
    }

    public String getHora2() {
        return hora2;
    }

    public void setHora2(String hora2) {
        this.hora2 = hora2;
    }

}
