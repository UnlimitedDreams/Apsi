/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.Proyectos;
import Entity.TipoProyecto;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
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
public class ListaProyectos {

    ArrayList<TipoProyecto> tipo = new ArrayList();
    ArrayList<Proyectos> Proyec = new ArrayList();
    private long Tipo_proyc;
    private DefaultStreamedContent download;

    public ListaProyectos() {
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

    public void consultar() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            Proyec = (ArrayList) session.createQuery("select P from Proyectos "
                    + " P INNER JOIN P.tipoProyecto TI where "
                    + " TI.codTipo=" + Tipo_proyc).list();
            System.out.println("- " + Proyec.size());
            t.commit();
        } catch (Exception ex) {
            System.out.println("error " + ex.toString());

        }
    }

    public void setDownload(DefaultStreamedContent download) {
        this.download = download;
    }

    public DefaultStreamedContent getDownload() throws Exception {
        return download;
    }

    public void prepDownload(String date) throws Exception {
        System.out.println("file " + date);
        File file = new File(date);
        InputStream input = new FileInputStream(file);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        setDownload(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
    }

    public void calificarProyecto(Proyectos p) throws IOException {
        if (p.getPorcentaje().equalsIgnoreCase("100")) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("calificar", p);
            FacesContext.getCurrentInstance().getExternalContext().redirect("DatosCalificar.xhtml");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Proyecto debe estar al 100% para ser calificado", ""));

        }

    }

    public ArrayList<TipoProyecto> getTipo() {
        return tipo;
    }

    public void setTipo(ArrayList<TipoProyecto> tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Proyectos> getProyec() {
        return Proyec;
    }

    public void setProyec(ArrayList<Proyectos> Proyec) {
        this.Proyec = Proyec;
    }

    public long getTipo_proyc() {
        return Tipo_proyc;
    }

    public void setTipo_proyc(long Tipo_proyc) {
        this.Tipo_proyc = Tipo_proyc;
    }

}
