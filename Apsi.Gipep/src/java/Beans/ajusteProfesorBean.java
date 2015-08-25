/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.Persona;
import Modelo.MDias;
import Modelo.Profesor;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Microinformatica
 */
@ManagedBean
@ViewScoped
public final class ajusteProfesorBean implements Serializable {

    private String nombreProfesor;
    private Date fecha_inicial;
    private Date fecha_final;
    private String RamgoHora;
    private String Cantidad_horas;
    private String hora_inicial;
    private String hora_Final;
    private int periodo;
    ArrayList<Integer> Peri = new ArrayList();
    ArrayList<MDias> Dias = new ArrayList();
    ArrayList<MDias> Dias_Recuerdo = new ArrayList();
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private String NombreUsuario;

    public ajusteProfesorBean() {

    }

    public ajusteProfesorBean(String nombreProfesor, Date fecha_inicial, Date fecha_final, String RamgoHora, String Cantidad_horas, int periodo) {
        this.nombreProfesor = nombreProfesor;
        this.fecha_inicial = fecha_inicial;
        this.fecha_final = fecha_final;
        this.RamgoHora = RamgoHora;
        this.Cantidad_horas = Cantidad_horas;
        this.periodo = periodo;
    }

    public void cargar_Periodo() {
        System.out.println("entro a cargar");
        Peri.clear();
        for (int i = 1; i <= 2; i++) {
            Peri.add(i);
        }
    }

    public void Recuerpar_informacion() {
        System.out.println("Entro al recuerdo");
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Datos_ajuste") != null) {
            ajusteProfesorBean ajuste = new ajusteProfesorBean();
            ajuste = (ajusteProfesorBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Datos_ajuste");
            nombreProfesor = ajuste.getNombreProfesor();
            fecha_inicial = ajuste.getFecha_inicial();
            fecha_final = ajuste.getFecha_final();
            RamgoHora = ajuste.getRamgoHora();
            Cantidad_horas = ajuste.getCantidad_horas();

            System.out.println(" existe");
        } else {
            System.out.println("No existe");
        }
//        

    }

    public void validarHora(String x) {
        boolean r = false;
        String hora;
        if (x.length() == 5) {

        }
    }

    public void ValidarRangoHora(String h1, String h2) {

    }

    public void inicionombreProfe() throws IOException {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        if (httpServletRequest.getSession().getAttribute("user") != null) {
            System.out.println("Existe");
            Persona p = (Persona) httpServletRequest.getSession().getAttribute("persona");
            NombreUsuario = p.getNombres() + " " + p.getApellidos();
            Profesor temp = null;
            temp = (Profesor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("profesor");
            this.nombreProfesor = temp.getNombre() + " " + temp.getApellido();
            System.out.println("--- " + temp.toString());
            hora_inicial = "";
            hora_Final = "";
            cargarDias();
            Recuerpar_informacion();
            cargar_Periodo();
        } else {
            System.out.println("No existe");
            FacesContext.getCurrentInstance().getExternalContext().redirect("../logIn/index.jsp");
        }

    }

    public void cargarDias() {
        Dias.clear();

        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("List_dias") != null) {
            MDias temp = null;
            MDias temp2 = null;
            ArrayList<MDias> list = new ArrayList();
            list = (ArrayList) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("List_dias");
            Dias.add(new MDias(false, "Lunes", "0", "0"));
            Dias.add(new MDias(false, "Martes", "0", "0"));
            Dias.add(new MDias(false, "Mieroles", "0", "0"));
            Dias.add(new MDias(false, "Jueves", "0", "0"));
            Dias.add(new MDias(false, "Viernes", "0", "0"));
            Dias.add(new MDias(false, "Sabado", "0", "0"));
            for (int i = 0; i < list.size(); i++) {
                temp = (MDias) list.get(i);
                for (int k = 0; k < Dias.size(); k++) {
                    temp2 = (MDias) Dias.get(k);
                    if (temp.getDia().equals(temp2.getDia())) {
                        temp2.setEstado(true);
                    }
                }
            }
        } else {
            Dias.add(new MDias(false, "Lunes", "0", "0"));
            Dias.add(new MDias(false, "Martes", "0", "0"));
            Dias.add(new MDias(false, "Mieroles", "0", "0"));
            Dias.add(new MDias(false, "Jueves", "0", "0"));
            Dias.add(new MDias(false, "Viernes", "0", "0"));
            Dias.add(new MDias(false, "Sabado", "0", "0"));

        }
        MDias temp3 = null;
        for (int k = 0; k < Dias.size(); k++) {
            temp3 = (MDias) Dias.get(k);
            System.out.println("---> " + temp3.toString());

        }

    }

