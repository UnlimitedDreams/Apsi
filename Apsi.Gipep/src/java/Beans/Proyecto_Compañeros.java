/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.Objetivos;
import Entity.Proyectos;
import Entity.Rol;
import Entity.UsuRol;
import Entity.Usuario;
import Entity.UsuarioProyecto;
import Modelo.Conecion_postgres;
import Modelo.Estudiante;
import Modelo.Profesor;
import Modelo.Secuencia;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Britt
 */
@ManagedBean
@SessionScoped
public class Proyecto_Compañeros {

    ArrayList<Estudiante> lista_estu = new ArrayList();
    private String nombre;

    public Proyecto_Compañeros() {
    }

    public void cargar_estudiante() {
        System.out.println("entro");
        if (lista_estu.size() == 0) {
            Estudiante estu = new Estudiante(false,
                    "234343", "Samuel", "Valencia", "6543");
            lista_estu.add(estu);
        }
//        System.out.println("+++ " + lista_estu.size());
    }

    public void buscarEstudiante() throws ClassNotFoundException {
        System.out.println("entor a buscar");
        Conecion_postgres.conectar();
        Conecion_postgres.ejecuteQuery("select * from estudiante where cedula='" + nombre + "'");
        boolean r = false;
        try {
            while (Conecion_postgres.rs.next()) {
                r = true;
                lista_estu.add(new Estudiante(false, Conecion_postgres.rs.getString(2), Conecion_postgres.rs.getString(3), Conecion_postgres.rs.getString(4), Conecion_postgres.rs.getString(1)));
            }
            System.out.println("--- " + lista_estu.size());
            Conecion_postgres.cerrarConexion();
            if (r == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "No existe Cedula en la UNIAJC", ""));

            }

        } catch (Exception ex) {

        }
    }

    public Usuario Crearusuario(String pege_id, String nombre) {
        Usuario temp = new Usuario();
        Usuario u = new Usuario();

        try {
            System.out.println("entro a usuario");
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction t = session.beginTransaction();
            u = (Usuario) session.createQuery("FROM Usuario as usu where usu.pegeId=" + pege_id).uniqueResult();
            if (u != null) {
                System.out.println("ya existe");
                temp = u;
            } else {
                temp.setPegeId(new BigDecimal(pege_id));
                temp.setUsuario(nombre);
                temp.setContrasea("Uniacj123");
                session.save(temp);
                t.commit();
                System.out.println("usuario----- " + temp.toString());

                crearUsuRol(temp);
            }

        } catch (Exception ex) {
            System.out.println("Error de Insert");
            System.out.println("error " + ex.getMessage());
//            Usuario temp2 = null;
//            temp = temp2;
        }
        return temp;
    }

    public void crearUsuRol(Usuario u) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            System.out.println("entro");
            Rol r = new Rol();
            r.setCodRol(new BigDecimal(3));
            r.setEstado("esta");
            r.setNombre("nombre");
            UsuRol usuR = new UsuRol();
            int cod_U = Secuencia.seque("select max(cod_usurol) from usu_rol");
            usuR.setCodUsurol(new BigDecimal(cod_U));
            usuR.setUsuario(u);
            usuR.setRol(r);
            session.save(usuR);
            t.commit();
        } catch (Exception ex) {
            System.out.println("Error al crear usu_rol");
            System.out.println("-- " + ex.toString());
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

    public void eliminarTodo() throws IOException {
        CargarArchivos CA = new CargarArchivos();
        CA = (CargarArchivos) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Documentos");
        File fil = new File(CA.getRuta());
        if (fil.delete()) {
            System.out.println("El fichero ha sido borrado satisfactoriamente");
        } else {
            System.out.println("El fichero no puede ser borrado");
        }
        Proyectos p = new Proyectos();
        p = (Proyectos) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Upload_proyecto");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        ArrayList<Objetivos> ob = new ArrayList();

        try {
            ob = (ArrayList) session.createQuery("select O from Proyectos P "
                    + "INNER JOIN P.objetivoses O where "
                    + " P.codigoProyecto=" + p.getCodigoProyecto()).list();
            System.out.println("size " + ob.size());
            for (int i = 0; i < ob.size(); i++) {
                session.createQuery("delete from Objetivos where codObjetivo=" + ob.get(i).getCodObjetivo()).executeUpdate();
            }
//            ArrayList<UsuarioProyecto> usu = new ArrayList();
//            usu = (ArrayList) session.createQuery("select U from UsuarioProyecto U "
//                    + " INNER JOIN U.proyectos P where "
//                    + " P.codigoProyecto=" + p.getCodigoProyecto()).list();
//            System.out.println("usu size " + usu.size());
//            for (int i = 0; i < ob.size(); i++) {
//                session.createQuery("delete from UsuarioProyecto where codUsuproyecto=" + usu.get(i).getCodUsuproyecto()).executeUpdate();
//            }
            session.createQuery("delete from Proyectos where codigoProyecto=" + p.getCodigoProyecto()).executeUpdate();
            t.commit();
        } catch (Exception ex) {
            System.out.println("Error " + ex.toString());
        }

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("Upload_proyecto");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("Documentos");
        FacesContext.getCurrentInstance().getExternalContext().redirect("Menu_Usuario.xhtml");

    }

    public boolean insertProyectUsu() throws ClassNotFoundException {
        int Codigo_UsuPro = Secuencia.seque("select max(cod_usuproyecto) from usuario_proyecto");
        Proyectos p = null;
        p = (Proyectos) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Upload_proyecto");
        Estudiante temp = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean r = false;
        Transaction t = session.beginTransaction();
        try {
            Usuario U = new Usuario();
            for (int i = 0; i < lista_estu.size(); i++) {
                temp = (Estudiante) lista_estu.get(i);
                U = Crearusuario(temp.getPege_id(), temp.getNombre() + " " + temp.getApellido());
                if (U == null) {
                    break;
                } else {
                    Usuario U1 = new Usuario();
                    U1.setPegeId(new BigDecimal(0));
                    Usuario UEstudiante = new Usuario();
                    UEstudiante.setPegeId(new BigDecimal(temp.getPege_id()));
                    UsuarioProyecto usu = new UsuarioProyecto();
                    usu.setProyectos(p);
                    usu.setCodUsuproyecto(new BigDecimal(Codigo_UsuPro));
                    usu.setUsuarioByDirector(U1);
                    usu.setUsuarioByEstudiante(UEstudiante);

                    session.save(usu);
                    r = true;
                    Codigo_UsuPro++;
                }

            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok", ""));
            lista_estu.clear();
            t.commit();
            FacesContext.getCurrentInstance().getExternalContext().redirect("Menu_Usuario.xhtml");

        } catch (Exception ex) {
            r = false;
            System.out.println("Error - " + ex.getMessage());
        }

        return r;
    }

    public ArrayList<Estudiante> getLista_estu() {
        return lista_estu;
    }

    public void setLista_estu(ArrayList<Estudiante> lista_estu) {
        this.lista_estu = lista_estu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
