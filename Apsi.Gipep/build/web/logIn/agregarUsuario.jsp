<%-- 
    Document   : agregarUsuario
    Created on : 28-jul-2015, 13:09:53
    Author     : usuario
--%>

<%@page import="Entity.Rol"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.TreeMap"%>
<%@page import="Entity.Correospersona"%>
<%@page import="Entity.Telefonos"%>
<%@page import="Dao.UsuarioImple"%>
<%@page import="Dao.UsuarioImple"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="Entity.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <%session = request.getSession();
        try {
            if (session.isNew() | session.getAttribute("user").equals(null)) {
                response.sendRedirect("index.jsp");
            }
        } catch (java.lang.NullPointerException e) {
            response.sendRedirect("index.jsp");
        }
    %>
    <%Persona p = (Persona) session.getAttribute("persona");%>
    <%

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
    %>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>ApSi.GiPep - Tus asesorias Faciles</title>

        <!-- Bootstrap Core CSS -->
        <link href="../css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link rel="stylesheet" href="../css/ingreso.css"/>
        <link rel="stylesheet" href="../css/formas.css"/>
        <link href="../css/stylish-portfolio.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="../font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
        <!--Icon facelet-->
        <link rel="icon" type="image/png" href="../img/unlimtd.ico"/>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>
        <!-- Navigation -->
        <a id="menu-toggle" href="#" class="btn btn-dark btn-lg toggle"><i class="fa fa-bars"></i></a>
        <nav id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <a id="menu-close" href="#" class="btn btn-light btn-lg pull-right toggle"><i class="fa fa-times"></i></a>
                <li class="sidebar-brand">
                    <a onclick="document.location = 'index.jsp'">ApSi</a>
                </li>
                <li>
                    <a href="#top"><i class="fa fa-home fa-stack-1x text-primary"></i>Inicio</a>
                </li>
                <%                    String page1 = "<li><a id=\"opt1\">Acceder</a>"
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
                %>


                <li>
                    <a href="#about"><i class="fa fa-bookmark fa-stack-1x text-primary"></i>Acerca de ApSi</a>
                </li>
                <li>
                    <a href="#services"><i class="fa fa-info fa-stack-1x text-primary"></i>Solicitar Registro</a>
                </li>

                <li>
                    <a href="#contacto"><i class="fa fa-comment fa-stack-1x text-primary"></i>Contacto</a>
                </li>
            </ul>
        </nav>
        <section id="about" class="about">
            <div class="alert alert-<%=request.getParameter("msgAlt") != null ? request.getParameter("msgAlt") : ""%>" role="alert">   
                <br><b><a style="color:red"><%= request.getParameter("msg") != null ? request.getParameter("msg") : ""%></a></b>
            </div>
            <div class="container">
                <div class="form-group">
                    <form method="post" action="crearUsuario" onsubmit="return validaIgualdad()">
                        <table class="table-condensed">
                            <thead>
                                <tr>
                            <h1><b><label> Informaci&oacute;n del nuevo usuario</label></b></h1>
                            </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <b><label class="form-control-static"> N&uacute;mero de identificaci&oacute;n </label></b>   
                                    </td>
                                    <td>
                                        <input type="text" id="id" name="id" class="form-control" placeholder="CC / TI / CE" required=""/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <b><label class="form-control-static"> Nombres: </label></b>
                                    </td>
                                    <td>
                                        <input type="text" id="name"  name="name" class="form-control" placeholder="Nombres" required=""/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <b><label class="form-control-static"> Apellidos: </label></b>
                                    </td>
                                    <td>
                                        <input type="text" id="lastName" name="lastName" class="form-control" placeholder="Apellidos" required=""/>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <hr>
                        <table class="table-condensed">
                            <thead>
                                <tr>
                            <b><label> Informaci&oacute;n de acceso</label></b>
                            </tr>
                            </thead>
                            <tbody>

                                <tr>
                                    <td>
                                        <label class="form-control-static"> Nombre de usuario: </label>     
                                    </td>
                                    <td>
                                        <input type="text" name="user" id="user" class="form-control" placeholder="Usuario" required="true"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="form-control-static"> Nueva Contraseña: </label>     
                                    </td>
                                    <td>
                                        <input type="password" name="pass1" id="pass1" class="form-control" placeholder="Contraseña" required="true"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <b><label class="form-control-static"> Confirmar Contraseña: </label></b>     
                                    </td>
                                    <td>
                                        <input type="password" name="pass2" id="pass2" class="form-control" placeholder="Contraseña" required="true"/>
                                    </td>
                                </tr>
                            <input type="hidden" name="pegeId" id="pegeId" value="<%=p.getUsuario().getPegeId()%>">
                            <input type="hidden" name="action" id="action" value="profile">
                            <tr>
                                <td>
                                    <input type="submit" class="btn btn-dark btn-lg" value="Guardar"/>
                                </td>
                                <td>
                                    <input type="reset" class="btn btn-danger btn-lg" value="Cancelar"/>
                                </td>
                            </tr>
                            </tbody>
                        </table>

                    </form>
                    <br>
                    <a href="http://www.uniajc.edu.co/index.php/component/content/article/14-sample-data-articles/197-politica-de-privacidad">
                        Pol&iacute;ticas de Privacidad
                    </a>
                </div>
            </div>
            <!-- /.container -->
        </section>
        <!-- Services -->
        <!-- The circle icons use Font Awesome's stacked icon classes. For more information, visit http://fontawesome.io/examples/ -->
        <section id="services" class="services bg-primary">
            <div class="container">
                <div class="row text-center">
                    <div class="col-lg-10 col-lg-offset-1">
                        <%
                            try {
                                if (!session.isNew() | !session.getAttribute("user").equals(null)) {
                                    out.print("<h2>Bienvenido</h2>");
                                } else {
                                    out.print("<h2>A&uacute;n sin registrarte?</h2>");
                                }
                            } catch (java.lang.NullPointerException e) {
                                out.print("<h2>A&uacute;n sin registrarte?</h2>");
                            }

                        %>


                        <hr class="small">
                        <div class="row">
                            <div class="col-md-3 col-sm-6">
                                <%                                    try {
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
                                %>

                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="service-item">
                                    <span class="fa-stack fa-4x">
                                        <i class="fa fa-circle fa-stack-2x"></i>
                                        <i class="fa fa-compass fa-stack-1x text-primary"></i>
                                    </span>
                                    <h4>
                                        <strong>Pide una asesoria</strong>
                                    </h4>
                                    <p>Capacitate, esfuerzate y trabaja con los mejores asesores. Tu tesis lo amerita.</p>
                                    <%
                                        try {
                                            if (!session.isNew() | !session.getAttribute("user").equals(null)) {
                                                out.print("<a href=\"#NewAsesoria\" class=\"btn btn-light\">Accede</a>");
                                            }
                                        } catch (java.lang.NullPointerException e) {
                                            out.print("<a href=\"logIn.jsp\" class=\"btn btn-light\">Accede</a>");
                                        }
                                    %>


                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <%
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
                                %>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="service-item">
                                    <%
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
                                    %>
                                </div>
                            </div>

                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.col-lg-10 -->
                    </div>
                    <!-- /.row -->
                </div>
                <!-- /.container -->
            </div>
        </section>
        <!-- Footer -->
        <footer>
            <div class="container" id="contacto">
                <div class="row">
                    <div class="col-lg-10 col-lg-offset-1 text-center">
                        <h4><strong>ApSi&reg;</strong>
                        </h4>
                        <p>Instituci&oacute;n Universitaria Antonio Jos&eacute; Camacho<br>Santiago de Cali, Colombia</p>
                        <ul class="list-unstyled">
                            <li><i class="fa fa-phone fa-fw"></i> (318) 265-7417</li>
                            <li><i class="fa fa-envelope-o fa-fw"></i>  <a href="mailto:unlimitedDreamsTi@gmail.com">unlimitedDreamsTi@gmail.com</a>
                            </li>
                        </ul>
                        <br>
                        <ul class="list-inline">
                            <li>
                                <a href="#"><i class="fa fa-facebook fa-fw fa-3x"></i></a>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-twitter fa-fw fa-3x"></i></a>
                            </li>
                        </ul>
                        <hr class="small">
                        <p class="text-muted">Powered By Unlimited Dreams <br>Copyright &copy; Unlimited Dreams, Todos los derechos reservados.</p>
                    </div>
                </div>
            </div>
        </footer>
        <!-- jQuery -->
        <script src="../js/Jquery/jquery-1.11.2.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="../js/bootstrap.js"></script>

        <script>
                        function validaIgualdad() {
                            var p1 = document.getElementById("pass1").value;
                            var p2 = document.getElementById("pass2").value;
                            var espacios = false;
                            var cont = 0;
                            while (!espacios && (cont < p1.length)) {
                                if (p1.charAt(cont) === " ")
                                    espacios = true;
                                cont++;
                            }
                            if (espacios) {
                                alert("La contraseña no puede contener espacios en blanco");
                                return false;
                            }
                            if (p1 !== p2) {
                                alert("Las contraseñas deben de coincidir");
                                return false;
                            } else {
                                return true;
                            }
                        }
        </script>

        <!--Script ocultar Divs-->
        <script>
            $(document).ready(function () {
                $("#ingresa").hide();
                $('#opt1').click(function () {
                    $("#ingresa").toggle("left", function () {
                    });
                });
            });</script>

        <!-- Custom Theme JavaScript -->
        <script>
            // Closes the sidebar menu
            $("#menu-close").click(function (e) {
                e.preventDefault();
                $("#sidebar-wrapper").toggleClass("active");
            });
            // Opens the sidebar menu
            $("#menu-toggle").click(function (e) {
                e.preventDefault();
                $("#sidebar-wrapper").toggleClass("active");
            });
            // Scrolls to the selected menu item on the page
            $(function () {
                $('a[href*=#]:not([href=#])').click(function () {
                    if (location.pathname.replace(/^\//, '') === this.pathname.replace(/^\//, '') || location.hostname === this.hostname) {

                        var target = $(this.hash);
                        target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
                        if (target.length) {
                            $('html,body').animate({
                                scrollTop: target.offset().top
                            }, 1000);
                            return false;
                        }
                    }
                });
            });
        </script>
    </body>
</html>
