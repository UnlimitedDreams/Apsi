/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.Estados;
import Entity.Proyectos;
import Entity.TipoProyecto;
import Entity.Usuario;
import Entity.Versiones;
import Modelo.Carreras;
import Modelo.Conecion_postgres;
import Modelo.Conecion_postgres1;
import Modelo.Estudiante;
import Modelo.ProyectosModelo;
import Modelo.R_profesor;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
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
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.model.DefaultStreamedContent;
import util.HibernateUtil;

/**
 *
 * @author Britt
 */
@ManagedBean
@SessionScoped
public class Reporte_Proyectos {

    /**
     * Creates a new instance of prueba
     */
    JasperPrint v_jasperPrint;
    ArrayList<ProyectosModelo> Proyectos = new ArrayList();
    ArrayList<Carreras> lista_Carrera = new ArrayList();
    ArrayList<String> b_año = new ArrayList();
    ArrayList<String> peri = new ArrayList();
    ArrayList<Estados> Lista_estados = new ArrayList();
    ArrayList<TipoProyecto> Lista_Tipo = new ArrayList();
    private String año;
    private String periodo;
    private int cod_carrera;
    private int cod_estado;
    private int tipo;
    private String carrera;
    private DefaultStreamedContent download;

    public Reporte_Proyectos() {

    }

    public void cargar_años(int condi) throws ClassNotFoundException {
        b_año.clear();
        for (int i = 2015; i < 2030; i++) {
            b_año.add("" + i);
        }
        if (condi == 1) {
            cargar_Periodo();
            cargarCarreras();
            cargarEstados();
            cargarTipos();
        } else if (condi == 2) {
            cargar_Periodo();
            cargarCarreras();
            cargarTipos();
        }

    }

    public void cargar_Periodo() {
        peri.clear();
        for (int i = 1; i <= 2; i++) {
            peri.add("" + i);
        }
    }

    public void cargarCarreras() throws ClassNotFoundException {
        lista_Carrera.clear();
        Conecion_postgres.conectar();
        Conecion_postgres.ejecuteQuery("select * from facultad");
        try {
            while (Conecion_postgres.rs.next()) {
                lista_Carrera.add(new Carreras(Conecion_postgres.rs.getInt(1),
                        Conecion_postgres.rs.getString(2)));
            }

        } catch (Exception ex) {
            System.out.println("Error carreras " + ex.toString());
        } finally {
            Conecion_postgres.cerrarConexion();
        }

    }

