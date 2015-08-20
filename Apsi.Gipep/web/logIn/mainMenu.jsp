<%-- 
    Document   : mainMenu
    Created on : 24-jul-2015, 7:49:04
    Author     : Miguel Angel Lemos
--%>

<%@page import="Entity.Revisiones"%>
<%@page import="Entity.Proyectos"%>
<%@page import="dao.proyectoHelper"%>
<%@page import="java.util.List"%>
<%@page import="Entity.Actividades"%>
<%@page import="Entity.Rol"%>
<%@page import="java.util.Map"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.TreeMap"%>
<%@page import="dao.UsuarioImple"%>
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
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta name="description" content="">
        <meta name="author" content="">
        <title>ApSi.GiPep - Menú Principal</title>
        <!-- Bootstrap Core CSS -->
        <link href="../css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link rel="stylesheet" href="../css/styles.css" />
        <link href="../css/stylish-portfolio.css" rel="stylesheet">
        <!-- Link para panel menu-->
        <link href="../css/style.css" rel="stylesheet"/>
        <!-- Custom Fonts -->
        <link href="../font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="//cdnjs.cloudflare.com/ajax/libs/animate.css/3.1.1/animate.min.css" rel="stylesheet" />
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
        <!-- Navigation -->
        <a id="menu-toggle" href="#" class="btn btn-dark btn-lg toggle"><i class="fa fa-bars"></i></a>
        <nav id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <a id="menu-close" href="#" class="btn btn-light btn-lg pull-right toggle"><i class="fa fa-times"></i></a>
                <li class="sidebar-brand">
                    <a href="index.jsp">ApSi</a>
                </li>
                <li>
                    <a href="index.jsp"><i class="fa fa-home fa-stack-1x text-primary"></i>Inicio</a>
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
                %>
                <li>
                    <a href="../modCT_CA/SubirDoc.xhtml"><i class="fa fa-folder-o fa-stack-1x text-primary"></i>Archivo</a>
                </li>
                <li>
                    <a href="profile.jsp"><i class="fa fa-cog fa-stack-1x text-primary"></i>Opciones</a>
                </li>
                <li>
                    <a href="#contacto"><i class="fa fa-comment fa-stack-1x text-primary"></i>Contacto</a>
                </li>

            </ul>
        </nav> 
        <header class="container-fluid" id="top">
            <div class="container-fluid">
                <div class="row">                   
                    <h3><i class="fa fa-tag"></i> <%
                        String rol = "";
                        String idRol = "";
                        TreeMap roles = new UsuarioImple().cargarRoles(p.getUsuario().getPegeId().toString());
                        for (Iterator it = roles.entrySet().iterator(); it.hasNext();) {
                            Map.Entry me = (Map.Entry) it.next();
                            BigDecimal key = (BigDecimal) me.getKey();
                            Rol value = (Rol) roles.get(key);
                            rol = value.getNombre();
                            idRol = value.getCodRol().toString();
                            out.println(value.getNombre());
                        }
                        %>
                    </h3> 
                    <hr>
                </div>
            </div>
        </header>

        <section class="container-fluid" id="section2">
            <div class="row">
                <div class="col-sm-3">
                    <aside>
                        <div id="sidebar"  class="nav-collapse  ">
                            <!-- sidebar menu start-->
                            <ul class="sidebar-menu">                
                                <li class="active">
                                    <a class="" href="#">
                                        <i class="icon_house_alt"></i>
                                        <span>Opciones de <%=rol%></span>
                                    </a>
                                </li>
                                <li class="sub-menu">
                                    <a href="javascript:;" class="">
                                        <i class="fa fa-meanpath"></i>
                                        <span>Actividades</span>
                                        <span class="menu-arrow arrow_carrot-right"></span>
                                    </a>
                                    <ul class="sub">
                                        <%
                                            TreeMap x = new UsuarioImple().cargarActividades(idRol);
                                            for (Iterator it = x.entrySet().iterator(); it.hasNext();) {
                                                Map.Entry me = (Map.Entry) it.next();
                                                BigDecimal key = (BigDecimal) me.getKey();
                                                Actividades value = (Actividades) x.get(key);
                                                out.println("<li><a href=\"" + value.getNombre() + "\"><i class=\"fa fa-bookmark\"></i>" + value.getDescripcion() + "</a></li>");
                                            }
                                        %>
                                    </ul>
                                </li> 
                                <li class="sub-menu">
                                    <a href="javascript:;" class="">
                                        <i class="fa fa-meanpath"></i>
                                        <span>Ayuda</span>
                                        <span class="menu-arrow arrow_carrot-right"></span></a>
                                    <ul class="sub">
                                        <li><a href="#"><i class="fa fa-bookmark"></i>Instructivos</a></li>
                                        <li><a href="#"><i class="fa fa-bookmark"></i>Recomendaciones</a></li>
                                        <li><a href="#"><i class="fa fa-bookmark"></i>Informaci&oacute;n &uacute;til</a></li>
                                    </ul>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </aside>
                </div>
                <div class="col-sm-9">

                    <div class="panel panel-default slideInUp animate">
                        <div class="panel-heading"><center><label> <%
                            if (!rol.equals("SuperUsuario")) {
                                out.print("Mis proyectos");
                            } else {
                                out.print("Información de Proyectos");
                            }
                                    %>:</label></center></div>


                        <%
                            List listaProyectos = new proyectoHelper().listarTodo();
                        %>

                        <h2 class="text-info">N&uacute;mero de Proyectos Activos en la facultad: <%=listaProyectos.size()%></h2>
                        <table class="table table-striped">
                            <%
                                double PromedioDeCumplimiento = 0.0;
                                String idProyecto = "";
                                if (!rol.equals("SuperUsuario")) {
                                    try {
                                        Proyectos miProyecto = (Proyectos) new proyectoHelper().leer(p.getUsuario().getPegeId().toString());
                                        idProyecto = miProyecto.getCodigoProyecto().toString();
                                        PromedioDeCumplimiento = Double.parseDouble(miProyecto.getPorcentaje());
                                        out.println("<tr>"
                                                + "<td><b>Nombre de mi Proyecto:</b> <a class=\"text-primary\" href='#'>" + miProyecto.getNombre()
                                                + "</a></td><td><b> Fecha de Inicio:</b> " + miProyecto.getFechaInicio() + "<br></td>"
                                                + "</td><td><b> Fecha de Finalizaci&oacute;n:</b> " + miProyecto.getFechaFinal() + "<br></td>"
                                                + "</tr>"
                                                + "<tr><td>"
                                                + "<div class=\"panel-body\">\n"
                                                + "<b>Progreso del proyecto: </b><br>"
                                                + "<div class=\"progress-bar \" role=\"progressbar\" aria-valuenow=\"20\" aria-valuemin=\"0\" aria-valuemax=\"100\" "
                                                + "style=\"width: " + miProyecto.getPorcentaje() + "%;\">\n"
                                                + miProyecto.getPorcentaje() + "%</div>\n"
                                                + "</div>"
                                                + "</td>");
                                        if (miProyecto.getCalificacion() < 3.5) {
                                            out.print("<td><b>Calificaci&oacute;n: <b><label class=\"text-danger\">" + miProyecto.getCalificacion() + "</label></td>"
                                                    + "</tr>");
                                        } else if (miProyecto.getCalificacion() >= 3.5 && miProyecto.getCalificacion() < 4.5) {
                                            out.print("<td><b>Calificaci&oacute;n: <b><label class=\"text-primary\">" + miProyecto.getCalificacion() + "</label></td>"
                                                    + "</tr>");
                                        } else if (miProyecto.getCalificacion() >= 4.5) {
                                            out.print("<td><b>Calificaci&oacute;n: <b><label class=\"text-success\">" + miProyecto.getCalificacion() + "</label></td>"
                                                    + "</tr>");
                                        }
                                    } catch (NullPointerException e) {
                                        out.print("<tr>"
                                                + "<td>No tienes proyectos registrados con tu usuario"
                                                + "</td></tr>");
                                    }

                                } else {
                                    for (Object proyectoTemp : listaProyectos) {
                                        Proyectos miProyecto = (Proyectos) proyectoTemp;
                                        PromedioDeCumplimiento = PromedioDeCumplimiento + Double.parseDouble(miProyecto.getPorcentaje());
                                        out.println("<tr>"
                                                + "<td><b>Nombre de Proyecto:</b> <a class=\"text-primary\" href='#'>" + miProyecto.getNombre()
                                                + "</a></td><td><b> Fecha de Inicio:</b> " + miProyecto.getFechaInicio() + "<br></td>"
                                                + "</td><td><b> Fecha de Finalizaci&oacute;n:</b> " + miProyecto.getFechaFinal() + "<br></td>"
                                                + "</tr>"
                                                + "<tr><td>"
                                                + "<div class=\"panel-body\">\n"
                                                + "<b>Progreso del proyecto: </b><br>"
                                                + "<div class=\"progress-bar \" role=\"progressbar\" aria-valuenow=\"20\" aria-valuemin=\"0\" aria-valuemax=\"100\" "
                                                + "style=\"width: " + miProyecto.getPorcentaje() + "%;\">\n"
                                                + miProyecto.getPorcentaje() + "%</div>\n"
                                                + "</div>"
                                                + "</td>");
                                        if (miProyecto.getCalificacion() < 3.5) {
                                            out.print("<td><b>Calificaci&oacute;n: <b><label class=\"text-danger\">" + miProyecto.getCalificacion() + "</label></td>"
                                                    + "</tr>");
                                        } else if (miProyecto.getCalificacion() >= 3.5 && miProyecto.getCalificacion() < 4.5) {
                                            out.print("<td><b>Calificaci&oacute;n: <b><label class=\"text-primary\">" + miProyecto.getCalificacion() + "</label></td>"
                                                    + "</tr>");
                                        } else if (miProyecto.getCalificacion() >= 4.5) {
                                            out.print("<td><b>Calificaci&oacute;n: <b><label class=\"text-success\">" + miProyecto.getCalificacion() + "</label></td>"
                                                    + "</tr>");
                                        }
                                    }
                                }

                            %>
                        </table>
                        <hr class="small">
                        <table class="table table-responsive">
                            <thead>
                                <tr>
                            <h2 class="text-info">Promedio de Cumplimiento hasta el momento:</h2> 
                            </tr>
                            <tr>
                                <%                                    if (rol.equals("SuperUsuario")) {
                                        PromedioDeCumplimiento = PromedioDeCumplimiento / listaProyectos.size();
                                    }
                                    PromedioDeCumplimiento = Math.rint(PromedioDeCumplimiento * 100) / 100;
                                    if (PromedioDeCumplimiento < 50.0) {
                                        out.print("<h2 class=\"text-danger\">" + PromedioDeCumplimiento + "%</h2>"
                                                + "<div class=\"panel-body\">\n"
                                                + "<div class=\"progress-bar-danger\" role=\"progressbar\" aria-valuenow=\"20\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: " + PromedioDeCumplimiento + "%;\">\n"
                                                + "<center>" + PromedioDeCumplimiento + "%</center>"
                                                + "</div>\n"
                                                + "</div>");
                                    } else if (PromedioDeCumplimiento >= 50.0 && PromedioDeCumplimiento < 90.0) {
                                        out.print("<h2 class=\"text-primary\">" + PromedioDeCumplimiento + "%</h2>"
                                                + "<div class=\"panel-body\">\n"
                                                + "<div class=\"progress-bar\" role=\"progressbar\" aria-valuenow=\"20\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: " + PromedioDeCumplimiento + "%;\">\n"
                                                + PromedioDeCumplimiento + "%\n"
                                                + "</div>\n"
                                                + "</div>");
                                    } else if (PromedioDeCumplimiento >= 90.0) {
                                        out.print("<h2 class=\"text-success\">" + PromedioDeCumplimiento + "%</h2>"
                                                + "<div class=\"panel-body\">\n"
                                                + "<div class=\"progress-bar-success\" role=\"progressbar\" aria-valuenow=\"20\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: " + PromedioDeCumplimiento + "%;\">\n"
                                                + "<center>" + PromedioDeCumplimiento + "%</center>"
                                                + "</div>\n"
                                                + "</div>");
                                    }
                                %>
                            </tr>
                            </thead>

                            <tbody class="table-bordered">
                                <tr>
                                    <td><h3 class="text-danger">Critico</h3></td>
                                    <td><h3 class="text-primary">En construcci&oacute;n / En espera</h3></td>
                                    <td><h3 class="text-success">Completado</h3></td>
                                </tr>
                            </tbody>
                        </table>
                        <p></p>
                        <div class="panel-heading"><center><b>Detalle de las revisiones</b></center></div>
                        <h1>Mi Proyecto: </h1>

                        <table class="table table-bordered">
                            <thead>
                            <th>
                            <h2>Detalle de las revisiones</h2>
                            </th>

                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <h2 class="text-info">Descripci&oacute;n de la revisi&oacute;n:</h2>
                                    </td>
                                    <td>
                                        <h2 class="text-info">Fecha:</h2>
                                    </td>    
                                </tr>
                                <%
                                    if (rol.equals("SuperUsuario")) {
                                        out.print("no disponible");
                                    } else {
                                        if (!idProyecto.isEmpty()) {
                                            List revisioList = new proyectoHelper().cargarRevisiones(idProyecto);
                                            for (int i = 0; i < revisioList.size(); i++) {
                                                Revisiones get = (Revisiones) revisioList.get(i);
                                                out.println("<tr>"
                                                        + "<td>"
                                                        + "<h4 class=\"text-primary\">" + get.getDescripcion() + "</h3>"
                                                        + "</td>"
                                                        + "<td><h4 class=\"text-primary\">" + get.getFecha() + "</h3></td>"
                                                        + "</tr>");
                                            }
                                        }
                                    }
                                %>
                            </tbody>    
                        </table>
                    </div>
                </div>

        </section>


        <!-- Footer -->
        <footer>
            <div class="container" id="contacto">
                <div class="row">
                    <div class="col-lg-10 col-lg-offset-1 text-center">
                        <h4><strong>ApSi&REG;</strong>
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

        <div class="scroll-up">
            <a href="#"><i class="fa fa-angle-up"></i></a>
        </div>


        <!--scripts para panel menu-->

        <!-- jQuery -->
        <script src="../js/Jquery/jquery-1.11.2.js"></script>
        <script src="../js/Jquery/jquery.nicescroll.js" type="text/javascript"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="../js/bootstrap.js"></script>


        <!--scripts loaded here-->
        <script src="../js/custom/scripts.js"></script>
        <script src="../js/custom/scripts1.js"></script>

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


