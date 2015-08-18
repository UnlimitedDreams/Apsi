/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.Usuario;
import Modelo.Conecion_postgres;
import Modelo.Conecion_postgres1;
import Modelo.R_profesor;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 *
 * @author Britt
 */
@ManagedBean
@SessionScoped
public class Reporte_Profesor {

    /**
     * Creates a new instance of prueba
     */
    JasperPrint v_jasperPrint;
    ArrayList<R_profesor> lista_Profe = new ArrayList();
    ArrayList<String> b_año = new ArrayList();
    ArrayList<String> peri = new ArrayList();
    private String año;
    private String periodo;

    public Reporte_Profesor() {

    }

    public void cargar_años() {
        System.out.println("entro a cargar");
        b_año.clear();
        for (int i = 2015; i < 2030; i++) {
            b_año.add("" + i);
        }
        cargar_Periodo();
    }

    public void cargar_Periodo() {
        System.out.println("entro a cargar");
        peri.clear();
        for (int i = 1; i <= 2; i++) {
            peri.add("" + i);
        }
    }

    public void reportPDF() {
        try {
            System.out.println("entro");
            Connection con = null;
            con = Conecion_postgres1.conectar();
            String fechaI = "", FechaF = "";
            fechaI = "01-01-" + año;
            FechaF = "31-12-" + año;
            Object o = null;

            String query = "select disponibilidad.cod_dis,persona.idpersona,nombres,apellidos,disponibilidad.num_horas \"Horas Asignadas\",disponibilidad.horas_cumplidas \"Hora Relizadas\" \n"
                    + "                            from usuario,dispo_usuario,disponibilidad,persona\n"
                    + "                             where\n"
                    + "                            usuario.pege_id=dispo_usuario.profesor and \n"
                    + "                            usuario.pege_id=persona.pege_id and\n"
                    + "                             dispo_usuario.cod_dis=disponibilidad.cod_dis \n"
                    + "                             and disponibilidad.periodo=" + periodo + "\n"
                    + "                               and fecha_inicial>='" + fechaI + "' "
                    + "and fecha_final<='" + FechaF + "'";
            o = query;
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("Query", o);
            System.out.println("query " + o);
            JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\Britt\\Desktop\\Apsi.git\\trunk\\Apsi.Gipep\\web\\Reportes\\ProfesoresHoras.jasper", parametros, con);
            System.out.println("1");
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            System.out.println("2");
            String user = System.getProperty("user.name");
            File f = new File("C:\\Users\\" + user + "\\Downloads\\reportePDFfinal.pdf");
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, f);
            System.out.println("3");
            exporter.exportReport();
            System.out.println("4");
            Conecion_postgres1.cerrarConexion();
        } catch (Exception ex) {
            System.out.println("ERROR " + ex.toString());
        }
    }

    public void reportExcel() {
        try {
            System.out.println("entro");
            Connection con = null;
            con = Conecion_postgres1.conectar();
            String fechaI = "", FechaF = "";
            fechaI = "01-01-" + año;
            FechaF = "31-12-" + año;
            Object o = null;

            String query = "select disponibilidad.cod_dis,persona.idpersona,nombres,apellidos,disponibilidad.num_horas \"Horas Asignadas\",disponibilidad.horas_cumplidas \"Hora Relizadas\" \n"
                    + "                            from usuario,dispo_usuario,disponibilidad,persona\n"
                    + "                             where\n"
                    + "                            usuario.pege_id=dispo_usuario.profesor and \n"
                    + "                            usuario.pege_id=persona.pege_id and\n"
                    + "                             dispo_usuario.cod_dis=disponibilidad.cod_dis \n"
                    + "                             and disponibilidad.periodo=" + periodo + "\n"
                    + "                               and fecha_inicial>='" + fechaI + "' "
                    + "and fecha_final<='" + FechaF + "'";
            o = query;
            Map<String, Object> parametros = new HashMap<String, Object>();
            System.out.println("query " + o);
            parametros.put("Query", o);
            JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\Britt\\Desktop\\Apsi.git\\trunk\\Apsi.Gipep\\web\\Reportes\\ProfesoresHoras.jasper", parametros, con);
            System.out.println("1");
            JRExporter exporter = new JRXlsxExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            System.out.println("2");
            String user = System.getProperty("user.name");
            File f = new File("C:\\Users\\" + user + "\\Downloads\\reporteXlsfinal.xlsx");
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, f);
            System.out.println("3");
            exporter.exportReport();
            System.out.println("4");
            Conecion_postgres1.cerrarConexion();
        } catch (Exception ex) {
            System.out.println("ERROR " + ex.toString());
        }
    }

