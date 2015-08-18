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
import Modelo.Carreras;
import Modelo.Conecion_postgres;
import Modelo.Conecion_postgres1;
import Modelo.Estudiante;
import Modelo.ProyectosModelo;
import Modelo.R_profesor;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.hibernate.Session;
import org.hibernate.Transaction;
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

    public Reporte_Proyectos() {

    }

    public void cargar_años() throws ClassNotFoundException {
        System.out.println("entro a cargar");
        b_año.clear();
        for (int i = 2015; i < 2030; i++) {
            b_año.add("" + i);
        }
        cargar_Periodo();
        cargarCarreras();
        cargarEstados();
        cargarTipos();
    }

    public void cargar_Periodo() {
        System.out.println("entro a cargar");
        peri.clear();
        for (int i = 1; i <= 2; i++) {
            peri.add("" + i);
        }
    }

    public void cargarCarreras() throws ClassNotFoundException {
        lista_Carrera.clear();
        Conecion_postgres.conectar();
        Conecion_postgres.ejecuteQuery("select * from carreras");
        try {
            while (Conecion_postgres.rs.next()) {
                lista_Carrera.add(new Carreras(Conecion_postgres.rs.getInt(1),
                        Conecion_postgres.rs.getString(2)));
            }
            Conecion_postgres.cerrarConexion();
        } catch (Exception ex) {
            System.out.println("Error carreras " + ex.toString());
        }

    }

    public void cargarEstados() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Lista_estados.clear();
        try {
            Lista_estados = (ArrayList) session.createQuery("from Estados").list();
        } catch (Exception ex) {
            System.out.println("Error estados " + ex.toString());
        }
    }

    public void cargarTipos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Lista_Tipo.clear();
        try {
            Lista_Tipo = (ArrayList) session.createQuery("from TipoProyecto").list();
            t.commit();
        } catch (Exception ex) {
            System.out.println("Error estados " + ex.toString());
        }
    }

    public void buscar() throws ClassNotFoundException {
        String query = "";
        System.out.println("año " + año);
        System.out.println("perido " + periodo);
        System.out.println("carrera " + cod_carrera);
        System.out.println("Estado " + cod_estado);
        System.out.println("tipo " + tipo);

        ArrayList temp = null;
        temp = TraerPege_idCArrera();
        System.out.println("Trajo " + temp.size() + " pegeId");
        Estudiante estu = null;
        ArrayList<Proyectos> Lista_Proyectos = new ArrayList();
        ArrayList proyec = null;
        Proyectos p = null;
        Proyectos p2 = null;
        boolean r = false;
        for (int i = 0; i < temp.size(); i++) {
            estu = (Estudiante) temp.get(i);
            proyec = Traer_proyectos(estu.getPege_id());
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
                System.out.println("Size " + Lista_Proyectos.size());
            }
            r = false;
        }
        Proyectos = pasarDatos(Lista_Proyectos);
        ProyectosModelo pro = null;
        ArrayList y = null;
        Estudiante estu2 = null;
        String integrantes = "";
        for (int i = 0; i < Proyectos.size(); i++) {
            pro = (ProyectosModelo) Proyectos.get(i);
            y = recuperarDatos("" + pro.getCodigo());

            for (int k = 0; k < y.size(); k++) {
                estu2 = (Estudiante) y.get(k);
                System.out.println("N- " + estu2.getNombre());
                integrantes = integrantes + estu2.getNombre() + "\n";
            }
            pro.setIntegrantes(integrantes);

            System.out.println("Integrantes son  " + integrantes);

            integrantes = "";
        }

        for (int i = 0; i < Proyectos.size(); i++) {
            pro = (ProyectosModelo) Proyectos.get(i);
            System.out.println("- " + Proyectos.get(i) + "\n");
        }
        FacesContext.getCurrentInstance().getExternalContext().responseReset();
    }

    public ArrayList recuperarDatos(String cod_pro) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        ArrayList<Usuario> Lista_Usuarios = new ArrayList();
        ArrayList x = null;
        try {
            System.out.println("pegeId " + cod_pro);
            Lista_Usuarios = (ArrayList) session.createQuery("select distinct U from Proyectos P INNER JOIN "
                    + "P.usuarioProyectos UP INNER JOIN UP.usuarioByEstudiante U "
                    + "where P.codigoProyecto=" + cod_pro).list();

            System.out.println("lista -- " + Lista_Usuarios.size());
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
                System.out.println("pege_id a recuperar " + temp.getPegeId());
                Conecion_postgres.ejecuteQuery("select * from estudiante where pege_id=" + temp.getPegeId());
                while (Conecion_postgres.rs.next()) {
                    System.out.println("Nombre que trajo " + Conecion_postgres.rs.getString(3));
                    nom = Conecion_postgres.rs.getString(3) + " " + Conecion_postgres.rs.getString(4);

                }
                Nombres.add(new Estudiante(nom));
            }

            for (int i = 0; i < Nombres.size(); i++) {
                System.out.println("Nom " + Nombres.get(i));
            }
            Conecion_postgres.cerrarConexion();
        } catch (Exception ex) {
            System.out.println("Error RecuperarNombres " + ex.toString());
        }
        return Nombres;
    }

    public ArrayList pasarDatos(ArrayList x) {
        ArrayList<ProyectosModelo> proyec = new ArrayList();
        Proyectos p = null;
        for (int i = 0; i < x.size(); i++) {
            p = (Proyectos) x.get(i);
            System.out.println("porcentajeeeee " + p.getPorcentaje());
            proyec.add(new ProyectosModelo(p.getCodigoProyecto().intValue(),
                    p.getNombre(), "", p.getFechaInicio().toString(), p.getFechaFinal().toString(), Integer.parseInt(p.getPorcentaje())));

        }
        return proyec;
    }

    public ArrayList Traer_proyectos(String x) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        ArrayList<Proyectos> Lista_Proyectos = new ArrayList();
        try {
            System.out.println("pegeId " + x);
            Lista_Proyectos = (ArrayList) session.createQuery("select distinct P from Proyectos P INNER JOIN "
                    + "P.usuarioProyectos UP INNER JOIN UP.usuarioByEstudiante U "
                    + "INNER JOIN P.estadoProyectos EP INNER JOIN EP.estados E INNER JOIN P.tipoProyecto T "
                    + "where U.pegeId=" + x + " and E.codigoEstados=" + cod_estado
                    + " and T.codTipo=" + tipo).list();

            System.out.println("lista -- " + Lista_Proyectos.size());
            t.commit();

        } catch (Exception ex) {

        }
        return Lista_Proyectos;
    }

    public ArrayList TraerPege_idCArrera() throws ClassNotFoundException {
        Conecion_postgres.conectar();
        Conecion_postgres.ejecuteQuery("select * from estudiante where tipo_carrera=" + cod_carrera);
        ArrayList<Estudiante> lista_pege_id = new ArrayList();
        try {
            while (Conecion_postgres.rs.next()) {
                lista_pege_id.add(new Estudiante(false, Conecion_postgres.rs.getString(2),
                        Conecion_postgres.rs.getString(3), Conecion_postgres.rs.getString(4), Conecion_postgres.rs.getString(1)));
            }
            Conecion_postgres.cerrarConexion();
        } catch (Exception ex) {
            System.out.println("Error Lista pege_id " + ex.toString());
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
            Conecion_postgres.cerrarConexion();
        } catch (Exception ex) {

        }
        return p;

    }

    public void reporteHorasProfePDf() throws JRException, ClassNotFoundException {
        System.out.println("--------- size " + Proyectos.size());
        Report_Proyecto datasource = new Report_Proyecto();
        ProyectosModelo temp = null;
        for (int i = 0; i < Proyectos.size(); i++) {
            temp = (ProyectosModelo) Proyectos.get(i);
            System.out.println("-");
            datasource.addObjecto(temp);
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\Britt\\Desktop\\Apsi.git\\trunk\\Apsi.Gipep\\web\\Reportes\\Proyectos.jasper", null, datasource);
        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        String user = System.getProperty("user.name");
        File f = new File("C:\\Users\\" + user + "\\Downloads\\reportePDFProyectos.pdf");
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, f);
        exporter.exportReport();
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
        JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\Britt\\Desktop\\Apsi.git\\trunk\\Apsi.Gipep\\web\\Reportes\\Proyectos.jasper", null, datasource);
        JRExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        String user = System.getProperty("user.name");
        File f = new File("C:\\Users\\" + user + "\\Downloads\\reporteExcel.xlsx");
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, f);
        exporter.exportReport();
    }

