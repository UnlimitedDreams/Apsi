/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.com.control;

import dao.com.modelo.Dias;
import dao.com.modelo.Dias_Ajuste;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USR_Toshiba
 */
@WebServlet(name = "Disponibilidad_control", urlPatterns = {"/Disponibilidad_control"})
public class Disponibilidad_control extends HttpServlet {

    ArrayList<Dias_Ajuste> Festivos = new ArrayList(); //Festivos que hay durante el año
    ArrayList<Dias> dias = new ArrayList(); //dias seleccionados en la vista
    private String periodo;

    public ArrayList<Dias_Ajuste> getFestivos() {
        return Festivos;
    }

    public void setFestivos(ArrayList<Dias_Ajuste> Festivos) {
        this.Festivos = Festivos;
    }

    public ArrayList<Dias> getDias() {
        return dias;
    }

    public void setDias(ArrayList<Dias> dias) {
        this.dias = dias;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public void cargarDias(String v[]) {
        // carga los dias de la primera vista de ajuste con lso dias normales de la semana
        // los ceros son la hora inicial y final
        dias.clear();
        for (int k = 0; k < v.length; k++) {
            Dias f = new Dias(false, v[k], "0", "0");
            dias.add(f);

        }

    }

    public boolean validarPeriodo(String x) {
//        valida la fecha que se ingreso que sea la correcta 
//        correcta en año y fechas no menores a la de hoy
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

    public int cantidad_aesorias(String año) throws ClassNotFoundException {
        Control.conectar();
        System.out.println("año " + año);
        Control.ejecuteQuery("select distinct horas_profesor from ajuste,año_festivo,dias\n"
                + "where ajuste.cod_ajuste=dias.cod_ajuste\n"
                + "and ajuste.festivo=año_festivo.cod_año\n"
                + "and año_festivo.año='" + año + "' and \n"
                + "ajuste.periodo=" + periodo);
        int cantidad = 0;
        try {
            while (Control.rs.next()) {
                cantidad = Control.rs.getInt(1);
            }
        } catch (Exception ex) {

        }
        System.out.println("cantidad de dias " + cantidad);
        return cantidad;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        String fecha1[] = request.getParameterValues("fec1");
        String fecha2[] = request.getParameterValues("fec2");
        String dia[] = request.getParameterValues("sele");
        String fecha_inicio = request.getParameter("fecha");
        cargarDias(dia);
        System.out.println("fecha " + fecha_inicio + ".-. " + dia.length);
        if (dia.length >= 1) {
            System.out.println("--- 2");
            if (validarPeriodo(fecha_inicio) == true) {
                System.out.println("---");
                Calendario calen = new Calendario();
                int numero_asesoria = cantidad_aesorias(fecha_inicio.substring(0, 4));
                System.out.println("dias " + dias.size());
                String fech = calen.Numero_asesorias(numero_asesoria, fecha_inicio, dias);
                System.out.println("Fecha final es " + fech);
            }

        }

        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Disponibilidad_control</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Disponibilidad_control at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
        } catch (ParseException ex) {
            Logger.getLogger(Disponibilidad_control.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Disponibilidad_control.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(Disponibilidad_control.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Disponibilidad_control.class.getName()).log(Level.SEVERE, null, ex);
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
