package dao.com.control;

import dao.com.control.Control;
import dao.com.modelo.Dias;
import java.io.IOException;
import java.io.Serializable;
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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@ManagedBean
@ViewScoped
public class Calendario_profesor implements Serializable {

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

    @PostConstruct
    public void init() {
        añadir_eventos();
    }

    public void añadir_eventos() {
        eventModel = new DefaultScheduleModel();
        ArrayList a = null;
        ArrayList b = null;
        try {
            a = traer_dias();
            b = traer_citas();
            Dias temp = null;
            Dias temp2 = null;
            String fecha = "";
            int h1 = 0, h2 = 0;
            int min1 = 0, min2 = 0;
            System.out.println("comenzamos");
            for (int i = 0; i < a.size(); i++) {
                temp = (Dias) a.get(i);
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = fmt.parse(temp.getFecha1());
                Date date2 = fmt.parse(temp.getFecha1());
//                h1 = Integer.parseInt((String) temp.getHora_inicio().subSequence(0, 2));
//                min1 = Integer.parseInt((String) temp.getHora_inicio().subSequence(3, 5));
//                h2 = Integer.parseInt((String) temp.getHora_final().subSequence(0, 2));
//                min2 = Integer.parseInt((String) temp.getHora_final().subSequence(3, 5));
//                date1.setHours(h1);
//                date1.setMinutes(min1);
//                date2.setHours(h2);
//                date2.setMinutes(min2);
//                date2.setDate(date2.getDate() + 1);
                System.out.println("Asesoria " + temp.getHora_inicio() + "-" + temp.getHora_final() + " #" + date1 + "+" + date2);
                eventModel.addEvent(new DefaultScheduleEvent("Asesoria " + temp.getHora_inicio() + "-" + temp.getHora_final() + " #" + temp.getCod(), date1, date2));
            }

            for (int k = 0; k < b.size(); k++) {
                temp2 = (Dias) b.get(k);
                System.err.println(temp2.toString());
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = fmt.parse(temp2.getFecha1());
                SimpleDateFormat fmt2 = new SimpleDateFormat("yyyy-MM-dd");
                Date date = fmt2.parse(temp2.getFecha2());
                System.out.println("hora1 " + temp2.getHora_inicio());
                System.out.println("hora2 " + temp2.getHora_final());
                h1 = Integer.parseInt((String) temp2.getHora_inicio().subSequence(0, 2));
                min1 = Integer.parseInt((String) temp2.getHora_inicio().subSequence(3, 5));
                h2 = Integer.parseInt((String) temp2.getHora_final().subSequence(0, 2));
                min2 = Integer.parseInt((String) temp2.getHora_final().subSequence(3, 5));
                date1.setHours(h1);
                date1.setMinutes(min1);
                date.setHours(h2);
                date.setMinutes(min2);
                date.setDate(date.getDate() + 1);
                System.out.println("citas--- " + date1 + "-" + date);
                eventModel.addEvent(new DefaultScheduleEvent("Cita #" + temp2.getCod(), date1, date));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Calendario_profesor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Calendario_profesor.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public ArrayList traer_dias() throws ClassNotFoundException {
        ArrayList<Dias> dias = new ArrayList();
        Control.conectar();
        Control.ejecuteQuery("select distinct asesoria.cod_asesoria,fecha_asesoria,hora_asesoria from disponibilidad,asesoria,asistente,usuario \n"
                + "where\n"
                + "disponibilidad.cod_dis=asesoria.cod_dis \n"
                + "and asesoria.cod_asesoria=asesoria.cod_asesoria\n"
                + "and asistente.pege_id=usuario.pege_id\n"
                + "and usuario.pege_id=2");
        String dia = "";
        String hora1 = "", hora2 = "", hfinal = "";
        int cod = 0;
        try {
            while (Control.rs.next()) {
                cod = Control.rs.getInt(1);
                dia = Control.rs.getString(2);
                hora1 = Control.rs.getString(3);
                Dias d = new Dias(cod, dia, "", hora1, hora2);
                dias.add(d);
                System.out.println("asesorias--------------- " + d.toString());

            }
        } catch (Exception ex) {

        }
        return dias;
    }

    public ArrayList traer_citas() throws ClassNotFoundException {
        ArrayList<Dias> dias = new ArrayList();
        Control.conectar();
        Control.ejecuteQuery("select calendario.* from calendario,usuario where usuario.pege_id=calendario.pege_id\n"
                + "                and usuario.pege_id=1");
        String dia = "", dia2 = "";
        String hora1 = "", hora2 = "", hfinal = "";
        String h1 = "", h2 = "", ulti = "";
        int cod = 0, cod2 = 0;
        System.out.println("--------------");
        try {
            while (Control.rs.next()) {
                cod = Control.rs.getInt(1);
                dia = Control.rs.getString(2);
                dia2 = Control.rs.getString(3);
                hora1 = Control.rs.getString(5);
                hora2 = Control.rs.getString(6);
                Dias d = new Dias(cod, dia, dia2, hora1, hora2);
                dias.add(d);
                System.out.println("citas--------------- " + d.toString());
            }
        } catch (Exception ex) {

        }
        return dias;
    }

    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1);    //set random day of month

        return date.getTime();
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar.getTime();
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar;
    }

    private Date previousDay8Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 8);