//    public void llenarDatosProfe() throws ClassNotFoundException {
//
//        Conecion_postgres1.conectar();
//        lista_Profe.clear();
//        String fechaI = "", FechaF = "";
//        fechaI = "01-01-" + año;
//        FechaF = "31-12-" + año;
//        Conecion_postgres1.ejecuteQuery("select disponibilidad.cod_dis,num_horas \"Horas Asignadas\",Count(asesoria.cod_dis) \"Hora Relizadas\" ,\n"
//                + "                usuario.pege_id\n"
//                + "               from usuario,dispo_usuario,disponibilidad,asesoria,asistente\n"
//                + "                where\n"
//                + "                usuario.pege_id=dispo_usuario.profesor and \n"
//                + "                dispo_usuario.cod_dis=disponibilidad.cod_dis and \n"
//                + "                disponibilidad.cod_dis=asesoria.cod_dis and\n"
//                + "                asesoria.cod_asesoria=asistente.cod_asesoria and periodo= " + periodo + "\n"
//                + "                and fecha_inicial>='" + fechaI + "' and fecha_final<='" + FechaF + "'\n"
//                + "                group by \n"
//                + "                disponibilidad.cod_dis,usuario.pege_id");
//        try {
//            while (Conecion_postgres1.rs.next()) {
//                System.out.println("entro");
//                R_profesor p = new R_profesor(
//                        Conecion_postgres1.rs.getInt(2), Conecion_postgres1.rs.getInt(3));
//                R_profesor R = new R_profesor();
//                R = TraerInfo(Conecion_postgres1.rs.getString(4));
//                p.setCedula(R.getCedula());
//                p.setNombre(R.getNombre());
//                p.setApellido(R.getApellido());
//                lista_Profe.add(p);
//            }
//            Conecion_postgres1.cerrarConexion();
//
//        } catch (Exception ex) {
//            System.out.println("Error " + ex.toString());
//        }
//
//    }

    public void mns() {
        System.out.println("entro");
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

}
