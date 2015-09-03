/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import dao.Sequence;
import Entity.EstadoProyecto;
import Entity.Estados;
import Entity.Objetivos;
import Entity.Proyectos;
import Entity.TipoProyecto;
import Entity.Usuario;
import Entity.UsuarioProyecto;
import Entity.Versiones;
import Modelo.Objetivo;
import Modelo.Secuencia;
import java.io.File;
import java.io.IOException;
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
 * @author Britt
 */
@ManagedBean
@SessionScoped
public class ObjetivosDocumento {

    private Part file1;
    private String Nombre;
    private String descripcion;
    private Long Tipo_proyc;
    ArrayList<Objetivo> obj = new ArrayList();

    public ObjetivosDocumento() {
    }

    public void recuperarInfo() {
        CargarArchivos CA = new CargarArchivos();
        CA = (CargarArchivos) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Documentos");
        System.out.println("Nombre " + CA.getNombre());
        Nombre = CA.getNombre();
        System.out.println("Entro a recu");

    }

    public void añadirObt() {
        if (descripcion == "") {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe Escribir un Objetivo", ""));

        } else {
            int max = 0;
            Objetivo temp = null;
            for (int i = 0; i < obj.size(); i++) {
                temp = (Objetivo) obj.get(i);
                if (temp.getCod() >= max) {
                    max = temp.getCod();
                }
            }
            if (max == 0) {
                max = 1;
            } else {
                max++;
            }
            obj.add(new Objetivo(max, descripcion));
            descripcion = "";
        }

    }

    public void borrar_Obejtivo(int cod) {
        System.out.println("borrar " + cod);
        Objetivo temp = null;
        for (int i = 0; i < obj.size(); i++) {
            temp = (Objetivo) obj.get(i);
            if (temp.getCod() == cod) {
                obj.remove(i);
            }
        }
    }

