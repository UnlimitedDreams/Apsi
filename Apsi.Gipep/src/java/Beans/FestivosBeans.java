package Beans;

import dao.FestivosDao;
import dao.FestivosImple;
import Entity.Aofestivo;
import Entity.Festivos;
import Modelo.Conecion_postgres1;
import Modelo.FEsti;
import Modelo.Secuencia;
import java.awt.BorderLayout;
import java.io.Serializable;
import java.math.BigDecimal;
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
        System.out.println("entro a contru");
        try {
            traer_fechas();

        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    public Calendar getFecha_hoy() {
        return fecha_hoy;
    }

    public void setFecha_hoy(Calendar fecha_hoy) {
        this.fecha_hoy = fecha_hoy;
    }

    public void traer_fechas() throws ClassNotFoundException {
        try {
            if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Dia_festivo") != null) {
                System.out.println("Existe");
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("Dia_festivo");
            } else {
                System.out.println("No existe");
            }
            if (fecha_hoy != null) {
                fecha_hoy = null;
            }

            eventModel = new DefaultScheduleModel();
            Conecion_postgres1.conectar();
            Conecion_postgres1.ejecuteQuery("select * from festivos");
            ArrayList<FEsti> fes = new ArrayList();
            try {
                while (Conecion_postgres1.rs.next()) {
                    fes.add(new FEsti(Conecion_postgres1.rs.getInt(1), Conecion_postgres1.rs.getDate(2),
                            Conecion_postgres1.rs.getInt(3)));
                }
            } catch (Exception ex) {

            }
            FEsti temp = null;
            for (int i = 0; i < fes.size(); i++) {
                temp = (FEsti) fes.get(i);
                eventModel.addEvent(new DefaultScheduleEvent("Festivo ", temp.getFecha(), temp.getFecha()));
            }
        } catch (Exception ex) {
            System.out.println("Error traerFechas: " + ex.toString());
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

    public void agregarFes() throws ClassNotFoundException {
        int codigo = Secuencia.seque("select max(codigo_festivos) from festivos");
        Conecion_postgres1.conectar();
        System.out.println("--------------------");
        System.out.println("entro fecha " + fecha_Borrar);
        Conecion_postgres1.ejecuteUpdate("insert into festivos values(" + codigo + ",'" + fecha_Borrar + "',1)");
        Conecion_postgres1.cerrarConexion();
        traer_fechas();
//        fecha_Borrar = null;
    }

    public static long traerAño(Date fecha) {
        String año = fecha.toString();
        String año2 = año.substring((año.length() - 4), año.length());
        FestivosDao festi = new FestivosImple();
        long codigo = 0;
        try {
            Aofestivo temp = null;
            temp = festi.Traer_año(año2);
            System.out.println("temp " + temp.toString());
            if (temp == null) {
                int codigo_año = Secuencia.seque("select max(cod_ao) from ao_festivo");
                Aofestivo añofestivo = new Aofestivo();
                añofestivo.setCodAo(new BigDecimal(codigo_año));
                añofestivo.setAo(año2);
                boolean r = festi.CrearAñoFestivo(añofestivo);
                if (r) {
                    codigo = codigo_año;
                } else {
                    codigo = 0;
                }
            } else {
                codigo = temp.getCodAo().intValue();
            }
        } catch (Exception ex) {

        }
        return codigo;
    }

    public void addEvent(ActionEvent actionEvent) throws ClassNotFoundException {
//        FestivosDao festi = new FestivosImple();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String fechaConFormato = sdf.format(fecha_hoy.getTime());
        if (traerCodFecha(fechaConFormato) == 0) {
            int codigo = Secuencia.seque("select max(codigo_festivos) from festivos");
            Conecion_postgres1.conectar();
            System.out.println("--------------------");
            Conecion_postgres1.ejecuteUpdate("insert into festivos values(" + codigo + ",'" + fechaConFormato + "',1)");
            Conecion_postgres1.cerrarConexion();
            fecha_hoy = null;
            traer_fechas();
        } else {
            System.out.println("ya existe");
        }

//        System.out.println("fecha hoy  " + fechaConFormato);
//        Festivos festivos = null;
//        festivos = festi.BuscarFestivo(fechaConFormato);
//        System.out.println("1");
//        if (festivos == null) {
//            System.out.println("2");
//            int codigo_Festivo = Secuencia.seque("select max(codigo_festivos) from festivos");
//            int codigo_año = (int) traerAño(fecha_hoy.getTime());
//            System.out.println("3");
//            if (codigo_año != 0) {
//                System.out.println("4");
//                Festivos festivo = new Festivos();
////                festivo.setCodigoFestivos(codigo_Festivo);
//                festivo.setFechaFestivo(fecha_hoy.getTime());
//                Aofestivo añoFes = new Aofestivo();
//                añoFes.setCodAo(new BigDecimal(codigo_año));
//                festivo.setAofestivo(añoFes);
//                System.out.println("5");
//                boolean r = festi.crearFestivo(festivo);
//                System.out.println("6");
//                if (r) {
//                    System.out.println("7");
//                    eventModel.addEvent(new DefaultScheduleEvent("Festivo ", fecha_hoy.getTime(), fecha_hoy.getTime()));
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO", ""));
//                    event = new DefaultScheduleEvent();
//                    traer_fechas();
//                } else {
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRROR", ""));
//                }
//            }
//        }
    }
//    

    public void DeleteEvent(ActionEvent actionEvent) throws ClassNotFoundException {
        FestivosDao festi = new FestivosImple();
        Festivos festivos = null;
        Date fecha = (Date) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Dia_festivo");
        System.out.println("Fecha borrar " + fecha.toString());
        int codfes = traerCodFecha(fecha.toString());
        Conecion_postgres1.conectar();
        Conecion_postgres1.ejecuteUpdate("delete from festivos where "
                + "codigo_festivos=" + codfes);
        Conecion_postgres1.cerrarConexion();
        traer_fechas();

//        boolean r = festi.EliminarFestivo(festivos);
//        if (r) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO", ""));
//            event = new DefaultScheduleEvent();
//            traer_fechas();
//
//        } else {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRROR", ""));
//        }
//        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("Dia_festivo");
    }

    public int traerCodFecha(String fecha) throws ClassNotFoundException {
        Conecion_postgres1.conectar();
        Conecion_postgres1.ejecuteQuery("select * from festivos where fecha_festivo='" + fecha + "'");
        int cod = 0;
        try {
            while (Conecion_postgres1.rs.next()) {
                cod = Conecion_postgres1.rs.getInt(1);
            }
            Conecion_postgres1.cerrarConexion();
        } catch (Exception ex) {
            System.out.println("Error traerCod " + ex.toString());
        }
        return cod;
    }

    public void onEventSelect(SelectEvent selectEvent) {
        try {
            event = (ScheduleEvent) selectEvent.getObject();
////            event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
            Date fecha = (Date) event.getStartDate();
            fecha_Borrar = fecha;
            System.out.println("fecha " + fecha_Borrar);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Dia_festivo", fecha_Borrar);
        } catch (Exception ex) {
            System.out.println("Error " + ex.toString());
        }

    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
        Date fecha = (Date) selectEvent.getObject();
        fecha_hoy = Calendar.getInstance();
        fecha_hoy.setTime(fecha); // Configuramos la fecha que se recibe
        fecha_hoy.add(Calendar.DAY_OF_YEAR, 1);

    }

}
