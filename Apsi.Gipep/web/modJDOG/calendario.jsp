<%-- 
    Document   : calendario
    Created on : 28-jul-2015, 13:09:06
    Author     : usuario
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

        <div class="container">
            <section class="container-fluid">
                <div class="container-fluid">
                    <div class="row">                   
                        <h3><i class="fa fa-user"> </i><%= p.getNombres()%></h3> 
                    </div>
                </div>
            </section>
            <hr>
            <div id="calendar"></div>
        </div>

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
                                dayClick: function () {
                                    alert('a day has been clicked! \n'+ m + '-'+d+'-'+y);
                                },
                                editable: true,
                                events: [
                                    {
                                        title: 'All Day Event',
                                        start: new Date(y, m, 1)
                                    },
                                    {
                                        title: 'Long Event',
                                        start: new Date(y, m, d - 5),
                                        end: new Date(y, m, d - 2)
                                    },
                                    {
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
                                ]

                            });
                        })
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

        <script src="http://code.jquery.com/jquery-latest.js"></script>

    </body>
</html>
