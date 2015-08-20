package Beans;

import dao.CalendarioImple;
import dao.CalendarioP;
import Entity.Asesoria;
import Entity.Asistente;
import Entity.Calendario;
import Entity.Persona;
import Entity.Usuario;
import Modelo.Conecion_postgres1;
import Modelo.MDias;
import Modelo.Secuencia;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @PostConstruct
    public void init() {
        borrarTodo();
        añadir_eventos();
    }

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

    public void añadir_eventos() {
        eventModel = new DefaultScheduleModel();
        ArrayList<Date> asesorias = null;

        try {
            asesorias = traer_dias();

            MDias temp = null;
            MDias temp2 = null;
            String fecha = "";
            System.out.println("2");
            int h1 = 0, h2 = 0;
            System.out.println("3");
            int min1 = 0, min2 = 0;
            for (int i = 0; i < asesorias.size(); i++) {
//                System.out.println(".l.");
//                temp = (MDias) asesorias.get(i);
//                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
//                Date date1 = fmt.parse(temp.getFecha1());
//                Date date2 = fmt.parse(temp.getFecha1());
//                h1 = Integer.parseInt((String) temp.getHora_inicio().subSequence(0, 2));
//                min1 = Integer.parseInt((String) temp.getHora_inicio().subSequence(3, 5));
//                h2 = Integer.parseInt((String) temp.getHora_final().subSequence(0, 2));
//                min2 = Integer.parseInt((String) temp.getHora_final().subSequence(3, 5));
//                date1.setHours(h1);
//                date1.setMinutes(min1);
//                date2.setHours(h2);
//                date2.setMinutes(min2);
//                date2.setDate(date2.getDate() - 1);
//                System.out.println("Asesoria " + temp.getHora_inicio() + "-" + temp.getHora_final() + " #" + date1 + "+" + date2);
                eventModel.addEvent(new DefaultScheduleEvent("Asesoria " + "", asesorias.get(i), asesorias.get(i)));
            }
        } catch (IOException ex) {
            Logger.getLogger(CalendarioAsesoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList traer_dias() throws IOException {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
//        if (httpServletRequest.getSession().getAttribute("user") != null) {
        System.out.println("Existe");

//        Persona p = (Persona) httpServletRequest.getSession().getAttribute("persona");
//        p.getUsuario().getPegeId();
//        try {
//            Conecion_postgres1.conectar();
//            Conecion_postgres1.ejecuteQuery("SELECT \n"
//                    + "  dia.*\n"
//                    + "FROM \n"
//                    + "  public.usuario, \n"
//                    + "  public.disponibilidad, \n"
//                    + "  public.dispo_usuario, \n"
//                    + "  public.dia\n"
//                    + "WHERE \n"
//                    + "  usuario.pege_id = dispo_usuario.profesor AND\n"
//                    + "  disponibilidad.cod_dis = dispo_usuario.cod_dispousu AND\n"
//                    + "  disponibilidad.cod_dis = dia.cod_dis AND \n"
//                    + "  usuario.pege_id = (\n"
//                    + "	SELECT \n"
//                    + "	  usuario_proyecto.director\n"
//                    + "	FROM \n"
//                    + "	  public.usuario, \n"
//                    + "	  public.usuario_proyecto\n"
//                    + "	WHERE \n"
//                    + "	  usuario.pege_id = usuario_proyecto.estudiante and\n"
//                    + "	  usuario.pege_id = 3\n"
//                    + "  )");
//
//            if (Conecion_postgres1.rs.next()) {
//                
//                while (Conecion_postgres1.rs.next()) {
//                    String dia = Conecion_postgres1.rs.getString(1);
//                    System.out.println("Dia disponible:" + dia);
//                }
//            }
        Date Fecha = new Date();
        Fecha.setDate(24);

        ArrayList<Date> dias = new ArrayList();
        dias.add(Fecha);
            
//        dias.add(new Date("31-08-2015"));
//        dias.add(new Date("07-09-2015"));
//        dias.add(new Date("14-09-2015"));
//        dias.add(new Date("21-09-2015"));
//        dias.add(new Date("29-09-2015"));

        System.out.println("--------------------------------------------------------------------------------------Hola");
        return dias;
//        } catch (SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(CalendarioAsesoria.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        } finally {
//            Conecion_postgres1.cerrarConexion();
//        }

//        } else {
//            System.out.println("No existe");
//            FacesContext.getCurrentInstance().getExternalContext().redirect("../logIn/index.jsp");
//            return null;
//        }
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

    public void onEventSelect(SelectEvent x) throws ClassNotFoundException {
        event = (ScheduleEvent) x.getObject();
        Date d = null;
        d = event.getEndDate();
        d.setDate(d.getDate() - 1);
        System.err.println("fechaaa " + d);
        titulo = "aaa";
        traer_datos();
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
//            boolean a = Control.ejecuteUpdate("delete from asistente where cod_asesoria=" + cod);
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

//            cod1 = event.getTitle().split("#");
//            cod = cod1[1];
//            System.err.println("titulo " + event.getTitle() + "--- " + event.getEndDate() + "---" + event.getStartDate() + "-- "
//                    + hora1 + "--" + hora2);
//            System.err.println("update calendario set fecha_inicio='" + event.getStartDate() + "'"
//                    + ",fecha_final='" + event.getEndDate() + "',hora_inicio='" + hora1 + "',hora_final='" + hora2 + "' where codigo=" + cod);
//            boolean r = Control.ejecuteUpdate("update calendario set fecha_inicio='" + event.getStartDate() + "'"
//                    + ",fecha_final='" + event.getEndDate() + "',hora_inicio='" + hora1 + "',hora_final='" + hora2 + "' where codigo=" + cod);
//            System.err.println("Boolen " + r);
//            if (r) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado", ""));
//                añadir_eventos();
//            } else {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede Actualizar", ""));
//            }
//        }
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

}