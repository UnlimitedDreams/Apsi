package org.apache.jsp.logIn;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class logIn_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"es\">\r\n");
      out.write("    ");
session = request.getSession();
        try {
            if (!session.isNew() | !session.getAttribute("user").equals(null)) {
                response.sendRedirect("index.jsp");
            }
        } catch (java.lang.NullPointerException e) {
        }
    
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
      out.write("        <link rel=\"stylesheet\" href=\"../css/ingreso.css\"/>\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"../css/formas.css\"/>\r\n");
      out.write("        <link href=\"../css/stylish-portfolio.css\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("        <!-- Custom Fonts -->\r\n");
      out.write("        <link href=\"../font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("        <link href=\"http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("\r\n");
      out.write("        <!--Icon facelet-->\r\n");
      out.write("        <link rel=\"icon\" type=\"image/png\" href=\"../img/unlimtd.ico\"/>\r\n");
      out.write("        \r\n");
      out.write("        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->\r\n");
      out.write("        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\r\n");
      out.write("        <!--[if lt IE 9]>\r\n");
      out.write("            <script src=\"https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js\"></script>\r\n");
      out.write("            <script src=\"https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js\"></script>\r\n");
      out.write("        <![endif]-->\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("\r\n");
      out.write("        <!-- Navigation -->\r\n");
      out.write("        <a id=\"menu-toggle\" href=\"#\" class=\"btn btn-dark btn-lg toggle\"><i class=\"fa fa-bars\"></i></a>\r\n");
      out.write("        <nav id=\"sidebar-wrapper\">\r\n");
      out.write("            <ul class=\"sidebar-nav\">\r\n");
      out.write("                <a id=\"menu-close\" href=\"#\" class=\"btn btn-light btn-lg pull-right toggle\"><i class=\"fa fa-times\"></i></a>\r\n");
      out.write("                <li class=\"sidebar-brand\">\r\n");
      out.write("                    <a href=\"index.jsp\">ApSi</a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li>\r\n");
      out.write("                    <a href=\"#top\">Inicio</a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li>\r\n");
      out.write("                    <a href=\"#contacto\">Contacto</a>\r\n");
      out.write("                </li>\r\n");
      out.write("            </ul>\r\n");
      out.write("        </nav>  \r\n");
      out.write("\r\n");
      out.write("        <!-- Header -->\r\n");
      out.write("        <header id=\"top\" class=\"header\">\r\n");
      out.write("            <div class=\"text-vertical-center\">\r\n");
      out.write("                <h1>ApSi&reg;</h1>\r\n");
      out.write("                <h3>Asesorias y proyectos en cualquier momento</h3>\r\n");
      out.write("                <h4>\r\n");
      out.write("                    <center>\r\n");
      out.write("                        <form id=\"log\" name=\"log\" action=\"ingresar\" method=\"POST\">\r\n");
      out.write("                            <table border=\"0\" style=\"border-spacing: 10px 20px; border-collapse: separate;\">\r\n");
      out.write("                                <tbody>\r\n");
      out.write("                                    <tr style=\"text-align: right;\">\r\n");
      out.write("                                        <td>\r\n");
      out.write("                                            <input placeholder=\"Usuario\" type=\"text\" id=\"user\" class=\"form-in\" name=\"user\" required=\"true\"/>\r\n");
      out.write("                                        </td>\r\n");
      out.write("                                    </tr>\r\n");
      out.write("                                    <tr style=\"text-align: right;\">\r\n");
      out.write("                                        <td>\r\n");
      out.write("                                            <input placeholder=\"Contrase&ntilde;a\" type=\"password\" id=\"pass\" class=\"form-in\" name=\"pass\" required=\"true\"/>\r\n");
      out.write("                                        </td>\r\n");
      out.write("                                    </tr>\r\n");
      out.write("                                </tbody>\r\n");
      out.write("                            </table>\r\n");
      out.write("                            <table style=\"text-align: center;\">\r\n");
      out.write("                                <tr>\r\n");
      out.write("                                    <td><a href=\"opcionesDeAcceso.jsp\">&iquest;No puedes acceder?</a></td>\r\n");
      out.write("                                </tr>\r\n");
      out.write("                                <tr>\r\n");
      out.write("                                    <td>&iquest;No Tienes una cuenta? <b><a href=\"http://www.uniajc.edu.co\"> Registrate</a> </b></td>\r\n");
      out.write("                                </tr>\r\n");
      out.write("                                <tr>\r\n");
      out.write("                                    <td><br>\r\n");
      out.write("                                        <input name=\"operar\" class=\"btn btn-dark btn-lg\" type=\"submit\" value=\"Iniciar sesi&oacute;n\"/> <a href=\"index.jsp#about\" class=\"btn btn-dark btn-lg\">Aprende m&aacute;s</a>\r\n");
      out.write("                                    </td>\r\n");
      out.write("                                </tr>\r\n");
      out.write("                            </table>\r\n");
      out.write("                        </form>\r\n");
      out.write("                    </center>\r\n");
      out.write("                </h4>\r\n");
      out.write("            </div>\r\n");
      out.write("        </header>\r\n");
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
      out.write("        <!-- jQuery -->\r\n");
      out.write("        <script src=\"../js/Jquery/jquery-1.11.2.js\"></script>\r\n");
      out.write("\r\n");
      out.write("        <!-- Bootstrap Core JavaScript -->\r\n");
      out.write("        <script src=\"../js/bootstrap.js\"></script>\r\n");
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
      out.write("\r\n");
      out.write("</html>\r\n");
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
