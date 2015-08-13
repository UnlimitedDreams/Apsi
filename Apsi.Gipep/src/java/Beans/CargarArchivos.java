/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import dao.Sequence;
import Entity.Objetivos;
import Entity.Proyectos;
import Entity.TipoProyecto;
import Entity.Usuario;
import Entity.UsuarioProyecto;
import Modelo.Objetivo;
import Modelo.Tipo_Proyecto;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
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
public class CargarArchivos implements Serializable {

    private Part file1;
    private String nombre;
    private long Tipo_proyc;
    ArrayList<TipoProyecto> tipo = new ArrayList();

    public CargarArchivos() {

    }

    public CargarArchivos(Part file1, String nombre, Long Tipo_proyc) {
        this.file1 = file1;
        this.nombre = nombre;
        this.Tipo_proyc = Tipo_proyc;
    }

    public void cargar_tipo() {
        System.out.println("Entr");
        tipo.clear();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            tipo = (ArrayList) session.createQuery("from TipoProyecto").list();
        } catch (Exception ex) {
            System.out.println("Error " + ex.toString());
        }

    }

    private String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    public void mandarAVista() throws IOException {
        System.out.println("Nombre " + nombre + " file " + file1 + " Tipo " + Tipo_proyc);
        String ruta=creardirectorios();
        System.out.println("ruta " + ruta);
        file1.write(ruta +"\\"+ getFilename(file1));
        CargarArchivos CA = new CargarArchivos(file1, nombre, Tipo_proyc);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Documentos", CA);
        FacesContext.getCurrentInstance().getExternalContext().redirect("AgregarObjetivos.xhtml");

    }

    public String creardirectorios() {
        Date fecha=new Date();
        String ruta="";
        File directorio = new File("C:\\Documentos\\"+fecha.toString().substring(fecha.toString().length()-4, fecha.toString().length()));
        if (directorio.mkdir()) {
            System.out.println("Se ha creado directorio");
        } else {
            System.out.println("Ya existe");
        }
        return ruta="C:\\Documentos\\"+fecha.toString().substring(fecha.toString().length()-4, fecha.toString().length());
    }
    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<TipoProyecto> getTipo() {
        return tipo;
    }

    public void setTipo(ArrayList<TipoProyecto> tipo) {
        this.tipo = tipo;
    }

    public long getTipo_proyc() {
        return Tipo_proyc;
    }

    public void setTipo_proyc(long Tipo_proyc) {
        this.Tipo_proyc = Tipo_proyc;
    }

}
