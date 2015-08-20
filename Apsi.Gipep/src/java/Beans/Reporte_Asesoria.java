/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.Asesoria;
import Entity.Asistente;
import Modelo.Conecion_postgres1;
import Modelo.ModeloAsesoria;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Britt
 */
@ManagedBean
@RequestScoped
public class Reporte_Asesoria {

    /**
     * Creates a new instance of Reporte_Asesoria
     */
    private Date fecha;
    private String estado_ase;
    ArrayList<String> Estados = new ArrayList();
    ArrayList<ModeloAsesoria> list_aseso = new ArrayList();

    public Reporte_Asesoria() {
    }

    public void buscar() throws ClassNotFoundException {
        asesorias();
    }

    public void asesorias() throws ClassNotFoundException {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction t = session.beginTransaction();
//        ArrayList<Asesoria> A = new ArrayList();
//
//        try {
//            A = (ArrayList) session.createQuery("select A from Asesoria A "
//                    + "INNER JOIN A.asistentes AS where A.fechaAsesoria='" + fecha + "' "
//                    + "and AS.asistencia=" + estado_ase).list();
//        } catch (Exception ex) {
//            System.out.println("Error " + ex.toString());
//        }
        Conecion_postgres1.conectar();
        Conecion_postgres1.ejecuteQuery("select asesoria.cod_asesoria,codigo_proyecto,fecha_asesoria ,count(asesoria.cod_asesoria)from asesoria,asistente\n"
                + "where\n"
                + "asesoria.cod_asesoria=asistente.cod_asesoria\n"
                + "and asesoria.fecha_asesoria='" + fecha + "'\n"
                + "and asistente.asistencia='" + estado_ase + "'\n"
                + "group by\n"
                + "asesoria.cod_asesoria,codigo_proyecto,fecha_asesoria ");
        System.out.println("select asesoria.cod_asesoria,codigo_proyecto,fecha_asesoria ,count(asesoria.cod_asesoria)from asesoria,asistente\n"
                + "where\n"
                + "asesoria.cod_asesoria=asistente.cod_asesoria\n"
                + "and asesoria.fecha_asesoria='" + fecha + "'\n"
                + "and asistente.asistencia='" + estado_ase + "'\n"
                + "group by\n"
                + "asesoria.cod_asesoria,codigo_proyecto,fecha_asesoria ");
        try {
            while (Conecion_postgres1.rs.next()) {
                ModeloAsesoria m = new ModeloAsesoria(Conecion_postgres1.rs.getInt(1),
                        Conecion_postgres1.rs.getString(3), Conecion_postgres1.rs.getDate(2));
//                m.setCantidad(Conecion_postgres1.rs.getInt(4));
                list_aseso.add(m);
            }
            Conecion_postgres1.cerrarConexion();
            traerProyecetos();
        } catch (Exception ex) {
            System.out.println("Error " + ex.toString());
        }

    }

    public void traerProyecetos() throws ClassNotFoundException {
        Conecion_postgres1.conectar();
        System.out.println("buscar proyecto");
        ModeloAsesoria temp = null;
        try {
            for (int i = 0; i < list_aseso.size(); i++) {
                temp = (ModeloAsesoria) list_aseso.get(i);
                System.out.println("codigo " + temp.getCod_proyecto());
                Conecion_postgres1.ejecuteQuery("select nombre,porcentaje from proyectos  where codigo_proyecto=" + temp.getNombre_proyecto());
                while (Conecion_postgres1.rs.next()) {
                    System.out.println("cambio nombre por " + Conecion_postgres1.rs.getString(1));
                    temp.setNombre_proyecto(Conecion_postgres1.rs.getString(1));
                    temp.setPorcentaje(Conecion_postgres1.rs.getString(2));
                }
            }
            Conecion_postgres1.cerrarConexion();
        } catch (Exception ex) {
            System.out.println("error traernombre " + ex.toString());
        }

    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ArrayList<String> getEstados() {
        return Estados;
    }

    public void setEstados(ArrayList<String> Estados) {
        this.Estados = Estados;
    }

    public String getEstado_ase() {
        return estado_ase;
    }

    public void setEstado_ase(String estado_ase) {
        this.estado_ase = estado_ase;
    }

    public ArrayList<ModeloAsesoria> getList_aseso() {
        return list_aseso;
    }

    public void setList_aseso(ArrayList<ModeloAsesoria> list_aseso) {
        this.list_aseso = list_aseso;
    }

}