//    public void llenar() throws ClassNotFoundException {
//        Conecion_postgres1.conectar();
//        Conecion_postgres1.ejecuteQuery("select * from usuario");
//        v_listOfUser.clear();
//        try {
//            while (Conecion_postgres1.rs.next()) {
//                v_listOfUser.add(new Usuario(Conecion_postgres1.rs.getBigDecimal(1),
//                        Conecion_postgres1.rs.getString(2), Conecion_postgres1.rs.getString(3)));
//            }
//            Conecion_postgres1.cerrarConexion();
//        } catch (Exception ex) {
//            System.out.println("Error " + ex.toString());
//        }
//
//    }
    public R_profesor TraerInfo(String pege_id) throws ClassNotFoundException {
        Conecion_postgres.conectar();
        System.out.println("pege_id " +pege_id);
        Conecion_postgres.ejecuteQuery("select * from profesor where pege_id=" + pege_id);
        R_profesor p = new R_profesor();

        try {
            while (Conecion_postgres.rs.next()) {
                System.out.println("entro");
                p.setCedula(Conecion_postgres.rs.getString(5));
                p.setNombre(Conecion_postgres.rs.getString(2));
                p.setApellido(Conecion_postgres.rs.getString(3));

            }
            System.out.println("trajo " + p.getCedula());
            Conecion_postgres.cerrarConexion();
        } catch (Exception ex) {
            System.out.println("Error traerinfo " + ex.toString());
        }
        return p;

    }

//    public void reporteHorasProfePDf() throws JRException {
//        System.out.println("--------- size " + lista_Profe.size());
//        Report_profe datasource = new Report_profe();
//        R_profesor temp = null;
//        for (int i = 0; i < lista_Profe.size(); i++) {
//            temp = (R_profesor) lista_Profe.get(i);
//            System.out.println("-");
//            datasource.addObjecto(temp);
//        }
//        JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\Britt\\Desktop\\Apsi.git\\trunk\\Apsi.Gipep\\web\\Reportes\\ProfesoresHoras.jasper", null, datasource);
//        JRExporter exporter = new JRPdfExporter();
//        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//        String user = System.getProperty("user.name");
//        File f = new File("C:\\Users\\" + user + "\\Downloads\\reportePDF22.pdf");
//        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, f);
//        exporter.exportReport();
//    }

//    public void reporteHorasProfeXml() throws JRException {
//        System.out.println("--------- size " + lista_Profe.size());
//        Report_profe datasource = new Report_profe();
//        R_profesor temp = null;
//        for (int i = 0; i < lista_Profe.size(); i++) {
//            temp = (R_profesor) lista_Profe.get(i);
//            System.out.println("-");
//            datasource.addObjecto(temp);
//        }
//        JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\Britt\\Desktop\\Apsi.git\\trunk\\Apsi.Gipep\\web\\Reportes\\ProfesoresHoras.jasper", null, datasource);
//        JRExporter exporter = new JRXlsxExporter();
//        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//        String user = System.getProperty("user.name");
//        File f = new File("C:\\Users\\" + user + "\\Downloads\\reportexml.xlsx");
//        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, f);
//        exporter.exportReport();
//    }

    public void llenarDatosProfe() throws ClassNotFoundException {

        Conecion_postgres1.conectar();
        lista_Profe.clear();
        String fechaI = "", FechaF = "";
        fechaI = "01-01-" + año;
        FechaF = "31-12-" + año;
        Conecion_postgres1.ejecuteQuery("select disponibilidad.cod_dis,persona.idpersona,nombres,apellidos,disponibilidad.num_horas \"Horas Asignadas\",disponibilidad.horas_cumplidas \"Hora Relizadas\" \n"
                + "                            from usuario,dispo_usuario,disponibilidad,persona\n"
                + "                             where\n"
                + "                            usuario.pege_id=dispo_usuario.profesor and \n"
                + "                            usuario.pege_id=persona.pege_id and\n"
                + "                             dispo_usuario.cod_dis=disponibilidad.cod_dis \n"
                + "                             and disponibilidad.periodo="+periodo+"\n"
                + "                               and fecha_inicial>='"+fechaI+"' and fecha_final<='"+FechaF+"'");
        try {
            while (Conecion_postgres1.rs.next()) {
                System.out.println("entro");
                R_profesor p = new R_profesor(
                        Conecion_postgres1.rs.getInt(5), Conecion_postgres1.rs.getInt(6));
                System.out.println("1");
                R_profesor R = new R_profesor();
                R = TraerInfo(Conecion_postgres1.rs.getString(2));
                 System.out.println("2 " + R.getCedula());
                p.setCedula(R.getCedula());
                p.setNombre(R.getNombre());
                p.setApellido(R.getApellido());
                System.out.println("3");
                lista_Profe.add(p);
            }
            FacesContext.getCurrentInstance().getExternalContext().responseReset();
            System.out.println("size horas " + lista_Profe.size());
            Conecion_postgres1.cerrarConexion();

        } catch (Exception ex) {
            System.out.println("Error llenarprofe" + ex.toString());
        }

    }

  

    public void mns() {
        System.out.println("entro");
    }

    public JasperPrint getV_jasperPrint() {
        return v_jasperPrint;
    }

    public void setV_jasperPrint(JasperPrint v_jasperPrint) {
        this.v_jasperPrint = v_jasperPrint;
    }

    public ArrayList<R_profesor> getLista_Profe() {
        return lista_Profe;
    }

    public void setLista_Profe(ArrayList<R_profesor> lista_Profe) {
        this.lista_Profe = lista_Profe;
    }

//   
    public ArrayList<String> getB_año() {
        return b_año;
    }

    public void setB_año(ArrayList<String> b_año) {
        this.b_año = b_año;
    }

    public ArrayList<String> getPeri() {
        return peri;
    }

    public void setPeri(ArrayList<String> peri) {
        this.peri = peri;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