        return t.getTime();
    }

    private Date previousDay11Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date today1Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 1);

        return t.getTime();
    }

    private Date theDayAfter3Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    private Date today6Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 6);

        return t.getTime();
    }

    private Date nextDay9Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 9);

        return t.getTime();
    }

    private Date nextDay11Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date fourDaysLater3pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void addEvent(ActionEvent actionEvent) throws ClassNotFoundException, ParseException {
        System.err.println("------------------");
        Control.conectar();
        System.err.println(titulo + "-" + esta + "-" + fecha_final);
        int a = Max_calendar();
        Control.ejecuteUpdate("insert into calendario values(" + a + ",'" + titulo + "','" + fecha_inicio + "','"
                + fecha_final + "'," + cod + ",'" + hora1 + "','" + hora2 + "')");
        System.err.println(event.getTitle() + "-" + esta + "-" + fecha_final);
        String f1 = "", f2 = "";
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        f1 = format.format(fecha_final);
        f2 = format.format(fecha_inicio);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = fmt.parse(f1);
        Date date2 = fmt.parse(f2);
        event.setId("" + a);
        eventModel.addEvent(new DefaultScheduleEvent(titulo, date2, date1));
        event = new DefaultScheduleEvent();
        hora1 = "";
        hora2 = "";
        añadir_eventos();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Executed", ""));
    }

    public int Max_calendar() throws ClassNotFoundException {
        Control.conectar();
        Control.ejecuteQuery("select max(codigo) from calendario");
        int a = 0;
        try {
            while (Control.rs.next()) {
                a = Control.rs.getInt(1);
            }
        } catch (Exception ex) {
        }
        return a + 1;
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
        traer_datos();
    }

    public void traer_datos() throws ClassNotFoundException {
        Control.conectar();
        String no = "", cod = "";
        String cod1[] = null;
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
//            System.err.println("-----------------------------------------------");
//            cod1 = event.getTitle().split("#");
//            cod = cod1[1];
//            Control.ejecuteQuery("select hora_inicio,hora_final,fecha_final from calendario where codigo='" + cod + "'");
//            hora1 = "";
//            hora2 = "";
//            fecha_final = null;
//            try {
//                while (Control.rs.next()) {
//                    hora1 = Control.rs.getString(1);
//                    fecha_final = Control.rs.getDate(3);
//                    System.err.println("---<< " + hora1 + "--" + hora2 + "-- fecha " + fecha_final);
//                }
//                cod = "";
//            } catch (Exception ex) {
//
//            }
        }
        System.err.println("hora " + hora1 + "--" + hora2);

    }

    public void onDateSelect(SelectEvent selectEvent) {
        System.err.println("----" + (Date) selectEvent.getObject() + "-" + (Date) selectEvent.getObject());
        esta = (Date) selectEvent.getObject();
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), null);
        hora1 = "";
        hora2 = "";
        fecha_final = null;
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    @Override
    public String toString() {
        return "ScheduleView2{" + "eventModel=" + eventModel + ", lazyEventModel=" + lazyEventModel + ", event=" + event + '}';
    }

    public void cancelar() throws ClassNotFoundException {
        Control.conectar();
        String no = "", cod = "";
        String cod1[] = null;
        System.err.println("Nombre " + event.getTitle());
        if (event.getTitle().length() >= 8) {
            no = event.getTitle().substring(0, 8);
        } else {
            no = event.getTitle();
        }
        if (no.equals("Asesoria")) {
            cod1 = event.getTitle().split("#");
            cod = cod1[1];
            System.err.println("Codigo es " + cod);
            boolean a = Control.ejecuteUpdate("delete from asistente where cod_asesoria=" + cod);
            if (a) {
                boolean r = Control.ejecuteUpdate("delete from asesoria where cod_asesoria=" + cod);
                if (r) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancelado", ""));
                    añadir_eventos();
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "NO Cancelado", ""));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "NO Cancelado", ""));
            }
        } else {
            cod1 = event.getTitle().split("#");
            cod = cod1[1];
            System.err.println("codigo es " + cod);
            boolean p = Control.ejecuteUpdate("delete from calendario where cod_calemndario=" + cod);
            if (p) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancelado", ""));
                añadir_eventos();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "NO Cancelado", ""));
            }
        }
    }

    public void actual() throws IOException {
        System.out.println("entro a update");
       String codigo=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Codigo_asesoria");
        System.out.println("codigo que trajo es "+ codigo);
    }

    public void actualizar() throws ClassNotFoundException, IOException {
        String no = "", cod = "";
        String cod1[] = null;
        cod1 = event.getTitle().split("#");
        cod = cod1[1];
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Codigo_asesoria", cod);
        FacesContext.getCurrentInstance().getExternalContext().redirect("Calendario_update.xhtml");
//        Control.conectar();
//      
//        if (event.getTitle().length() >= 8) {
//            no = event.getTitle().substring(0, 8);
//        } else {
//            no = event.getTitle();
//        }
//        if (no.equals("Asesoria")) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede Actualizar", ""));
//        } else {
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

}
