/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.ProyectosModelo;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Britt
 */
@ManagedBean
@SessionScoped
public class CambioDirector {

    /**
     * Creates a new instance of CambioDirector
     */
    public CambioDirector() {
    }
     public void guardarCambios(String pege_id) {
        System.out.println("entro");
        ProyectosModelo p = new ProyectosModelo();
        if(FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("cambio_dire")!=null){
            System.out.println("exite");
        }else{
            System.out.println("No existe");
        }
//        p = (ProyectosModelo) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("cambio_dire");
//        System.out.println("proyecto " + p.getCod_pro());
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction t = session.beginTransaction();
//        ArrayList<UsuarioProyecto> up = new ArrayList();
//        try {
//            up = (ArrayList) session.createQuery("select up from UsuarioProyecto"
//                    + " up INNER JOIN up.proyectos p INNER JOIN up.usuarioByDirector u"
//                    + " where p.codigoProyecto=" + p.getCod_pro()).list();
//            System.out.println("proycetos " + up.size());
//            Usuario u = new Usuario();
//            u = (Usuario) session.createQuery("select u from Usuario where pegeId=" + pege_id).uniqueResult();
//            UsuarioProyecto temp = null;
//            for (int i = 0; i < up.size(); i++) {
//                System.out.println("cambio " + i);
//                temp = (UsuarioProyecto) up.get(i);
//                temp.setUsuarioByDirector(u);
//                session.update("UsuarioProyecto", temp);
//            }
//            t.commit();
//        } catch (Exception ex) {
//            System.out.println("Error guardarCambios " + ex.toString());
//        }
    }
}