    public void upload() throws IOException, ClassNotFoundException {
        if (obj.size() != 0) {
            System.out.println("Crear nuevo");
            int Codigo_Proyecto = Sequence.GetUltimoRegistro("select max(p.codigoProyecto) from Proyectos p");
            System.out.println("cod " + Codigo_Proyecto);
            Date fecha = new Date();
            CargarArchivos CA = new CargarArchivos();
            CA = (CargarArchivos) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Documentos");
            System.out.println("1");
            Proyectos proyecto = new Proyectos();
            proyecto.setCodigoProyecto(new BigDecimal(Codigo_Proyecto));
            proyecto.setNombre(Nombre);
            proyecto.setCalificacion(new Long(0));
            proyecto.setPorcentaje("1");
            proyecto.setFechaInicio(fecha);
            proyecto.setFechaFinal(fecha);
            TipoProyecto t = new TipoProyecto();
            t.setCodTipo(CA.getTipo_proyc());
            proyecto.setTipoProyecto(t);
            String ruta = "";
            System.out.println("2");
            System.out.println("proyecto " + proyecto.getNombre());
            boolean r = insertProyect(proyecto);
            System.out.println("r " + r);
            boolean r2 = RegistroEstados(proyecto);
            System.out.println("r2 " + r);
            boolean r3 = insertObjetivos(proyecto);
            System.out.println(" r3 " + r3);
            boolean r4 = crearVersion(proyecto, CA.getRuta());
            System.out.println(" r4 " + r4);

            try {
                if (r4) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Upload_proyecto", proyecto);
                    obj.clear();
                    FacesContext.getCurrentInstance().getExternalContext().redirect("Seleccionar_Companeros.xhtml");
                } else {
                    File fil = new File(CA.getRuta());
                    if (fil.delete()) {
                        System.out.println("El fichero ha sido borrado satisfactoriamente");
                    } else {
                        System.out.println("El fichero no puede ser borrado");
                    }
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ""));
                }
            } catch (Exception ex) {
                System.out.println("Error Archivo " + ex.toString());
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Agrege Objetivos al proyecto", ""));

        }

    }

    public boolean crearVersion(Proyectos p, String ruta) throws ClassNotFoundException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Versiones v = new Versiones();

        boolean r = false;
        try {
            int cod = Secuencia.seque("select max(cod_version) from versiones");
            v.setFecha(p.getFechaInicio());
            v.setNombre(p.getNombre()+" - v1");
            v.setProyectos(p);
            v.setCodVersion(new Long(cod));
            v.setRutaArchivo(ruta);
            session.save("Versiones", v);
            t.commit();
            r = true;
        } catch (Exception ex) {
            r = false;
            System.out.println("Error crearVersion " + ex.toString());
        }
        return r;
    }

    public boolean RegistroEstados(Proyectos p) throws ClassNotFoundException {
        boolean r = false;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Estados e = new Estados();
        e.setCodigoEstados(new BigDecimal(5));
        EstadoProyecto EP = new EstadoProyecto();
        int codigo = Secuencia.seque("select max(cod_estadoproyec) from estado_proyecto");
        EP.setCodEstadoproyec(new BigDecimal(codigo));
        EP.setEstados(e);
        EP.setProyectos(p);
        try {
            session.save("EstadoProyecto", EP);
            t.commit();
            r = true;
        } catch (Exception ex) {
            r = false;
            System.out.println("Error Estados" + ex.toString());
        }
        return r;
    }

    public void atras() throws IOException {
        CargarArchivos CA = new CargarArchivos();
        CA = (CargarArchivos) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Documentos");
        File fil = new File(CA.getRuta());
        if (fil.delete()) {
            System.out.println("El fichero ha sido borrado satisfactoriamente");
        } else {
            System.out.println("El fichero no puede ser borrado");
        }
        obj.clear();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Documentos");
        FacesContext.getCurrentInstance().getExternalContext().redirect("SubirDoc.xhtml");

    }

    public void cancelar() throws IOException {
        CargarArchivos CA = new CargarArchivos();
        CA = (CargarArchivos) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Documentos");
        File fil = new File(CA.getRuta());
        if (fil.delete()) {
            System.out.println("El fichero ha sido borrado satisfactoriamente");
        } else {
            System.out.println("El fichero no puede ser borrado");
        }
        obj.clear();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("Documentos");
        FacesContext.getCurrentInstance().getExternalContext().redirect("Menu_Usuario.xhtml");

    }

    public boolean insertObjetivos(Proyectos p) {
        boolean r = false;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        try {
            int cod = 0;
            Objetivo temp = null;
            cod = Secuencia.seque("select max(cod_objetivo) from objetivos");
            for (int i = 0; i < obj.size(); i++) {
                temp = (Objetivo) obj.get(i);
                Objetivos ob = new Objetivos();
                System.out.println("Codigo " + cod);
                ob.setCodObjetivo(cod);
                ob.setDescripcion(temp.getObj());
                ob.setProyectos(p);
                session.save(ob);
                r = true;
                cod++;

            }
            t.commit();
        } catch (Exception ex) {
            r = false;
            System.out.println("Error - Objetivos " + ex.getMessage());
        }
        return r;
    }

    public boolean insertProyect(Proyectos p) {
        boolean r = false;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        System.out.println(".++++ " + p.getTipoProyecto());
        try {
            session.save(p);
            r = true;
            t.commit();
        } catch (Exception ex) {
            r = false;
            System.out.println("Error -Proyecto " + ex.getMessage());
        }
        return r;
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

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public ArrayList<Objetivo> getObj() {
        return obj;
    }

    public void setObj(ArrayList<Objetivo> obj) {
        this.obj = obj;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getTipo_proyc() {
        return Tipo_proyc;
    }

    public void setTipo_proyc(Long Tipo_proyc) {
        this.Tipo_proyc = Tipo_proyc;
    }

}
