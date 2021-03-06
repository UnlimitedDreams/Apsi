/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import dao.CalendarioImple;
import dao.CalendarioP;
import Entity.Calendario;
import Entity.Usuario;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Microinformatica
 */
@ManagedBean
@SessionScoped
public class CalendarioProfe_update {
    
    private String Actividad;
    private Date fecha_final;
    private Date fecha_inicio;
    private String hora1;
    private String hora2;
    
    public CalendarioProfe_update() {
    }
    
    public CalendarioProfe_update(String Actividad, Date fecha_final, Date fecha_inicio, String hora1, String hora2) {
        this.Actividad = Actividad;
        this.fecha_final = fecha_final;
        this.fecha_inicio = fecha_inicio;
        this.hora1 = hora1;
        this.hora2 = hora2;
    }
    
    public void traer_informacion() throws ClassNotFoundException {
        CalendarioP calen = new CalendarioImple();
        String codigo = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Codigo_asesoria");
        Calendario calendario = new Calendario();
        
        calendario = calen.BuscarCalendario(codigo);
        try {
            fecha_inicio = calendario.getFechaInicial();
            fecha_final = calendario.getFechaFinal();
            hora1 = "" + calendario.getHoraIni();
            hora2 = "" + calendario.getHoraFin();
            Actividad = calendario.getDescripcion();
            
            System.out.println("tra");
        } catch (Exception ex) {
            
        }
    }
    
    public void update() throws ClassNotFoundException, IOException {
        try {
            String codigo = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Codigo_asesoria");
            System.out.println("codigo es " + codigo);
            Usuario usu = new Usuario();
            usu.setPegeId(new BigDecimal(1));
            long cod = Long.parseLong(codigo);
            CalendarioP calen = new CalendarioImple();
            Calendario calendario = new Calendario();
            calendario.setCodCalendario(cod);
            calendario.setFechaInicial(fecha_inicio);
            calendario.setFechaFinal(fecha_final);
            System.out.println("hora 1 " + hora1);
            System.out.println("hora 2 " + hora2);
            String v[] = hora1.split(":");
            Date h1 = new Date();
            h1.setHours(Integer.parseInt(v[0]));
            h1.setMinutes(Integer.parseInt(v[1]));
            h1.setSeconds(00);
            Date h2 = new Date();
            String v2[] = hora2.split(":");
            h2.setHours(Integer.parseInt(v2[0]));
            h2.setMinutes(Integer.parseInt(v2[1]));
            h2.setSeconds(00);
            calendario.setFechaInicial(h1);
            calendario.setHoraFin(h2);
            calendario.setDescripcion(Actividad);
            calendario.setUsuario(usu);
            boolean r = calen.ActualizarCalendario(calendario);
            if (r) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("Calendario_profesor.xhtml");
                System.out.println("Ok");
            } else {
                System.out.println("No");
            }
        } catch (Exception ex) {
            System.out.println("Error update " + ex.toString());
        }
        
    }
    
    public void redirecionar() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("Calendario_profesor.xhtml");
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
    
    public String getActividad() {
        return Actividad;
    }
    
    public void setActividad(String Actividad) {
        this.Actividad = Actividad;
    }
    
}