    public void ponerhorasinicial(String dia) {
        System.out.println("hora inicial" + dia);
        MDias temp = null;
        boolean esta = false;
        for (int i = 0; i < Dias.size(); i++) {
            temp = (MDias) Dias.get(i);
            if (temp.getDia().equalsIgnoreCase(dia)) {
                if (temp.isEstado() == true) {
                    System.out.println("Entroooo");
                    temp.setHora_inicio(hora_inicial);
                    esta = true;
                    System.out.println("estaaa 1 " + esta);
                } else {
                    esta = false;
                }
            }

        }
        System.out.println("estaaa " + esta);
        if (esta == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No ha Seleccionado el dia", ""));
        }
        hora_inicial = "";
    }

    public void ponerhorasFinal(String dia) {
        System.out.println("hora final" + dia);
        MDias temp = null;
        boolean esta = false;
        for (int i = 0; i < Dias.size(); i++) {
            temp = (MDias) Dias.get(i);
            if (temp.getDia().equalsIgnoreCase(dia)) {
                if (temp.isEstado() == true) {
                    temp.setHora_final(hora_Final);
                    System.out.println("ok");
                    esta = true;
                } else {
                    esta = false;
                }
            }
        }
        if (esta == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No ha Seleccionado el dia", ""));
        }
        hora_Final = "";
    }

    public boolean validarFechas() {
        Calendar fecha_hoy = new GregorianCalendar();
        Calendar fecha_I = new GregorianCalendar();
        Calendar fechaF = new GregorianCalendar();
        fecha_I.setTime(fecha_inicial);
        fechaF.setTime(fecha_final);
        boolean r = false;
        System.out.println("fecha hoy " + fecha_hoy.getTime());
        System.out.println("fecha Inicial " + fecha_I.getTime());
        System.out.println("fecha Final " + fechaF.getTime());

        if (fecha_I.get(Calendar.DAY_OF_MONTH) < fecha_hoy.get(Calendar.DAY_OF_MONTH)) {
            r = true;
        } else if (fechaF.get(Calendar.DAY_OF_MONTH) < fecha_hoy.get(Calendar.DAY_OF_MONTH)) {
            r = true;

        }

        return r;
    }

    public boolean validarFechasMayores() {
        Calendar fecha_I = new GregorianCalendar();
        Calendar fechaF = new GregorianCalendar();
        fecha_I.setTime(fecha_inicial);
        fechaF.setTime(fecha_final);
        boolean r = false;
        System.out.println("fecha Inicial " + fecha_I.getTime());
        System.out.println("fecha Final " + fechaF.getTime());

        if (fechaF.get(Calendar.DAY_OF_MONTH) < fecha_I.get(Calendar.DAY_OF_MONTH)) {
            r = true;
        }
        return r;
    }

    public boolean validarJornada(ArrayList x) {
        MDias temp = null;
        boolean r = false;
        String Estado = "";
        try {
            System.out.println("Dias size " + x.size());
            for (int k = 0; k < x.size(); k++) {
                temp = (MDias) x.get(k);
                Calendar Horas = new GregorianCalendar();
                System.out.println("fechaI " + temp.getHora_inicio());
                String v1[] = temp.getHora_inicio().split(":");
                Horas.set(Calendar.HOUR_OF_DAY, Integer.parseInt(v1[0]));
                Horas.set(Calendar.MINUTE, Integer.parseInt(v1[1]));
                System.out.println("fechaI final " + Horas.getTime());
                Calendar Horas2 = new GregorianCalendar();
                System.out.println("fechaF " + temp.getHora_final());

                String v2[] = temp.getHora_final().split(":");
                Horas2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(v2[0]));
                Horas2.set(Calendar.MINUTE, Integer.parseInt(v2[1]));
                System.out.println("fechaF final " + Horas2.getTime());

                System.out.println(Horas.get(Calendar.HOUR_OF_DAY) + " >7---" + Horas2.get(Calendar.HOUR_OF_DAY) + " <21");
                if (Horas.get(Calendar.HOUR_OF_DAY) > 7 && Horas2.get(Calendar.HOUR_OF_DAY) < 21) {
                    System.out.println("paso primera fase");
                    if (Horas2.get(Calendar.HOUR_OF_DAY) == 21 && Horas2.get(Calendar.MINUTE) == 00) {
                        r = true;
                    } else if (Horas2.get(Calendar.HOUR_OF_DAY) < 21) {
                        r = true;
                    } else {
                        r = false;
                        break;
                    }
                } else {
                    r = false;
                    break;
                }

            }
        } catch (Exception ex) {
            System.out.println("Error ValidarHoras" + ex.toString());
        }