    public void cargarEstados() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Lista_estados.clear();
        try {
            Lista_estados = (ArrayList) session.createQuery("from Estados").list();
            t.commit();
        } catch (Exception ex) {
            System.out.println("Error estados " + ex.toString());
        }
    }

    public void cargarTipos() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Lista_Tipo.clear();
        try {
            Lista_Tipo = (ArrayList) session.createQuery("from TipoProyecto").list();
            t.commit();
        } catch (Exception ex) {
            System.out.println("Error estados " + ex.toString());
        }
    }

    public void buscar(int condi) throws ClassNotFoundException {
        String query = "";
        ArrayList temp = null;
        temp = TraerPege_idCArrera();
        Estudiante estu = null;
        ArrayList<Proyectos> Lista_Proyectos = new ArrayList();
        ArrayList proyec = null;
        Proyectos p = null;
        Proyectos p2 = null;
        boolean r = false;
        for (int i = 0; i < temp.size(); i++) {
            estu = (Estudiante) temp.get(i);
            proyec = Traer_proyectos(estu.getPege_id(), condi);
            for (int k = 0; k < proyec.size(); k++) {
                p = (Proyectos) proyec.get(k);
                for (int j = 0; j < Lista_Proyectos.size(); j++) {
                    p2 = (Proyectos) Lista_Proyectos.get(j);
                    if (p2.getNombre().equals(p.getNombre())) {
                        r = true;
                    }
                }
                if (r != true) {
                    Lista_Proyectos.add(p);
                } else {
                    System.out.println("ya estaba");
                }
            }
            r = false;
        }
        Proyectos = pasarDatos(Lista_Proyectos, condi);
        ProyectosModelo pro = null;
        ArrayList y = null;
        Estudiante estu2 = null;
        String integrantes = "";
        for (int i = 0; i < Proyectos.size(); i++) {
            pro = (ProyectosModelo) Proyectos.get(i);
            y = recuperarDatos("" + pro.getCodigo());

            for (int k = 0; k < y.size(); k++) {
                estu2 = (Estudiante) y.get(k);
                integrantes = integrantes + estu2.getNombre() + "\n";
            }
            pro.setIntegrantes(integrantes);

            integrantes = "";
        }

        for (int i = 0; i < Proyectos.size(); i++) {
            pro = (ProyectosModelo) Proyectos.get(i);
            System.out.println("- " + Proyectos.get(i) + "\n");
        }
        FacesContext.getCurrentInstance().getExternalContext().responseReset();
    }

    public ArrayList recuperarDatos(String cod_pro) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        ArrayList<Usuario> Lista_Usuarios = new ArrayList();
        ArrayList x = null;
        try {
            Lista_Usuarios = (ArrayList) session.createQuery("select distinct U from Proyectos P INNER JOIN "
                    + "P.usuarioProyectos UP INNER JOIN UP.usuarioByEstudiante U "
                    + "where P.codigoProyecto=" + cod_pro).list();
            t.commit();
            x = RecuperarNombres(Lista_Usuarios);
        } catch (Exception ex) {
            System.out.println("Error recuperarDatos " + ex.toString());
        }
        return x;
    }

    public ArrayList RecuperarNombres(ArrayList x) throws ClassNotFoundException {
        Conecion_postgres.conectar();
        Usuario temp = null;
        ArrayList<Estudiante> Nombres = new ArrayList();
        String nom = "";
        try {
            for (int i = 0; i < x.size(); i++) {
                temp = (Usuario) x.get(i);
                Conecion_postgres.ejecuteQuery("select * from estudiante where pege_id=" + temp.getPegeId());
                while (Conecion_postgres.rs.next()) {
                    nom = Conecion_postgres.rs.getString(2) + " " + Conecion_postgres.rs.getString(3);

                }
                Nombres.add(new Estudiante(nom));
            }
        } catch (Exception ex) {
            System.out.println("Error RecuperarNombres " + ex.toString());
        } finally {
            Conecion_postgres.cerrarConexion();

        }
        return Nombres;
    }

    public ArrayList pasarDatos(ArrayList x, int condi) {
        ArrayList<ProyectosModelo> proyec = new ArrayList();
        Proyectos p = null;
        for (int i = 0; i < x.size(); i++) {
            p = (Proyectos) x.get(i);
            ProyectosModelo pr = new ProyectosModelo(p.getCodigoProyecto().intValue(),
                    p.getNombre(), "", p.getFechaInicio().toString(), p.getFechaFinal().toString(), Integer.parseInt(p.getPorcentaje()));
//            if (condi == 2) {
            String ruta = TraerRutaDescarga(p.getCodigoProyecto().intValue());
            pr.setRuta(ruta);
//            }
            proyec.add(pr);

        }
        return proyec;
    }

    public String TraerRutaDescarga(int cod) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        ArrayList<Versiones> v = new ArrayList();
        try {
            v = (ArrayList) session.createQuery("select V from Versiones V INNER JOIN "
                    + " V.proyectos P where P.codigoProyecto=" + cod
                    + "").list();
            t.commit();
        } catch (Exception ex) {
            System.out.println("Error " + ex.toString());
        }
        return v.get(v.size() - 1).getRutaArchivo();
    }

    public ArrayList Traer_proyectos(String x, int condi) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        ArrayList<Proyectos> Lista_Proyectos = new ArrayList();
        try {
            if (condi == 1) {
                Lista_Proyectos = (ArrayList) session.createQuery("select distinct P from Proyectos P INNER JOIN "
                        + "P.usuarioProyectos UP INNER JOIN UP.usuarioByEstudiante U "
                        + "INNER JOIN P.estadoProyectos EP INNER JOIN EP.estados E INNER JOIN P.tipoProyecto T "
                        + "where U.pegeId=" + x + " and E.codigoEstados=" + cod_estado
                        + " and T.codTipo=" + tipo).list();
            } else if (condi == 2) {
                if (tipo == 1) {
                    Lista_Proyectos = (ArrayList) session.createQuery("select distinct P from Proyectos P INNER JOIN "
                            + "P.usuarioProyectos UP INNER JOIN UP.usuarioByEstudiante U "
                            + "INNER JOIN P.estadoProyectos EP INNER JOIN EP.estados E INNER JOIN P.tipoProyecto T "
                            + "where U.pegeId=" + x + " and E.codigoEstados=" + 6
                            + " and T.codTipo=" + tipo).list();
                } else if (tipo == 2) {
                    Lista_Proyectos = (ArrayList) session.createQuery("select distinct P from Proyectos P INNER JOIN "
                            + "P.usuarioProyectos UP INNER JOIN UP.usuarioByEstudiante U "
                            + "INNER JOIN P.tipoProyecto T "
                            + "where U.pegeId=" + x
                            + " and T.codTipo=" + tipo).list();
                }

            }
            t.commit();

        } catch (Exception ex) {

        }
        return Lista_Proyectos;
    }

    public void prepDownload(String date) throws Exception {
        File file = new File(date);
        InputStream input = new FileInputStream(file);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        setDownload(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
    }

    public ArrayList TraerPege_idCArrera() throws ClassNotFoundException {
        Conecion_postgres.conectar();
        Conecion_postgres.ejecuteQuery("select * from estudiante where facultad='" + carrera + "'");
        ArrayList<Estudiante> lista_pege_id = new ArrayList();
        try {
            while (Conecion_postgres.rs.next()) {
                lista_pege_id.add(new Estudiante(false, Conecion_postgres.rs.getString(2),
                        Conecion_postgres.rs.getString(3), Conecion_postgres.rs.getString(4), Conecion_postgres.rs.getString(1)));
            }
        } catch (Exception ex) {
            System.out.println("Error Lista pege_id " + ex.toString());
        } finally {
            Conecion_postgres.cerrarConexion();

        }
        return lista_pege_id;
    }

//    public void report() {
//        try {
//            System.out.println("entro");
//            Connection con = null;
//            con = Conecion_postgres1.conectar();
//            JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\Britt\\Desktop\\Apsi.git\\trunk\\Apsi.Gipep\\web\\Reportes\\report1.jasper", null, con);
//            JRExporter exporter = new JRPdfExporter();
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//            String user = System.getProperty("user.name");
//            File f = new File("C:\\Users\\" + user + "\\Downloads\\reportePDF.pdf");
//            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, f);
//            exporter.exportReport();
//            Conecion_postgres1.cerrarConexion();
//        } catch (Exception ex) {
//            System.out.println("ERROR " + ex.toString());
//        }
//    }
    public R_profesor TraerInfo(String pege_id) throws ClassNotFoundException {
        Conecion_postgres.conectar();
        Conecion_postgres.ejecuteQuery("select * from profesor where pege_id=" + pege_id);
        R_profesor p = new R_profesor();

        try {
            while (Conecion_postgres.rs.next()) {
                p.setCedula(Conecion_postgres.rs.getString(5));
                p.setNombre(Conecion_postgres.rs.getString(2));
                p.setApellido(Conecion_postgres.rs.getString(3));

            }
        } catch (Exception ex) {

        } finally {
            Conecion_postgres.cerrarConexion();

        }
        return p;

    }

    public void reporteHorasProfePDf() throws JRException, ClassNotFoundException {
        Report_Proyecto datasource = new Report_Proyecto();
        ProyectosModelo temp = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        System.out.println("proyectos size " + Proyectos.size());
        try {
            for (int i = 0; i < Proyectos.size(); i++) {
                temp = (ProyectosModelo) Proyectos.get(i);
                datasource.addObjecto(temp);
            }
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("Año", año);
            parametros.put("Carrera", carrera);
            TipoProyecto tip=(TipoProyecto)session.load(TipoProyecto.class, new Long(tipo));
            parametros.put("Tipo", tip.getDescripcion());

            JasperPrint jasperPrint = JasperFillManager.fillReport("d:\\Users\\USR_Toshiba\\Desktop\\Apsi\\Apsi\\Apsi.Gipep\\web\\Reportes\\Proyectos.jasper", parametros, datasource);
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            String user = System.getProperty("user.name");
            File f = new File("C:\\Users\\" + user + "\\Downloads\\reportePDFProyectos.pdf");
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, f);
            exporter.exportReport();
            t.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Descarga Pdf Exitosa", ""));

        } catch (Exception ex) {
            System.out.println("Error : " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Pdf Descarga ", ""));

        }

    }
//

    public void reporteHorasProfeXml() throws JRException {
        System.out.println("--------- size " + Proyectos.size());
        Report_Proyecto datasource = new Report_Proyecto();
        ProyectosModelo temp = null;
        for (int i = 0; i < Proyectos.size(); i++) {
            temp = (ProyectosModelo) Proyectos.get(i);
            System.out.println("-");
            datasource.addObjecto(temp);
        }
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport("d:\\Users\\USR_Toshiba\\Desktop\\Apsi\\Apsi\\Apsi.Gipep\\web\\Reportes\\Proyectos.jasper", null, datasource);
            JRExporter exporter = new JRXlsxExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            String user = System.getProperty("user.name");
            File f = new File("C:\\Users\\" + user + "\\Downloads\\reporteExcel.xlsx");
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, f);
            exporter.exportReport();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Descarga Excel Exitosa", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Excel Descarga ", ""));

        }

    }

    public JasperPrint getV_jasperPrint() {
        return v_jasperPrint;
    }

    public void setV_jasperPrint(JasperPrint v_jasperPrint) {
        this.v_jasperPrint = v_jasperPrint;
    }

    public ArrayList<ProyectosModelo> getProyectos() {
        return Proyectos;
    }

    public void setProyectos(ArrayList<ProyectosModelo> Proyectos) {
        this.Proyectos = Proyectos;
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

    public ArrayList<Carreras> getLista_Carrera() {
        return lista_Carrera;
    }

    public void setLista_Carrera(ArrayList<Carreras> lista_Carrera) {
        this.lista_Carrera = lista_Carrera;
    }

    public int getCod_carrera() {
        return cod_carrera;
    }

    public void setCod_carrera(int cod_carrera) {
        this.cod_carrera = cod_carrera;
    }

    public ArrayList<Estados> getLista_estados() {
        return Lista_estados;
    }

    public void setLista_estados(ArrayList<Estados> Lista_estados) {
        this.Lista_estados = Lista_estados;
    }

    public int getCod_estado() {
        return cod_estado;
    }

    public void setCod_estado(int cod_estado) {
        this.cod_estado = cod_estado;
    }

    public ArrayList<TipoProyecto> getLista_Tipo() {
        return Lista_Tipo;
    }

    public void setLista_Tipo(ArrayList<TipoProyecto> Lista_Tipo) {
        this.Lista_Tipo = Lista_Tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public DefaultStreamedContent getDownload() {
        return download;
    }

    public void setDownload(DefaultStreamedContent download) {
        this.download = download;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

}
