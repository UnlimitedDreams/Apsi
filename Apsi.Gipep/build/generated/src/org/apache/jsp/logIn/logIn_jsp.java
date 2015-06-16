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

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("        <meta name=\"description\" content=\"\">\n");
      out.write("        <meta name=\"author\" content=\"\">\n");
      out.write("        <title>Apsi.GiPep - Tus asesorias Faciles</title>\n");
      out.write("\n");
      out.write("        <!-- Bootstrap Core CSS -->\n");
      out.write("        <link href=\"../css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("        <!-- Custom CSS -->\n");
      out.write("        <link rel=\"stylesheet\" href=\"../css/ingreso.css\"/>\n");
      out.write("        <link rel=\"stylesheet\" href=\"../css/formas.css\"/>\n");
      out.write("        <link href=\"../css/stylish-portfolio.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("        <!-- Custom Fonts -->\n");
      out.write("        <link href=\"../font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("        <link href=\"http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("\n");
      out.write("        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->\n");
      out.write("        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\n");
      out.write("        <!--[if lt IE 9]>\n");
      out.write("            <script src=\"https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js\"></script>\n");
      out.write("            <script src=\"https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js\"></script>\n");
      out.write("        <![endif]-->\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        <!-- Navigation -->\n");
      out.write("        <a id=\"menu-toggle\" href=\"#\" class=\"btn btn-dark btn-lg toggle\"><i class=\"fa fa-bars\"></i></a>\n");
      out.write("        <nav id=\"sidebar-wrapper\">\n");
      out.write("            <ul class=\"sidebar-nav\">\n");
      out.write("                <a id=\"menu-close\" href=\"#\" class=\"btn btn-light btn-lg pull-right toggle\"><i class=\"fa fa-times\"></i></a>\n");
      out.write("                <li class=\"sidebar-brand\">\n");
      out.write("                    <a href=\"index.jsp\">Apsi</a>\n");
      out.write("                </li>\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"index.jsp\">Inicio</a>\n");
      out.write("                </li>\n");
      out.write("                <li>\n");
      out.write("                    <a id=\"opt1\">Acceder</a>\n");
      out.write("                    <div id=\"ingresa\">\n");
      out.write("                        <form name=\"log\" action=\"ingresar\" method=\"POST\">\n");
      out.write("                            <table style=\"border-spacing: 6px 12px; border-collapse: separate\">\n");
      out.write("                                <tr style=\"text-align: right;\">\n");
      out.write("                                    <td></td>\n");
      out.write("                                    <td>\n");
      out.write("                                        <input placeholder=\"Usuario\" type=\"text\" id=\"user\" class=\"form-in\" name=\"user\" required=\"\" />\n");
      out.write("                                    </td>\n");
      out.write("                                </tr>\n");
      out.write("                                <tr style=\"text-align: right;\">\n");
      out.write("                                    <td>\n");
      out.write("                                    </td>\n");
      out.write("                                    <td>\n");
      out.write("                                        <input placeholder=\"Constrase&ntilde;a\" type=\"password\" id=\"pass\" class=\"form-in\" name=\"pass\" required=\"\" />\n");
      out.write("                                    </td>\n");
      out.write("                                </tr>\n");
      out.write("                                <tr>\n");
      out.write("                                    <td>\n");
      out.write("                                    </td>\n");
      out.write("                                    <td>\n");
      out.write("                                        <input type=\"submit\" class=\"btn-log\" value=\"Iniciar sesi&oacute;n\"/>\n");
      out.write("                                    </td>\n");
      out.write("                                </tr>\n");
      out.write("                            </table>\n");
      out.write("                            <b><a href=\"opcionesDeAcceso.jsp\">No puedes acceder?</a></b>\n");
      out.write("                            <b><a href=\"http://www.uniajc.edu.co\">Registrate</a></b>\n");
      out.write("                        </form>\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                </li>\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"#contacto\">Contacto</a>\n");
      out.write("                </li>\n");
      out.write("            </ul>\n");
      out.write("        </nav>  \n");
      out.write("\n");
      out.write("        <!-- Header -->\n");
      out.write("        <header id=\"top\" class=\"header\">\n");
      out.write("            <div class=\"text-vertical-center\">\n");
      out.write("                <h1>Apsi</h1>\n");
      out.write("                <h3>Asesorias y proyectos en cualquier momento</h3>\n");
      out.write("                <h4><center>\n");
      out.write("                        <form name=\"log\" action=\"ingresar\" method=\"POST\">\n");
      out.write("                            <table border=\"0\" style=\"border-spacing: 10px 20px; border-collapse: separate;\">\n");
      out.write("                                <tbody>\n");
      out.write("                                    <tr style=\"text-align: right;\">\n");
      out.write("                                        <td>\n");
      out.write("                                            <input placeholder=\"Usuario\" type=\"text\" id=\"user\" class=\"form-in\" name=\"user\" required=\"\" />\n");
      out.write("                                        </td>\n");
      out.write("                                    </tr>\n");
      out.write("                                    <tr style=\"text-align: right;\">\n");
      out.write("                                        <td>\n");
      out.write("                                            <input placeholder=\"Contrase&ntilde;a\" type=\"password\" id=\"pass\" class=\"form-in\" name=\"pass\" required=\"\"/>\n");
      out.write("                                        </td>\n");
      out.write("                                    </tr>\n");
      out.write("                                </tbody>\n");
      out.write("                            </table>\n");
      out.write("                            <table style=\"text-align: center;\">\n");
      out.write("                                <tr>\n");
      out.write("                                    <td><a href=\"opcionesDeAcceso.jsp\">&iquest;No puedes acceder?</a></td>\n");
      out.write("                                </tr>\n");
      out.write("                                <tr>\n");
      out.write("                                    <td>&iquest;No Tienes una cuenta? <b><a href=\"http://www.uniajc.edu.co\"> Registrate</a> </b></td>\n");
      out.write("                                </tr>\n");
      out.write("                            </table>\n");
      out.write("                        </form>\n");
      out.write("                    </center></h4>\n");
      out.write("                <a  class=\"btn btn-dark btn-lg\" value=\"Iniciar sesi&oacute;n\">Iniciar sesi&oacute;n</a>\n");
      out.write("                <a href=\"#about\" class=\"btn btn-dark btn-lg\">Aprende m&aacute;s</a>\n");
      out.write("            </div>\n");
      out.write("        </header>\n");
      out.write("        <!-- Footer -->\n");
      out.write("        <footer>\n");
      out.write("            <div class=\"container\" id=\"contacto\">\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <div class=\"col-lg-10 col-lg-offset-1 text-center\">\n");
      out.write("                        <h4><strong>Apsi</strong>\n");
      out.write("                        </h4>\n");
      out.write("                        <p>Instituci&oacute;n Universitaria Antonio Jos&eacute; Camacho<br>Santiago de Cali, Colombia</p>\n");
      out.write("                        <ul class=\"list-unstyled\">\n");
      out.write("                            <li><i class=\"fa fa-phone fa-fw\"></i> (318) 265-7417</li>\n");
      out.write("                            <li><i class=\"fa fa-envelope-o fa-fw\"></i>  <a href=\"mailto:unlimitedDreamsTi@gmail.com\">unlimitedDreamsTi@gmail.com</a>\n");
      out.write("                            </li>\n");
      out.write("                        </ul>\n");
      out.write("                        <br>\n");
      out.write("                        <ul class=\"list-inline\">\n");
      out.write("                            <li>\n");
      out.write("                                <a href=\"#\"><i class=\"fa fa-facebook fa-fw fa-3x\"></i></a>\n");
      out.write("                            </li>\n");
      out.write("                            <li>\n");
      out.write("                                <a href=\"#\"><i class=\"fa fa-twitter fa-fw fa-3x\"></i></a>\n");
      out.write("                            </li>\n");
      out.write("                            <li>\n");
      out.write("                                <a href=\"#\"><i class=\"fa fa-dribbble fa-fw fa-3x\"></i></a>\n");
      out.write("                            </li>\n");
      out.write("                        </ul>\n");
      out.write("                        <hr class=\"small\">\n");
      out.write("                        <p class=\"text-muted\">Powered By Unlimited Dreams <br>Copyright &copy; Unlimited Dreams, Todos los derechos reservados.</p>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </footer>\n");
      out.write("        <!-- jQuery -->\n");
      out.write("        <script src=\"../js/Jquery/jquery-1.11.2.js\"></script>\n");
      out.write("\n");
      out.write("        <!-- Bootstrap Core JavaScript -->\n");
      out.write("        <script src=\"../js/bootstrap.js\"></script>\n");
      out.write("\n");
      out.write("        <!--Script ocultar Divs-->\n");
      out.write("        <script>\n");
      out.write("            $(document).ready(function () {\n");
      out.write("                $(\"#ingresa\").hide();\n");
      out.write("                $('#opt1').click(function () {\n");
      out.write("                    $(\"#ingresa\").toggle(\"left\", function () {\n");
      out.write("                    });\n");
      out.write("                });\n");
      out.write("            });\n");
      out.write("        </script>\n");
      out.write("\n");
      out.write("        <!-- Custom Theme JavaScript -->\n");
      out.write("        <script>\n");
      out.write("            // Closes the sidebar menu\n");
      out.write("            $(\"#menu-close\").click(function (e) {\n");
      out.write("                e.preventDefault();\n");
      out.write("                $(\"#sidebar-wrapper\").toggleClass(\"active\");\n");
      out.write("            });\n");
      out.write("\n");
      out.write("            // Opens the sidebar menu\n");
      out.write("            $(\"#menu-toggle\").click(function (e) {\n");
      out.write("                e.preventDefault();\n");
      out.write("                $(\"#sidebar-wrapper\").toggleClass(\"active\");\n");
      out.write("            });\n");
      out.write("\n");
      out.write("            // Scrolls to the selected menu item on the page\n");
      out.write("            $(function () {\n");
      out.write("                $('a[href*=#]:not([href=#])').click(function () {\n");
      out.write("                    if (location.pathname.replace(/^\\//, '') === this.pathname.replace(/^\\//, '') || location.hostname === this.hostname) {\n");
      out.write("\n");
      out.write("                        var target = $(this.hash);\n");
      out.write("                        target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');\n");
      out.write("                        if (target.length) {\n");
      out.write("                            $('html,body').animate({\n");
      out.write("                                scrollTop: target.offset().top\n");
      out.write("                            }, 1000);\n");
      out.write("                            return false;\n");
      out.write("                        }\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            });\n");
      out.write("        </script>\n");
      out.write("    </body>\n");
      out.write("\n");
      out.write("</html>\n");
      out.write("\n");
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
