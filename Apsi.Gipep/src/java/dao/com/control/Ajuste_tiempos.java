/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.com.control;

import dao.com.modelo.Ajuste_Modelo;
import dao.com.modelo.Dias;
import dao.com.modelo.Dias_Ajuste;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Juan David Castrillon
 */
@WebServlet(name = "Ajuste_tiempos", urlPatterns = {"/Ajuste_tiempos"})
public class Ajuste_tiempos extends HttpServlet {
    //Ajuste de tiempos es la organizacion de los horarios de los profesores y asesorias durante un semestre
    //cada semestre se debera hacer esta operacion

    private String periodo;
    ArrayList<Dias> dias = new ArrayList(); //dias seleccionados en la vista
    ArrayList<Dias_Ajuste> Festivos = new ArrayList(); //Festivos que hay durante el año
    ArrayList<Ajuste_Modelo> molde = new ArrayList();

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public ArrayList<Dias> getDias() {
        return dias;
    }

    public void setDias(ArrayList<Dias> dias) {
        this.dias = dias;
    }

    public boolean Verificar(String fecha) throws ClassNotFoundException {
        //se verifica si el ajuste que va hacr ya se encuentra registrado en la base de datos
        //PD: no pueden haber dos ajuste en el mismo periodo de su año respectivo
        Control.conectar();
        String año = fecha.toString();
        String año2 = año.substring(0, 4);
        Control.ejecuteQuery("select * from ajuste,año_festivo\n"
                + "where\n"
                + "ajuste.festivo=año_festivo.cod_año\n"
                + "and ajuste.periodo=" + periodo + " and año_festivo.año='" + año2 + "'");
        boolean r = false;
        try {
            while (Control.rs.next()) {
                r = true;
            }
        } catch (Exception ex) {
        }
        return r;
    }

    public void Traer_festivos() throws ClassNotFoundException {
        //se traen los festivos del respetivo año para mas adelante tenerlos encuenta
        Festivos.clear();
        Control.conectar();
        Date fecha1 = new Date();
        String año = fecha1.toString();
        String año2 = año.substring((año.length() - 4), año.length());
        Control.ejecuteQuery("select festivos.* from festivos,año_festivo where\n"
                + "año_festivo.cod_año=festivos.cod_año\n"
                + "and año_festivo.año='" + año2 + "'");
        Date fecha = null;
        String fecha2 = "";
        int dia = 0, mes = 0;
        try {
            while (Control.rs.next()) {
                fecha = Control.rs.getDate(2);
                fecha2 = fecha.toString();
                dia = Integer.parseInt(fecha2.substring((fecha2.length() - 2), fecha2.length()));
                mes = Integer.parseInt(fecha2.substring((fecha2.length() - 5), fecha2.length() - 4));
                Dias_Ajuste dia1 = new Dias_Ajuste(dia, mes, Integer.parseInt(año2));
                Festivos.add(dia1);
            }
        } catch (Exception ex) {
        }
    }

    public void cargarDias(String v[]) {
        // carga los dias de la primera vista de ajuste con lso dias normales de la semana
        // los ceros son la hora inicial y final
        dias.clear();
        Dias a = new Dias(false, "lunes", "0", "0");
        dias.add(a);
        Dias b = new Dias(false, "martes", "0", "0");
        dias.add(b);
        Dias c = new Dias(false, "miercoles", "0", "0");
        dias.add(c);
        Dias d = new Dias(false, "jueves", "0", "0");
        dias.add(d);
        Dias e = new Dias(false, "Viernes", "0", "0");
        dias.add(e);
        Dias f = new Dias(false, "sabado", "0", "0");
        dias.add(f);
        Dias temp = null;
        for (int i = 0; i < dias.size(); i++) {
            temp = (Dias) dias.get(i);
            for (int k = 0; k < v.length; k++) {
                if (temp.getDia().equalsIgnoreCase(v[k])) {
                    temp.setEstado(true);
                }
            }
        }
        Dias temp2 = null;
        for (int i = 0; i < dias.size(); i++) {
            temp2 = (Dias) dias.get(i);
            if (temp2.isEstado()) {
            } else {
                dias.remove(i);
            }
        }
    }

