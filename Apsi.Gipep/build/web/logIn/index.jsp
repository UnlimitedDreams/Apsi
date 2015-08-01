<%-- 
    Document   : opcionesDeAcceso
    Created on : 16-jun-2015, 10:02:23
    Author     : Miguel Angel Lemos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<%HttpSession sesion = request.getSession();%>
<!DOCTYPE html>
<html lang="es" >

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
        <link href="../css/stylish-portfolio.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="../font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

        <!--Icon facelet-->
        <link rel="icon" type="image/png" href="../img/apsi.ico"/>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>
<%session = request.getSession();
        try {
            if (!session.isNew() | !session.getAttribute("user").equals(null)) {
                response.sendRedirect("../modJDCJJS/Menu_Admon2.xhtml");
            }
        } catch (java.lang.NullPointerException e) {
        }
    %>
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

        <!-- Header -->
        <header id="top" class="header">
            <div class="text-vertical-center">
                <h1><img src="../img/Logo.png"/>&reg;</h1>
                <h3>Asesorias y proyectos en cualquier momento</h3>
                <br>
                <a href="#about" class="btn btn-dark btn-lg">Aprende m&aacute;s sobre ApSi.</a>
                <div class="alert alert-<%=request.getParameter("msgAlt") != null ? request.getParameter("msgAlt") : ""%>" role="alert">   
                    <br><b><a style="color:red"><%= request.getParameter("msg") != null ? request.getParameter("msg") : ""%></a></b>
                </div>

            </div>
        </header>

        <!-- About -->
        <section id="about" class="about">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2>Un nuevo concepto, la misma realidad</h2>
                        <p>Sencillo y r&aacute;pido, sin complicaciones.</p>
                        <p class="lead">&quot;Las m&aacute;quinas no son nada 
                            sin una mente humana que las opere, los cuerpos 
                            humanos no pueden hacer lograr la perfecci&oacute;n 
                            sin una m&aacute;quina que ayude en las labores 
                            m&aacute;s arduas e imposibles para la humanidad&quot;.
                            <br>
                            <label>- Unlimited Dreams</label></p>
                    </div>
                </div>
                <!-- /.row -->
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
                                if (!sesion.isNew() | !session.getAttribute("user").equals(null)) {
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
                                            if (!sesion.isNew() | !session.getAttribute("user").equals(null)) {
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
                                %>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="service-item">
                                    <%
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
                        <h4><strong><img src="../img/LogoMin.png"/>&reg;</strong>
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

        <!--Script ocultar Divs-->
        <script>
                        $(document).ready(function () {
                            $("#ingresa").hide();
                            $('#opt1').click(function () {
                                $("#ingresa").toggle("left", function () {
                                });
                            });
                        });
        </script>

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
