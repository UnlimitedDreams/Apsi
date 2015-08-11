package org.apache.jsp.logIn;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Entity.Rol;
import java.util.Map;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.TreeMap;
import dao.UsuarioImple;
import Entity.Persona;

public final class mainMenu_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"es\">\r\n");
      out.write("    ");
session = request.getSession();
        try {
            if (session.isNew() | session.getAttribute("user").equals(null)) {
                response.sendRedirect("index.jsp");
            }
        } catch (java.lang.NullPointerException e) {
            response.sendRedirect("index.jsp");
        }
    
      out.write("\r\n");
      out.write("    ");
Persona p = (Persona) session.getAttribute("persona");
      out.write("\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta charset=\"utf-8\">\r\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\r\n");
      out.write("        <meta name=\"description\" content=\"\">\r\n");
      out.write("        <meta name=\"author\" content=\"\">\r\n");
      out.write("        <title>ApSi.GiPep - Menú Principal</title>\r\n");
      out.write("        <!-- Bootstrap Core CSS -->\r\n");
      out.write("        <link href=\"../css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("        <!-- Custom CSS -->\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"../css/styles.css\" />\r\n");
      out.write("        <link href=\"../css/stylish-portfolio.css\" rel=\"stylesheet\">\r\n");
      out.write("        <!-- Link para panel menu-->\r\n");
      out.write("        <link href=\"../css/style.css\" rel=\"stylesheet\"/>\r\n");
      out.write("        <!-- Custom Fonts -->\r\n");
      out.write("        <link href=\"../font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("        <link href=\"//cdnjs.cloudflare.com/ajax/libs/animate.css/3.1.1/animate.min.css\" rel=\"stylesheet\" />\r\n");
      out.write("        <!--Icon facelet-->\r\n");
      out.write("        <link rel=\"icon\" type=\"image/png\" href=\"../img/apsi.ico\"/>\r\n");
      out.write("        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->\r\n");
      out.write("        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\r\n");
      out.write("        <!--[if lt IE 9]>\r\n");
      out.write("            <script src=\"https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js\"></script>\r\n");
      out.write("            <script src=\"https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js\"></script>\r\n");
      out.write("        <![endif]-->\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <!-- Navigation -->\r\n");
      out.write("        <a id=\"menu-toggle\" href=\"#\" class=\"btn btn-dark btn-lg toggle\"><i class=\"fa fa-bars\"></i></a>\r\n");
      out.write("        <nav id=\"sidebar-wrapper\">\r\n");
      out.write("            <ul class=\"sidebar-nav\">\r\n");
      out.write("                <a id=\"menu-close\" href=\"#\" class=\"btn btn-light btn-lg pull-right toggle\"><i class=\"fa fa-times\"></i></a>\r\n");
      out.write("                <li class=\"sidebar-brand\">\r\n");
      out.write("                    <a href=\"index.jsp\">ApSi</a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li>\r\n");
      out.write("                    <a href=\"#top\"><i class=\"fa fa-home fa-stack-1x text-primary\"></i>Inicio</a>\r\n");
      out.write("                </li>\r\n");
      out.write("                ");
                    String page1 = "<li><a id=\"opt1\">Acceder</a>"
                            + "<div id=\"ingresa\" >"
                            + "<form name=\"log\" action=\"ingresar\" method=\"POST\">\n"
                            + "<table style=\"border-spacing: 6px 12px; border-collapse: separate\"><tr style=\"text-align: right;\">\n"
                            + "<td></td><td>"
                            + "<input placeholder=\"Usuario\" type=\"text\" id=\"user\" class=\"form-in\" name=\"user\" required=\"\" />\n"
                            + "</td></tr><tr style=\"text-align: right;\"><td></td><td><input placeholder=\"Constrase&ntilde;a\" type=\"password\" id=\"pass\" class=\"form-in\" name=\"pass\" required=\"\" />\n"
                            + "</td></tr><tr><td></td><td><input type=\"submit\" class=\"btn btn-light btn-log\" name='operar' value=\"Iniciar sesi&oacute;n\"/></td>\n"
                            + "</tr></table><b><a href=\"opcionesDeAcceso.jsp\">No puedes acceder?</a></b><b><a href=\"http://www.uniajc.edu.co\">Registrate</a></b>\n"
                            + "</form></div></li>";
                    try {
                        if (session.isNew() | session.getAttribute("user").equals(null)) {
                            out.print(page1);
                        } else {

                            out.print("<li><a id=\"opt1\"><i class=\"fa fa-user-md fa-stack-1x text-primary\"></i>" + session.getAttribute("user") + "</a></li>"
                                    + "<div id=\"ingresa\">"
                                    + "<ul>"
                                    + "<li><a href=\"profile.jsp\">Mi perfil</a></li>"
                                    + "<li><a href=\"#Activity\">Mi actividad</a></li>"
                                    + "</ul>"
                                    + "<form name=\"log\" action=\"ingresar\" method=\"GET\">"
                                    + "<table style=\"border-spacing: 6px 12px; border-collapse: separate\">"
                                    + "<tr style=\"text-align: right;\">\n"
                                    + "<td></td>"
                                    + "<td>"
                                    + "<input type=\"submit\" class=\"btn btn-light btn-log\" name='operar' value=\"Cerrar sesi&oacute;n\"/>"
                                    + "</td>"
                                    + "</tr>"
                                    + "</table> "
                                    + "</form>"
                                    + "</div>");
                        }
                    } catch (java.lang.NullPointerException e) {
                        out.print(page1);
                    }
                
      out.write("\r\n");
      out.write("                <li>\r\n");
      out.write("                    <a href=\"#Archivos\"><i class=\"fa fa-folder-o fa-stack-1x text-primary\"></i>Archivo</a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li>\r\n");
      out.write("                    <a href=\"#Opciones\"><i class=\"fa fa-cog fa-stack-1x text-primary\"></i>Opciones</a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li>\r\n");
      out.write("                    <a href=\"#contacto\"><i class=\"fa fa-comment fa-stack-1x text-primary\"></i>Contacto</a>\r\n");
      out.write("                </li>\r\n");
      out.write("\r\n");
      out.write("            </ul>\r\n");
      out.write("        </nav> \r\n");
      out.write("        <header class=\"container-fluid\" id=\"top\">\r\n");
      out.write("            <div class=\"container-fluid\">\r\n");
      out.write("                <div class=\"row\">                   \r\n");
      out.write("                    <h3><i class=\"fa fa-tag\"></i> ");

                        TreeMap roles = new UsuarioImple().cargarRoles(p.getUsuario().getPegeId().toString());
                        String rol = "";
                        for (Iterator it = roles.entrySet().iterator(); it.hasNext();) {
                            Map.Entry me = (Map.Entry) it.next();
                            BigDecimal key = (BigDecimal) me.getKey();
                            Rol value = (Rol) roles.get(key);
                            rol = value.getNombre();
                            out.println(value.getNombre());
                        }
                        
      out.write("\r\n");
      out.write("                    </h3> \r\n");
      out.write("                    <hr>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </header>\r\n");
      out.write("\r\n");
      out.write("        <section class=\"container-fluid\" id=\"section2\">\r\n");
      out.write("            <div class=\"row\">\r\n");
      out.write("                <div class=\"col-sm-3\">\r\n");
      out.write("                    <aside>\r\n");
      out.write("                        <div id=\"sidebar\"  class=\"nav-collapse  \">\r\n");
      out.write("                            <!-- sidebar menu start-->\r\n");
      out.write("                            <ul class=\"sidebar-menu\">                \r\n");
      out.write("                                <li class=\"active\">\r\n");
      out.write("                                    <a class=\"\" href=\"#\">\r\n");
      out.write("                                        <i class=\"icon_house_alt\"></i>\r\n");
      out.write("                                        <span>Opciones de ");
      out.print(rol);
      out.write("</span>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </li>\r\n");
      out.write("                                <li class=\"sub-menu\">\r\n");
      out.write("                                    <a href=\"javascript:;\" class=\"\">\r\n");
      out.write("                                        <i class=\"fa fa-male\"></i>\r\n");
      out.write("                                        <span>Accion</span>\r\n");
      out.write("                                        <span class=\"menu-arrow arrow_carrot-right\"></span>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                    <ul class=\"sub\">\r\n");
      out.write("                                        <li><a href=\"#\"><i class=\"fa fa-plus\"></i>Actividad</a></li>\r\n");
      out.write("                                        <li><a href=\"#\"><i class=\"fa fa-cogs\"> </i>Actividad</a></li>\r\n");
      out.write("                                    </ul>\r\n");
      out.write("                                </li>       \r\n");
      out.write("                                <li class=\"sub-menu\">\r\n");
      out.write("                                    <a href=\"javascript:;\" class=\"\">\r\n");
      out.write("                                        <i class=\"fa fa-users\"></i>\r\n");
      out.write("                                        <span>Accion</span>\r\n");
      out.write("                                        <span class=\"menu-arrow arrow_carrot-right\"></span>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                    <ul class=\"sub\">\r\n");
      out.write("                                        <li><a href=\"#\" ><i class=\"fa fa-plus\"> </i>Actividad</a></li>\r\n");
      out.write("                                        <li><a href=\"#\" ><i class=\"fa fa-cogs\"> </i>Actividad</a></li>\r\n");
      out.write("                                    </ul>\r\n");
      out.write("                                </li>\r\n");
      out.write("                            </ul>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </aside>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"col-sm-9\">\r\n");
      out.write("\r\n");
      out.write("                    <div class=\"panel panel-default slideInUp animate\">\r\n");
      out.write("                        <div class=\"panel-heading\"><center><label>Avance de proyecto</label></center></div>\r\n");
      out.write("                        <div class=\"panel panel-primary slideInUp animate\">\r\n");
      out.write("                            <div class=\"panel-heading\">\r\n");
      out.write("                                Mis proyectos:\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div class=\"container\">\r\n");
      out.write("                                <h3>Center content</h3>    \r\n");
      out.write("                            </div>\r\n");
      out.write("\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"container\">\r\n");
      out.write("                            <h2 class=\"text-info\">Mis proyectos:</h2>\r\n");
      out.write("                            <h2 class=\"text-success\">Text color Green</h2>\r\n");
      out.write("                            <h3 class=\"text-danger\">Text color Red</h3>\r\n");
      out.write("                            <h3 class=\"text-primary\">Text color Blue</h3>   \r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"panel-heading\"><center>Tablero de noticias</center></div>\r\n");
      out.write("\r\n");
      out.write("                        <h1>Text</h1>\r\n");
      out.write("                        <h2 class=\"text-success\">Text color Green</h2>\r\n");
      out.write("                        <h3 class=\"text-danger\">Text color Red</h3>\r\n");
      out.write("                        <h3 class=\"text-primary\">Text color Blue</h3>\r\n");
      out.write("                        <h1>Links</h1>\r\n");
      out.write("                        <a class=\"btn-success\">Hello</a> - <a class=\"text-success\">Facelets</a>\r\n");
      out.write("                        <a class=\"btn-danger\">Hello</a> - <a class=\"text-danger\">Facelets</a>\r\n");
      out.write("                        <a class=\"btn-primary\">Hello</a> - <a class=\"text-primary\">Facelets</a>\r\n");
      out.write("\r\n");
      out.write("                        <h1>Buttons</h1>\r\n");
      out.write("                        <button type=\"submit\" class=\"btn btn-success\">Submit</button>\r\n");
      out.write("                        <button type=\"submit\" class=\"btn btn-danger\">Delete</button>\r\n");
      out.write("                        <button type=\"submit\" class=\"btn btn-primary\">Options</button>\r\n");
      out.write("\r\n");
      out.write("                        <h1>Progres Var</h1>\r\n");
      out.write("                        <div class=\"panel-body\">\r\n");
      out.write("                            <div class=\"progress-bar \" role=\"progressbar\" aria-valuenow=\"20\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: 20%;\">\r\n");
      out.write("                                20%\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"panel-body\">\r\n");
      out.write("                            <div class=\"progress-bar\" role=\"progressbar\" aria-valuenow=\"50\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: 50%;\">\r\n");
      out.write("                                50%\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("                        <h1>Panel examples</h1>\r\n");
      out.write("\r\n");
      out.write("                        <div class=\"panel panel-primary slideInUp animate\">\r\n");
      out.write("                            <div class=\"panel-heading\">\r\n");
      out.write("                                <h4>Title Panel</h4></div><h3>Center content</h3></div>\r\n");
      out.write("\r\n");
      out.write("                        <div class=\"panel panel-danger slideInUp animate\">\r\n");
      out.write("                            <div class=\"panel-heading\">\r\n");
      out.write("                                <h4>Title Panel</h4></div><h3>Center content</h3></div>\r\n");
      out.write("\r\n");
      out.write("                        <div class=\"panel panel-success slideInUp animate\">\r\n");
      out.write("                            <div class=\"panel-heading\">\r\n");
      out.write("                                <h4>Title Panel</h4></div><h3>Center content</h3></div>\r\n");
      out.write("\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("        </section>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <!-- Footer -->\r\n");
      out.write("        <footer>\r\n");
      out.write("            <div class=\"container\" id=\"contacto\">\r\n");
      out.write("                <div class=\"row\">\r\n");
      out.write("                    <div class=\"col-lg-10 col-lg-offset-1 text-center\">\r\n");
      out.write("                        <h4><strong>ApSi&REG;</strong>\r\n");
      out.write("                        </h4>\r\n");
      out.write("                        <p>Instituci&oacute;n Universitaria Antonio Jos&eacute; Camacho<br>Santiago de Cali, Colombia</p>\r\n");
      out.write("                        <ul class=\"list-unstyled\">\r\n");
      out.write("                            <li><i class=\"fa fa-phone fa-fw\"></i> (318) 265-7417</li>\r\n");
      out.write("                            <li><i class=\"fa fa-envelope-o fa-fw\"></i>  <a href=\"mailto:unlimitedDreamsTi@gmail.com\">unlimitedDreamsTi@gmail.com</a>\r\n");
      out.write("                            </li>\r\n");
      out.write("                        </ul>\r\n");
      out.write("                        <br>\r\n");
      out.write("                        <ul class=\"list-inline\">\r\n");
      out.write("                            <li>\r\n");
      out.write("                                <a href=\"#\"><i class=\"fa fa-facebook fa-fw fa-3x\"></i></a>\r\n");
      out.write("                            </li>\r\n");
      out.write("                            <li>\r\n");
      out.write("                                <a href=\"#\"><i class=\"fa fa-twitter fa-fw fa-3x\"></i></a>\r\n");
      out.write("                            </li>\r\n");
      out.write("                        </ul>\r\n");
      out.write("                        <hr class=\"small\">\r\n");
      out.write("                        <p class=\"text-muted\">Powered By Unlimited Dreams <br>Copyright &copy; Unlimited Dreams, Todos los derechos reservados.</p>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </footer>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"scroll-up\">\r\n");
      out.write("            <a href=\"#\"><i class=\"fa fa-angle-up\"></i></a>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <!--scripts para panel menu-->\r\n");
      out.write("\r\n");
      out.write("        <!-- jQuery -->\r\n");
      out.write("        <script src=\"../js/Jquery/jquery-1.11.2.js\"></script>\r\n");
      out.write("        <script src=\"../js/Jquery/jquery.nicescroll.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\r\n");
      out.write("        <!-- Bootstrap Core JavaScript -->\r\n");
      out.write("        <script src=\"../js/bootstrap.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <!--scripts loaded here-->\r\n");
      out.write("        <script src=\"../js/custom/scripts.js\"></script>\r\n");
      out.write("        <script src=\"../js/custom/scripts1.js\"></script>\r\n");
      out.write("\r\n");
      out.write("        <!--Script ocultar Divs-->\r\n");
      out.write("        <script>\r\n");
      out.write("            $(document).ready(function () {\r\n");
      out.write("                $(\"#ingresa\").hide();\r\n");
      out.write("                $('#opt1').click(function () {\r\n");
      out.write("                    $(\"#ingresa\").toggle(\"left\", function () {\r\n");
      out.write("                    });\r\n");
      out.write("                });\r\n");
      out.write("            });\r\n");
      out.write("        </script>\r\n");
      out.write("\r\n");
      out.write("        <!-- Custom Theme JavaScript -->\r\n");
      out.write("        <script>\r\n");
      out.write("            // Closes the sidebar menu\r\n");
      out.write("            $(\"#menu-close\").click(function (e) {\r\n");
      out.write("                e.preventDefault();\r\n");
      out.write("                $(\"#sidebar-wrapper\").toggleClass(\"active\");\r\n");
      out.write("            });\r\n");
      out.write("\r\n");
      out.write("            // Opens the sidebar menu\r\n");
      out.write("            $(\"#menu-toggle\").click(function (e) {\r\n");
      out.write("                e.preventDefault();\r\n");
      out.write("                $(\"#sidebar-wrapper\").toggleClass(\"active\");\r\n");
      out.write("            });\r\n");
      out.write("\r\n");
      out.write("            // Scrolls to the selected menu item on the page\r\n");
      out.write("            $(function () {\r\n");
      out.write("                $('a[href*=#]:not([href=#])').click(function () {\r\n");
      out.write("                    if (location.pathname.replace(/^\\//, '') === this.pathname.replace(/^\\//, '') || location.hostname === this.hostname) {\r\n");
      out.write("\r\n");
      out.write("                        var target = $(this.hash);\r\n");
      out.write("                        target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');\r\n");
      out.write("                        if (target.length) {\r\n");
      out.write("                            $('html,body').animate({\r\n");
      out.write("                                scrollTop: target.offset().top\r\n");
      out.write("                            }, 1000);\r\n");
      out.write("                            return false;\r\n");
      out.write("                        }\r\n");
      out.write("                    }\r\n");
      out.write("                });\r\n");
      out.write("            });\r\n");
      out.write("        </script>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
