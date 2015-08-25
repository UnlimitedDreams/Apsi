package Beans;

import dao.CalendarioImple;
import dao.CalendarioP;
import Entity.Asesoria;
import Entity.Asistente;
import Entity.Calendario;
import Entity.Usuario;
import Modelo.Conecion_postgres1;
import Modelo.MDias;
import Modelo.Profesor;
import Modelo.Secuencia;
import Modelo.asesoria;
import dao.Dias;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
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
//        borrarTodo();
        //añadir_eventos();
        traerDocente();
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
            System.out.println("borrado todo");
        } catch (Exception ex) {
            System.out.println("Error borrarTodo " + ex.toString());
        }
    }

    /**
     * Metodo para añadir los eventos año la vista CalentadioAsesoria.xhtml.
     */
    public void añadir_eventos() {

        eventModel = new DefaultScheduleModel();
        System.out.println("---- " + pegeString);
        ArrayList<Dias> asesorias = null;

        try {
            asesorias = traer_dias();
            MDias temp = null;
            MDias temp2 = null;
            String fecha = "";
            // System.out.println("2");
            int h1 = 0, h2 = 0;
            //  System.out.println("3");
            int min1 = 0, min2 = 0;
            for (Dias asesoria : asesorias) {
                System.out.println("Dias fechas:::::::::::::::\n" + asesoria.getFechas() + "----" + asesoria.getCod());

            }
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
    public void traerDocente() {
        docentes.clear();
        try {
            Conecion_postgres1.conectar();
            Conecion_postgres1.ejecuteQuery("SELECT \n"
                    + "    usuario.pege_id, persona.nombres,persona.apellidos\n"
                    + " FROM \n"
                    + "  public.disponibilidad, \n"
                    + "  public.dia, \n"
                    + "  public.dispo_usuario, \n"
                    + "  public.usuario, \n"
                    + "  public.persona\n"
                    + " WHERE \n"
                    + "  disponibilidad.cod_dis = dia.cod_dis AND\n"
                    + "  dispo_usuario.cod_dis = disponibilidad.cod_dis AND\n"
                    + "  usuario.pege_id = dispo_usuario.profesor AND\n"
                    + "  usuario.pege_id = persona.pege_id");
            while (Conecion_postgres1.rs.next()) {

                docentes.add(new Profesor(Conecion_postgres1.rs.getString(1), Conecion_postgres1.rs.getString(2), Conecion_postgres1.rs.getString(3)));
            }
        } catch (NullPointerException e) {
            System.out.println("No hay docentes" + e);
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("-------------------");
            Logger.getLogger(CalendarioAsesoria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conecion_postgres1.cerrarConexion();
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
        System.out.println("----------------------------------------");
        System.out.println("pegeID " + pegeString);
        try {
            Conecion_postgres1.conectar();
            Conecion_postgres1.ejecuteQuery("SELECT dia.*, disponibilidad.fecha_inicial,disponibilidad.fecha_final,disponibilidad.rango FROM \n"
                    + "  public.usuario, \n"
                    + "  public.disponibilidad, \n"
                    + "  public.dispo_usuario, \n"
                    + "  public.dia\n"
                    + " WHERE \n"
                    + "  usuario.pege_id = dispo_usuario.profesor AND\n"
                    + "  disponibilidad.cod_dis = dispo_usuario.cod_dispousu AND\n"
                    + "  disponibilidad.cod_dis = dia.cod_dis AND \n"
                    + "  usuario.pege_id = " + pegeString);

            Date f1 = null;
            Date f2 = null;
            while (Conecion_postgres1.rs.next()) {
                System.out.println("----->>>>>>>>>>" + Conecion_postgres1.rs.getString(3));
                di.add(new Dias(Integer.parseInt(Conecion_postgres1.rs.getString(3)), Conecion_postgres1.rs.getString(2)));
                f1 = Conecion_postgres1.rs.getDate(6);
                f2 = Conecion_postgres1.rs.getDate(7);
            }

            if (new Date().before(f2)) {
                dias = Numero_asesorias(new SimpleDateFormat("YYYY-MM-dd").format(new Date()).toString(), f2.toString(), di);
                System.out.println("La fecha esta antes");
            } else {
                System.out.println("Sin sentivo");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Lo sentimos, el docente que ha seleccionado "
                        + "ya no esta disponible",
                        ""));
            }
//            System.out.println("---´´+´" +f1.toString());
            //System.out.println(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));

//            añadir_eventos();
        } catch (SQLException ex) {
            Logger.getLogger(CalendarioAsesoria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CalendarioAsesoria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conecion_postgres1.cerrarConexion();
        }

        return dias;

    }

    public static ArrayList<Dias> Numero_asesorias(String fechaInicial, String fechaFinal, ArrayList x) throws ParseException {
        String diaInicio = "", mesInicio = "", añoInicio = "";
        diaInicio = fechaInicial.substring(8, 10);
        mesInicio = fechaInicial.substring(6, 7);
        añoInicio = fechaInicial.substring(0, 4);
        System.out.println("----------------------" + fechaInicial);
        String diaFinal = "", mesFinal = "", añoFinal = "";
        diaFinal = fechaFinal.substring(8, 10);
        mesFinal = fechaFinal.substring(6, 7);
        añoFinal = fechaFinal.substring(0, 4);
        //System.err.println("----------------------");
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
                    //          System.out.println("-----");
                    fechas_final.add(new Dias(temp.getCod(), Diadehoy.getTime()));
                }
                diia = 0;
            }
            if ((Diadehoy.get(Calendar.YEAR) == d2) && (Diadehoy.get(Calendar.MONTH) == m2) && (Diadehoy.get(Calendar.DAY_OF_MONTH) == a2)) {
                p = true;
                //    System.out.println("Salio");
                break;

            }
            Diadehoy.add(Calendar.DAY_OF_MONTH, 1);

        }
        return fechas_final;
    }

    public void addEvent(ActionEvent actionEvent) throws ClassNotFoundException, ParseException {
        System.out.println("------------------ " + hora1);
        Date h1 = new Date();
        Date h2 = new Date();
        String v[] = hora1.split(":");
        h1.setHours(Integer.parseInt(v[0]));
        h1.setMinutes(Integer.parseInt(v[1]));
        h1.setSeconds(00);
        System.out.println("h1: " + h1);
        String v2[] = hora1.split(":");
        h2.setHours(Integer.parseInt(v2[0]));
        h2.setMinutes(Integer.parseInt(v2[1]));
        h2.setSeconds(00);
//        if (buscarFechas(h1, h2) == null) {
        System.out.println("timeee " + h2.getTime());
        System.out.println("h2: " + h2);
        CalendarioP calen = new CalendarioImple();
        int Codigo_calendario = Secuencia.seque("select max(cod_calendario) from calendario");
        Usuario usu = new Usuario();
        usu.setPegeId(new BigDecimal(1));
        Calendario c = new Calendario();
        c.setCodCalendario(Codigo_calendario);
        c.setFechaInicial(fecha_inicio);
        c.setFechaFinal(fecha_final);
        c.setUsuario(usu);
        System.out.println("2");

        System.out.println("3");

        c.setHoraIni(h1);
        c.setHoraFin(h2);
        c.setDescripcion(titulo);
        System.out.println("1");
        boolean r = calen.CrearCalendario(c);
        System.out.println("2 " + r);
        if (r) {
            String f1 = "", f2 = "";
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            f1 = format.format(fecha_final);
            f2 = format.format(fecha_inicio);
            System.out.println("4");
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = fmt.parse(f1);
//            date1.setTime(Long.parseLong(hora1));
            Date date2 = fmt.parse(f2);
//            date2.setTime(Long.parseLong(hora2));
            System.out.println("5");
////            event.setId("" + Codigo_calendario);
            eventModel.addEvent(new DefaultScheduleEvent(titulo, date2, date1));
            event = new DefaultScheduleEvent();
            hora1 = null;
            hora2 = null;
            System.out.println("6");
            añadir_eventos();
            System.out.println("7");
            borrarTodo();
            System.out.println("8");

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No Executed", ""));
        }
//        } else {
//            System.out.println("ya esta el calendario");
//        }

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
        event = (ScheduleEvent) x.getObject();
        Date fechaCalendario = null;
        fechaCalendario = event.getEndDate();
        int diaNumero = fechaCalendario.getDay();
        String diaLetra = "";
        if (diaNumero == 1) {
            diaLetra = "Lunes";
        } else if (diaNumero == 2) {
            diaLetra = "Martes";
        } else if (diaNumero == 3) {
            diaLetra = "Miercoles";
        } else if (diaNumero == 4) {
            diaLetra = "Jueves";
        } else if (diaNumero == 5) {
            diaLetra = "Viernes";
        } else if (diaNumero == 6) {
            diaLetra = "Sabado";
        }
        System.out.println(diaLetra + "--- " + event.getTitle());
        String v[] = event.getTitle().split("#");
        Conecion_postgres1.conectar();
        Conecion_postgres1.ejecuteQuery("select hora_inicial,hora_final,rango from disponibilidad,dia where\n"
                + "    disponibilidad.cod_dis = dia.cod_dis \n"
                + "    and disponibilidad.cod_dis=" + v[1] + " and dia.dia='" + diaLetra + "'"); // day.setDate(day.getDate() - 1);
        //System.err.println("fechaaa " + day);}
        String horaI = "", horaF = "";
        int rango = 0;

        try {
            while (Conecion_postgres1.rs.next()) {
                asesoriasPorDia = calcularHoras(Conecion_postgres1.rs.getString(1), Conecion_postgres1.rs.getString(2), Conecion_postgres1.rs.getInt(3), fechaCalendario, Integer.parseInt(v[1]));
            }
            validarAssoria();
            for (asesoria asesoriasPorDia1 : asesoriasPorDia) {
                System.out.println("-- " + asesoriasPorDia1.toString());
            }
            System.out.println("size : " + asesoriasPorDia.size());

        } catch (Exception ex) {

        } finally {
            Conecion_postgres1.cerrarConexion();
        }
    }

    public void guardarAsesoria(Date fecha, int cod_dis, String hora) throws ClassNotFoundException {
        try {
            int codgio_ase = Secuencia.seque("select max(cod_asesoria) from asesoria");
            int cod_asis=Secuencia.seque("select max(cod_asis) from asistente");
            Calendar Horas = new GregorianCalendar();
            ArrayList<asesoria> horas = new ArrayList();
            String vectorHoras[] = hora.split(":");
            System.out.println("horaa--  " + hora);
            Horas.set(Calendar.HOUR_OF_DAY, Integer.parseInt(vectorHoras[0]));
            Horas.set(Calendar.MINUTE, Integer.parseInt(vectorHoras[1]));
            String hora_fina = "";
            Conecion_postgres1.conectar();
            Conecion_postgres1.ejecuteQuery("select rango from disponibilidad where cod_dis=" + cod_dis);
            int rango = 0;
            while (Conecion_postgres1.rs.next()) {
                rango = Conecion_postgres1.rs.getInt(1);
            }
            Horas.add(Calendar.MINUTE, rango);
            String hora_f=Horas.get(Calendar.HOUR)+":"+Horas.get(Calendar.MINUTE);
            Conecion_postgres1.ejecuteUpdate("insert into asesoria values(" + codgio_ase + ",'" + fecha + "',1,'pendiente','pendiente'," + cod_dis + "," + 1 + ",'" + hora + "','" +hora_f+"')");
            Conecion_postgres1.ejecuteUpdate("insert into asistente values("+cod_asis+",'Pendiente',6,"+codgio_ase+")");
            
        } catch (ClassNotFoundException | NumberFormatException ex ) {
            System.out.println("Erorr " + ex.toString());
        } catch(SQLException ex){
            System.out.println("Error 2 " +ex.toString());
        }
        finally {
            Conecion_postgres1.cerrarConexion();
        }

//         faceContext = FacesContext.getCurrentInstance();
//        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
//        if (httpServletRequest.getSession().getAttribute("user") != null) {
//             faceContext = FacesContext.getCurrentInstance();
//        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
//        if (httpServletRequest.getSession().getAttribute("user") != null) {
//            System.out.println("Existe");
//
//            Persona p = (Persona) httpServletRequest.getSession().getAttribute("persona");
//           
//        } else {
//            System.out.println("No existe");
//            FacesContext.getCurrentInstance().getExternalContext().redirect("../logIn/index.jsp");
//
//        }
    }

    public void validarAssoria() throws ClassNotFoundException {
        Conecion_postgres1.conectar();
        try {
            for (int i = 0; i < asesoriasPorDia.size(); i++) {
                Conecion_postgres1.ejecuteQuery("select * from asesoria where hora_ini='" + asesoriasPorDia.get(i).getHora() + "'");
                while (Conecion_postgres1.rs.next()) {
                    asesoriasPorDia.get(i).setAsiginado(true);
                }
            }
        } catch (Exception ex) {
            System.err.println("Error " + ex.toString());
        } finally {
            Conecion_postgres1.cerrarConexion();
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

    public static ArrayList<asesoria> calcularHoras(String horaInicio, String horaFinal, int rango, Date fecha, int cod_dis) {
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
            horas.add(new asesoria(fecha, Horas.get(Calendar.HOUR_OF_DAY) + ":" + minutos, false, cod_dis));
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

}
