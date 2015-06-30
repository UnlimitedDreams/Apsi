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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet srvltOA</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet srvltOA at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        processRequest(request, response);
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
                s.setAttribute("tempUser", "mi usuario");
                response.sendRedirect("recuperaAcceso.jsp");
//                RequestDispatcher a = request.getRequestDispatcher("opcionesDeAcceso.jsp?msg=Su usuario si existe&msgAlt=info");
//                a.forward(request, response);
            }
        } catch (java.lang.NullPointerException e) {
            RequestDispatcher a = request.getRequestDispatcher("opcionesDeAcceso.jsp?msg=No se permirten campos vacios&msgAlt=danger");
            a.forward(request, response);
        }

        // Logueo del usuario
//        if (request.getSession().getAttribute("pegeId") != null) {
//        }
//        login(request, response);
    }

    void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        if (request.getParameter("username") != null && request.getParameter("password").equals(usuarioLogin.getContrasenia())) {
//            if (request.getParameter("username").equals("NombreDeUnUsuario")  {
//                HttpSession session = request.getSession();
//                session.setAttribute("user", request.getParameter("username"));
//                RequestDispatcher a = request.getRequestDispatcher("index.jsp");
//                a.forward(request, response);
//            }
//            RequestDispatcher a = request.getRequestDispatcher("login.jsp?msg=Usuario y/o "
//                    + "contraseña incorrectos");
//            a.forward(request, response);
//        }
//        RequestDispatcher a = request.getRequestDispatcher("login.jsp?msg=Usuario y/o contraseña incorrectos");
//        a.forward(request, response);
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
