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
 * @author USR_Toshiba
 */
@WebServlet(name = "Ajuste_tiempos", urlPatterns = {"/Ajuste_tiempos"})
public class Ajuste_tiempos extends HttpServlet {

    private String periodo;
    ArrayList<Dias> dias = new ArrayList();
    ArrayList<Dias_Ajuste> Festivos = new ArrayList();
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

    public boolean Verificar() throws ClassNotFoundException {
//        control.conectar();
//        control.ejecuteQuery("select hora_inicial,asesorias_dia,rango_horas from ajuste where \n"
//                + "                fecha_inicial<=CURRENT_DATE and fecha_final>=CURRENT_DATE");
        boolean r = false;
//        try {
//            while (control.rs.next()) {
//                System.err.println("-----------------------------------------------******");
//                r = true;
//            }
//        } catch (Exception ex) {
//
//        }
        return r;
    }

    public void Traer_festivos() {
        Dias_Ajuste dia1 = new Dias_Ajuste(31, 03, 2015);
        Festivos.add(dia1);
        Dias_Ajuste dia2 = new Dias_Ajuste(07, 04, 2015);
        Festivos.add(dia2);

    }

    public void cargarDias(String v[]) { // carga los dias de la primerz vista de ajuste
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
        boolean r = false;
        Calendar Diadehoy = new GregorianCalendar();
        int a = Diadehoy.get(Calendar.YEAR);
        int m = Diadehoy.get(Calendar.MONTH);
        int d = Diadehoy.get(Calendar.DAY_OF_MONTH);
        String año = "", año2 = "";
        String mes = "", mes2 = "";
        String dia = "", dia2 = "";
        int año3 = 0, año1 = 0;
        int mes3 = 0, mes1 = 0;
        int dia3 = 0, dia1 = 0;
        String[] numerosComoArray = x.split("-");
        if (numerosComoArray[0] != null) {
            año = numerosComoArray[0];
            mes = numerosComoArray[1];
            dia = numerosComoArray[2];
        }
        año3 = Integer.parseInt(año);
        mes3 = Integer.parseInt(mes);
        dia3 = Integer.parseInt(dia);
        if ((año3 == a)) {
            if (mes3 >= 1 && mes1 <= 6) {
                periodo = "" + 1;
            } else {
                periodo = "" + 2;
            }
            if (mes3 >= (m + 1)) {
                if (mes3 == (m + 1)) {
//                    System.err.println("Mes igual");
                    if (dia3 >= d) {
////                        System.err.println("dia mayor o igual");
                        r = true;
                    } else {
//                        System.err.println("dia menor");
                        r = false;
                    }
                }
            } else {
//                System.err.println("mes menor");
                r = false;
            }
        } else {
            r = false;
//            System.out.println("El año no conincide o no es el actual");
        }
//        System.err.println("Quedo " + r);
        return r;
    }

