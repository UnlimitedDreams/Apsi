/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.Calendario;
import Entity.Dia;
import Entity.Disponibilidad;
import Modelo.MDias;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Microinformatica
 */
@ManagedBean
@SessionScoped
public class ListaProfesoresBeansUpdate {

    private String nombreProfesor;
    private Date fecha_inicial;
    private Date fecha_final;
    private String RamgoHora;
    private String Cantidad_horas;
    ArrayList<Dia> Dias = new ArrayList();

    public ListaProfesoresBeansUpdate() {
    }

    public void recuperar_info() {
        System.out.println("entro a recuperar");
        String codigo = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Pege_idProfe");
        String v[] = codigo.split("-");
        Disponibilidad d = new Disponibilidad();
        d = (Disponibilidad) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Dispo_profe");
        System.out.println("Codigo dispo " +d.getCodDis());
        traer_dias(d);
        System.out.println("-------------------------");
        nombreProfesor = v[1];
        fecha_inicial = d.getFechaInicial();
        fecha_final = d.getFechaFinal();
        RamgoHora = "" + d.getRango();
        Cantidad_horas = "" + d.getNumHoras();

    }
//
//    public Disponibilidad Traer_info(String pege_id, String año, String periodo) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction t = session.beginTransaction();
//        String fechaI = "", FechaF = "";
//        fechaI = "01-01-" + año;
//        FechaF = "01-12-" + año;
//        Disponibilidad d = new Disponibilidad();
//        try {
//            d = (Disponibilidad) session.createQuery("select D from Disponibilidad D "
//                    + " INNER JOIN D.dispoUsuarios UD "
//                    + " INNER JOIN UD.usuarioByProfesor Usu WHERE Usu.pegeId=" + pege_id + " and D.periodo=" + periodo + " "
//                    + " and D.fechaInicial>='" + fechaI + "' and  D.fechaFinal<='" + FechaF + "'").uniqueResult();
//            System.out.println("d " + d.toString());
//
//            t.commit();
//        } catch (Exception ex) {
//
//        }
//        return d;
//    }

    public void traer_dias(Disponibilidad d) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            Dias = (ArrayList) session.createQuery("select dia from Disponibilidad D "
                    + " INNER JOIN D.dias dia "
                    + " WHERE dia.disponibilidad=" + d.getCodDis()).list();
        } catch (Exception ex) {
            System.out.println("Error Dias " + ex.toString());
        }
    }

    public void update() throws IOException {
        String codigo = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Pege_idProfe");
        String v[] = codigo.split("-");
        Disponibilidad dispo = new Disponibilidad();
        dispo = (Disponibilidad) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Dispo_profe");
        int peri = Integer.parseInt(v[3]);
        Disponibilidad d = new Disponibilidad();
        d.setFechaInicial(fecha_inicial);
        d.setFechaFinal(fecha_final);
        int rango = Integer.parseInt(RamgoHora);
        d.setRango(new BigDecimal(rango));
        int NumH = Integer.parseInt(Cantidad_horas);
        d.setNumHoras(new BigDecimal(NumH));
        d.setCodDis(dispo.getCodDis());
        d.setPeriodo(new BigDecimal(peri));
        d.setEstado(dispo.getEstado());
        System.out.println("--- " + d.getNumHoras() + " --- " + d.getCodDis());
        boolean r = update_disponibilidad(d);
        if (r) {
            System.out.println("ok2---------------------");
            boolean r2 = update_dias(d);
            if (r2) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("Pege_idProfe");
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("Dispo_profe");
                FacesContext.getCurrentInstance().getExternalContext().redirect("Lista_Profesor.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Comunicarce con el Administrador", ""));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Comunicarce con el Administrador", ""));

        }
    }

    public boolean update_disponibilidad(Disponibilidad d) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        boolean r = false;
        System.out.println("----- update " + d.getCodDis() + "----" + d.getNumHoras());
        try {
            session.update(d);
            t.commit();
            r = true;
        } catch (Exception ex) {
            System.out.println("error " + ex.getMessage());
            r = false;

        }
        System.out.println("+++++ " + r);
        return r;
    }

    public boolean update_dias(Disponibilidad d) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        boolean r = false;
        Dia dias = new Dia();
        try {
            for (int i = 0; i < Dias.size(); i++) {
                dias = (Dia) Dias.get(i);
                dias.setDisponibilidad(d);
                session.update(dias);
            }

            t.commit();
            r = true;
        } catch (Exception ex) {
            System.out.println("Error al update de dias " + ex.getMessage());
            r = false;

        }
        return r;
    }

//    public void pasar_horas(String dia, String hora, int decision) {
//        System.out.println("-**-*-/-*-*/-*/* condi " + decision);
//        Dia temp = null;
//        boolean esta = false;
//        for (int i = 0; i < Dias.size(); i++) {
//            temp = (Dia) Dias.get(i);
//            if (temp.getDia().equalsIgnoreCase(dia)) {
//                if (decision == 1) {
//                    temp.setHoraInicial(hora);
//                } else {
//                    temp.setHoraFinal(hora);
//                }
//            }
//        }
//        for (int i = 0; i < Dias.size(); i++) {
//            System.out.println("- " + Dias.get(i).getHoraInicial() + "-" + Dias.get(i).getHoraFinal());
//        }
//    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public Date getFecha_inicial() {
        return fecha_inicial;
    }

    public void setFecha_inicial(Date fecha_inicial) {
        this.fecha_inicial = fecha_inicial;
    }

    public Date getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(Date fecha_final) {
        this.fecha_final = fecha_final;
    }

    public String getRamgoHora() {
        return RamgoHora;
    }

    public void setRamgoHora(String RamgoHora) {
        this.RamgoHora = RamgoHora;
    }

    public String getCantidad_horas() {
        return Cantidad_horas;
    }

    public void setCantidad_horas(String Cantidad_horas) {
        this.Cantidad_horas = Cantidad_horas;
    }

    public ArrayList<Dia> getDias() {
        return Dias;
    }

    public void setDias(ArrayList<Dia> Dias) {
        this.Dias = Dias;
    }

}
