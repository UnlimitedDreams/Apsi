/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apsi.Security;

import Dao.UsuarioImple;
import Entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author usuario
 */
public class srvltOA extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        processRequest(request, response);
        try {
            HttpSession s = request.getSession();
            sender.EnviadorMail EnviadorMail = new sender.EnviadorMail(request.getParameter("email"),
                    "No reply / No responder",
                    "Apreciado usuario de ApSi, su contraseña ha sido renovada\n \nContraseña: " + s.getAttribute("nPass")
                    + "\n\nAcceda, le recomendamos que cambie su contraseña a la mayor brevedad. \nEquipo ApSi");
            RequestDispatcher a = request.getRequestDispatcher("index.jsp?msg=Contraseña cambiada&msgAlt=info");
            a.forward(request, response);
        } catch (Error | NullPointerException e) {
            RequestDispatcher a = request.getRequestDispatcher("recuperaAcceso.jsp?msg=Algo Fallo&msgAlt=danger");
            a.forward(request, response);
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
            if (!new UsuarioImple().BuscarUsuario(new Usuario(BigDecimal.valueOf(Integer.parseInt(request.getParameter("pegeId"))), null, null))) {
                RequestDispatcher a = request.getRequestDispatcher("opcionesDeAcceso.jsp?msg=Su usuario no existe&msgAlt=danger");
                a.forward(request, response);
            } else {
                HttpSession s = request.getSession();
                Usuario U = new UsuarioImple().cargarUsu(request.getParameter("pegeId"));
                String x = "";
                x = (int) (Math.random() * 10000) + "";
                s.setAttribute("nPass", x);
                new UsuarioImple().CambiarContraseña(request.getParameter("pegeId"), x);
                s.setAttribute("tempUser", U.getUsuario());
                response.sendRedirect("recuperaAcceso.jsp");
            }
        } catch (java.lang.NullPointerException e) {
            RequestDispatcher a = request.getRequestDispatcher("opcionesDeAcceso.jsp?msg=No se permirten campos vacios&msgAlt=danger");
            a.forward(request, response);
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
