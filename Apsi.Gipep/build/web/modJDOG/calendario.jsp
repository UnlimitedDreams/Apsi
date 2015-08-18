<%-- 
    Document   : calendario
    Created on : 28-jul-2015, 13:09:06
    Author     : Miguel Angel Lemos
--%>
<%@page import="Entity.Rol"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.TreeMap"%>
<%@page import="Entity.Correospersona"%>
<%@page import="Entity.Telefonos"%>
<%@page import="java.util.List"%>
<%@page import="dao.UsuarioImple"%>
<%@page import="Entity.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="es">
    <%session = request.getSession();
        try {
            if (session.isNew() | session.getAttribute("user").equals(null)) {
                response.sendRedirect("../main.html");
            }
        } catch (java.lang.NullPointerException e) {
            response.sendRedirect("../main.html");
        }
    %>
    <%Persona p = (Persona) session.getAttribute("persona");%>
    <%
        String rol = "";
        String idRol = "";
        TreeMap roles = new UsuarioImple().cargarRoles(p.getUsuario().getPegeId().toString());
        for (Iterator it = roles.entrySet().iterator(); it.hasNext();) {
            Map.Entry me = (Map.Entry) it.next();
            BigDecimal key = (BigDecimal) me.getKey();
            Rol value = (Rol) roles.get(key);
            rol = value.getNombre();
            idRol = value.getCodRol().toString();
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
        <link rel="stylesheet" href="../css/Calendar.css"/>
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
    <body>
        <!-- Navigation -->
        <a id="menu-toggle" href="#" class="btn btn-dark btn-lg toggle"><i class="fa fa-bars"></i></a>
        <nav id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <a id="menu-close" href="#" class="btn btn-light btn-lg pull-right toggle"><i class="fa fa-times"></i></a>
                <li class="sidebar-brand">
                    <a onclick="document.location = '../main.html'">ApSi</a>
                </li>
                <li>
                    <a href="../logIn/index.jsp"><i class="fa fa-home fa-stack-1x text-primary"></i>Inicio</a>
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
                                    + "<li><a href=\"../logIn/profile.jsp\">Mi perfil</a></li>"
                                    + "<li><a href=\"../logIn/mainMenu.jsp\">Mi actividad</a></li>"
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
                    <a href="#services"><i class="fa fa-info fa-stack-1x text-primary"></i>Solicitar Registro</a>
                </li>
                <li>
                    <a href="#contacto"><i class="fa fa-comment fa-stack-1x text-primary"></i>Contacto</a>
                </li>
            </ul>
        </nav>
        <div class="container">
            <section class="container-fluid">
                <div class="container-fluid">
                    <div class="row">                   
                        <h3><i class="fa fa-user"> </i><%=rol%></h3> 
                    </div>
                </div>
            </section>
            <hr>
            <div id="calendar"></div>
            <button Style="visibility: hidden" data-toggle="modal" 
                    data-target="#modalVentana1" value="Holi"
                    name="">Hola que tal </button>
        </div>
        <!-- MODALES -->
        <!-- MODAL 1 -->
        <div id="modalVentana1" class="modal fade " tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <form id="registrarAsesoria" name="registrarAsesoria" method="post">
                            <table class="table-condensed">
                                <thead>
                                    <tr>
                                        <td>
                                            <h3>Nueva Asesor&iacute;a | <a href="#" data-dismiss="modal" >Volver</a></h3> 
                                        </td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            Fecha:
                                        </td>
                                        <td>
                                            <input type="text" id="date" name="date" class="form-control" disabled="" value="" required=""/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Docente:
                                        </td>
                                        <td>
                                            <select name="docentes" id="docentes" class="form-control" 
                                                    required="" 
                                                    onchange="mostrarHora()"> 
                                                <option value="none">--Seleccione una opci&oacute;n--</option>
                                                <option value="1">TutorJava</option>
                                                <option value="2">TutorHTML</option>
                                                <option value="4">TutorPHP</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label id="horita" style="visibility: hidden">Horas disponibles:</label> 
                                        </td>
                                        <td>
                                            <select name="horasDisponibles" id="horasDisponibles" class="form-control" required="" style="visibility: hidden;">
                                                <option value="1">21 : 30</option>
                                                <option value="2">22 : 00</option>
                                                <option value="4">22 : 30</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Descripci&oacute;n:
                                        </td>
                                        <td>
                                            <textarea id="Descripcion" name="Descripcion" style="resize: none" class="form-control">
                                                
                                            </textarea>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <br>
                            <hr>
                            <br>
                            <input class="btn btn-dark" type="reset" data-dismiss="modal" value="Cerrar"> 
                            <input id="proceso" name="proceso" class="btn btn-dark" type="submit" value="Enviar">      
                        </form> 

                    </div>
                </div>
            </div>
        </div>
        <!-- Alertas -->
        <div class="alert alert-<%=request.getParameter("msgAlt") != null ? request.getParameter("msgAlt") : ""%>" role="alert">   
            <br>
            <b>
                <h2 style="text-align: center;">
                    <a style="color:red"><%= request.getParameter("msg") != null ? request.getParameter("msg") : ""%></a>
                </h2>
            </b>
        </div>

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
                                                out.print("<a href=\"../modJDOG/calendario.jsp\" class=\"btn btn-light\">Accede</a>");
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
                                                    + "                                    <a href=\"../logIn/profile.jsp\" class=\"btn btn-light\">Ver mi perfil</a>\n"
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
                                                        + "                                    <a href=\"../logIn/logIn.jsp\" class=\"btn btn-light\">Accede</a>\n"
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
        <script type="text/javascript" src="../js/Jquery/jquery-1.11.2.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script type="text/javascript" src="../js/bootstrap.js"></script>
        <!--Script ocultar Divs-->
        <script>
                                                        $.getScript('http://arshaw.com/js/fullcalendar-1.6.4/fullcalendar/fullcalendar.min.js', function () {

                                                            var date = new Date();
                                                            var d = date.getDate();
                                                            var m = date.getMonth();
                                                            var y = date.getFullYear();

                                                            $('#calendar').fullCalendar({
                                                                header: {
                                                                    left: 'prev,next today',
                                                                    center: 'title',
                                                                    right: 'month,agendaWeek,agendaDay'
                                                                },
                                                                lang: 'es',
                                                                dayClick: function (date) {
                                                                    if (date < new Date()) {
                                                                        alert('No puedes seleccionar fechas menores a la actual');
                                                                        location.reload();
                                                                    } else {
                                                                        $("Button").click();
                                                                        $("input[name=date]").val(date.getFullYear() + '-' + date.getMonth() + '-' + date.getUTCDate());
                                                                    }

                                                                },
                                                                editable: true,
                                                                events: [
                                                                    {
                                                                        title: 'Rumba',
                                                                        start: new Date('2015-08-01T22:00:00'),
                                                                    },
                                                                    {
                                                                        title: 'Fiesta',
                                                                        start: new Date('2015-08-01T22:00:00'),
                                                                    },
                                                                    {
                                                                        title: 'Long Event',
                                                                        start: new Date(y, m, d - 5),
                                                                        end: new Date(y, m, d - 2)
                                                                    }]
                                                                        /*{
                                                                         id: 999,
                                                                         title: 'Repeating Event',
                                                                         start: new Date(y, m, d - 3, 16, 0),
                                                                         allDay: false
                                                                         },
                                                                         {
                                                                         id: 999,
                                                                         title: 'Repeating Event',
                                                                         start: new Date(y, m, d + 4, 16, 0),
                                                                         allDay: false
                                                                         },
                                                                         {
                                                                         title: 'Meeting',
                                                                         start: new Date(y, m, d, 10, 30),
                                                                         allDay: false
                                                                         },
                                                                         {
                                                                         title: 'Lunch',
                                                                         start: new Date(y, m, d, 12, 0),
                                                                         end: new Date(y, m, d, 14, 0),
                                                                         allDay: false
                                                                         },
                                                                         {
                                                                         title: 'Birthday Party',
                                                                         start: new Date(y, m, d + 1, 19, 0),
                                                                         end: new Date(y, m, d + 1, 22, 30),
                                                                         allDay: false
                                                                         },
                                                                         {
                                                                         title: 'Click for Google',
                                                                         start: new Date(y, m, 28),
                                                                         end: new Date(y, m, 29),
                                                                         url: 'http://google.com/'
                                                                         }
                                                                         ]*/

                                                            });
                                                        })
        </script>
        <script>
            function mostrarHora() {
                if (document.getElementById("docentes").value === 'none') {
                    document.getElementById('horasDisponibles').style.visibility = 'hidden'
                    document.getElementById('horita').style.visibility = 'hidden'
                } else {
                    document.getElementById('horasDisponibles').style.visibility = 'visible'
                    document.getElementById('horita').style.visibility = 'visible'
                }
            }
        </script>
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
            });</script>
    </body>
</html>
