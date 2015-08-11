<%-- 
    Document   : mainMenu
    Created on : 24-jul-2015, 7:49:04
    Author     : Miguel Angel Lemos
--%>

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
                    <a href="#Archivos"><i class="fa fa-folder-o fa-stack-1x text-primary"></i>Archivo</a>
                </li>
                <li>
                    <a href="#Opciones"><i class="fa fa-cog fa-stack-1x text-primary"></i>Opciones</a>
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
                        TreeMap roles = new UsuarioImple().cargarRoles(p.getUsuario().getPegeId().toString());
                        String rol = "";
                        for (Iterator it = roles.entrySet().iterator(); it.hasNext();) {
                            Map.Entry me = (Map.Entry) it.next();
                            BigDecimal key = (BigDecimal) me.getKey();
                            Rol value = (Rol) roles.get(key);
                            rol = value.getNombre();
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
                                            TreeMap x = new UsuarioImple().cargarActividades("-1");
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
                        <div class="panel-heading"><center><label>Avance de proyecto</label></center></div>
                        <div class="panel panel-primary slideInUp animate">
                            <div class="panel-heading">
                                Mis proyectos:
                            </div>
                            <div class="container">
                                <h3>Center content</h3>    
                            </div>

                        </div>
                        <div class="container">
                            <h2 class="text-info">Mis proyectos:</h2>
                            <h2 class="text-success">Text color Green</h2>
                            <h3 class="text-danger">Text color Red</h3>
                            <h3 class="text-primary">Text color Blue</h3>   
                        </div>
                        <div class="panel-heading"><center>Tablero de noticias</center></div>

                        <h1>Text</h1>
                        <h2 class="text-success">Text color Green</h2>
                        <h3 class="text-danger">Text color Red</h3>
                        <h3 class="text-primary">Text color Blue</h3>
                        <h1>Links</h1>
                        <a class="btn-success">Hello</a> - <a class="text-success">Facelets</a>
                        <a class="btn-danger">Hello</a> - <a class="text-danger">Facelets</a>
                        <a class="btn-primary">Hello</a> - <a class="text-primary">Facelets</a>

                        <h1>Buttons</h1>
                        <button type="submit" class="btn btn-success">Submit</button>
                        <button type="submit" class="btn btn-danger">Delete</button>
                        <button type="submit" class="btn btn-primary">Options</button>

                        <h1>Progres Var</h1>
                        <div class="panel-body">
                            <div class="progress-bar " role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%;">
                                20%
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width: 50%;">
                                50%
                            </div>
                        </div>

                        <h1>Panel examples</h1>

                        <div class="panel panel-primary slideInUp animate">
                            <div class="panel-heading">
                                <h4>Title Panel</h4></div><h3>Center content</h3></div>

                        <div class="panel panel-danger slideInUp animate">
                            <div class="panel-heading">
                                <h4>Title Panel</h4></div><h3>Center content</h3></div>

                        <div class="panel panel-success slideInUp animate">
                            <div class="panel-heading">
                                <h4>Title Panel</h4></div><h3>Center content</h3></div>

                    </div>
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


