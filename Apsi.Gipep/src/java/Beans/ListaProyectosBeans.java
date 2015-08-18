/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.DispoUsuario;
import Entity.Proyectos;
import Entity.Usuario;
import Entity.UsuarioProyecto;
import Modelo.Carreras;
import Modelo.Conecion_postgres;
import Modelo.Estudiante;
import Modelo.Profesor;
import Modelo.ProyectosModelo;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Britt
 */
@ManagedBean
@SessionScoped
public class ListaProyectosBeans {

    /**
     * Creates a new instance of ListaProyectosBeans
     */
    ArrayList<ProyectosModelo> pro2 = new ArrayList();
    ArrayList<Carreras> lista_Carrera = new ArrayList();
    private int cod_carrera;
    ArrayList<Profesor> profe = new ArrayList();

    public ListaProyectosBeans() {
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

    public void listarProyectos() {
        ArrayList<Proyectos> pro = new ArrayList();
        pro2.clear();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            ArrayList pege_id = null;
            pege_id = TraerPege_idCArrera();
            Estudiante temp3 = null;
            for (int i = 0; i < pege_id.size(); i++) {
                temp3 = (Estudiante) pege_id.get(i);
                pro = (ArrayList) session.createQuery("select distinct P FROM Proyectos P"
                        + " INNER JOIN P.estadoProyectos E INNER JOIN"
                        + " E.estados  ES INNER JOIN P.usuarioProyectos US INNER JOIN"
                        + " US.usuarioByEstudiante U where ES.codigoEstados=1 and "
                        + " U.pegeId=" + temp3.getPege_id()).list();
                ProyectosModelo temp = null;
                Proyectos temp2 = null;
                boolean esta = false;
                for (int k = 0; k < pro.size(); k++) {
                    temp2 = (Proyectos) pro.get(k);
                    for (int j = 0; j < pro2.size(); j++) {
                        temp = (ProyectosModelo) pro2.get(j);
                        if (temp.getCod_pro() == temp2.getCodigoProyecto().intValue()) {
                            esta = true;
                        }
                    }
                    if (pro2.size() == 0) {
                        pro2.add(new ProyectosModelo(temp2.getCodigoProyecto().intValue(), temp2.getNombre(), false));
                    } else if (esta == false) {
                        pro2.add(new ProyectosModelo(temp2.getCodigoProyecto().intValue(), temp2.getNombre(), false));
                    } else {
                        System.out.println("ya estaba");
                    }
                    esta = false;
                    System.out.println("codigoooooo " + temp2.getCodigoProyecto());
                }
            }
            t.commit();

            System.out.println("---- " + pro2.size());
        } catch (Exception ex) {
            System.out.println("Error " + ex.toString());
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

    public void recojerselecion(ProyectosModelo p) throws IOException {
        System.out.println("entro recojer " + p.getCod_pro());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cambio_dire", p);
        FacesContext.getCurrentInstance().getExternalContext().redirect("SelecionarDirector.xhtml");
    }

    public void guardarCambios(String pege_id) {
        System.out.println("entro " + pege_id);
        ProyectosModelo p = new ProyectosModelo();
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cambio_dire") != null) {
            System.out.println("exite");
        } else {
            System.out.println("No existe");
        }
        p = (ProyectosModelo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cambio_dire");
        System.out.println("proyecto " + p.getCod_pro());
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        ArrayList<UsuarioProyecto> up = new ArrayList();
        try {
            up = (ArrayList) session.createQuery("select up from UsuarioProyecto"
                    + " up INNER JOIN up.proyectos p INNER JOIN up.usuarioByDirector u"
                    + " where p.codigoProyecto=" + p.getCod_pro()).list();
            System.out.println("proycetos " + up.size());
            Usuario u = new Usuario();
            u = traerUsu(pege_id);
            System.out.println("usuario qe trajo " + u.getUsuario());
            UsuarioProyecto temp = null;
            for (int i = 0; i < up.size(); i++) {
                System.out.println("cambio " + i);
                temp = (UsuarioProyecto) up.get(i);
                temp.setUsuarioByDirector(u);
                session.update("UsuarioProyecto", temp);
            }
            t.commit();
        } catch (Exception ex) {
            System.out.println("Error guardarCambios " + ex.toString());
        }
    }

    public Usuario traerUsu(String pege_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Usuario u = new Usuario();
        try {
            u = (Usuario) session.createQuery("from Usuario u where pege_id=" + pege_id).uniqueResult();
            t.commit();
        } catch (Exception ex) {
            System.out.println("Error TraerUsu " + ex.toString());
        }
        return u;
    }

    public void cargarProfeII() throws ClassNotFoundException, SQLException {
        profe.clear();
        System.out.println("Entro a inicio");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Usuario admon2 = new Usuario();
        admon2.setPegeId(new BigDecimal(1));
        ArrayList<DispoUsuario> users = new ArrayList();
        users = (ArrayList) session.createQuery("select Du from DispoUsuario Du INNER JOIN Du.usuarioByAdmon UA "
                + "INNER JOIN Du.disponibilidad D where UA=1 and D.estado='A'").list();
        System.out.println("------------------ " + users.size());
        TraerInfoProfe2(users);

    }

    public void TraerInfoProfe2(ArrayList users) throws ClassNotFoundException, SQLException {
        Conecion_postgres.conectar();
        System.out.println("entro a buscar Profe " + users.size());
        DispoUsuario usu = new DispoUsuario();
        System.out.println("1");
        for (int i = 0; i < users.size(); i++) {
            System.out.println("2");
            usu = (DispoUsuario) users.get(i);
            System.out.println("+++++++++++++ " + usu.getUsuarioByProfesor().getPegeId());
            Conecion_postgres.ejecuteQuery("select * from profesor where pege_id="
                    + usu.getUsuarioByProfesor().getPegeId());
            System.out.println("3");
            try {
                while (Conecion_postgres.rs.next()) {
                    if (Conecion_postgres.rs.getString(4) == null) {
                        System.out.println("opcion 1");
                        profe.add(new Profesor(false, Conecion_postgres.rs.getString(2), Conecion_postgres.rs.getString(3),
                                Conecion_postgres.rs.getString(5), Conecion_postgres.rs.getString(1)));
                    } else {
                        System.out.println("opcion 2");
                        profe.add(new Profesor(false, Conecion_postgres.rs.getString(5), Conecion_postgres.rs.getString(2),
                                Conecion_postgres.rs.getString(3), Conecion_postgres.rs.getString(1)));
                        System.out.println("++ " + profe.toString());
                    }
                }

            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
        Conecion_postgres.cerrarConexion();
    }

    public ArrayList<ProyectosModelo> getPro2() {
        return pro2;
    }

    public void setPro2(ArrayList<ProyectosModelo> pro2) {
        this.pro2 = pro2;
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

    public ArrayList<Profesor> getProfe() {
        return profe;
    }

    public void setProfe(ArrayList<Profesor> profe) {
        this.profe = profe;
    }

}
