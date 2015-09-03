/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import dao.Sequence;
import dao.UsuarioDao;
import dao.UsuarioImple;
import Entity.Dia;
import Entity.DispoUsuario;
import Entity.Disponibilidad;
import Entity.Persona;
import Entity.Rol;
import Entity.UsuRol;
import Entity.Usuario;
import Modelo.MDias;
import Modelo.Profesor;
import Modelo.Secuencia;
import dao.DisponibilidadDao;
import dao.DisponibilidadImple;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
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
public class ajusteProfesorInfo {

    private String nombreProfesor;
    private Date fecha_inicial;
    private Date fecha_final;
    private String RamgoHora;
    private String Cantidad_horas;
    private int periodo;
    ArrayList<MDias> Dias = new ArrayList();
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private String NombreUsuario;

    public ajusteProfesorInfo() {

    }

    public void recuperarInfo() throws IOException {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        if (httpServletRequest.getSession().getAttribute("user") != null) {
            System.out.println("Existe");
            Persona p = (Persona) httpServletRequest.getSession().getAttribute("persona");
            NombreUsuario = p.getNombres() + " " + p.getApellidos();
            System.out.println("recupero");
            ajusteProfesorBean ajuste;
            ajuste = new ajusteProfesorBean();
            ajuste = (ajusteProfesorBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Datos_ajuste");
            Dias = (ArrayList) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Dias_ajuste");
            System.out.println("dias qe trajo " + Dias.size());
            nombreProfesor = ajuste.getNombreProfesor();
            fecha_inicial = ajuste.getFecha_inicial();
            fecha_final = ajuste.getFecha_final();
            RamgoHora = ajuste.getRamgoHora();
            Cantidad_horas = ajuste.getCantidad_horas();
            periodo = ajuste.getPeriodo();
        } else {
            System.out.println("No existe");
            FacesContext.getCurrentInstance().getExternalContext().redirect("../logIn/index.jsp");
        }

    }

    public void insertar_ajuste() throws ClassNotFoundException, IOException {
        System.out.println("Entro al ajuste");

        int Codigo_Dispo = 0, codigo_dispoUsu = 0;
        codigo_dispoUsu = Secuencia.seque("select max(cod_dispousu) from dispo_usuario");
        Codigo_Dispo = Secuencia.seque("select max(cod_dis) from disponibilidad");

        System.out.println("codigo dis : " + codigo_dispoUsu);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Profesor profe = null;

        profe = (Profesor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("profesor");
        Usuario temp = (Usuario) session.load(Usuario.class, new BigDecimal(Integer.parseInt(profe.getPege_id())));
        Usuario temp2 = (Usuario) session.load(Usuario.class, new BigDecimal(1));

        t.commit();

        Disponibilidad dispo = new Disponibilidad();
        dispo.setCodDis(new BigDecimal(Codigo_Dispo));
        dispo.setFechaInicial(fecha_inicial);
        dispo.setFechaFinal(fecha_final);
        dispo.setNumHoras(new BigDecimal(Integer.parseInt(Cantidad_horas)));
        dispo.setRango(new BigDecimal(Integer.parseInt(RamgoHora)));
        dispo.setPeriodo(new BigDecimal(periodo));
        dispo.setEstado("A");
        dispo.setHorasCumplidas(new Long(0));
        System.out.println("----");
        boolean CrearDispo = Insert_Dispo(dispo);
        System.out.println("---------" + CrearDispo);

        if (CrearDispo) {
            System.out.println("creo dispo cod : " + codigo_dispoUsu);
            DispoUsuario dispo_usu = new DispoUsuario();
            dispo_usu.setCodDispousu(new BigDecimal(codigo_dispoUsu));
            dispo_usu.setDisponibilidad(dispo);
            dispo_usu.setUsuarioByProfesor(temp);
            dispo_usu.setUsuarioByAdmon(temp2);
            System.out.println("::::: "  +dispo.toString());
            boolean dis_usu = InsertDispo_Usu(dispo_usu);
            System.out.println("--: " + dis_usu);
            if (dis_usu) {
                boolean dispo_dias = Insert_Dias(dispo, Dias);
                System.out.println("-- 2 : " + dispo_dias);
                if (dispo_dias) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok", ""));
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("Datos_ajuste");
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("Dias_ajuste");
                    FacesContext.getCurrentInstance().getExternalContext().redirect("ValidarProfesor.xhtml");
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error Comunicarce Con el Administrador", ""));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error Comunicarce Con el Administrador", ""));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error Comunicarce Con el Administrador", ""));
        }

    }

    public boolean Insert_Dispo(Disponibilidad dis) {
        System.out.println("entro al insert dispo " + dis.getCodDis());
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        boolean r = false;
        System.out.println("codigooooo disssssssssssssssssssssss : " + dis.getCodDis());

        try {
            session.save(dis);
            t.commit();
            r = true;
        } catch (Exception ex) {
            System.out.println("Error Insert Dispo " + ex.toString());
            r = false;
        }
        return r;
    }

    public boolean InsertDispo_Usu(DispoUsuario dis_usu) {
        System.out.println("----- entro ");
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        boolean r = false;
        try {
            session.save(dis_usu);
            t.commit();
            r = true;
        } catch (Exception ex) {
            System.out.println("Error Insert Dispo_Usu" + ex.toString());
            r = false;
        }
        return r;
    }

    public boolean Insert_Dias(Disponibilidad dis, ArrayList dias) {
        boolean r = false;
        MDias temp = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        int cod_dia = 0;
        System.out.println("codigo de dispo " + dis.getCodDis());
        try {
            cod_dia = Secuencia.seque("select max(codigo_dia) from dia");
            for (int i = 0; i < dias.size(); i++) {
                temp = (MDias) dias.get(i);
                Dia d = new Dia();
                d.setCodigoDia(new BigDecimal(cod_dia));
                d.setDia(temp.getDia());
                d.setHoraInicial(temp.getHora_inicio());
                d.setHoraFinal(temp.getHora_final());
                d.setDisponibilidad(dis);
                session.save(d);
                cod_dia++;
                r = true;
            }
            t.commit();
        } catch (Exception ex) {
            System.out.println("Error Dias " + ex.toString());
            r = false;
        }
        return r;
    }

    public Usuario Crearusuario() {
        Usuario temp = new Usuario();
        Usuario u = new Usuario();

        try {
            System.out.println("entro a usuario");
            Profesor profe = null;
            profe = (Profesor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("profesor");
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction t = session.beginTransaction();
            System.out.println("" + profe.toString());
            System.out.println("-------------------------------- profe " + profe.getPege_id());
            u = (Usuario) session.createQuery("FROM Usuario as usu where usu.pegeId=" + profe.getPege_id()).uniqueResult();
            if (u != null) {
                System.out.println("ya existe");
                temp = u;
            } else {

                temp.setPegeId(new BigDecimal(profe.getPege_id()));
                temp.setUsuario(profe.getNombre().substring(0, 1) + "" + profe.getApellido());
                temp.setContrasea("123456");
                session.save("Usuario", temp);
                Persona p = new Persona();
                p.setIdpersona(new BigDecimal(profe.getCedula()));
                p.setNombres(profe.getNombre());
                p.setApellidos(profe.getApellido());
                p.setUsuario(temp);
//                session.save("Persona", p);
                session.save(p);
                t.commit();
////                crearPersona(p);
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

    public void crearPersona(Persona p) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        try {
            session.save("Persona", p);
            t.commit();
        } catch (Exception ex) {
            System.out.println("Error crearPersona " + ex.toString());
        }

    }

    public void crearUsuRol(Usuario u) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        try {
            System.out.println("entro");
            Rol r = new Rol();
            r.setCodRol(new BigDecimal(4));
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

    public ArrayList<MDias> getDias() {
        return Dias;
    }

    public void setDias(ArrayList<MDias> Dias) {
        this.Dias = Dias;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public Date getFecha_inicial() {
        return fecha_inicial;
    }

    public void setFecha_inicial(Date fecha_inicial) {
        this.fecha_inicial = fecha_inicial;
    }

    public Date getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(Date fecha_final) {
        this.fecha_final = fecha_final;
    }

    public String getRamgoHora() {
        return RamgoHora;
    }

    public void setRamgoHora(String RamgoHora) {
        this.RamgoHora = RamgoHora;
    }

    public String getCantidad_horas() {
        return Cantidad_horas;
    }

    public void setCantidad_horas(String Cantidad_horas) {
        this.Cantidad_horas = Cantidad_horas;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
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

}