    public boolean validarPeriodo(String x) {
        //valida la fecha que se ingreso que sea la correcta 
        //correcta en año y fechas no menores a la de hoy
        boolean r = false;
        Date fecha1 = new Date();
        System.out.println(fecha1.toString());
        String año = fecha1.toString();
        String año2 = x.substring(0, 4);
        String mes = x.substring(6, 7);
        String dia = x.substring(8, 10);
        String fecha = "" + año2 + "/" + mes + "/" + dia;
        Date fecha2 = new Date(fecha);
        if (fecha2.getMonth() >= 1 && fecha2.getMonth() <= 6) {
            periodo = "1";
        } else {
            periodo = "2";
        }
        if (fecha1.getYear() == fecha2.getYear()) {
            if (fecha1.getMonth() == fecha2.getMonth()) {
                if (fecha1.getDate() <= fecha2.getDate()) {
                    r = true;
                } else {
                    r = false;
                }
            } else if (fecha1.getMonth() < fecha2.getMonth()) {
                r = true;
            } else {
                r = false;
            }
        }
        return r;
    }

    public int Diasdelperiodo(String numero_horas, String Horas_porDia, String Rango_hora) {
        // se calcula cuantos dias se necesitan para cumplir con las horas dichas en la vista
        //para ellos se usa la formula de (Numero Horas*60)/(Hora_pordia*RangodeHora)
        int total_Min = 0, dia_min = 0, ac = 0, ran = 0, b = 0;
        total_Min = Integer.parseInt(numero_horas) * 60;
        ac = Integer.parseInt(Horas_porDia);
        float a = 0;
        ac = Integer.parseInt(Horas_porDia);
        ran = Integer.parseInt(Rango_hora);
        a = (float) total_Min / (ac * ran);
        b = (int) Math.round(a);
        return b;
    }

    public String Numero_asesorias(int tot, String fecha) throws ParseException {
        //este metodo me calcula la fecha final de todas las asesorias
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dia2 = "", mes = "", año = "";
        dia2 = fecha.substring(8, 10);
        mes = fecha.substring(5, 7);
        año = fecha.substring(0, 4);
        String dias_semana = "";
        String fecha_final = "";
        ArrayList<Dias_Ajuste> n = new ArrayList();
        if (dias.size() >= 1) {
            int tot2 = tot;
            int dia = 0;
            int num_id = 0;
            boolean p = true;
            int a = Integer.parseInt(dia2);
            int m = Integer.parseInt(mes);
            int d = Integer.parseInt(año);
            Dias_Ajuste temp = null;
            int conce = 1;
            boolean con2 = true;
            while (p) {
                //tomamos la fecha ingresada y la pasamos a un calendar
                //mandamos los valores al metodo dia_semana el cual me devuelve un arreglo con los dias qe conto
                //a esos dias qe trajo le paso el estado de true y cuanto cuantos dias me trajo
                //la variable de tot2 comienza con la cantidad de dias que se necesitan llegar
                //y a esa variable de tot2 le empiezo a rebajar los dias qe traje
                //hasta cuando llege a cero no parara este proceso
                //al final me doy cuenta cual es la fecha final buscando el dia en la posicion tot
                Calendar Diadehoy = Calendar.getInstance();
                String fecha_convertida = "" + a + "-" + m + "-" + d;
                Date date = formatter.parse(fecha_convertida);
                Diadehoy.setTime(date);
                conce = conce + num_id;
                num_id = 0;
                n = dia_semana(Diadehoy, tot2, n, conce, con2);
                for (int i = 0; i < n.size(); i++) {
                    temp = (Dias_Ajuste) n.get(i);
                    if (temp.isR() == false) {
                        num_id++;
                        temp.setR(true);
                    }
                }
                tot2 = tot2 - num_id;
                if (tot2 <= 0) {
                    p = false;
                }
                if (m == 11) {
                    m = 0;
                    d++;
                } else {
                    m++;
                }
                con2 = false;
            }
        }
        return fecha_final;
    }

