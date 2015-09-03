/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.Objetivos;
import Entity.Persona;
import Entity.Proyectos;
import Entity.Revisiones;
import Entity.Versiones;
import Modelo.Conecion_postgres;
import Modelo.Conecion_postgres1;
import Modelo.ProyectosModelo;
import Modelo.Secuencia;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
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
public class MiProyecto {

    private Proyectos proyecto;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private String NombreUsuario;
    ArrayList<String> list_obj = new ArrayList();
    ArrayList<ProyectosModelo> list_versiones = new ArrayList();
    private DefaultStreamedContent download;
    ArrayList<Revisiones> list_revi = new ArrayList();
    Proyectos pro = new Proyectos();
    private Part file1;
    private String nombre;

    public MiProyecto() {

    }

    public void cargarMiProyecto() throws IOException {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        if (httpServletRequest.getSession().getAttribute("user") != null) {
            System.out.println("Existe");
            Persona p = (Persona) httpServletRequest.getSession().getAttribute("persona");
            NombreUsuario = p.getNombres() + " " + p.getApellidos();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction t = session.beginTransaction();
            System.out.println("pege_id " + p.getUsuario().getPegeId());
            pro = (Proyectos) httpServletRequest.getSession().getAttribute("codProyecto");
            try {
                list_obj.clear();
                list_versiones.clear();
                proyecto = (Proyectos) session.createQuery("select P "
                        + " from Proyectos P INNER JOIN P.usuarioProyectos US "
                        + "INNER JOIN US.usuarioByEstudiante U where U.pegeId=" + p.getUsuario().getPegeId()).uniqueResult();
                Object obj[] = proyecto.getObjetivoses().toArray();
                String objetivo = "";
                for (int i = 0; i < obj.length; i++) {
                    objetivo = obj[i].toString();
                    list_obj.add(objetivo);
                }
                Object ver[] = proyecto.getVersioneses().toArray();
                String version = "";
                String vectorVersion[] = null;
                for (int i = 0; i < ver.length; i++) {
                    version = ver[i].toString();
                    vectorVersion = version.split("#");
                    list_versiones.add(new ProyectosModelo(vectorVersion[0],
                            vectorVersion[1], vectorVersion[2]));
                }
                t.commit();
                sacarRevisiones();
            } catch (Exception ex) {
                System.out.println("Error al cargar proyecto");
            }
            System.out.println("--- " + p.toString());

////            httpServletRequest.getAttribute("user");
        } else {
            System.out.println("No existe");
            FacesContext.getCurrentInstance().getExternalContext().redirect("../logIn/index.jsp");

        }
    }

    public void sacarRevisiones() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        list_revi.clear();
        try {
            list_revi = (ArrayList) session.createQuery("select R from Revisiones R"
                    + " INNER JOIN R.revisionProyectos RE "
                    + " INNER JOIN RE.proyectos P "
                    + " INNER JOIN P.usuarioProyectos UP"
                    + " INNER JOIN UP.usuarioByEstudiante U "
                    + " where P.codigoProyecto=" + pro.getCodigoProyecto() + " "
                    + " order by R.fecha DESC").list();
            t.commit();
        } catch (Exception ex) {
            System.out.println("Error sacarRevisiones " + ex.toString());
        }
    }

    public void prepDownload(String date) throws Exception {
        System.out.println("file " + date);
        File file = new File(date);
        InputStream input = new FileInputStream(file);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        setDownload(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
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

    public String creardirectorios() {
        String ruta = "";
        System.out.println("C:\\Documentos\\" + pro.getFechaInicio().toString().substring(pro.getFechaInicio().toString().length() - 4, pro.getFechaInicio().toString().length()));

        File directorio2 = new File("C:\\Documentos\\" + pro.getFechaInicio().toString().substring(pro.getFechaInicio().toString().length() - 4, pro.getFechaInicio().toString().length()) + "\\" + pro.getNombre());
        if (directorio2.mkdir()) {
            System.out.println("Se ha creado directorio");

        }

        return ruta = "C:\\Documentos\\" + pro.getFechaInicio().toString().substring(pro.getFechaInicio().toString().length() - 4, pro.getFechaInicio().toString().length()) + "\\" + pro.getNombre();
    }

    public void subirVersion() throws ClassNotFoundException {
        System.out.println("nombre " + nombre + " file  " + file1);
        if (verificarArchivo()) {
            int count = Secuencia.seque("select count(cod_version) from versiones,proyectos\n"
                    + "where\n"
                    + "proyectos.codigo_proyecto=versiones.cod_proyecto\n"
                    + "and proyectos.codigo_proyecto=" + pro.getCodigoProyecto());
            int cod_ver=Secuencia.seque("select max(cod_version) from versiones");
            String ruta = "";
            String version = "";
            String r = "C:\\Documentos\\" + pro.getFechaInicio().toString().substring(0, 4) + "\\" + pro.getNombre();
            String v1 = getFilename(file1).substring(0, getFilename(file1).length() - 5);
            String f = v1 + "-V" + count + getFilename(file1).substring(getFilename(file1).length() - 5, getFilename(file1).length());
            System.out.println("rut " + f);
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction t = session.beginTransaction();
            try {
                file1.write(r + "\\" + f);
                ruta=r+"\\"+f;
                Versiones ver=new Versiones();
                Date fecha=new Date();
                ver.setCodVersion(new Long(cod_ver));
                ver.setFecha(fecha);
                ver.setNombre(nombre);
                ver.setProyectos(pro);
                ver.setRutaArchivo(ruta);
                session.save("Versiones",ver);
                t.commit();
                FacesContext.getCurrentInstance().getExternalContext().responseReset();
            } catch (Exception ex) {
                System.out.println("Error " + ex.toString());
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El archivo debe ser tipo Word", ""));

        }
    }

    public Proyectos getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyectos proyecto) {
        this.proyecto = proyecto;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public FacesContext getFaceContext() {
        return faceContext;
    }

    public void setFaceContext(FacesContext faceContext) {
        this.faceContext = faceContext;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public ArrayList<String> getList_obj() {
        return list_obj;
    }

    public void setList_obj(ArrayList<String> list_obj) {
        this.list_obj = list_obj;
    }

    public ArrayList<ProyectosModelo> getList_versiones() {
        return list_versiones;
    }

    public void setList_versiones(ArrayList<ProyectosModelo> list_versiones) {
        this.list_versiones = list_versiones;
    }

    public DefaultStreamedContent getDownload() {
        return download;
    }

    public void setDownload(DefaultStreamedContent download) {
        this.download = download;
    }

    public ArrayList<Revisiones> getList_revi() {
        return list_revi;
    }

    public void setList_revi(ArrayList<Revisiones> list_revi) {
        this.list_revi = list_revi;
    }

    public Proyectos getPro() {
        return pro;
    }

    public void setPro(Proyectos pro) {
        this.pro = pro;
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

}
