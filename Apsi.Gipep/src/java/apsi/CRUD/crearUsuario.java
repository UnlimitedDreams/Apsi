/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apsi.CRUD;

import dao.UsuarioImple;
import Entity.Persona;
import Entity.Usuario;
import dao.PersonaHelper;
import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author usuario
 */
public class crearUsuario extends HttpServlet {

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
        UsuarioImple MiUser = new UsuarioImple();
        Usuario u = new Usuario(BigDecimal.valueOf(MiUser.cargarTodo().size() + 1), request.getParameter("pass1"), request.getParameter("user"));
        boolean js1 = false, js2 = false;
        try {
            js1 = MiUser.CrearUsuario(u);
        } catch (Exception e) {
            RequestDispatcher a = request.getRequestDispatcher("agregarUsuario.jsp?msg=Error guardando usuario: " + e.toString() + "&msgAlt=danger");
            a.forward(request, response);
        }
        PersonaHelper myPeople = new PersonaHelper();
        Persona p = new Persona(BigDecimal.valueOf(Integer.parseInt(request.getParameter("id"))),
                u, request.getParameter("name"), request.getParameter("lastName"));
        try {
            js2 = myPeople.agregar(p);
        } catch (Exception e) {
            RequestDispatcher a = request.getRequestDispatcher("agregarUsuario.jsp?msg=Error guardando usuario: " + e.toString() + "&msgAlt=danger");
            a.forward(request, response);
        }

        if (js2 == true && js1 == true) {
            myPeople.limpiarSessiones();
            RequestDispatcher a = request.getRequestDispatcher("agregarUsuario.jsp?msg=Usuario guardado&msgAlt=info");
            a.forward(request, response);
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
        processRequest(request, response);
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
//
}
