package org.apache.jsp.logIn;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
HttpSession sesion = request.getSession();
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"es\" >\r\n");
      out.write("\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta charset=\"utf-8\">\r\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("        <meta name=\"description\" content=\"\">\r\n");
      out.write("        <meta name=\"author\" content=\"\">\r\n");
      out.write("        <title>ApSi.GiPep - Tus asesorias Faciles</title>\r\n");
      out.write("\r\n");
      out.write("        <!-- Bootstrap Core CSS -->\r\n");
      out.write("        <link href=\"../css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("        <!-- Custom CSS -->\r\n");
      out.write("        <link href=\"../css/stylish-portfolio.css\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("        <!-- Custom Fonts -->\r\n");
      out.write("        <link href=\"../font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("        <link href=\"http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("\r\n");
      out.write("        <!--Icon facelet-->\r\n");
      out.write("        <link rel=\"icon\" type=\"image/png\" href=\"../img/apsi.ico\"/>\r\n");
      out.write("        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->\r\n");
      out.write("        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\r\n");
      out.write("        <!--[if lt IE 9]>\r\n");
      out.write("            <script src=\"https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js\"></script>\r\n");
      out.write("            <script src=\"https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js\"></script>\r\n");
      out.write("        <![endif]-->\r\n");
      out.write("    </head>\r\n");
      out.write("\r\n");
      out.write("    <body>\r\n");
session = request.getSession();
        try {
            if (!session.isNew() | !session.getAttribute("user").equals(null)) {
                response.sendRedirect("../modJDCJJS/Menu_Admon2.xhtml");
            }
        } catch (java.lang.NullPointerException e) {
        }
    
      out.write("\r\n");
      out.write("        <!-- Navigation -->\r\n");
      out.write("        <a id=\"menu-toggle\" href=\"#\" class=\"btn btn-dark btn-lg toggle\"><i class=\"fa fa-bars\"></i></a>\r\n");
      out.write("        <nav id=\"sidebar-wrapper\">\r\n");
      out.write("            <ul class=\"sidebar-nav\">\r\n");
      out.write("                <a id=\"menu-close\" href=\"#\" class=\"btn btn-light btn-lg pull-right toggle\"><i class=\"fa fa-times\"></i></a>\r\n");
      out.write("                <li class=\"sidebar-brand\">\r\n");
      out.write("                    <a onclick=\"document.location = 'index.jsp'\">ApSi</a>\r\n");
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
                        if (sesion.isNew() | session.getAttribute("user").equals(null)) {
                            out.print(page1);
                        } else {

                            out.print("<li><a id=\"opt1\"><i class=\"fa fa-user-md fa-stack-1x text-primary\"></i>" + sesion.getAttribute("user") + "</a></li>"
                                    + "<div id=\"ingresa\">"
                                    + "<ul>"
                                    + "<li><a href=\"profile.jsp\">Mi perfil</a></li>"
                                    + "<li><a href=\"mainMenu.jsp\">Mi actividad</a></li>"
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("                <li>\r\n");
      out.write("                    <a href=\"#about\"><i class=\"fa fa-bookmark fa-stack-1x text-primary\"></i>Acerca de ApSi</a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li>\r\n");
      out.write("                    <a href=\"#services\"><i class=\"fa fa-info fa-stack-1x text-primary\"></i>Solicitar Registro</a>\r\n");
      out.write("                </li>\r\n");
      out.write("\r\n");
      out.write("                <li>\r\n");
      out.write("                    <a href=\"#contacto\"><i class=\"fa fa-comment fa-stack-1x text-primary\"></i>Contacto</a>\r\n");
      out.write("                </li>\r\n");
      out.write("            </ul>\r\n");
      out.write("        </nav>\r\n");
      out.write("\r\n");
      out.write("        <!-- Header -->\r\n");
      out.write("        <header id=\"top\" class=\"header\">\r\n");
      out.write("            <div class=\"text-vertical-center\">\r\n");
      out.write("                <h1><img src=\"../img/Logo.png\"/>&reg;</h1>\r\n");
      out.write("                <h3>Asesorias y proyectos en cualquier momento</h3>\r\n");
      out.write("                <br>\r\n");
      out.write("                <a href=\"#about\" class=\"btn btn-dark btn-lg\">Aprende m&aacute;s sobre ApSi.</a>\r\n");
      out.write("                <div class=\"alert alert-");
      out.print(request.getParameter("msgAlt") != null ? request.getParameter("msgAlt") : "");
      out.write("\" role=\"alert\">   \r\n");
      out.write("                    <br><b><a style=\"color:red\">");
      out.print( request.getParameter("msg") != null ? request.getParameter("msg") : "");
      out.write("</a></b>\r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("        </header>\r\n");
      out.write("\r\n");
      out.write("        <!-- About -->\r\n");
      out.write("        <section id=\"about\" class=\"about\">\r\n");
      out.write("            <div class=\"container\">\r\n");
      out.write("                <div class=\"row\">\r\n");
      out.write("                    <div class=\"col-lg-12 text-center\">\r\n");
      out.write("                        <h2>Un nuevo concepto, la misma realidad</h2>\r\n");
      out.write("                        <p>Sencillo y r&aacute;pido, sin complicaciones.</p>\r\n");
      out.write("                        <p class=\"lead\">&quot;Las m&aacute;quinas no son nada \r\n");
      out.write("                            sin una mente humana que las opere, los cuerpos \r\n");
      out.write("                            humanos no pueden hacer lograr la perfecci&oacute;n \r\n");
      out.write("                            sin una m&aacute;quina que ayude en las labores \r\n");
      out.write("                            m&aacute;s arduas e imposibles para la humanidad&quot;.\r\n");
      out.write("                            <br>\r\n");
      out.write("                            <label>- Unlimited Dreams</label></p>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <!-- /.row -->\r\n");
      out.write("            </div>\r\n");
      out.write("            <!-- /.container -->\r\n");
      out.write("        </section>\r\n");
      out.write("\r\n");
      out.write("        <!-- Services -->\r\n");
      out.write("        <!-- The circle icons use Font Awesome's stacked icon classes. For more information, visit http://fontawesome.io/examples/ -->\r\n");
      out.write("        <section id=\"services\" class=\"services bg-primary\">\r\n");
      out.write("            <div class=\"container\">\r\n");
      out.write("                <div class=\"row text-center\">\r\n");
      out.write("                    <div class=\"col-lg-10 col-lg-offset-1\">\r\n");
      out.write("                        ");

                            try {
                                if (!sesion.isNew() | !session.getAttribute("user").equals(null)) {
                                    out.print("<h2>Bienvenido</h2>");
                                } else {
                                    out.print("<h2>A&uacute;n sin registrarte?</h2>");
                                }
                            } catch (java.lang.NullPointerException e) {
                                out.print("<h2>A&uacute;n sin registrarte?</h2>");
                            }

                        
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                        <hr class=\"small\">\r\n");
      out.write("                        <div class=\"row\">\r\n");
      out.write("                            <div class=\"col-md-3 col-sm-6\">\r\n");
      out.write("                                ");
                                    try {
                                        if (!sesion.isNew() | !session.getAttribute("user").equals(null)) {
                                            out.print("                               <div class=\"service-item\">\n"
                                                    + "                                    <span class=\"fa-stack fa-4x\">\n"
                                                    + "                                        <i class=\"fa fa-circle fa-stack-2x\"></i>\n"
                                                    + "                                        <i class=\"fa fa-bookmark fa-stack-1x text-primary\"></i>\n"
                                                    + "                                    </span>\n"
                                                    + "                                    <h4>\n"
                                                    + "                                        <strong>Menú Principal</strong>\n"
                                                    + "                                    </h4>\n"
                                                    + "                                    <p>Acceso al menu principal.</p>\n"
                                                    + "                                    <a href=\"mainMenu.jsp\" class=\"btn btn-light\">Accede</a>\n"
                                                    + "                                </div>\n");
                                        }
                                    } catch (java.lang.NullPointerException e) {
                                    }
                                
      out.write("\r\n");
      out.write("\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div class=\"col-md-3 col-sm-6\">\r\n");
      out.write("                                <div class=\"service-item\">\r\n");
      out.write("                                    <span class=\"fa-stack fa-4x\">\r\n");
      out.write("                                        <i class=\"fa fa-circle fa-stack-2x\"></i>\r\n");
      out.write("                                        <i class=\"fa fa-compass fa-stack-1x text-primary\"></i>\r\n");
      out.write("                                    </span>\r\n");
      out.write("                                    <h4>\r\n");
      out.write("                                        <strong>Pide una asesoria</strong>\r\n");
      out.write("                                    </h4>\r\n");
      out.write("                                    <p>Capacitate, esfuerzate y trabaja con los mejores asesores. Tu tesis lo amerita.</p>\r\n");
      out.write("                                    ");

                                        try {
                                            if (!sesion.isNew() | !session.getAttribute("user").equals(null)) {
                                                out.print("<a href=\"#NewAsesoria\" class=\"btn btn-light\">Accede</a>");
                                            }
                                        } catch (java.lang.NullPointerException e) {
                                            out.print("<a href=\"logIn.jsp\" class=\"btn btn-light\">Accede</a>");
                                        }
                                    
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div class=\"col-md-3 col-sm-6\">\r\n");
      out.write("                                ");

                                    try {
                                        if (!sesion.isNew() | !session.getAttribute("user").equals(null)) {
                                            out.print("<div class=\"service-item\">\n"
                                                    + "                                    <span class=\"fa-stack fa-4x\">\n"
                                                    + "                                        <i class=\"fa fa-circle fa-stack-2x\"></i>\n"
                                                    + "                                        <i class=\"fa fa-user fa-stack-1x text-primary\"></i>\n"
                                                    + "                                    </span>\n"
                                                    + "                                    <h4>\n"
                                                    + "                                        <strong>Perfil</strong>\n"
                                                    + "                                    </h4>\n"
                                                    + "                                    <p>Mant&eacute;n tu perfil actualizado, esto ayuda a que llegue tus correos a tiempo y al lugar indicado.</p>\n"
                                                    + "                                    <a href=\"profile.jsp\" class=\"btn btn-light\">Ver perfil</a>\n"
                                                    + "                                </div>");
                                        }
                                    } catch (java.lang.NullPointerException e) {
                                        out.print("<div class=\"service-item\">\n"
                                                + "                                    <span class=\"fa-stack fa-4x\">\n"
                                                + "                                        <i class=\"fa fa-circle fa-stack-2x\"></i>\n"
                                                + "                                        <i class=\"fa fa-cloud fa-stack-1x text-primary\"></i>\n"
                                                + "                                    </span>\n"
                                                + "                                    <h4>\n"
                                                + "                                        <strong>¡Pide tu usuario!</strong>\n"
                                                + "                                    </h4>\n"
                                                + "                                    <p>La &uacute;nica forma de acceder a esta herramienta es que estes registrado. H&aacute;slo pronto!.</p>\n"
                                                + "                                    <a href=\"http://uniajc.edu.co/index.php/estudiantes\" class=\"btn btn-light\">Como registrarse</a>\n"
                                                + "                                </div>");
                                    }
                                
      out.write("\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div class=\"col-md-3 col-sm-6\">\r\n");
      out.write("                                <div class=\"service-item\">\r\n");
      out.write("                                    ");

                                        try {
                                            if (!sesion.isNew() | !session.getAttribute("user").equals(null)) {
                                                out.print("                               <div class=\"service-item\">\n"
                                                        + "                                    <span class=\"fa-stack fa-4x\">\n"
                                                        + "                                        <i class=\"fa fa-circle fa-stack-2x\"></i>\n"
                                                        + "                                        <i class=\"fa fa-users fa-stack-1x text-primary\"></i>\n"
                                                        + "                                    </span>\n"
                                                        + "                                    <h4>\n"
                                                        + "                                        <strong>Ajustes personales</strong>\n"
                                                        + "                                    </h4>\n"
                                                        + "                                    <p>Mant&eacute;n al d&iacute;a tus datos.</p>\n"
                                                        + "                                    <a href=\"logIn.jsp\" class=\"btn btn-light\">Accede</a>\n"
                                                        + "                                </div>\n");
                                            }
                                        } catch (java.lang.NullPointerException e) {
                                        }
                                    
      out.write("\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("\r\n");
      out.write("                            <!-- /.row (nested) -->\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <!-- /.col-lg-10 -->\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <!-- /.row -->\r\n");
      out.write("                </div>\r\n");
      out.write("                <!-- /.container -->\r\n");
      out.write("            </div>\r\n");
      out.write("        </section>\r\n");
      out.write("\r\n");
      out.write("        <!-- Footer -->\r\n");
      out.write("        <footer>\r\n");
      out.write("            <div class=\"container\" id=\"contacto\">\r\n");
      out.write("                <div class=\"row\">\r\n");
      out.write("                    <div class=\"col-lg-10 col-lg-offset-1 text-center\">\r\n");
      out.write("                        <h4><strong><img src=\"../img/LogoMin.png\"/>&reg;</strong>\r\n");
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
      out.write("        <!-- jQuery -->\r\n");
      out.write("        <script src=\"../js/Jquery/jquery-1.11.2.js\"></script>\r\n");
      out.write("\r\n");
      out.write("        <!-- Bootstrap Core JavaScript -->\r\n");
      out.write("        <script src=\"../js/bootstrap.js\"></script>\r\n");
      out.write("\r\n");
      out.write("        <!--Script ocultar Divs-->\r\n");
      out.write("        <script>\r\n");
      out.write("                        $(document).ready(function () {\r\n");
      out.write("                            $(\"#ingresa\").hide();\r\n");
      out.write("                            $('#opt1').click(function () {\r\n");
      out.write("                                $(\"#ingresa\").toggle(\"left\", function () {\r\n");
      out.write("                                });\r\n");
      out.write("                            });\r\n");
      out.write("                        });\r\n");
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
      out.write("\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
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
