package org.apache.jsp.logIn;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Entity.Rol;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Iterator;
import java.util.TreeMap;
import Entity.Correospersona;
import Entity.Telefonos;
import Dao.UsuarioImple;
import Dao.UsuarioImple;
import java.util.List;
import java.util.List;
import Entity.Persona;

public final class agregarUsuario_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"es\">\n");
      out.write("    ");
session = request.getSession();
        try {
            if (session.isNew() | session.getAttribute("user").equals(null)) {
                response.sendRedirect("index.jsp");
            }
        } catch (java.lang.NullPointerException e) {
            response.sendRedirect("index.jsp");
        }
    
      out.write("\n");
      out.write("    ");
Persona p = (Persona) session.getAttribute("persona");
      out.write("\n");
      out.write("    ");


        TreeMap roles = new UsuarioImple().cargarRoles(p.getUsuario().getPegeId().toString());
        boolean accede = false;
        for (Iterator it = roles.entrySet().iterator(); it.hasNext();) {
            Map.Entry me = (Map.Entry) it.next();
            BigDecimal key = (BigDecimal) me.getKey();
            Rol value = (Rol) roles.get(key);
            if (value.getNombre().equals("SuperUsuario")
                    | value.getNombre().equals("Director")) {
                accede = true;
            }
        }
        if (!accede) {
            response.sendRedirect("index.jsp");
        }
    
      out.write("\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("        <meta name=\"description\" content=\"\">\n");
      out.write("        <meta name=\"author\" content=\"\">\n");
      out.write("        <title>ApSi.GiPep - Tus asesorias Faciles</title>\n");
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
      out.write("        <!--Icon facelet-->\n");
      out.write("        <link rel=\"icon\" type=\"image/png\" href=\"../img/unlimtd.ico\"/>\n");
      out.write("        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->\n");
      out.write("        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\n");
      out.write("        <!--[if lt IE 9]>\n");
      out.write("            <script src=\"https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js\"></script>\n");
      out.write("            <script src=\"https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js\"></script>\n");
      out.write("        <![endif]-->\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("        <!-- Navigation -->\n");
      out.write("        <a id=\"menu-toggle\" href=\"#\" class=\"btn btn-dark btn-lg toggle\"><i class=\"fa fa-bars\"></i></a>\n");
      out.write("        <nav id=\"sidebar-wrapper\">\n");
      out.write("            <ul class=\"sidebar-nav\">\n");
      out.write("                <a id=\"menu-close\" href=\"#\" class=\"btn btn-light btn-lg pull-right toggle\"><i class=\"fa fa-times\"></i></a>\n");
      out.write("                <li class=\"sidebar-brand\">\n");
      out.write("                    <a onclick=\"document.location = 'index.jsp'\">ApSi</a>\n");
      out.write("                </li>\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"#top\"><i class=\"fa fa-home fa-stack-1x text-primary\"></i>Inicio</a>\n");
      out.write("                </li>\n");
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
                                    + "<li><a href=\"#profile\">Mi perfil</a></li>"
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
                
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"#about\"><i class=\"fa fa-bookmark fa-stack-1x text-primary\"></i>Acerca de ApSi</a>\n");
      out.write("                </li>\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"#services\"><i class=\"fa fa-info fa-stack-1x text-primary\"></i>Solicitar Registro</a>\n");
      out.write("                </li>\n");
      out.write("\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"#contacto\"><i class=\"fa fa-comment fa-stack-1x text-primary\"></i>Contacto</a>\n");
      out.write("                </li>\n");
      out.write("            </ul>\n");
      out.write("        </nav>\n");
      out.write("        <section id=\"about\" class=\"about\">\n");
      out.write("            <div class=\"alert alert-");
      out.print(request.getParameter("msgAlt") != null ? request.getParameter("msgAlt") : "");
      out.write("\" role=\"alert\">   \n");
      out.write("                <br><b><a style=\"color:red\">");
      out.print( request.getParameter("msg") != null ? request.getParameter("msg") : "");
      out.write("</a></b>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <form method=\"post\" action=\"crearUsuario\" onsubmit=\"return validaIgualdad()\">\n");
      out.write("                        <table class=\"table-condensed\">\n");
      out.write("                            <thead>\n");
      out.write("                                <tr>\n");
      out.write("                            <h1><b><label> Informaci&oacute;n del nuevo usuario</label></b></h1>\n");
      out.write("                            </tr>\n");
      out.write("                            </thead>\n");
      out.write("                            <tbody>\n");
      out.write("                                <tr>\n");
      out.write("                                    <td>\n");
      out.write("                                        <b><label class=\"form-control-static\"> N&uacute;mero de identificaci&oacute;n </label></b>   \n");
      out.write("                                    </td>\n");
      out.write("                                    <td>\n");
      out.write("                                        <input type=\"text\" id=\"id\" name=\"id\" class=\"form-control\" placeholder=\"CC / TI / CE\" required=\"\"/>\n");
      out.write("                                    </td>\n");
      out.write("                                </tr>\n");
      out.write("                                <tr>\n");
      out.write("                                    <td>\n");
      out.write("                                        <b><label class=\"form-control-static\"> Nombres: </label></b>\n");
      out.write("                                    </td>\n");
      out.write("                                    <td>\n");
      out.write("                                        <input type=\"text\" id=\"name\"  name=\"name\" class=\"form-control\" placeholder=\"Nombres\" required=\"\"/>\n");
      out.write("                                    </td>\n");
      out.write("                                </tr>\n");
      out.write("                                <tr>\n");
      out.write("                                    <td>\n");
      out.write("                                        <b><label class=\"form-control-static\"> Apellidos: </label></b>\n");
      out.write("                                    </td>\n");
      out.write("                                    <td>\n");
      out.write("                                        <input type=\"text\" id=\"lastName\" name=\"lastName\" class=\"form-control\" placeholder=\"Apellidos\" required=\"\"/>\n");
      out.write("                                    </td>\n");
      out.write("                                </tr>\n");
      out.write("                            </tbody>\n");
      out.write("                        </table>\n");
      out.write("                        <hr>\n");
      out.write("                        <table class=\"table-condensed\">\n");
      out.write("                            <thead>\n");
      out.write("                                <tr>\n");
      out.write("                            <b><label> Informaci&oacute;n de acceso</label></b>\n");
      out.write("                            </tr>\n");
      out.write("                            </thead>\n");
      out.write("                            <tbody>\n");
      out.write("\n");
      out.write("                                <tr>\n");
      out.write("                                    <td>\n");
      out.write("                                        <label class=\"form-control-static\"> Nombre de usuario: </label>     \n");
      out.write("                                    </td>\n");
      out.write("                                    <td>\n");
      out.write("                                        <input type=\"text\" name=\"user\" id=\"user\" class=\"form-control\" placeholder=\"Usuario\" required=\"true\"/>\n");
      out.write("                                    </td>\n");
      out.write("                                </tr>\n");
      out.write("                                <tr>\n");
      out.write("                                    <td>\n");
      out.write("                                        <label class=\"form-control-static\"> Nueva Contraseña: </label>     \n");
      out.write("                                    </td>\n");
      out.write("                                    <td>\n");
      out.write("                                        <input type=\"password\" name=\"pass1\" id=\"pass1\" class=\"form-control\" placeholder=\"Contraseña\" required=\"true\"/>\n");
      out.write("                                    </td>\n");
      out.write("                                </tr>\n");
      out.write("                                <tr>\n");
      out.write("                                    <td>\n");
      out.write("                                        <b><label class=\"form-control-static\"> Confirmar Contraseña: </label></b>     \n");
      out.write("                                    </td>\n");
      out.write("                                    <td>\n");
      out.write("                                        <input type=\"password\" name=\"pass2\" id=\"pass2\" class=\"form-control\" placeholder=\"Contraseña\" required=\"true\"/>\n");
      out.write("                                    </td>\n");
      out.write("                                </tr>\n");
      out.write("                            <input type=\"hidden\" name=\"pegeId\" id=\"pegeId\" value=\"");
      out.print(p.getUsuario().getPegeId());
      out.write("\">\n");
      out.write("                            <input type=\"hidden\" name=\"action\" id=\"action\" value=\"profile\">\n");
      out.write("                            <tr>\n");
      out.write("                                <td>\n");
      out.write("                                    <input type=\"submit\" class=\"btn btn-dark btn-lg\" value=\"Guardar\"/>\n");
      out.write("                                </td>\n");
      out.write("                                <td>\n");
      out.write("                                    <input type=\"reset\" class=\"btn btn-danger btn-lg\" value=\"Cancelar\"/>\n");
      out.write("                                </td>\n");
      out.write("                            </tr>\n");
      out.write("                            </tbody>\n");
      out.write("                        </table>\n");
      out.write("\n");
      out.write("                    </form>\n");
      out.write("                    <br>\n");
      out.write("                    <a href=\"http://www.uniajc.edu.co/index.php/component/content/article/14-sample-data-articles/197-politica-de-privacidad\">\n");
      out.write("                        Pol&iacute;ticas de Privacidad\n");
      out.write("                    </a>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <!-- /.container -->\n");
      out.write("        </section>\n");
      out.write("        <!-- Services -->\n");
      out.write("        <!-- The circle icons use Font Awesome's stacked icon classes. For more information, visit http://fontawesome.io/examples/ -->\n");
      out.write("        <section id=\"services\" class=\"services bg-primary\">\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <div class=\"row text-center\">\n");
      out.write("                    <div class=\"col-lg-10 col-lg-offset-1\">\n");
      out.write("                        ");

                            try {
                                if (!session.isNew() | !session.getAttribute("user").equals(null)) {
                                    out.print("<h2>Bienvenido</h2>");
                                } else {
                                    out.print("<h2>A&uacute;n sin registrarte?</h2>");
                                }
                            } catch (java.lang.NullPointerException e) {
                                out.print("<h2>A&uacute;n sin registrarte?</h2>");
                            }

                        
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                        <hr class=\"small\">\n");
      out.write("                        <div class=\"row\">\n");
      out.write("                            <div class=\"col-md-3 col-sm-6\">\n");
      out.write("                                ");
                                    try {
                                        if (!session.isNew() | !session.getAttribute("user").equals(null)) {
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
                                
      out.write("\n");
      out.write("\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"col-md-3 col-sm-6\">\n");
      out.write("                                <div class=\"service-item\">\n");
      out.write("                                    <span class=\"fa-stack fa-4x\">\n");
      out.write("                                        <i class=\"fa fa-circle fa-stack-2x\"></i>\n");
      out.write("                                        <i class=\"fa fa-compass fa-stack-1x text-primary\"></i>\n");
      out.write("                                    </span>\n");
      out.write("                                    <h4>\n");
      out.write("                                        <strong>Pide una asesoria</strong>\n");
      out.write("                                    </h4>\n");
      out.write("                                    <p>Capacitate, esfuerzate y trabaja con los mejores asesores. Tu tesis lo amerita.</p>\n");
      out.write("                                    ");

                                        try {
                                            if (!session.isNew() | !session.getAttribute("user").equals(null)) {
                                                out.print("<a href=\"#NewAsesoria\" class=\"btn btn-light\">Accede</a>");
                                            }
                                        } catch (java.lang.NullPointerException e) {
                                            out.print("<a href=\"logIn.jsp\" class=\"btn btn-light\">Accede</a>");
                                        }
                                    
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"col-md-3 col-sm-6\">\n");
      out.write("                                ");

                                    try {
                                        if (!session.isNew() | !session.getAttribute("user").equals(null)) {
                                            out.print("<div class=\"service-item\">\n"
                                                    + "                                    <span class=\"fa-stack fa-4x\">\n"
                                                    + "                                        <i class=\"fa fa-circle fa-stack-2x\"></i>\n"
                                                    + "                                        <i class=\"fa fa-user fa-stack-1x text-primary\"></i>\n"
                                                    + "                                    </span>\n"
                                                    + "                                    <h4>\n"
                                                    + "                                        <strong>Perfil</strong>\n"
                                                    + "                                    </h4>\n"
                                                    + "                                    <p>Mant&eacute;n tu perfil actualizado, esto ayuda a que llegue tus correos a tiempo y al lugar indicado.</p>\n"
                                                    + "                                    <a href=\"#profile\" class=\"btn btn-light\">Ver mi perfil</a>\n"
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
                                
      out.write("\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"col-md-3 col-sm-6\">\n");
      out.write("                                <div class=\"service-item\">\n");
      out.write("                                    ");

                                        try {
                                            if (!session.isNew() | !session.getAttribute("user").equals(null)) {
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
                                    
      out.write("\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("\n");
      out.write("                            <!-- /.row (nested) -->\n");
      out.write("                        </div>\n");
      out.write("                        <!-- /.col-lg-10 -->\n");
      out.write("                    </div>\n");
      out.write("                    <!-- /.row -->\n");
      out.write("                </div>\n");
      out.write("                <!-- /.container -->\n");
      out.write("            </div>\n");
      out.write("        </section>\n");
      out.write("        <!-- Footer -->\n");
      out.write("        <footer>\n");
      out.write("            <div class=\"container\" id=\"contacto\">\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <div class=\"col-lg-10 col-lg-offset-1 text-center\">\n");
      out.write("                        <h4><strong>ApSi&reg;</strong>\n");
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
      out.write("        <script>\n");
      out.write("                        function validaIgualdad() {\n");
      out.write("                            var p1 = document.getElementById(\"pass1\").value;\n");
      out.write("                            var p2 = document.getElementById(\"pass2\").value;\n");
      out.write("                            var espacios = false;\n");
      out.write("                            var cont = 0;\n");
      out.write("                            while (!espacios && (cont < p1.length)) {\n");
      out.write("                                if (p1.charAt(cont) === \" \")\n");
      out.write("                                    espacios = true;\n");
      out.write("                                cont++;\n");
      out.write("                            }\n");
      out.write("                            if (espacios) {\n");
      out.write("                                alert(\"La contraseña no puede contener espacios en blanco\");\n");
      out.write("                                return false;\n");
      out.write("                            }\n");
      out.write("                            if (p1 !== p2) {\n");
      out.write("                                alert(\"Las contraseñas deben de coincidir\");\n");
      out.write("                                return false;\n");
      out.write("                            } else {\n");
      out.write("                                return true;\n");
      out.write("                            }\n");
      out.write("                        }\n");
      out.write("        </script>\n");
      out.write("\n");
      out.write("        <!--Script ocultar Divs-->\n");
      out.write("        <script>\n");
      out.write("            $(document).ready(function () {\n");
      out.write("                $(\"#ingresa\").hide();\n");
      out.write("                $('#opt1').click(function () {\n");
      out.write("                    $(\"#ingresa\").toggle(\"left\", function () {\n");
      out.write("                    });\n");
      out.write("                });\n");
      out.write("            });</script>\n");
      out.write("\n");
      out.write("        <!-- Custom Theme JavaScript -->\n");
      out.write("        <script>\n");
      out.write("            // Closes the sidebar menu\n");
      out.write("            $(\"#menu-close\").click(function (e) {\n");
      out.write("                e.preventDefault();\n");
      out.write("                $(\"#sidebar-wrapper\").toggleClass(\"active\");\n");
      out.write("            });\n");
      out.write("            // Opens the sidebar menu\n");
      out.write("            $(\"#menu-toggle\").click(function (e) {\n");
      out.write("                e.preventDefault();\n");
      out.write("                $(\"#sidebar-wrapper\").toggleClass(\"active\");\n");
      out.write("            });\n");
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
      out.write("</html>\n");
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
