/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.Proyectos;
import Entity.Versiones;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
public class ProyectoVersones {

    /**
     * Creates a new instance of ProyectoVersones
     */
    private DefaultStreamedContent download;
    ArrayList<Versiones> lista_ver = new ArrayList();

    public ProyectoVersones() {
    }

    public void listarVersiones() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Proyectos p = new Proyectos();
        p = (Proyectos) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Poryecto_Revi");
        try {
            lista_ver = (ArrayList) session.createQuery("select v from Proyectos p INNER JOIN p.versioneses "
                    + "v where p.codigoProyecto=" + p.getCodigoProyecto() + " "
                    + " order by v.fecha DESC").list();
            t.commit();

        } catch (Exception ex) {
            System.out.println("error listaReviciones " + ex.toString());
        }
    }

    public void prepDownload(String date) throws Exception {
        System.out.println("file " + date);
        File file = new File(date);
        InputStream input = new FileInputStream(file);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        setDownload(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
    }

    public void atrasVista1() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("Poryecto_Revi");
        FacesContext.getCurrentInstance().getExternalContext().redirect("DireccionProyecto.xhtml");

    }

    public ArrayList<Versiones> getLista_ver() {
        return lista_ver;
    }

    public void setLista_ver(ArrayList<Versiones> lista_ver) {
        this.lista_ver = lista_ver;
    }

    public DefaultStreamedContent getDownload() {
        return download;
    }

    public void setDownload(DefaultStreamedContent download) {
        this.download = download;
    }

}
