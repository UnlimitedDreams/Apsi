/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.Asesoria;
import Entity.DetalleAsesoria;
import Entity.Proyectos;
import Entity.Tareas;
import Modelo.Conecion_postgres1;
import Modelo.ModeloAsesoria;
import java.math.BigDecimal;
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
        horaF = "";
        nombre_proyecto = "";
        Estudiante = "";
        porcentaje = "";
        detalle_Asesoria = "";
        list_tareas.clear();
        String cod = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Codigo_asesoria");
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        DetalleAsesoria d = new DetalleAsesoria();
        try {
            Asesoria a = (Asesoria) session.load(Asesoria.class, new BigDecimal(Integer.parseInt(cod)));
            horaI = a.getHoraIni().toString();
            horaF = a.getHoraFin().toString();
            Proyectos p = (Proyectos) session.load(Proyectos.class, a.getCodigoProyecto());
            nombre_proyecto = p.getNombre();
            porcentaje = p.getPorcentaje() + " %";
            DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
            String convertido = fechaHora.format(a.getFechaAsesoria());
            t.commit();
            traerDetalle(a);
            traerTareas(a);

        } catch (Exception ex) {
            System.out.println("Error cargar" + ex.toString());
        }

    }

    public void traerDetalle(Asesoria ase) throws ClassNotFoundException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        System.out.println("Entroo detalle");

//        Conecion_postgres1.conectar();
//        Conecion_postgres1.ejecuteQuery("select  detalle_asesoria.descripcion \n"
//                + "from asesoria,detalle_asesoria\n"
//                + "where\n"
//                + " asesoria.cod_asesoria=detalle_asesoria.cod_asesoria\n"
//                + "and asesoria.cod_asesoria=" + ase.getCodAsesoria() + "");
        ArrayList<DetalleAsesoria> lisD = new ArrayList();
        ArrayList<String> detalles = new ArrayList();
        try {
            lisD = (ArrayList) session.createQuery("select D from Asesoria A inner join "
                    + " A.detalleAsesorias D where A.fechaAsesoria<='" + ase.getFechaAsesoria() + "' "
                    + " and A.codigoProyecto=" + ase.getCodigoProyecto()).list();
            detalle_Asesoria = lisD.get(lisD.size() - 1).getDescripcion();
            System.out.println("size : " + lisD.get(lisD.size() - 1).getCodDetalle());
//            while (Conecion_postgres1.rs.next()) {
//                detalles.add(Conecion_postgres1.rs.getString(1));
//            }
//            System.out.println("detalle : " + detalles.get(0) + " size:" + detalles.size());
//            detalle_Asesoria = detalles.get(0);
//            Conecion_postgres1.cerrarConexion();
            t.commit();
        } catch (Exception ex) {
            System.out.println("Error traerDetalle " + ex.toString());
        }
    }

    public void traerTareas(Asesoria ase) throws ClassNotFoundException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        System.out.println("Entroo Tareas");

        ArrayList<Tareas> listT = new ArrayList();
//        ArrayList<String> detalles = new ArrayList();
        ArrayList<Asesoria> listA = new ArrayList();
        list_tareas.clear();
        try {
            listA = (ArrayList) session.createQuery("select A from Asesoria A where "
                    + "A.fechaAsesoria<'" + ase.getFechaAsesoria() + "' "
                    + "and A.codigoProyecto=" + ase.getCodigoProyecto()+" "
                    + " order by A.fechaAsesoria DESC").list();
            
            for(Asesoria a : listA){
                System.out.println("---- : " + a.getFechaAsesoria());
            }
            
            System.out.println("size Asesoria : " + listA.size());
            listT = (ArrayList) session.createQuery("select t from Asesoria A "
                    + " inner join A.tareases t where "
                    + "A.codAsesoria=" + listA.get(0).getCodAsesoria()).list();
            System.out.println("Size tarea : " +listT.size() +" cod : " +listA.get(0).getCodAsesoria());
            for (Tareas ta : listT) {
                System.out.println("----");
                list_tareas.add(new ModeloAsesoria(ta.getResponsable(), ta.getTarea()));
            }
            t.commit();

        } catch (Exception ex) {
            System.out.println("Error tareas " + ex.toString());
        }

//        Conecion_postgres1.conectar();
//        list_tareas.clear();
//        System.out.println("entro a tareas");
//        Conecion_postgres1.ejecuteQuery("select  tareas.* \n"
//                + "from asesoria,tareas\n"
//                + "where\n"
//                + " asesoria.cod_asesoria=tareas.cod_asesoria\n"
//                + "and asesoria.cod_asesoria=" + cod_ase);
//
//        try {
//            while (Conecion_postgres1.rs.next()) {
//                list_tareas.add(new ModeloAsesoria(Conecion_postgres1.rs.getString(2), Conecion_postgres1.rs.getString(3)));
//            }
//            System.out.println("tamaño tareas " + list_tareas.size());
//            Conecion_postgres1.cerrarConexion();
//        } catch (Exception ex) {
//            System.out.println("Error Tareas " + ex.toString());
//        }
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
