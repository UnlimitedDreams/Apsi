package Beans;

import Dao.FestivosDao;
import Dao.FestivosImple;
import Dao.Sequence;
import Entity.AñoFestivo;
import Entity.Festivos;
import java.awt.BorderLayout;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.hibernate.Query;
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
public class FestivosBeans implements Serializable {

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
            System.out.println("Error "+ex.getMessage());
        }
    }

    public Calendar getFecha_hoy() {
        return fecha_hoy;
    }

    public void setFecha_hoy(Calendar fecha_hoy) {
        this.fecha_hoy = fecha_hoy;
    }

    public void traer_fechas() {
        eventModel = new DefaultScheduleModel();
        ArrayList<Festivos> fes = new ArrayList();
        FestivosDao festi = new FestivosImple();
        fes = festi.listaFestivos();
        Festivos temp = null;
        for (int i = 0; i < fes.size(); i++) {
            temp = (Festivos) fes.get(i);
            eventModel.addEvent(new DefaultScheduleEvent("Festivo ", temp.getFecha(), temp.getFecha()));
        }
    }

    public Date getFecha_Borrar() {
        return fecha_Borrar;
    }

    public void setFecha_Borrar(Date fecha_Borrar) {
        this.fecha_Borrar = fecha_Borrar;
    }

    
    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public static long traerAño(Date fecha) {
        String año = fecha.toString();
        String año2 = año.substring((año.length() - 4), año.length());
        FestivosDao festi = new FestivosImple();
        Sequence seque = new Sequence();
        long codigo = 0;
        try {
            AñoFestivo temp = null;
            temp = festi.Traer_año(año2);
            if (temp == null) {
                Query query = Sequence.GetUltimoRegistro("FROM Dias order by codDias DESC");
                String cod_salvador = "" + query.uniqueResult();
                int codigo_año = Integer.parseInt(cod_salvador);
                AñoFestivo añofestivo = new AñoFestivo();
                añofestivo.setCodAño(codigo_año + 1);
                añofestivo.setAño(año2);
                boolean r = festi.CrearAñoFestivo(añofestivo);
                if (r) {
                    codigo = codigo_año;
                } else {
                    codigo = 0;
                }
            } else {
                codigo = temp.getCodAño();
            }
        } catch (Exception ex) {

        }
        return codigo;
    }

    public void addEvent(ActionEvent actionEvent) {
        FestivosDao festi = new FestivosImple();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String fechaConFormato = sdf.format(fecha_hoy.getTime());
        Festivos festivos = null;
        festivos = festi.BuscarFestivo(fechaConFormato);
        if (festivos == null) {
            Query query = Sequence.GetUltimoRegistro("FROM Festivos order by festivo DESC");
            String num_salvador = "" + query.uniqueResult();
            int codigo_Festivo = Integer.parseInt(num_salvador);
            int codigo_año = (int) traerAño(fecha_hoy.getTime());
            if (codigo_año != 0) {
                Festivos festivo = new Festivos();
                festivo.setFestivo(codigo_Festivo + 1);
                festivo.setFecha(fecha_hoy.getTime());
                AñoFestivo añoFes = new AñoFestivo();
                añoFes.setCodAño(codigo_año);
                festivo.setAñoFestivo(añoFes);
                boolean r = festi.crearFestivo(festivo);
                if (r) {
                    eventModel.addEvent(new DefaultScheduleEvent("Festivo ", fecha_hoy.getTime(), fecha_hoy.getTime()));
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO", ""));
                    event = new DefaultScheduleEvent();
                    traer_fechas();
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRROR", ""));
                }
            }
        }
    }
    public void DeleteEvent(ActionEvent actionEvent) throws ClassNotFoundException {
        FestivosDao festi = new FestivosImple();
        Festivos festivos = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String fechaConFormato = sdf.format(fecha_Borrar.getTime());
        festivos = festi.BuscarFestivo(fechaConFormato);
        System.out.println("--"+festivos.getFecha());
        boolean r=festi.EliminarFestivo(festivos);
        if (r) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO", ""));
            event = new DefaultScheduleEvent();
            traer_fechas();

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRROR", ""));
        }
    }
    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
        fecha_Borrar = (Date) event.getStartDate();
    }
    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
        Date fecha = (Date) selectEvent.getObject();
        fecha_hoy = Calendar.getInstance();
        fecha_hoy.setTime(fecha); // Configuramos la fecha que se recibe
        fecha_hoy.add(Calendar.DAY_OF_YEAR, 1);

    }

}
