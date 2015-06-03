///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Beans;
//
//import Dao.Sequence;
//import Entity.Proyectos;
//import java.io.IOException;
//import java.io.Serializable;
//import java.math.BigDecimal;
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//import javax.faces.context.FacesContext;
//import javax.servlet.http.Part;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import util.HibernateUtil;
//
///**
// *
// * @author Microinformatica
// */
//@ManagedBean
//@SessionScoped
//public class CargarArchivos {
//
//    private Part file1;
//    private String nombre;
////    ProyectosDao pro = new ProyectosImple();
//
//    public CargarArchivos() {
//    }
//
//    public void upload() throws IOException {
//        System.out.println("nombre " + nombre );
//        Query query = Sequence.GetUltimoRegistro("FROM Proyectos order by codigoProyecto DESC");
//        System.out.println("---->" + query.uniqueResult());
//        String cod_salvador = "" + query.uniqueResult();
//        int Codigo_Proyecto = Integer.parseInt(cod_salvador);
//        System.out.println("cod " + Codigo_Proyecto);
//        Proyectos proyecto = new Proyectos();
//        proyecto.setCodigoProyecto(new BigDecimal(Codigo_Proyecto + 1));
//        proyecto.setNombre(nombre);
//        String ruta = "";
//        ruta = "C:\\Documentos\\" + getFilename(file1);
//        proyecto.setRuta(ruta);
//        System.out.println("proyecto " + proyecto.getNombre());
//        boolean r = insertProyect(proyecto);
//        System.out.println("r " + r);
//        if (r) {
//            file1.write("C:\\Documentos\\" + getFilename(file1));
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Executed", ""));
//        } else {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro", ""));
//        }
//    }
//
//    public boolean insertProyect(Proyectos p) {
//        boolean r = false;
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction t = session.beginTransaction();
//        try {
//            session.save(p);
//            r = true;
//            t.commit();
//        } catch (Exception ex) {
//            r = false;
//            System.out.println("Error - " + ex.getMessage());
//        }
//
//        return r;
//    }
//
//    private static String getFilename(Part part) {
//        for (String cd : part.getHeader("content-disposition").split(";")) {
//            if (cd.trim().startsWith("filename")) {
//                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
//                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
//            }
//        }
//        return null;
//    }
//
//    public Part getFile1() {
//        return file1;
//    }
//
//    public void setFile1(Part file1) {
//        this.file1 = file1;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//   
//}
