
import dao.com.control.Control;
import dao.com.control.Sequence;
import java.awt.BorderLayout;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
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
public class Festivos implements Serializable {

    private ScheduleModel eventModel;
    private Calendar fecha_hoy;
    private Date fecha_Borrar;

    private ScheduleModel lazyEventModel;

    private ScheduleEvent event = new DefaultScheduleEvent();

    @PostConstruct
    public void init() {
        try {
            traer_fechas();

        } catch (Exception ex) {

        }
//        eventModel = new DefaultScheduleModel();
//        eventModel.addEvent(new DefaultScheduleEvent("Champions League Match", previousDay8Pm(), previousDay11Pm()));
//        eventModel.addEvent(new DefaultScheduleEvent("Birthday Party", today1Pm(), today6Pm()));
//        eventModel.addEvent(new DefaultScheduleEvent("Breakfast at Tiffanys", nextDay9Am(), nextDay11Am()));
//        eventModel.addEvent(new DefaultScheduleEvent("Plant the new garden stuff", theDayAfter3Pm(), fourDaysLater3pm()));
//
//        lazyEventModel = new LazyScheduleModel() {
//
//            @Override
//            public void loadEvents(Date start, Date end) {
//                Date random = getRandomDate(start);
//                addEvent(new DefaultScheduleEvent("Lazy Event 1", random, random));
//
//                random = getRandomDate(start);
//                addEvent(new DefaultScheduleEvent("Lazy Event 2", random, random));
//            }
//        };
    }

    public Calendar getFecha_hoy() {
        return fecha_hoy;
    }

    public void setFecha_hoy(Calendar fecha_hoy) {
        this.fecha_hoy = fecha_hoy;
    }

    public void traer_fechas() throws ClassNotFoundException {
        eventModel = new DefaultScheduleModel();
        Control.conectar();
        Control.ejecuteQuery("select * from festivos");
        Date fecha = null;
        try {
            while (Control.rs.next()) {
                fecha = Control.rs.getDate(2);
                eventModel.addEvent(new DefaultScheduleEvent("Festivo ", fecha, fecha));

            }

        } catch (Exception ex) {

        }

    }

    public Date getFecha_Borrar() {
        return fecha_Borrar;
    }

    public void setFecha_Borrar(Date fecha_Borrar) {
        this.fecha_Borrar = fecha_Borrar;
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
     public static int traerAño(Date fecha) {
        String año = fecha.toString();
        String año2 = año.substring((año.length() - 4), año.length());
        Control.ejecuteQuery("select * from año_Festivo where año='" + año2 + "'");
        boolean yaEsta = false;
        int codigo = 0;
        try {
            while (Control.rs.next()) {
                codigo = Control.rs.getInt(1);
                yaEsta = true;
            }
            if (yaEsta == false) {
                int cod_año = Sequence.Sequen("select max(cod_año) from año_Festivo");
                boolean r = Control.ejecuteUpdate("insert into año_Festivo values(" + cod_año + ",'" + año2 + "')");
                if (r) {
                    codigo = cod_año;
                } else {
                    codigo = 0;
                }
            }
        } catch (Exception ex) {

        }
        return codigo;
    }
    public void addEvent(ActionEvent actionEvent) {
        System.out.println("Entro1");
        Control.ejecuteQuery("select * from festivos where fecha='" + fecha_hoy.getTime() + "'");
        boolean yaesta = false;
        System.out.println("Entro2");
        try {
            while (Control.rs.next()) {
                yaesta = true;
            }
            if (yaesta == false) {
                System.out.println("Entro3");
                int codigo = Sequence.Sequen("select max(Festivo) from festivos");
                int codigo_año = traerAño(fecha_hoy.getTime());
                System.out.println("Entro4");
                if (codigo_año != 0) {
                    Control.ejecuteUpdate("insert into festivos values(" + codigo + ",'" + fecha_hoy.getTime() + "','" + codigo_año + "')");
                    eventModel.addEvent(new DefaultScheduleEvent("Festivo ", fecha_hoy.getTime(), fecha_hoy.getTime()));
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO", ""));
                    event = new DefaultScheduleEvent();
                    traer_fechas();
                }
            } else {
                System.out.println("No hay ");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRROR", ""));
            }
        } catch (Exception ex) {

        }

    }

    public void DeleteEvent(ActionEvent actionEvent) throws ClassNotFoundException {
        System.out.println("Entro1");
        boolean dele = Control.ejecuteUpdate("delete from festivos where fecha='" + fecha_Borrar + "'");
        if (dele) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO", ""));
            event = new DefaultScheduleEvent();
            traer_fechas();

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRROR", ""));
        }
    }

    public void onEventSelect(SelectEvent selectEvent) {
        System.out.println("entrooooo");
        event = (ScheduleEvent) selectEvent.getObject();
        fecha_Borrar = (Date) event.getStartDate();
        System.out.println("" + fecha_Borrar);
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
        Date fecha = (Date) selectEvent.getObject();
        fecha_hoy = Calendar.getInstance();
        fecha_hoy.setTime(fecha); // Configuramos la fecha que se recibe
        fecha_hoy.add(Calendar.DAY_OF_YEAR, 1);
        System.out.println("fecha " + fecha_hoy.getTime());

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
}