    public ArrayList dia_semana(Calendar x, int tot, ArrayList p, int conce, boolean con) throws ParseException {
        //este metodo cuenta cuantos dias de los selecionado hay en un mes y devuelve el resultado guardandolo en un arreglo
        int cuenta = 0;
        int mes = x.get(Calendar.MONTH);
        if (con == false) {
            int diii = x.get(Calendar.DAY_OF_MONTH);
            x.add(Calendar.DAY_OF_MONTH, -diii + 1);
        }
        int diia = 0;
        while (x.get(Calendar.MONTH) == mes) {
            String r = "";
            Dias temp = null;
            for (int i = 0; i < dias.size(); i++) {
                //le asignamos un numero al dia porque asii esta determinado en el calendario de java
                temp = (Dias) dias.get(i);
                if (temp.isEstado() == true) {
                    if (temp.getDia().equalsIgnoreCase("lunes")) {
                        diia = 2;
                    } else if (temp.getDia().equalsIgnoreCase("martes")) {
                        diia = 3;
                    } else if (temp.getDia().equalsIgnoreCase("miercoles")) {
                        diia = 4;
                    } else if (temp.getDia().equalsIgnoreCase("jueves")) {
                        diia = 5;
                    } else if (temp.getDia().equalsIgnoreCase("viernes")) {
                        diia = 6;
                    } else if (temp.getDia().equalsIgnoreCase("sabado")) {
                        diia = 7;
                    }
                    boolean EsFestivo = false;
                    Dias_Ajuste temp2 = null;
                    //miramos si el dia que se va a contar es festivo para no contarlo
                    for (int k = 0; k < Festivos.size(); k++) {
                        temp2 = (Dias_Ajuste) Festivos.get(k);
                        if (temp2.getMes() == mes && temp2.getDia() == (x.get(Calendar.DAY_OF_MONTH))) {
                            EsFestivo = true;
                        }
                    }
                    if (EsFestivo == false) {
                        //comparamos que el dia de la semana sea el mismo qe el calendario
                        if (x.get(Calendar.DAY_OF_WEEK) == diia) {
                            Dias_Ajuste Festi = null;
                            if (cuenta >= tot) {
                                cuenta = x.get(Calendar.DAY_OF_MONTH);
                                Dias_Ajuste na = new Dias_Ajuste(cuenta, conce, x.get(Calendar.DAY_OF_MONTH), x.get(Calendar.MONTH), x.get(Calendar.YEAR), false);
                                p.add(na);
                            } else {
                                cuenta++;
                                Dias_Ajuste na = new Dias_Ajuste(cuenta, conce, x.get(Calendar.DAY_OF_MONTH), x.get(Calendar.MONTH), x.get(Calendar.YEAR), false);
                                p.add(na);
                            }
                            conce++;
                        }
                    } else {
                        System.out.println("Es Festivo");
                    }

                }

            }
            x.add(Calendar.DAY_OF_MONTH, 1);
        }
        // end while   
        return p;
    }

