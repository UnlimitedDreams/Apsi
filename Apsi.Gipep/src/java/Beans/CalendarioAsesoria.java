package Beans;

import dao.CalendarioImple;
import dao.CalendarioP;
import Entity.Asesoria;
import Entity.Asistente;
import Entity.Calendario;
import Entity.Disponibilidad;
import Entity.Estados;
import Entity.EstadosAsesoria;
import Entity.Persona;
import Entity.Proyectos;
import Entity.Ubicacion;
import Entity.Usuario;
import Modelo.Conecion_postgres1;
import Modelo.MDias;
import Modelo.Profesor;
import Modelo.Secuencia;
import Modelo.asesoria;
import dao.Dias;
import dao.Sequence;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import util.HibernateUtil;

@ManagedBean
@SessionScoped
public class CalendarioAsesoria implements Serializable {

    private ScheduleModel eventModel;
    private ScheduleModel lazyEventModel;
    private String hora1;
    private String hora2;
    private String titulo;
    private Date fecha_final;
    private Date fecha_inicio;
    Date esta = null;
    Date end = null;
    private int cod;
    private ScheduleEvent event = new DefaultScheduleEvent();
    ArrayList<String> res = new ArrayList();
    ArrayList<CalendarioProfe_update> cal = new ArrayList();
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private String NombreUsuario;
    String pegeString;
    ArrayList<Profesor> docentes = new ArrayList();
    ArrayList<asesoria> asesoriasPorDia = new ArrayList();
    private String prueba;
    Proyectos proyecto = new Proyectos();
    private String pege_id;

    public String getPegeString() {
        return pegeString;
    }

    public void setPegeString(String pegeString) {
        this.pegeString = pegeString;
    }

    public ArrayList<Profesor> getDocentes() {

        return docentes;
    }