        System.out.println(
                "retorna r " + Estado);
        return r;
    }

    public String validarHoras(ArrayList x) {
        MDias temp = null;
        boolean r = false;
        String Estado = "";
        try {
            System.out.println("Dias size " + x.size());
            for (int k = 0; k < x.size(); k++) {
                temp = (MDias) x.get(k);
                Calendar Horas = new GregorianCalendar();
                System.out.println("fechaI " + temp.getHora_inicio());
                String v1[] = temp.getHora_inicio().split(":");
                Horas.set(Calendar.HOUR_OF_DAY, Integer.parseInt(v1[0]));
                Horas.set(Calendar.MINUTE, Integer.parseInt(v1[1]));
                Calendar Horas2 = new GregorianCalendar();
                System.out.println("fechaF " + temp.getHora_final());

                String v2[] = temp.getHora_final().split(":");
                Horas2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(v2[0]));
                Horas2.set(Calendar.MINUTE, Integer.parseInt(v2[1]));
                System.out.println("1");

                if (Horas.get(Calendar.HOUR_OF_DAY) == Horas2.get(Calendar.HOUR_OF_DAY)) {
                    if (Horas.get(Calendar.MINUTE) == Horas2.get(Calendar.MINUTE)) {
                        System.out.println("iguales de hora");
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las Horas son iguales", "" + temp.getHora_inicio() + " y " + temp.getHora_final() + " del dia " + temp.getDia()));
                        r = true;
                        Estado = "P";
                    } else {
                        for (int i = 0; r = true; i++) {
                            System.out.println(Horas.get(Calendar.HOUR_OF_DAY) + ":" + Horas.get(Calendar.MINUTE)
                                    + "==" + Horas2.get(Calendar.HOUR_OF_DAY) + ":" + Horas2.get(Calendar.MINUTE));
                            if (Horas.get(Calendar.HOUR_OF_DAY) == Horas2.get(Calendar.HOUR_OF_DAY)) {
                                if (Horas.get(Calendar.MINUTE) == Horas2.get(Calendar.MINUTE)) {
                                    System.out.println("iguales");
                                    r = false;
                                    Estado = "I";
                                    break;
                                }
                            } else if (Horas.get(Calendar.HOUR_OF_DAY) > Horas2.get(Calendar.HOUR_OF_DAY)) {
                                if (Horas.get(Calendar.MINUTE) > Horas2.get(Calendar.MINUTE)) {
                                    System.out.println("se paso");
                                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las Horas no encajan en el rango de tiempo", "" + temp.getHora_inicio() + " y " + temp.getHora_final() + " del dia " + temp.getDia()));
                                    r = true;
                                    Estado = "P";
                                    break;
                                }

                            }
                            Horas.add(Calendar.MINUTE, Integer.parseInt(RamgoHora));
                        }
                    }
                } else {
                    for (int i = 0; r = true; i++) {
                        System.out.println(Horas.get(Calendar.HOUR_OF_DAY) + ":" + Horas.get(Calendar.MINUTE)
                                + "==" + Horas2.get(Calendar.HOUR_OF_DAY) + ":" + Horas2.get(Calendar.MINUTE));
                        if (Horas.get(Calendar.HOUR_OF_DAY) == Horas2.get(Calendar.HOUR_OF_DAY)) {
                            if (Horas.get(Calendar.MINUTE) == Horas2.get(Calendar.MINUTE)) {
                                System.out.println("iguales");
                                r = false;
                                Estado = "I";
                                break;
                            }
                        } else if (Horas.get(Calendar.HOUR_OF_DAY) > Horas2.get(Calendar.HOUR_OF_DAY)) {
                            if (Horas.get(Calendar.MINUTE) > Horas2.get(Calendar.MINUTE)) {
                                System.out.println("se paso");
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las Horas no encajan en el rango de tiempo", "" + temp.getHora_inicio() + " y " + temp.getHora_final() + " del dia " + temp.getDia()));
                                r = true;
                                Estado = "P";
                                break;
                            }

                        }
                        Horas.add(Calendar.MINUTE, Integer.parseInt(RamgoHora));
                    }
                }

                System.out.println("----------------- " + r);
                if (r == true) {
                    break;
                } else {
                    r = false;
                }
            }
        } catch (Exception ex) {
            System.out.println("Error ValidarHoras" + ex.toString());
        }

        System.out.println("retorna r " + Estado);
        return Estado;
    }

    public void cargarTodo() throws IOException {
   
        if (validarFechas() == false) {
            if (validarFechasMayores() == false) {
                ArrayList<MDias> d = new ArrayList();
                MDias temp = null;
                for (int i = 0; i < Dias.size(); i++) {
                    temp = (MDias) Dias.get(i);
                    if (temp.isEstado() == true) {
                        d.add(temp);
                    }
                }
                System.out.println("tamaño de d " + d.size());
                if (d.size() > 0) {
                    if (validarJornada(d) == true) {
                        if (validarHoras(d) == "I") {
                            traerPeriodo();
                            System.out.println("entro todo bien");
                            ajusteProfesorBean ajuste = new ajusteProfesorBean(nombreProfesor, fecha_inicial, fecha_final, RamgoHora, Cantidad_horas, periodo);

                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("List_dias"); //                        ajusteProfesorBean ajuste = new ajusteProfesorBean(nombreProfesor, fecha_inicial, fecha_final, RamgoHora, Cantidad_horas, periodo);
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Dias_ajuste", d);
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Datos_ajuste", ajuste);
                            FacesContext.getCurrentInstance().getExternalContext().redirect("AjusteProfesor2.xhtml");
                        } else {
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("List_dias", d);
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Las horas validas son de 7:00 AM hasta 9:00 PM", ""));
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("List_dias", d);

                    }

                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No ha seleccionado ningun dia", ""));
                    System.out.println("No ha seleccionado ningun dia");
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La fecha final es menor que la fecha inicial", ""));

            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Una de las fechas es menor que la fecha de hoy", ""));

        }

    }

    public void RevisarHoras() {

    }

    public void activarDias(String dia) {
        System.out.println("Act" + dia);
        MDias temp = null;
        boolean esta = false;
        for (int i = 0; i < Dias.size(); i++) {
            temp = (MDias) Dias.get(i);
            if (temp.getDia().equalsIgnoreCase(dia)) {
                if (temp.isEstado() == true) {
                    temp.setEstado(false);
                } else {
                    temp.setEstado(true);
                }

            }
        }
    }

    public void traerPeriodo() {
        Date fechahoy = new Date();
        if (fecha_inicial.getYear() == fechahoy.getYear() && fecha_final.getYear() == fechahoy.getYear()) {
            if (fecha_inicial.getMonth() >= 1 && fecha_final.getMonth() <= 6) {
                periodo = 1;
            } else {
                periodo=2;
            }
        } else {
            System.out.println("Fecha pasada");
        }
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

    public String getHora_inicial() {
        return hora_inicial;
    }

    public void setHora_inicial(String hora_inicial) {
        this.hora_inicial = hora_inicial;
    }

    public String getHora_Final() {
        return hora_Final;
    }

    public void setHora_Final(String hora_Final) {
        this.hora_Final = hora_Final;
    }

    public ArrayList<MDias> getDias() {
        return Dias;
    }

    public void setDias(ArrayList<MDias> Dias) {
        this.Dias = Dias;
    }

    public ArrayList<MDias> getDias_Recuerdo() {
        return Dias_Recuerdo;
    }

    public void setDias_Recuerdo(ArrayList<MDias> Dias_Recuerdo) {
        this.Dias_Recuerdo = Dias_Recuerdo;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public ArrayList<Integer> getPeri() {
        return Peri;
    }

    public void setPeri(ArrayList<Integer> Peri) {
        this.Peri = Peri;
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