    public static int traerAño(String fecha) {
        //trae el año referente a la fecha asignada en la vista
        String año = fecha.toString();
        String año2 = año.substring(0, 4);
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

    public String Hora_final(String hora, String Rango_hora, String Horas_porDia) {
        //me calcula la hora final de las asesorias del dia dependiendo de la hora inicial que se envio
        String hora_final = "";
        if (hora.length() == 5) {
            Calendar Diadehoy = new GregorianCalendar();
            String ho = "", min = "";
            int hor = 0, minu = 0;
            ho = hora.substring(0, 2);
            min = hora.substring(3, 5);
            hor = Integer.parseInt(ho);
            minu = Integer.parseInt(min);
            int ran = 0, horapor = 0;
            ran = Integer.parseInt(Rango_hora);
            horapor = Integer.parseInt(Horas_porDia);
            int minutos = 0;
            minutos = ran * horapor;
            Diadehoy.set(01, 05, 2014, hor, minu, 00);
            Diadehoy.add(Calendar.MINUTE, minutos);
            hora_final = Diadehoy.get(Calendar.HOUR_OF_DAY) + ":" + Diadehoy.get(Calendar.MINUTE);
        } else {
            System.err.println("error");
        }

        return hora_final;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        String respuesta = request.getParameter("accion");
        //se optione la option con la que vamos a procesar la informacion
        System.err.println("++ " + respuesta);
        if (respuesta.equalsIgnoreCase("Primero")) {
            Traer_festivos();//cargamos los festivos
            String fecha = request.getParameter("fecha_ini");
            String Num_asesoria = request.getParameter("Nase");
            String Ramgo_asesoria = request.getParameter("Rango");
            String Asesoeria_dia = request.getParameter("dia");
            String Hora_inicio = request.getParameter("horaI");
            String Asesoria_profesor = request.getParameter("AseP");
            String valo[] = request.getParameterValues("che");

            String Hora_fincho = "No";
            //miramos si seleciono el sabado
            for (int i = 0; i < valo.length; i++) {
                System.err.println("dias " + valo[i]);
                if (valo[i].equalsIgnoreCase("sabado")) {
                    Hora_fincho = request.getParameter("horaIS");
                }

            }
            if (valo.length >= 1) {//debe haber seleccionado algun dia para poder proseguir
                cargarDias(valo);//cargamos los dias
                molde.clear();//borramos el model del ajuste para nuevos ajustes
                session.setAttribute("dias", dias);//traemos los dias cargados 
                if (validarPeriodo(fecha) == true) {
                    if (Verificar(fecha) == false) {
                        int horarSemestre = Diasdelperiodo(Num_asesoria, Asesoeria_dia, Ramgo_asesoria);
                        String fecha_final = Numero_asesorias(horarSemestre, fecha);
                        String Hora_final = Hora_final(Hora_inicio, Ramgo_asesoria, Asesoeria_dia);
                        String Hora_final_sabado = "No";
                        if (Hora_fincho.equalsIgnoreCase("No")) {
                            molde.add(new Ajuste_Modelo(fecha, fecha_final, periodo, Ramgo_asesoria, Num_asesoria, Hora_inicio, Hora_final, Asesoeria_dia, Asesoria_profesor));
                        } else {
                            Hora_final_sabado = Hora_final(Hora_fincho, Ramgo_asesoria, Asesoeria_dia);
                            molde.add(new Ajuste_Modelo(fecha, fecha_final, periodo, Ramgo_asesoria, Num_asesoria,
                                    Hora_inicio, Hora_final, Asesoeria_dia, Asesoria_profesor, Hora_fincho, Hora_final_sabado));

                        }
                        //guardamos el model de ajuste con los datos cargados en una session para optenerlas despues para poder guardarlo
                        session.setAttribute("molde_ajuste", molde);
//                      
                        PrintWriter out = response.getWriter();
                        try {
                            /* TODO output your page here. You may use following sample code. */
                            out.println("<!DOCTYPE html>");
                            out.println("<html>");
                            out.println("<head>");
                            out.println("<script src=\"Jquery/jquery-1.11.2.js\"></script>");
                            out.println(" <title>Bienvenido</title>\n"
                                    + " <link rel=\"stylesheet\" href=\"css/ingreso.css\"/>"
                                    + "  <link rel=\"stylesheet\" href=\"css/Estilo_hoja.css\"/>"
                                    + " <link rel=\"stylesheet\" href=\"css/formas.css\"/>"
                                    + "        <link rel=\"stylesheet\" href=\"css/Tablas.css\"/>\n"
                                    + ""
                                    + "        <script>\n"
                                    //                                    + "            $(document).ready(function() {\n"
                                    //                                    + "                $(\"#submenu1\").hide();\n"
                                    //                                    + "\n"
                                    //                                    + "                $(\"#menu\").click(function() {\n"
                                    //                                    + "                    $(\"#submenu1\").toggle(\"left\", function() {\n"
                                    //                                    + "\n"
                                    //                                    + "                    });\n"
                                    //                                    + "                });\n"
                                    //                                    + "            });\n"
                                    + "\n"
                                    + "            $(document).ready(function() {\n"
                                    + "                $(\"#Enviar\").click(function(event) {\n"
                                    + "\n"
                                    + "                    var opt = 2;\n"
                                    + ""
                                    + "                        $.post('Ajuste_tiempos', {\n"
                                    + "                            accion: opt,\n"
                                    + "                        }, function(responseText) {\n"
                                    + "$('#SegungoAjuste').hide();"
                                    + " $('#Mensaje').html(responseText);\n"
                                    + "                        });\n"
                                    + "                });\n"
                                    + "            });\n"
                                    + "            </script>");
                            out.println("</head>");
                            out.println("<h:body onload=\"if (window.innerWidth < '400') {\n"
                                    + "                        document.getElementById('slider').style.display = 'none'\n"
                                    + "                    }\n"
                                    + "                    ;>");
                            out.println(" <div id=\"header2\" class=\"head\">\n"
                                    + "            <div id=\"menu\" style=\"position: fixed;top:0%\">\n"
                                    + "                <img id=\"btn-menu\" src=\"img/men.png\">\n"
                                    + "\n"
                                    + "            </div>\n"
                                    + "            <div  style=\"text-align: center;\">\n"
                                    + " <b> Ajuste Tiempo</b>   \n"
                                    + "            </div>\n"
                                    + "        </div>\n");
                            out.println("<div id=\"SegungoAjuste\" class=\"letras\">\n"
                                    + "            <center>\n"
                                    + "                <h5 id=\"title\">AJUSTE DE TIEMPOS</h5>"
                                    + "<form>");

                            out.println("<table>\n"
                                    + "                        <tr><td><label>Fecha Inicio</label></td>\n"
                                    + "                            <td><input type=\"text\" name=\"fecha_ini2\" size=\"10\" value=" + fecha + " disabled=\"true\"></td></tr>\n"
                                    + "                        <tr></tr><tr></tr>\n"
                                    + "                        <tr ><td><label>Fecha Final</label></td>\n"
                                    + "                            <td><input type=\"text\" name=\"fecha_fin\" size=\"10\" value=" + fecha_final + " disabled=\"true\"></td></tr>\n"
                                    + "                        <tr></tr><tr></tr>\n"
                                    + "                        <tr><td><label>Numero Asesoria</label></td>\n"
                                    + "                            <td><input type=\"text\" name=\"Nase\" value=" + Num_asesoria + " disabled=\"true\"></td></tr>\n"
                                    + "                        <tr></tr><tr></tr>\n"
                                    + "                        <tr><td><label>Rango de Asesoria </label></td>\n"
                                    + "                            <td><input type=\"text\" name=\"Rango\" value=" + Ramgo_asesoria + " disabled=\"true\"></td></tr>\n"
                                    + "                        <tr></tr><tr></tr>\n"
                                    + "                        <tr><td><label>Asesorias por dia </label></td>\n"
                                    + "                            <td><input type=\"text\" name=\"dia\" value=" + Asesoeria_dia + " disabled=\"true\"></td></tr>\n"
                                    + "                        <tr></tr><tr></tr>\n"
                                    + "                        <tr><td><label>    Hora Inicial </label></td>\n"
                                    + "                            <td><input type=\"text\" name=\"horaI\" value=" + Hora_inicio + " disabled=\"true\"></td></tr>\n"
                                    + "                        <tr></tr><tr></tr>\n"
                                    + "                        <tr><td><label>    Hora Final </label></td>\n"
                                    + "                            <td><input type=\"text\" name=\"horaF\" value=" + Hora_final + " disabled=\"true\"></td></tr>\n"
                                    + "                        <tr></tr><tr></tr>\n"
                                    + "                        <tr><td><label>    Hora Inicio Sabado </label></td>\n"
                                    + "                            <td><input type=\"text\" name=\"horaF\" value=" + Hora_fincho + " disabled=\"true\"></td></tr>\n"
                                    + "                        <tr></tr><tr></tr>\n" + "    "
                                    + "                    <tr><td><label>    Hora Final Sabado </label></td>\n"
                                    + "                            <td><input type=\"text\" name=\"horaF\" value=" + Hora_final_sabado + " disabled=\"true\"></td></tr>\n"
                                    + "                        <tr></tr><tr></tr>\n"
                                    + "                        <tr><td><label>   Asesorias Profesor  </label></td>\n"
                                    + "                            <td><input type=\"text\" name=\"AseP\" value=" + Asesoria_profesor + " disabled=\"true\"></td></tr>\n"
                                    + "                        <tr></tr><tr></tr>\n");

                            out.println("                    </table>");
                            out.println("<br>");

                            out.println("<table class=\"CSSTableGenerator\">");
                            Dias temp2 = null;
                            out.println("<tr><td>Dias</td></tr>");
                            for (int i = 0; i < dias.size(); i++) {
                                temp2 = (Dias) dias.get(i);
                                if (temp2.isEstado()) {
                                    out.println("<tr>");
                                    out.println("<td>" + temp2.getDia() + "</td>");
                                    out.println("</tr>");
                                }

                            }
                            out.println("</table>");
                            out.println("<br>");
                            out.println("<br>");
                            out.println(" <input  type=\"button\"  value=\"ENVIAR\" id=\"Enviar\" >\n"
                                    + "        <a href=\"Ajuste.html\">Atras</a>\n"
                                    + "</div>");
                            out.println("<div id=\"Mensaje\"  ></div>");

                            out.println("</h:body>");
                            out.println("</html>");
                        } finally {
                            out.close();
                        }
                    } else {
                        System.err.println("ya existe");
                    }
                } else {
                    System.err.println("Periodo malo");
                }
            } else {
                System.err.println("No ha seleccionado dias");
            }
        } else if (respuesta.equalsIgnoreCase("2")) {
            //en la option 2 registramos el model de ajuste 
            ArrayList temporal = null;
            ArrayList mis_dias = null;
            temporal = (ArrayList) session.getAttribute("molde_ajuste");//obtenemos el model de ajuste que guardamos antes
            mis_dias = (ArrayList) session.getAttribute("dias");//obtenemos los dias que guardamos 
            int sequence = Sequence.Sequen("select max(cod_ajuste) from ajuste");
            Ajuste_Modelo temp2 = null;
            for (int i = 0; i < temporal.size(); i++) {
                temp2 = (Ajuste_Modelo) temporal.get(i);
                int cod_año = traerAño(temp2.getFecha_inicio());
                System.err.println("r+ " + temp2.toString());
                 Control.ejecuteUpdate("insert into ajuste values(" + sequence + ",'" + temp2.getFecha_inicio() + "','" + temp2.getFecha_final() + "'," + temp2.getRango_hora() + "," + temp2.getPeriodo()
                        + "," + temp2.getNumero_horas() + ",'" + temp2.getHora_inicio() + "','" + temp2.getHora_final() + "','No','No',1,'"
                        + temp2.getAsesorias_profesor() + "','" + temp2.getHoras_porDia() + "'," + cod_año + ")");
            }
            Dias temp = null;
            for (int i = 0; i < mis_dias.size(); i++) {
                temp = (Dias) mis_dias.get(i);
                if (temp.isEstado()) {
                    int sequence_dias = Sequence.Sequen("select max(cod_dias) from dias");
                    Control.ejecuteUpdate("insert into dias values(" + sequence_dias + ",'" + temp.getDia() + "'," + sequence + ")");
                }
            }
            boolean con = true;
            if (con == true) {
                PrintWriter out = response.getWriter();
                try {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("  <script src=\"js/alertify.min.js\"></script> "
                            + "<link rel=\"stylesheet\" href=\"css/alertify.bootstrap.css\" />\n"
                            + "        <link rel=\"stylesheet\" href=\"css/alertify.default.css\" />\n"
                            + "        <link rel=\"stylesheet\" href=\"css/alertify.core.css\" />");
                    out.println("<script src=\"Jquery/jquery-1.11.2.js\"></script>");
                    out.println(" <script>$(document).ready(function() {\n"
                            + " alertify.success(\"INSERTADO CON EXITO\");\n"
                            + " });\n"
                            + "</script>");
                    out.println("</head>");
                    out.println("<body>"
                            + "hola"
                            + "</body>"
                            + "</html>"
                    );
                } finally {
                    out.close();
                }
            }
            dias.clear();
            molde.clear();
            response.sendRedirect("index.html");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ajuste_tiempos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Ajuste_tiempos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ajuste_tiempos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Ajuste_tiempos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