    public void setDocentes(ArrayList<Profesor> docentes) {
        this.docentes = docentes;
    }
//

    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        try {
            docentes.clear();
            traerDocente();
        } catch (IOException ex) {
            Logger.getLogger(CalendarioAsesoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Limpiear todo
     */
    public void borrarTodo() {
        try {
            event = null;
            hora1 = null;
            hora2 = null;
            titulo = "";
            fecha_final = null;
            fecha_inicio = null;
        } catch (Exception ex) {
            System.out.println("Error borrarTodo " + ex.toString());
        }
    }

    /**
     * Metodo para añadir los eventos año la vista CalentadioAsesoria.xhtml.
     */
    public void añadir_eventos() {

        eventModel = new DefaultScheduleModel();
        ArrayList<Dias> asesorias = null;

        try {
            asesorias = traer_dias();
            MDias temp = null;
            MDias temp2 = null;
            String fecha = "";
            int h1 = 0, h2 = 0;
            int min1 = 0, min2 = 0;

            for (Dias asesoria : asesorias) {
                eventModel.addEvent(new DefaultScheduleEvent("Asesoria #" + asesoria.getCod(), asesoria.getFechas(), asesoria.getFechas()));
            }
        } catch (IOException ex) {
            Logger.getLogger(CalendarioAsesoria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CalendarioAsesoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cargar los usuarios (Rol: Docente) con carga previa de disponibilidad.
     *
     */
    public void traerDocente() throws IOException {
        docentes.clear();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        if (httpServletRequest.getSession().getAttribute("user") != null) {
            faceContext = FacesContext.getCurrentInstance();
            httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
            if (httpServletRequest.getSession().getAttribute("user") != null) {
                System.out.println("Existe");
                proyecto = (Proyectos) httpServletRequest.getSession().getAttribute("codProyecto");
                if (proyecto == null) {
                    System.out.println("esta vacio");
                } else {
                    System.out.println("no esta vasio");
                }
                pege_id = (String) httpServletRequest.getSession().getAttribute("pege_id");
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                Transaction t = session.beginTransaction();
                try {
                    ArrayList<Persona> per = new ArrayList();
                    per = (ArrayList) session.createQuery("select p from Persona p inner join "
                            + " p.usuario u inner join u.dispoUsuariosForProfesor d "
                            + " inner join d.disponibilidad di "
                            + " where  di.estado='A'").list();
//                    Conecion_postgres1.conectar();
//                    Conecion_postgres1.ejecuteQuery("SELECT  distinct \n"
//                            + "    usuario.pege_id, persona.nombres,persona.apellidos\n"
//                            + " FROM \n"
//                            + "  public.disponibilidad, \n"
//                            + "  public.dia, \n"
//                            + "  public.dispo_usuario, \n"
//                            + "  public.usuario, \n"
//                            + "  public.persona\n"
//                            + " WHERE \n"
//                            + "  disponibilidad.cod_dis = dia.cod_dis AND\n"
//                            + "  dispo_usuario.cod_dis = disponibilidad.cod_dis AND\n"
//                            + "  usuario.pege_id = dispo_usuario.profesor AND\n"
//                            + "  usuario.pege_id = persona.pege_id");

//                    while (Conecion_postgres1.rs.next()) {
//
//                        docentes.add(new Profesor(Conecion_postgres1.rs.getString(1), Conecion_postgres1.rs.getString(2), Conecion_postgres1.rs.getString(3)));
//                    }
                    for (Persona pe : per) {
                        docentes.add(new Profesor(pe.getUsuario().getPegeId().toString(), pe.getNombres(), pe.getApellidos()));
                    }

                    t.commit();
                } catch (Exception ex) {
                    System.out.println("No hay docentes" + ex.toString());
                } finally {
//                    Conecion_postgres1.cerrarConexion();
                }
            } else {
                System.out.println("No existe");
                FacesContext.getCurrentInstance().getExternalContext().redirect("../logIn/index.jsp");

            }

        }
    }

    /**
     * Metodo para la carga de dias del calendario
     *
     * @return Un arreglo con la lista de dias para el calendario.
     * @throws IOException En caso
     * @throws ParseException
     */
    public ArrayList traer_dias() throws IOException, ParseException {

//        if (httpServletRequest.getSession().getAttribute("user") != null) {
        // System.out.println("************************Existe");
        ArrayList<Dias> di = new ArrayList();
        ArrayList<Dias> dias = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        try {
            Disponibilidad dis = new Disponibilidad();
            dis = (Disponibilidad) session.createQuery("select di from Persona p inner join "
                    + " p.usuario u inner join u.dispoUsuariosForProfesor d "
                    + " inner join d.disponibilidad di "
                    + " where  di.estado='A' and u.pegeId=" + pegeString).uniqueResult();
            Object lis_dias[] = dis.getDias().toArray();
            String nom_dia = "";
            for (int i = 0; i < lis_dias.length; i++) {
                nom_dia = lis_dias[i].toString();
                String nom[] = nom_dia.split("-");
                di.add(new Dias(dis.getCodDis().intValue(), nom[0]));

            }
//            while (Conecion_postgres1.rs.next()) {
//                System.out.println("----->>>>>>>>>>" + Conecion_postgres1.rs.getString(3));
//                di.add(new Dias(Integer.parseInt(Conecion_postgres1.rs.getString(3)), Conecion_postgres1.rs.getString(2)));
//                f1 = Conecion_postgres1.rs.getDate(6);
//                f2 = Conecion_postgres1.rs.getDate(7);
//            }

//            Conecion_postgres1.conectar();
//            Conecion_postgres1.ejecuteQuery("SELECT dia.*, disponibilidad.fecha_inicial,disponibilidad.fecha_final,disponibilidad.rango FROM \n"
//                    + "  public.usuario, \n"
//                    + "  public.disponibilidad, \n"
//                    + "  public.dispo_usuario, \n"
//                    + "  public.dia\n"
//                    + " WHERE \n"
//                    + "  usuario.pege_id = dispo_usuario.profesor AND\n"
//                    + "  disponibilidad.cod_dis = dispo_usuario.cod_dispousu AND\n"
//                    + "  disponibilidad.cod_dis = dia.cod_dis AND \n"
//                    + "  usuario.pege_id = " + pegeString);
//            Date f1 = null;
//            Date f2 = null;
//            while (Conecion_postgres1.rs.next()) {
//                System.out.println("----->>>>>>>>>>" + Conecion_postgres1.rs.getString(3));
//                di.add(new Dias(Integer.parseInt(Conecion_postgres1.rs.getString(3)), Conecion_postgres1.rs.getString(2)));
//                f1 = Conecion_postgres1.rs.getDate(6);
//                f2 = Conecion_postgres1.rs.getDate(7);
//            }
            if (new Date().before(dis.getFechaFinal())) {
                dias = Numero_asesorias(new SimpleDateFormat("YYYY-MM-dd").format(new Date()).toString(), dis.getFechaFinal().toString(), di);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Lo sentimos, el docente que ha seleccionado "
                        + "ya no esta disponible",
                        ""));
            }
//            System.out.println("---´´+´" +f1.toString());
            //System.out.println(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));

//            añadir_eventos();
            t.commit();
        } catch (Exception ex) {
            System.out.println("Error : " + ex.toString());
        } finally {
        }

        return dias;

    }

    public static ArrayList<Dias> Numero_asesorias(String fechaInicial, String fechaFinal, ArrayList x) throws ParseException {
        String diaInicio = "", mesInicio = "", añoInicio = "";
        diaInicio = fechaInicial.substring(8, 10);
        mesInicio = fechaInicial.substring(5, 7);
        añoInicio = fechaInicial.substring(0, 4);
        String diaFinal = "", mesFinal = "", añoFinal = "";
        diaFinal = fechaFinal.substring(8, 10);
        mesFinal = fechaFinal.substring(5, 7);
        añoFinal = fechaFinal.substring(0, 4);
        String dias_semana = "";
        String fecha_final = "";
        ArrayList<Dias> n = new ArrayList();
        int dia = 0;
        String dio = "";
        int num_id = 0, sum = 0, sum_mes = 0, count = 0;
        boolean p = true;
        int a = Integer.parseInt(diaInicio);
        int m = Integer.parseInt(mesInicio);
        int d = Integer.parseInt(añoInicio);
        int a2 = Integer.parseInt(diaFinal);
        int m2 = Integer.parseInt(mesFinal);
        int d2 = Integer.parseInt(añoFinal);
        m = m - 1;
        m2 = m2 - 1;
        int pasado = 0;
        int s = 0;
        int az = 0;
        Dias temp = null;
        int con = 0;
        int conce = 1;
        boolean con2 = true;
        Calendar Diadehoy = new GregorianCalendar();
        Diadehoy.set(d, m, a);
        int diia = 0;
        ArrayList<Dias> fechas_final = new ArrayList();
        while (p) {
//            System.out.println("- " + Diadehoy.get(Calendar.DAY_OF_WEEK) + "fecha "
//                    + Diadehoy.get(Calendar.DAY_OF_MONTH) + " mes " + Diadehoy.get(Calendar.MONTH));
            for (int i = 0; i < x.size(); i++) {
                temp = (Dias) x.get(i);
                if (temp.getDia().equals("Lunes")) {
                    diia = 2;
                } else if (temp.getDia().equals("Martes")) {
                    diia = 3;
                } else if (temp.getDia().equals("Miercoles")) {
                    diia = 4;
                } else if (temp.getDia().equals("Jueves")) {
                    diia = 5;
                } else if (temp.getDia().equals("Viernes")) {
                    diia = 6;
                } else if (temp.getDia().equals("Sabado")) {
                    diia = 7;
                }
                if (Diadehoy.get(Calendar.DAY_OF_WEEK) == diia) {
                    fechas_final.add(new Dias(temp.getCod(), Diadehoy.getTime()));
                }
                diia = 0;
            }
            if ((Diadehoy.get(Calendar.YEAR) == d2) && (Diadehoy.get(Calendar.MONTH) == m2) && (Diadehoy.get(Calendar.DAY_OF_MONTH) == a2)) {
                p = true;
                break;

            }
            Diadehoy.add(Calendar.DAY_OF_MONTH, 1);

        }
        return fechas_final;
    }

    public ArrayList<String> getRes() {
        return res;
    }

    public void setRes(ArrayList<String> res) {
        this.res = res;
    }

    /**
     * Accion para la meritar la acción del calendario de la Asesoría.
     *
     * @param x Evento de la seleccion en la View CalendarioAsesoria.xhtml
     * @throws ClassNotFoundException
     */
    public void onEventSelect(SelectEvent x) throws ClassNotFoundException {
        asesoriasPorDia.clear();
        try {
            event = (ScheduleEvent) x.getObject();
            Date fechaCalendario = null;
            fechaCalendario = event.getEndDate();
            int diaNumero = 0;
            fechaCalendario.getDay();
            String diaLetra = "";
            System.out.println("Diaaaa " + event.getStartDate());
            Calendar Horas = new GregorianCalendar();
            Horas.setTime(event.getStartDate());
            System.out.println("Diaaa ...... " + Horas.get(Calendar.DAY_OF_WEEK));
            diaNumero = Horas.get(Calendar.DAY_OF_WEEK);
            if (diaNumero == 2) {
                diaLetra = "Lunes";
            } else if (diaNumero == 3) {
                diaLetra = "Martes";
            } else if (diaNumero == 4) {
                diaLetra = "Miercoles";
            } else if (diaNumero == 5) {
                diaLetra = "Jueves";
            } else if (diaNumero == 6) {
                diaLetra = "Viernes";
            } else if (diaNumero == 7) {
                diaLetra = "Sabado";
            }
            String v[] = event.getTitle().split("#");
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction t = session.beginTransaction();
            Disponibilidad dis = (Disponibilidad) session.load(Disponibilidad.class, new BigDecimal(Integer.parseInt(v[1])));
            System.out.println("codigo dis : " + dis.getCodDis());
//            Conecion_postgres1.conectar();
//        Conecion_postgres1.ejecuteQuery("select hora_inicial,hora_final,rango from disponibilidad,dia where\n"
//                + "    disponibilidad.cod_dis = dia.cod_dis \n"
//                + "    and disponibilidad.cod_dis=" + v[1] + " and dia.dia='" + diaLetra + "'"); // day.setDate(day.getDate() - 1);
            String horaI = "", horaF = "";
            int rango = 0;
            Object lis_dia[] = dis.getDias().toArray();
            String trajo = "";
            System.out.println("dia letra : " + diaLetra);
            for (int i = 0; i < lis_dia.length; i++) {
                trajo = lis_dia[i].toString();
                String di[] = trajo.split("-");
                System.out.println("diaa ____ " + di[0]);
                if (diaLetra.equalsIgnoreCase(di[0])) {
                    System.out.println("Entro");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String stringDate = sdf.format(fechaCalendario);
                    System.out.println("h1: " + di[1] + " h2: " + di[2]);
                    asesoriasPorDia = calcularHoras(di[1], di[2], dis.getRango().intValue(), fechaCalendario, dis.getCodDis().intValue(), stringDate);
                } else {
                    System.out.println("No entro");
                }

            }
            System.out.println("---- ::: " + asesoriasPorDia.size());
//            while (Conecion_postgres1.rs.next()) {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                String stringDate = sdf.format(fechaCalendario);
//                asesoriasPorDia = calcularHoras(Conecion_postgres1.rs.getString(1), Conecion_postgres1.rs.getString(2), Conecion_postgres1.rs.getInt(3), fechaCalendario, Integer.parseInt(v[1]), stringDate);
//            }

            for (asesoria asesoriasPorDia1 : asesoriasPorDia) {
                System.out.println("-- " + asesoriasPorDia1.toString());
            }
            System.out.println("size : " + asesoriasPorDia.size());
            t.commit();
            System.out.println("-------------------");

            validarAssoria();

        } catch (Exception ex) {

        } finally {
        }
    }

    public int[] comprobar(int cod) throws ClassNotFoundException {
        Conecion_postgres1.conectar();
        int num[] = new int[2];

        Conecion_postgres1.ejecuteQuery("SELECT \n"
                + " count(asesoria.cod_asesoria) as asignadas , num_horas \n"
                + "FROM \n"
                + " public.disponibilidad, \n"
                + " public.asesoria, \n"
                + " public.dispo_usuario, \n"
                + " public.usuario\n"
                + "WHERE \n"
                + " disponibilidad.cod_dis = asesoria.cod_dis AND\n"
                + " disponibilidad.cod_dis = dispo_usuario.cod_dis AND\n"
                + " dispo_usuario.profesor = usuario.pege_id AND\n"
                + " disponibilidad.cod_dis = " + cod + "\n"
                + "Group by\n"
                + " num_horas; ");
        try {
            int num1 = 0, num2 = 0;

            while (Conecion_postgres1.rs.next()) {
                num1 = Conecion_postgres1.rs.getInt(1);
                num2 = Conecion_postgres1.rs.getInt(2);
            }
            if (num1 == 0 && num2 == 0) {
                num[0] = 10;
                num[1] = 10;
            } else {
                num[0] = num1;
                num[1] = num2;
            }

        } catch (Exception ex) {

        }

        return num;

    }

    public void guardarAsesoria(Date fecha, int cod_dis, String hora) throws ClassNotFoundException, ParseException {
        String cod = event.getTitle().toString();
        System.out.println("codigo " + cod);
        String co[] = cod.split("#");

        int v[] = comprobar(Integer.parseInt(co[1]));
        System.out.println("cod 1 : " + v[0] + " cod 2: " + v[1]);
        if (v[0] < v[1] || v[0]==10 && v[1]==10) {
            DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
            String convertido = fechaHora.format(fecha);
            if (aprobarAsesoria(convertido) == false) {
                System.out.println("Entroooooooo 1 ");
                int codgio_ase = Sequence.GetUltimoRegistro("select max(a.codAsesoria) from Asesoria a");
                int cod_asis = Sequence.GetUltimoRegistro("select max(a.codAsis) from Asistente a");
                int cod_estado = Sequence.GetUltimoRegistro("select max(e.codEstadoasee) from EstadosAsesoria e");
                System.out.println("codigooo : " + cod_estado);
                Calendar Horas = new GregorianCalendar();
                ArrayList<asesoria> horas = new ArrayList();
                String vectorHoras[] = hora.split(":");
                System.out.println("horaa--  " + hora);
                Horas.set(Calendar.HOUR_OF_DAY, Integer.parseInt(vectorHoras[0]));
                Horas.set(Calendar.MINUTE, Integer.parseInt(vectorHoras[1]));
                String hora_fina = "";
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                Transaction t = session.beginTransaction();
                try {
                    System.out.println("disponibilidad " + cod_dis);
                    Disponibilidad d = (Disponibilidad) session.load(Disponibilidad.class, new BigDecimal(cod_dis));
                    int rango = 0;
                    Ubicacion u = (Ubicacion) session.load(Ubicacion.class, new BigDecimal(1));
                    rango = d.getRango().intValue();
                    Horas.add(Calendar.MINUTE, rango);
                    String hora_f = Horas.get(Calendar.HOUR_OF_DAY) + ":" + Horas.get(Calendar.MINUTE);
                    if (hora_f.length() == 4) {
                        if (hora_f.substring(1, 2).equalsIgnoreCase(":")) {
                            hora_f = "0" + hora_f;
                        } else {
                            hora_f = hora_f + "0";
                        }
                    }
                    if (hora.length() == 4) {
                        if (hora.substring(1, 2).equalsIgnoreCase(":")) {
                            hora = "0" + hora;
                        }
                    }

                    fecha.setHours(Integer.parseInt(vectorHoras[0]));
                    fecha.setMinutes(Integer.parseInt(vectorHoras[1]));
                    fecha.setSeconds(00);
                    System.out.println("proyecto:::::: " + proyecto.getCodigoProyecto());
                    System.out.println("fecha Final : " + fecha.getHours() + ":" + fecha.getMinutes());

                    Asesoria asesoria = new Asesoria();
                    asesoria.setCodAsesoria(new BigDecimal(codgio_ase));
                    asesoria.setFechaAsesoria(fecha);
                    asesoria.setCodigoProyecto(proyecto.getCodigoProyecto());
                    asesoria.setDescription("Pendiente");
                    asesoria.setTipoAsesoria("Pendiente");
                    asesoria.setDisponibilidad(d);
                    asesoria.setUbicacion(u);
                    System.out.println("------");
                    asesoria.setHoraIni(hora);
                    asesoria.setHoraFin(hora_f);
                    session.save(asesoria);
                    ArrayList<Usuario> usu = new ArrayList();
                    System.out.println("-----2 ");
                    usu = (ArrayList) session.createQuery("select u from Usuario u inner join "
                            + "u.usuarioProyectosForEstudiante up inner join up.proyectos p"
                            + " where p.codigoProyecto=" + proyecto.getCodigoProyecto()).list();
                    for (Usuario get : usu) {
                        System.out.println("----3");
                        Asistente asi = new Asistente();
                        asi.setCodAsis(new BigDecimal(cod_asis));
                        asi.setAsistencia("Pendiente");
                        asi.setAsesoria(asesoria);
                        asi.setUsuario(get);
                        session.save(asi);
                        cod_asis++;
//                Conecion_postgres1.ejecuteUpdate("insert into asistente values(" + cod_asis + ",'Pendiente'," + get.getPegeId() + "," + codgio_ase + ")");

                    }
                    System.out.println("----4");
                    Estados e = (Estados) session.load(Estados.class, new BigDecimal(1));
                    EstadosAsesoria EA = new EstadosAsesoria();
                    EA.setCodEstadoasee(new BigDecimal(cod_estado));
                    System.out.println("----5");
                    EA.setAsesoria(asesoria);
                    EA.setEstados(e);
                    System.out.println("-----6");
                    session.save(EA);
                    System.out.println("----7");
                    t.commit();

                } catch (Exception ex) {
                    System.out.println("Error :++ " + ex.toString());
                }
                //            Conecion_postgres1.ejecuteUpdate("insert into estados_asesoria values(" + cod_estado + "," + codgio_ase + ",1)");

            } else {
                System.out.println("Entroooooooo 2 ");

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No puedes pedir 2 asesoria en un dia", ""));

            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ya paso el tope de asesorias para el docente", ""));

        }

//     
    }

    public boolean aprobarAsesoria(String fecha) throws ClassNotFoundException {
        System.out.println("pege_id --> " + pege_id + " fecha:--- " + fecha);
        boolean r = false;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();

        try {

            Asesoria a = new Asesoria();
            a = (Asesoria) session.createQuery("select A from Asesoria A inner join "
                    + " A.asistentes S inner join S.usuario U where"
                    + " A.fechaAsesoria='" + fecha + "' and U.pegeId=" + pege_id).uniqueResult();
            if (a != null) {
                r = true;
            }
            t.commit();
        } catch (Exception ex) {
            System.out.println("Error AprobarAsesria " + ex.toString());
        } finally {
        }
        return r;

    }

    public void validarAssoria() throws ClassNotFoundException {
        System.out.println("entroooooooooo : " + asesoriasPorDia.size());

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        System.out.println("----´-´´-");
//        Conecion_postgres1.conectar();
        try {
            for (int i = 0; i < asesoriasPorDia.size(); i++) {
                System.out.println("hora asesoria : " + asesoriasPorDia.get(i).getHora());
//                Conecion_postgres1.ejecuteQuery("select * from asesoria where hora_ini='" + asesoriasPorDia.get(i).getHora() + "'");
                Asesoria list_ase = new Asesoria();
                list_ase = (Asesoria) session.createQuery("select A from Asesoria A inner join "
                        + " A.estadosAsesorias es inner join es.estados e "
                        + " where A.fechaAsesoria='" + new SimpleDateFormat("YYYY-MM-dd").format(asesoriasPorDia.get(i).getFecha()) + "' "
                        + " and A.horaIni='" + asesoriasPorDia.get(i).getHora() + "' and e.codigoEstados=1").uniqueResult();
//                Conecion_postgres1.ejecuteQuery("select * from asesoria,estados_asesoria where asesoria.cod_asesoria=estados_asesoria.cod_asesoria and hora_ini='"
//                        + asesoriasPorDia.get(i).getHora()
//                        + "' and fecha_asesoria = '"
//                        + new SimpleDateFormat("YYYY-MM-dd").format(asesoriasPorDia.get(i).getFecha())
//                        + "' and estados_asesoria.codigo_estados=1");
//                while (Conecion_postgres1.rs.next()) {
//                    asesoriasPorDia.get(i).setAsiginado(true);
//                }
                if (list_ase != null) {
                    asesoriasPorDia.get(i).setAsiginado(true);
                }
            }
            t.commit();
        } catch (Exception ex) {
            System.err.println("Error validarAsesoria" + ex.toString());
        } finally {
//            Conecion_postgres1.cerrarConexion();
        }

    }

    public void traer_datos() throws ClassNotFoundException {
        String no = "", cod = "";
        String cod1[] = null;
        cal.clear();
        System.out.println("----- " + event.getTitle().toString());
        if (event.getTitle().length() >= 8) {
            no = event.getTitle().substring(0, 8);
        } else {
            no = event.getTitle();
        }
        if (no.equals("Asesoria")) {
            System.out.println("Asesoria");
//            cod1 = event.getTitle().split("#");
//            cod = cod1[1];
//            Control.ejecuteQuery(" select hora_asesoria, hora_final, fecha_asesoria from asesoria where cod_asesoria='" + cod + "'");
//            hora1 = "";
//            hora2 = "";
//            fecha_final = null;
//            try {
//                while (Control.rs.next()) {
//                    hora1 = Control.rs.getString(1);
//                    hora2 = Control.rs.getString(2);
//                    fecha_final = Control.rs.getDate(3);
//                }
//                cod = "";
//            } catch (Exception ex) {
//
//            }
        } else {
            System.out.println("Cita");
            CalendarioP calen = new CalendarioImple();
            Calendario calendario = new Calendario();
            cod1 = event.getTitle().split("#");
            cod = cod1[1];
            calendario = calen.BuscarCalendario(cod);
//            String titul = "";
            Date hora1 = null, hora2 = null;
            Date fecha1 = null, fecha2 = null;
            try {
                System.out.println("-");
                fecha_inicio = calendario.getFechaInicial();
//                titulo=calendario.getDescripcion();
                fecha2 = calendario.getFechaFinal();
                hora1 = calendario.getHoraIni();
                hora2 = calendario.getHoraFin();
//                cal.add(new CalendarioProfe_update(titul, fecha_inicio, fecha2, hora1, hora2));
//                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("DetalleCalen", cal);
//                FacesContext.getCurrentInstance().getExternalContext().responseReset();
                System.out.println("tra  " + titulo + "size " + cal.size());
            } catch (Exception ex) {

            }
        }

    }

    public void onDateSelect(SelectEvent selectEvent) {
        fecha_inicio = null;
        fecha_final = null;
        System.err.println("----" + (Date) selectEvent.getObject() + "-" + (Date) selectEvent.getObject());
//        esta = (Date) selectEvent.getObject();
//        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), null);

        hora1 = null;
        hora2 = null;
        fecha_inicio = (Date) selectEvent.getObject();
        fecha_final = (Date) selectEvent.getObject();
    }

    public static ArrayList<asesoria> calcularHoras(String horaInicio, String horaFinal, int rango, Date fecha, int cod_dis, String fec) {
        boolean r = false;
        Calendar Horas = new GregorianCalendar();
        ArrayList<asesoria> horas = new ArrayList();
        String vectorHoras[] = horaInicio.split(":");
        Horas.set(Calendar.HOUR_OF_DAY, Integer.parseInt(vectorHoras[0]));
        Horas.set(Calendar.MINUTE, Integer.parseInt(vectorHoras[1]));
        Calendar Horas2 = new GregorianCalendar();
        String vectorHoras2[] = horaFinal.split(":");
        Horas2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(vectorHoras2[0]));
        Horas2.set(Calendar.MINUTE, Integer.parseInt(vectorHoras2[1]));
        for (int i = 0; r = true; i++) {
            if (Horas.get(Calendar.HOUR_OF_DAY) == Horas2.get(Calendar.HOUR_OF_DAY)) {
                if (Horas.get(Calendar.MINUTE) == Horas2.get(Calendar.MINUTE)) {
                    r = true;
                    break;
                }
            }
            String minutos = Horas.get(Calendar.MINUTE) + "";
            if (minutos.length() == 1) {
                minutos = minutos + "0";
            }
            horas.add(new asesoria(fecha, Horas.get(Calendar.HOUR_OF_DAY) + ":" + minutos, false, cod_dis, fec));
            //horas.add(Horas.get(Calendar.HOUR_OF_DAY) + ":" + minutos);
            Horas.add(Calendar.MINUTE, rango);
        }
        return horas;
    }

    @Override
    public String toString() {
        return "ScheduleView2{" + "eventModel=" + eventModel + ", lazyEventModel=" + lazyEventModel + ", event=" + event + '}';
    }

    public void cancelar() {
        String no = "", cod = "";
        String cod1[] = null;
        System.err.println("Nombre " + event.getTitle());
        CalendarioP calen = new CalendarioImple();
        if (event.getTitle().length() >= 8) {
            no = event.getTitle().substring(0, 8);
        } else {
            no = event.getTitle();
        }
        if (no.equals("Asesoria")) {
            cod1 = event.getTitle().split("#");
            cod = cod1[1];
            System.err.println("Codigo es " + cod);
            Asesoria asesoria = new Asesoria();
            int convertir = Integer.parseInt(cod);
            asesoria.setCodAsesoria(new BigDecimal(convertir));
            Asistente asistente = new Asistente();
            asistente.setAsesoria(asesoria);
            boolean a = calen.BorrarASesoria(asesoria, asistente);
//            boolean año = Control.ejecuteUpdate("delete from asistente where cod_asesoria=" + cod);
            if (a) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancelado", ""));
                añadir_eventos();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "NO Cancelado", ""));
            }
        } else {
            cod1 = event.getTitle().split("#");
            cod = cod1[1];

            boolean p = buscarYborrar(cod);
            if (p) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancelado", ""));
                añadir_eventos();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "NO Cancelado", ""));
            }
        }
    }

    public boolean buscarYborrar(String cod) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        boolean r = false;
        try {
            Calendario temp = new Calendario();
            temp = (Calendario) session.createQuery("FROM Calendario c WHERE c.codCalendario=" + cod).uniqueResult();
            if (temp != null) {
                session.delete("Calendario", temp);
                r = true;
            }
            t.commit();
        } catch (Exception ex) {
            System.out.println("Error buscarYborrar " + ex.toString());
        }
        return r;
    }

    public void actual() throws IOException {
        System.out.println("entro a update");
        String codigo = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Codigo_asesoria");
        System.out.println("codigo que trajo es " + codigo);
    }

    public void actualizar() throws ClassNotFoundException, IOException {
        String no = "", cod = "";
        String cod1[] = null;
        System.out.println("--> " + event.getTitle());
//        Control.conectar();
        if (event.getTitle().length() >= 8) {
            no = event.getTitle().substring(0, 8);
        } else {
            no = event.getTitle();
        }
        if (no.equals("Asesoria")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede Actualizar", ""));
        } else {
            cod1 = event.getTitle().split("#");
            cod = cod1[1];
            System.out.println("codigo " + cod);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Codigo_asesoria", cod);
            FacesContext.getCurrentInstance().getExternalContext().redirect("Calendario_update.xhtml");
        }
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    public void setLazyEventModel(ScheduleModel lazyEventModel) {
        this.lazyEventModel = lazyEventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getEsta() {
        return esta;
    }

    public void setEsta(Date esta) {
        this.esta = esta;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getHora1() {
        return hora1;
    }

    public Date getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(Date fecha_final) {
        this.fecha_final = fecha_final;
    }

    public void setHora1(String hora1) {
        this.hora1 = hora1;
    }

    public String getHora2() {
        return hora2;
    }

    public void setHora2(String hora2) {
        this.hora2 = hora2;
    }

    public ArrayList<CalendarioProfe_update> getCal() {
        System.out.println("sizeee ---------------------------" + cal.size());
        return cal;
    }

    public void setCal(ArrayList<CalendarioProfe_update> cal) {
        this.cal = cal;
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

    public ArrayList<asesoria> getAsesoriasPorDia() {
        return asesoriasPorDia;
    }

    public void setAsesoriasPorDia(ArrayList<asesoria> asesoriasPorDia) {
        this.asesoriasPorDia = asesoriasPorDia;
    }

    public String getPrueba() {
        return prueba;
    }

    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

    public Proyectos getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyectos proyecto) {
        this.proyecto = proyecto;
    }

    public String getPege_id() {
        return pege_id;
    }

    public void setPege_id(String pege_id) {
        this.pege_id = pege_id;
    }

}
