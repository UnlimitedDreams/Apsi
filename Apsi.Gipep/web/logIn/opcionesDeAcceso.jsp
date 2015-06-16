<%-- 
    Document   : opcionesDeAcceso
    Created on : 16-jun-2015, 10:02:23
    Author     : Miguel Angel Lemos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es" >
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Apsi.GiPep - Tus asesorias Faciles</title>

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
                    <a href="../index.html">Apsi</a>
                </li>
                <li>
                    <a href="#top">Inicio</a>
                </li>
                <li>
                    <a id="opt1">Acceder</a>
                    <div id="ingresa" >
                        <form name="log" action="ingresar" method="POST">
                            <table style="border-spacing: 6px 12px; border-collapse: separate">
                                <tr style="text-align: right;">
                                    <td></td>
                                    <td>
                                        <input placeholder="Usuario" type="text" id="user" class="form-in" name="user" required="" />
                                    </td>
                                </tr>
                                <tr style="text-align: right;">
                                    <td>
                                    </td>
                                    <td>
                                        <input placeholder="Constrase&ntilde;a" type="password" id="pass" class="form-in" name="pass" required="" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                    </td>
                                    <td>
                                        <input type="submit" class="btn-log" value="Iniciar sesi&oacute;n"/>
                                    </td>
                                </tr>
                            </table>
                            <b><a href="opcionesDeAcceso.jsp">No puedes acceder?</a></b>
                            <b><a href="http://www.uniajc.edu.co">Registrate</a></b>
                        </form>
                    </div>
                </li>
                <li>
                    <a href="#contacto">Contacto</a>
                </li>
            </ul>
        </nav>

        <!-- Services -->
        <!-- The circle icons use Font Awesome's stacked icon classes. For more information, visit http://fontawesome.io/examples/ -->
        <section id="services" class="services bg-primary">
            <div class="container">
                <div class="row text-center">
                    <div class="col-lg-10 col-lg-offset-1">
                        <h2>&iquest;Tienes alg&uacute;n problema para entrar?</h2>
                        <h1>Ve sin problemas, escoge una de las opciones de aqu&iacute; abajo:</h1>
                        <hr class="small">
                        <div class="row">
                            <div class="col-md-3 col-sm-6">
                                <div class="service-item">
                                    <span class="fa-stack fa-4x">
                                        <i class="fa fa-circle fa-stack-2x"></i>
                                        <i class="fa fa-clipboard fa-stack-1x text-primary"></i>
                                    </span>
                                    <h4>
                                        <strong>Si olvidaste tu contraseña:</strong>
                                    </h4>
                                    <p>Presiona aqu&iacute; abajo para recuperarla de una manera segura y sencilla.</p>
                                    <a href="logIn.jsp" class="btn btn-light">Recordarme</a>
                                </div>

                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="service-item">
                                    <span class="fa-stack fa-4x">
                                        <i class="fa fa-circle fa-stack-2x"></i>
                                        <i class="fa fa-book fa-stack-1x text-primary"></i>
                                    </span>
                                    <h4>
                                        <strong>Contacta tu facultad:</strong>
                                    </h4>
                                    <p>Si no logras acceder al sistema, contacta a tu facultad lo mas pronto posible.</p>
                                    <a href="http://www.uniajc.edu.co" class="btn btn-light">Ver m&aacute;s</a>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="service-item">
                                    <span class="fa-stack fa-4x">
                                        <i class="fa fa-circle fa-stack-2x"></i>
                                        <i class="fa fa-user fa-stack-1x text-primary"></i>
                                    </span>
                                    <h4>
                                        <strong>¡Pide tu usuario!</strong>
                                    </h4>
                                    <p>La &uacute;nica forma de acceder a Apsi&REG;, es estando registrado en UNIAJC. Matric&uacute;late pronto!.</p>
                                    <a href="http://uniajc.edu.co/index.php/estudiantes" class="btn btn-light">&iquest;C&oacute;mo registrarme?</a>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="service-item">
                                    <div class="service-item">
                                        <span class="fa-stack fa-4x">
                                            <i class="fa fa-circle fa-stack-2x"></i>
                                            <i class="fa fa-times fa-stack-1x text-primary"></i>
                                        </span>
                                        <h4>
                                            <strong>¡Estoy desesperado!</strong>
                                        </h4>
                                        <p>Mant&eacute;n la calma, quiz&aacute; t&uacute; problema ya le ha sucedido a alguien m&aacute;s. Dale aqu&iacute; y mira m&aacute;s</p>
                                        <a href="http://uniajc.edu.co/index.php/estudiantes" class="btn btn-light">¡Ay&uacute;dame ya!</a>
                                    </div>
                                </div>
                            </div>

                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.col-lg-10 -->
                    </div>
                    <!-- /.row -->
                </div>
                <!-- /.container -->
        </section>

        <!-- Footer -->
        <footer>
            <div class="container" id="contacto">
                <div class="row">
                    <div class="col-lg-10 col-lg-offset-1 text-center">
                        <h4><strong>Apsi</strong>
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

