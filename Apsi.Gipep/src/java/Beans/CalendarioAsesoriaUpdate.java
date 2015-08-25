/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.Asesoria;
import Entity.DetalleAsesoria;
import Entity.Proyectos;
import Modelo.Conecion_postgres1;
import Modelo.ModeloAsesoria;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Britt
 */
@ManagedBean
@SessionScoped
public class CalendarioAsesoriaUpdate {

    /**
     * Creates a new instance of CalendarioAsesoriaUpdate
     */
    private String horaI;
    private String horaF;
    private String nombre_proyecto;
    private String Estudiante;
    private String porcentaje;
    private String detalle_Asesoria;
    ArrayList<ModeloAsesoria> list_tareas = new ArrayList();

    public CalendarioAsesoriaUpdate() {
    }

    public void cargarInformacion() throws ParseException {
        horaI = "";
        horaF="";
        nombre_proyecto="";
        Estudiante="";
        porcentaje="";
        detalle_Asesoria="";
        list_tareas.clear();

        String cod = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Codigo_asesoria");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Asesoria a = new Asesoria();
        Proyectos p = new Proyectos();
        DetalleAsesoria d = new DetalleAsesoria();

        try {
            a = (Asesoria) session.createQuery("select A from Asesoria A where A.codAsesoria=" + cod).uniqueResult();
            horaI = a.getHoraIni().toString();
            horaF = a.getHoraFin().toString();
            p = (Proyectos) session.createQuery("select P from Proyectos P where P.codigoProyecto=" + a.getCodigoProyecto()).uniqueResult();
            nombre_proyecto = p.getNombre();
            porcentaje = p.getPorcentaje() + " %";
            t.commit();
            DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
            String convertido = fechaHora.format(a.getFechaAsesoria());
            int cod_ase = TraerASesoriaPasada(convertido);
            traerDetalle(cod_ase);
            traerTareas(cod_ase);
        } catch (Exception ex) {
            System.out.println("Error " + ex.toString());
        }

    }

    public void traerDetalle(int cod_ase) throws ClassNotFoundException {
        Conecion_postgres1.conectar();
        Conecion_postgres1.ejecuteQuery("select  detalle_asesoria.descripcion \n"
                + "from asesoria,detalle_asesoria\n"
                + "where\n"
                + " asesoria.cod_asesoria=detalle_asesoria.cod_asesoria\n"
                + "and asesoria.cod_asesoria=" + cod_ase + "");
        ArrayList<String> detalles = new ArrayList();
        try {
            while (Conecion_postgres1.rs.next()) {
                detalles.add(Conecion_postgres1.rs.getString(1));
            }
            System.out.println("detalle : " + detalles.get(0) + " size:" + detalles.size());
            detalle_Asesoria = detalles.get(0);
            Conecion_postgres1.cerrarConexion();
        } catch (Exception ex) {
            System.out.println("Error traerDetalle " + ex.toString());
        }
    }

    public void traerTareas(int cod_ase) throws ClassNotFoundException {
        Conecion_postgres1.conectar();
        list_tareas.clear();
        System.out.println("entro a tareas");
        Conecion_postgres1.ejecuteQuery("select  tareas.* \n"
                + "from asesoria,tareas\n"
                + "where\n"
                + " asesoria.cod_asesoria=tareas.cod_asesoria\n"
                + "and asesoria.cod_asesoria=" + cod_ase);

        try {
            while (Conecion_postgres1.rs.next()) {
                list_tareas.add(new ModeloAsesoria(Conecion_postgres1.rs.getString(2), Conecion_postgres1.rs.getString(3)));
            }
            System.out.println("tamaño tareas " + list_tareas.size());
            Conecion_postgres1.cerrarConexion();
        } catch (Exception ex) {
            System.out.println("Error Tareas " + ex.toString());
        }
    }

    public int TraerASesoriaPasada(String fecha) throws ClassNotFoundException {
        Conecion_postgres1.conectar();
        int cod = 0;
        Conecion_postgres1.ejecuteQuery("select cod_asesoria,fecha_asesoria from asesoria\n"
                + "where fecha_asesoria<'" + fecha + "'\n"
                + " group by cod_asesoria,fecha_asesoria \n"
                + " order by cod_asesoria DESC");
        ArrayList<Integer> codigos = new ArrayList();
        try {
            while (Conecion_postgres1.rs.next()) {
                codigos.add(Conecion_postgres1.rs.getInt(1));
            }
        } catch (Exception ex) {
            System.out.println("Error Traer codigo asesoria pasado " + ex.toString());
        }

        return codigos.get(0);
    }

    public String getHoraI() {
        return horaI;
    }

    public void setHoraI(String horaI) {
        this.horaI = horaI;
    }

    public String getHoraF() {
        return horaF;
    }

    public void setHoraF(String horaF) {
        this.horaF = horaF;
    }

    public String getNombre_proyecto() {
        return nombre_proyecto;
    }

    public void setNombre_proyecto(String nombre_proyecto) {
        this.nombre_proyecto = nombre_proyecto;
    }

    public String getEstudiante() {
        return Estudiante;
    }

    public void setEstudiante(String Estudiante) {
        this.Estudiante = Estudiante;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getDetalle_Asesoria() {
        return detalle_Asesoria;
    }

    public void setDetalle_Asesoria(String detalle_Asesoria) {
        this.detalle_Asesoria = detalle_Asesoria;
    }

    public ArrayList<ModeloAsesoria> getList_tareas() {
        return list_tareas;
    }

    public void setList_tareas(ArrayList<ModeloAsesoria> list_tareas) {
        this.list_tareas = list_tareas;
    }

}
