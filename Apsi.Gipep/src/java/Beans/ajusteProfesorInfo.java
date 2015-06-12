/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Dao.Sequence;
import Dao.UsuarioDao;
import Dao.UsuarioImple;
import Entity.DispoUsuario;
import Entity.Disponibilidad;
import Entity.Usuario;
import Modelo.MDias;
import Modelo.Profesor;
import dao.DisponibilidadDao;
import dao.DisponibilidadImple;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Microinformatica
 */
@ManagedBean
@SessionScoped
public class ajusteProfesorInfo {

    private String nombreProfesor;
    private Date fecha_inicial;
    private Date fecha_final;
    private String RamgoHora;
    private String Cantidad_horas;
    ArrayList<MDias> Dias = new ArrayList();

    public ajusteProfesorInfo() {

    }

    public void recuperarInfo() {
        System.out.println("recupero");
//        ajusteProfesorBean ajuste;
//        ajuste = new ajusteProfesorBean();
//        ajuste = (ajusteProfesorBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Datos_ajuste");
//        Dias = (ArrayList) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Dias_ajuste");
//        System.out.println("dias qe trajo " + Dias.size());
//        nombreProfesor = ajuste.getNombreProfesor();
//        fecha_inicial = ajuste.getFecha_inicial();
//        fecha_final = ajuste.getFecha_final();
//        RamgoHora = ajuste.getRamgoHora();
//        Cantidad_horas = ajuste.getCantidad_horas();
    }

//    public void insertar_ajuste() throws ClassNotFoundException, IOException {
//
//        System.out.println("Entro al ajuste");
//        Usuario temp = Crearusuario();
//        int perido = validarPeriodo(fecha_inicial);
////            try {
//        int Codigo_Dispo = 0, codigo_dispoUsu = 0;
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction t = session.beginTransaction();
//        Query query = session.createQuery("FROM Disponibilidad order by codDis DESC ").setMaxResults(1);
////            Query query = Sequence.GetUltimoRegistro("FROM Disponibilidad order by codDis DESC");
//        System.out.println("query " + query.uniqueResult());
//        if (query.uniqueResult() == null) {
//            Codigo_Dispo = 1;
//        } else {
//            String cod_salvador = "" + query.uniqueResult();
//            Codigo_Dispo = Integer.parseInt(cod_salvador);
//        }
//        Disponibilidad dispo = new Disponibilidad();
//        dispo.setCodDis(new BigDecimal(Codigo_Dispo + 1));
//        dispo.setFechaInicial(fecha_inicial);
//        dispo.setFechaFinal(fecha_final);
//        dispo.setNumHoras(new BigDecimal(Integer.parseInt(Cantidad_horas)));
//        dispo.setRango(new BigDecimal(Integer.parseInt(RamgoHora)));
//        dispo.setPeriodo(new BigDecimal(perido));
//        DisponibilidadDao disponi = new DisponibilidadImple();
//        System.out.println("----");
//        boolean CrearDispo = disponi.Insert_Dispo(dispo);
//        System.out.println("---------" + CrearDispo);
//        if (CrearDispo) {
//            System.out.println("creo dispo");
//            DispoUsuario dispo_usu = new DispoUsuario();
//            Query query2 = session.createQuery("FROM DispoUsuario order by codDispousu DESC ").setMaxResults(1);
//            if (query2.uniqueResult() == null) {
//                codigo_dispoUsu = 1;
//            } else {
//                String cod_salvador = "" + query2.uniqueResult();
//                codigo_dispoUsu = Integer.parseInt(cod_salvador);
//            }
//            System.out.println("codigo de dispop_usu " + codigo_dispoUsu + 1);
//            Usuario temp2 = new Usuario();
//            temp2.setPegeId(new BigDecimal(1));
//            dispo_usu.setCodDispousu(new BigDecimal(codigo_dispoUsu + 1));
//            dispo_usu.setDisponibilidad(dispo);
//            dispo_usu.setUsuarioByProfesor(temp);
//            dispo_usu.setUsuarioByAdmon(temp2);
//            boolean dis_usu = disponi.InsertDispo_Usu(dispo_usu);
//            if (dis_usu) {
//                boolean dispo_dias = disponi.Insert_Dias(dispo, Dias);
//                if (dispo_dias) {
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok", ""));
//                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("Datos_ajuste");
//                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("Dias_ajuste");
//                    FacesContext.getCurrentInstance().getExternalContext().redirect("ValidarProfesor.xhtml");
//                } else {
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error Comunicarce Con el Administrador", ""));
//                }
//            } else {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error Comunicarce Con el Administrador", ""));
//            }
//        } else {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error Comunicarce Con el Administrador", ""));
//        }
//
//    }

    public int validarPeriodo(Date x) {
//        valida la fecha que se ingreso que sea la correcta 
//        correcta en año y fechas no menores a la de hoy
        System.out.println("Fecha es " + x);
        int periodo = 0;
        if (x.getMonth() >= 1 && x.getMonth() <= 6) {
            periodo = 1;
        } else {
            periodo = 2;
        }
        System.out.println("perido " + periodo);
        return periodo;
    }
//

    public Usuario Crearusuario() {
        Usuario temp = new Usuario();
        Usuario u = new Usuario();
        try {

            Profesor profe = null;
            profe = (Profesor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("profesor");
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction t = session.beginTransaction();
            u = (Usuario) session.createQuery("FROM Usuario as usu where usu.pegeId=" + profe.getCedula()).uniqueResult();
            if (u != null) {
                System.out.println("ya existe");
                temp = u;
            } else {
                temp.setPegeId(new BigDecimal(profe.getCedula()));
                temp.setUsuario(profe.getNombre());
                temp.setContrasea("123456");
                session.save(temp);
                t.commit();
            }

        } catch (Exception ex) {
            System.out.println("Error de Insert");
            System.out.println("error " + ex.getMessage());
//            Usuario temp2 = null;
//            temp = temp2;
        }
        return temp;
    }

    public ArrayList<MDias> getDias() {
        return Dias;
    }

    public void setDias(ArrayList<MDias> Dias) {
        this.Dias = Dias;
    }

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

}
