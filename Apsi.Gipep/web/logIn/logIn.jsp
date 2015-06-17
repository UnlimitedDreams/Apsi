<%-- 
    Document   : opcionesDeAcceso
    Created on : 16-jun-2015, 10:02:23
    Author     : Miguel Angel Lemos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <%session = request.getSession();
        try {
            if (!session.isNew() | !session.getAttribute("user").equals(null)) {
                response.sendRedirect("index.jsp");
            }
        } catch (java.lang.NullPointerException e) {
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
                    <a href="#top">Inicio</a>
                </li>
                <li>
                    <a href="#contacto">Contacto</a>
                </li>
            </ul>
        </nav>  

        <!-- Header -->
        <header id="top" class="header">
            <div class="text-vertical-center">
                <h1>ApSi&reg;</h1>
                <h3>Asesorias y proyectos en cualquier momento</h3>
                <h4><center>
                        <form id="log" name="log" action="ingresar" method="POST">
                            <table border="0" style="border-spacing: 10px 20px; border-collapse: separate;">
                                <tbody>
                                    <tr style="text-align: right;">
                                        <td>
                                            <input placeholder="Usuario" type="text" id="user" class="form-in" name="user" required="true"/>
                                        </td>
                                    </tr>
                                    <tr style="text-align: right;">
                                        <td>
                                            <input placeholder="Contrase&ntilde;a" type="password" id="pass" class="form-in" name="pass" required="true"/>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <table style="text-align: center;">
                                <tr>
                                    <td><a href="opcionesDeAcceso.jsp">&iquest;No puedes acceder?</a></td>
                                </tr>
                                <tr>
                                    <td>&iquest;No Tienes una cuenta? <b><a href="http://www.uniajc.edu.co"> Registrate</a> </b></td>
                                </tr>
                                <tr>
                                    <td><br>
                                        <input name="operar" class="btn btn-dark btn-lg" type="submit" value="Iniciar sesi&oacute;n"/> <a href="#about" class="btn btn-dark btn-lg">Aprende m&aacute;s</a>
                                    </td>

                                </tr>
                            </table>
                        </form>
                    </center></h4>


            </div>
        </header>
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

