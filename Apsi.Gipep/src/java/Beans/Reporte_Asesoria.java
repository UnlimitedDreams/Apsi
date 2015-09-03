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
import Modelo.ProyectosModelo;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Britt
 */
@ManagedBean
@SessionScoped
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
        System.out.println("Buscar");
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
        Conecion_postgres1.ejecuteQuery("select asesoria.cod_asesoria,codigo_proyecto,fecha_asesoria from asesoria,asistente\n"
                + "where\n"
                + "asesoria.cod_asesoria=asistente.cod_asesoria\n"
                + "and asesoria.fecha_asesoria='" + fecha + "'\n"
                + "and asistente.asistencia='" + estado_ase + "'\n"
                );

        try {
            while (Conecion_postgres1.rs.next()) {
                System.out.println("----- estado " + estado_ase);
                ModeloAsesoria m = new ModeloAsesoria(Conecion_postgres1.rs.getInt(1),
                        Conecion_postgres1.rs.getInt(2), Conecion_postgres1.rs.getDate(3),
                        estado_ase);

                m.setCantidad(0);
                list_aseso.add(m);
            }
            System.out.println("lista asesos : " + list_aseso.size());
            traerProyecetos();
        } catch (Exception ex) {
            System.out.println("Error " + ex.toString());
        } finally {
            Conecion_postgres1.cerrarConexion();
        }

    }
   

    public void reporteHorasProfePDf() throws JRException {
        ModeloAsesoria temp = null;
        System.out.println("proyectos size " + list_aseso.size());
        Report_Asesoria datasource = new Report_Asesoria();

        try {
            for (int i = 0; i < list_aseso.size(); i++) {
                temp = (ModeloAsesoria) list_aseso.get(i);
                System.out.println("-");
                datasource.addObjecto(temp);
            }
            System.out.println("+++++ ::::");
            Date fec = new Date();
            DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd ");
            String convertido = fechaHora.format(fec);
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("Fecha", convertido);
            JasperPrint jasperPrint = JasperFillManager.fillReport("d:\\Users\\USR_Toshiba\\Desktop\\Apsi\\Apsi\\Apsi.Gipep\\web\\Reportes\\Asesorias.jasper", parametros, datasource);
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            String user = System.getProperty("user.name");
            File f = new File("C:\\Users\\" + user + "\\Downloads\\reporteAsesorias.pdf");
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, f);
            exporter.exportReport();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Descarga Pdf Exitosa", ""));

        } catch (Exception ex) {
            System.out.println("Error : " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Pdf Descarga ", ""));

        }

    }

    public void traerProyecetos() throws ClassNotFoundException {
        Conecion_postgres1.conectar();
        System.out.println("buscar proyecto size " + list_aseso.size());
        ModeloAsesoria temp = null;
        try {
            for (int i = 0; i < list_aseso.size(); i++) {
                temp = (ModeloAsesoria) list_aseso.get(i);
                System.out.println("codigo " + temp.getCod_proyecto());
                Conecion_postgres1.ejecuteQuery("select nombre,porcentaje from proyectos  where codigo_proyecto=" + temp.getCod_proyecto());
                while (Conecion_postgres1.rs.next()) {
                    temp.setNombre_proyecto(Conecion_postgres1.rs.getString(1));
                    temp.setPorcentaje(Conecion_postgres1.rs.getString(2));
                }
            }
        } catch (Exception ex) {
            System.out.println("error traernombre " + ex.toString());
        } finally {
            Conecion_postgres1.cerrarConexion();

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
