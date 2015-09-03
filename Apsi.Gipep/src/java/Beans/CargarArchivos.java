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
    private String ruta;
    private long Tipo_proyc;
    ArrayList<TipoProyecto> tipo = new ArrayList();

    public CargarArchivos() {

    }

    public CargarArchivos(Part file1, String nombre, String ruta, long Tipo_proyc) {
        this.file1 = file1;
        this.nombre = nombre;
        this.ruta = ruta;
        this.Tipo_proyc = Tipo_proyc;
    }

    public void cargar_tipo() {
        System.out.println("Entr");
        tipo.clear();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        try {
            tipo = (ArrayList) session.createQuery("from TipoProyecto").list();
            t.commit();
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

    public boolean verificarArchivo() {
        boolean r = false;
        System.err.println("entro a validar " + getFilename(file1));
        String vp = getFilename(file1).substring(getFilename(file1).length() - 4, getFilename(file1).length());
        if (vp.equalsIgnoreCase("docx")) {
            r = true;
        } 
        return r;

    }

    public void mandarAVista() {
        if (verificarArchivo()) {
        String ruta = "";
        String r = creardirectorios();
        String v1 = getFilename(file1).substring(0, getFilename(file1).length() - 5);
        String f = v1 + "-V1" + getFilename(file1).substring(getFilename(file1).length() - 5, getFilename(file1).length());
        System.out.println("rut " + f);
        try {
            file1.write(r + "\\" + f);
            ruta = r + "\\" + f;
            CargarArchivos ca = new CargarArchivos(file1, nombre, ruta, Tipo_proyc);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Documentos", ca);
            FacesContext.getCurrentInstance().getExternalContext().redirect("AgregarObjetivos.xhtml");
        } catch (Exception ex) {

        }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El archivo debe ser tipo Word", ""));

        }

    }

    public String creardirectorios() {
        Date fecha = new Date();
        String ruta = "";
        System.out.println("C:\\Documentos\\" + fecha.toString().substring(fecha.toString().length() - 4, fecha.toString().length()));
        File directorio = new File("C:\\Documentos\\" + fecha.toString().substring(fecha.toString().length() - 4, fecha.toString().length()));
        if (directorio.mkdir()) {
            File directorio2 = new File("C:\\Documentos\\" + fecha.toString().substring(fecha.toString().length() - 4, fecha.toString().length()) + "\\" + nombre);
            if (directorio2.mkdir()) {
                System.out.println("Se ha creado directorio");

            }
        } else {
            File directorio2 = new File("C:\\Documentos\\" + fecha.toString().substring(fecha.toString().length() - 4, fecha.toString().length()) + "\\" + nombre);
            if (directorio2.mkdir()) {
                System.out.println("Se ha creado directorio");

            }
            System.out.println("Ya existe");
        }
        return ruta = "C:\\Documentos\\" + fecha.toString().substring(fecha.toString().length() - 4, fecha.toString().length()) + "\\" + nombre;
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

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

}
