/*
 * The MIT License
 *
 * Copyright 2015 usuario.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package apsi.Security;

import dao.UsuarioImple;
import Entity.Persona;
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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import util.HibernateUtil;

/**
 *
 * @author Miguel Angel Lemos
 */
public class ingresar extends HttpServlet {

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
            HttpSession s;
            s = request.getSession();
            if (request.getParameter("operar").equalsIgnoreCase("Cerrar sesión")) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                session.clear();
                session.close();
                s.invalidate();
                response.sendRedirect("index.jsp");
            } else {
                Usuario x;
                x = new Usuario(new BigDecimal(1), md5.getMD5(request.getParameter("pass")), request.getParameter("user"));
                try {
                    Usuario Loguin = new UsuarioImple().Loguin(x);
                    if (Loguin.getContrasea().equals(x.getContrasea()) && Loguin.getUsuario().equals(x.getUsuario())) {
                        s.setAttribute("user", x.getUsuario());
                        s.setAttribute("pass", x.getContrasea());
                        Persona ww = new UsuarioImple().verPersona(Loguin.getPegeId().toString());
                        s.setAttribute("persona", ww);
                        response.sendRedirect("index.jsp");
                    } else {
                        RequestDispatcher a = request.getRequestDispatcher("index.jsp?msg=Usuario y/o contraseña incorrectos&msgAlt=danger");
                        a.forward(request, response);
                    }
                } catch (java.lang.NullPointerException e) {
                    RequestDispatcher a = request.getRequestDispatcher("index.jsp?msg=Usuario y/o contraseña incorrectos&msgAlt=danger");
                    a.forward(request, response);
                }

            }

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

}