    public int Diasdelperiodo(String numero_horas, String Horas_porDia, String Rango_hora) {
        int total_Min = 0, dia_min = 0, ac = 0, ran = 0, b = 0;
        total_Min = Integer.parseInt(numero_horas) * 60;
        ac = Integer.parseInt(Horas_porDia);
        float a = 0;
        ac = Integer.parseInt(Horas_porDia);
        ran = Integer.parseInt(Rango_hora);
        a = (float) total_Min / (ac * ran);
        b = (int) Math.round(a);
//        System.err.println("float " + a + "- " + b);
        return b;
    }

//  
    public String Numero_asesorias(int tot, String fecha) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dia2 = "", mes = "", año = "";
        dia2 = fecha.substring(8, 10);
        mes = fecha.substring(5, 7);
        año = fecha.substring(0, 4);
//
//        System.err.println("dia " + dia2 + "mes " + mes + "año " + año);
        String dias_semana = "";
        String fecha_final = "";
        ArrayList<Dias_Ajuste> n = new ArrayList();
        if (dias.size() >= 1) {
            int tot2 = tot;
            int dia = 0;
            String dio = "";
            int num_id = 0, sum = 0, sum_mes = 0, count = 0;
            boolean p = true;
            int a = Integer.parseInt(dia2);
            int m = Integer.parseInt(mes);
            int d = Integer.parseInt(año);
            int pasado = 0;
            int s = 0;
            int az = 0;
            Dias_Ajuste temp = null;
            int con = 0;
            int conce = 1;
            boolean con2 = true;
            while (p) {
                Calendar Diadehoy = Calendar.getInstance();
                String fecha_convertida = "" + a + "-" + m + "-" + d;
//                System.err.println("fechaaaaa convertidadd " + fecha_convertida);
                Date date = formatter.parse(fecha_convertida);
                Diadehoy.setTime(date);
                conce = conce + num_id;
                num_id = 0;
                n = dia_semana(Diadehoy, dia, a, m, d, tot2, n, conce, dias, con2);
                for (int i = 0; i < n.size(); i++) {
                    temp = (Dias_Ajuste) n.get(i);
                    if (temp.isR() == false) {
                        num_id++;
//                        System.err.println("---------------------------------------");
                        temp.setR(true);
                    }
                }
                tot2 = tot2 - num_id;

//                System.err.println("tot esta en " + tot2);
                if (tot2 <= 0) {
//                    System.err.println("Entro para salir");
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
        Dias_Ajuste temp2 = null;
        String fa = "";
        for (int i = 0; i < n.size(); i++) {
            temp2 = (Dias_Ajuste) n.get(i);
            fa = fa + n.get(i) + "\n";
            if (temp2.getCod2() == tot) {
//                System.err.println("la fecha final es " + n.get(i));
                fecha_final = temp2.getDia() + "-" + (temp2.getMes() + 1) + "-" + temp2.getAño();
            }
        }

        System.err.println(fa);
        return fecha_final;
    }

    public ArrayList dia_semana(Calendar x, int diaAContar, int inicio, int mes2, int año, int tot, ArrayList p, int conce, ArrayList y, boolean con) throws ParseException {
        String de = "";
        String strDia = "";
        int cuenta = 0;
        int count = 0, c = 0;
//        System.err.println("dia " + x.get(Calendar.DAY_OF_MONTH));
        int mes = x.get(Calendar.MONTH);
        if (con == false) {
            int diii = x.get(Calendar.DAY_OF_MONTH);
//            int dii2 = x.get(Calendar.DAY_OF_WEEK);
            x.add(Calendar.DAY_OF_MONTH, -diii + 1);
//            x.add(Calendar.DAY_OF_WEEK, -5);

        }
        int diia = 0;
//        System.err.println("Son iguales " + mes + " y " + x.get(Calendar.MONTH));
//        System.err.println("dia de semana " + x.get(Calendar.DAY_OF_WEEK));
        while (x.get(Calendar.MONTH) == mes) {
//            System.err.println("entor al mes");
            String r = "";
            Dias temp = null;
            for (int i = 0; i < dias.size(); i++) {
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
                    for (int k = 0; k < Festivos.size(); k++) {
                        temp2 = (Dias_Ajuste) Festivos.get(k);
                        if (temp2.getMes() == mes && temp2.getDia() == (x.get(Calendar.DAY_OF_MONTH))) {
                            EsFestivo = true;
                        }
                    }
                    if (EsFestivo == false) {
                        if (x.get(Calendar.DAY_OF_WEEK) == diia) {
                            Dias_Ajuste Festi = null;
//                        System.err.println("Son iguales ----- mes " + x.get(Calendar.MONTH) + " dia " + x.get(Calendar.DAY_OF_MONTH));
                            if (cuenta >= tot) {
                                cuenta = x.get(Calendar.DAY_OF_MONTH);
                                Dias_Ajuste na = new Dias_Ajuste(cuenta, conce, x.get(Calendar.DAY_OF_MONTH), x.get(Calendar.MONTH), x.get(Calendar.YEAR), false);
                                p.add(na);
//                    break;
                            } else {
                                cuenta++;
                                Dias_Ajuste na = new Dias_Ajuste(cuenta, conce, x.get(Calendar.DAY_OF_MONTH), x.get(Calendar.MONTH), x.get(Calendar.YEAR), false);
                                p.add(na);
                            }
                            conce++;
                        }
                    } else {
                        System.out.println("Error");
                    }

                }

            }
            x.add(Calendar.DAY_OF_MONTH, 1);
        }
        // end while   
        x.add(Calendar.DAY_OF_MONTH, -count);
        return p;
    }

    public String Hora_final(String hora, String Rango_hora, String Horas_porDia) {
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
        System.err.println("++ " + respuesta);
        if (respuesta.equalsIgnoreCase("Primero")) {

            String fecha = request.getParameter("fecha_ini");
            String Num_asesoria = request.getParameter("Nase");
            String Ramgo_asesoria = request.getParameter("Rango");
            String Asesoeria_dia = request.getParameter("dia");
            String Hora_inicio = request.getParameter("horaI");
            String Asesoria_profesor = request.getParameter("AseP");
            String valo[] = request.getParameterValues("che");

            String Hora_fincho = "No";
            for (int i = 0; i < valo.length; i++) {
                System.err.println("dias " + valo[i]);
                if (valo[i].equalsIgnoreCase("sabado")) {
                    Hora_fincho = request.getParameter("horaIS");
                }

            }
            if (valo.length >= 1) {
                cargarDias(valo);
                molde.clear();
                session.setAttribute("dias", dias);
                ArrayList di = (ArrayList) session.getAttribute("dias");
                System.err.println("Tamaño " + di.size());

                if (Verificar() == false) {
                    if (validarPeriodo(fecha) == true) {
                        int horarSemestre = Diasdelperiodo(Num_asesoria, Asesoeria_dia, Ramgo_asesoria);
                        String fecha_final = Numero_asesorias(horarSemestre, fecha);
                        String Hora_final = Hora_final(Hora_inicio, Ramgo_asesoria, Asesoeria_dia);
                        int peri = 0;
                        String Hora_final_sabado = "No";
                        if (Hora_fincho.equalsIgnoreCase("No")) {
                            molde.add(new Ajuste_Modelo(fecha, fecha_final, periodo, Ramgo_asesoria, Num_asesoria, Hora_inicio, Hora_final, Asesoeria_dia, Asesoria_profesor));
                        } else {
                            Hora_final_sabado = Hora_final(Hora_fincho, Ramgo_asesoria, Asesoeria_dia);
                            molde.add(new Ajuste_Modelo(fecha, fecha_final, periodo, Ramgo_asesoria, Num_asesoria,
                                    Hora_inicio, Hora_final, Asesoeria_dia, Asesoria_profesor, Hora_fincho, Hora_final_sabado));

                        }
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
                                    //                                    + "                            out.println(\" <input  type=\\\"button\\\"  value=\\\"ENVIAR\\\" id=\\\"Enviar\\\" >\\n\"\n"
                                    //                                    + "                                    + \"<a <href=\\\"Ajuste.xhtml\\\"> atras </a>form>"
                                    + "</div>");
                            out.println("<div id=\"Mensaje\"  ></div>");

                            out.println("</h:body>");
                            out.println("</html>");
                        } finally {
                            out.close();
                        }
//                        } else {
//                            System.err.println("Error");
//                        }
//                    } else {
//                        System.err.println("Error de insert");
//                    }
                    } else {
                        System.err.println("Periodo malo");
                    }
                } else {
                    System.err.println("Ya existe");
                }
            } else {
                System.err.println("No ha seleccionado dias");
            }
        } else if (respuesta.equalsIgnoreCase("2")) {
////             HttpSession session2 = request.getSession(true);
            ArrayList temporal = null;
            ArrayList mis_dias = null;
            temporal = (ArrayList) session.getAttribute("molde_ajuste");
            mis_dias = (ArrayList) session.getAttribute("dias");
            System.err.println("tamaño " + temporal.size());
            int sequence = Sequence.Sequen("select max(cod_ajuste) from ajuste");

            Ajuste_Modelo temp2 = null;
            String qe = "";
            for (int i = 0; i < temporal.size(); i++) {
                temp2 = (Ajuste_Modelo) temporal.get(i);
                System.err.println("r+ " + temp2.toString());
                qe = "insert into ajuste values(" + sequence + ",'" + temp2.getFecha_inicio() + "','" + temp2.getFecha_final() + "'," + temp2.getRango_hora() + "," + temp2.getPeriodo()
                        + "," + temp2.getNumero_horas() + "','" + temp2.getHora_inicio() + "','" + temp2.getHora_final() + "','No','No',',pege_usuario" + temp2.getAsesorias_profesor() + "','" + temp2.getHoras_porDia() + "')";
            }
            System.err.println("Query: " + qe);
            Dias temp = null;
            for (int i = 0; i < mis_dias.size(); i++) {
                temp = (Dias) mis_dias.get(i);
                if (temp.isEstado()) {
                    int sequence_dias = Sequence.Sequen("select max(cod_dias) from dias");
                    System.err.println("r+ " + temp.getDia());
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
            HttpSession s = request.getSession(true);
            dias.clear();
            molde.clear();
            response.sendRedirect("index.xhtml");

//                    boolean r = control.ejecuteUpdate(qe);
//                    boolean r = true;
//                    if (r) {
//                        Dias temp = null;
//                        for (int i = 0; i < dias.size(); i++) {
//                            temp = (Dias) dias.get(i);
//                            int a = traerNumdias();
////                          ok = control.ejecuteUpdate("insert into dias values(" + a + ",'" + temp.getnombre() + "'," + sequence + ")");
//                            System.err.println("boolean " + ok);
//                        }
//                        if (ok == true) {
//            borrar();
//////                                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Executed", "Using RemoteCommand."));
////                                    Ajuste2();
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
