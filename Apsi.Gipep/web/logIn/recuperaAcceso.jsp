<%-- 
    Document   : recuperaContraseña
    Created on : 30-jun-2015, 16:56:53
    Author     : Miguel Angel lemos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es" >.
   
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
        <link rel="stylesheet" href="../css/formas.css"/>
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

        <!-- Services -->
        <!-- The circle icons use Font Awesome's stacked icon classes. For more information, visit http://fontawesome.io/examples/ -->
        <section id="services" class="services bg-primary">
            <div class="container">
                <div class="row text-center">
                    <div class="col-lg-10 col-lg-offset-1">
                        <h2>¡Cuentas con usuario activo!</h2>
                        <h1>Con calma <%= session.getAttribute("tempUser") != null ? session.getAttribute("tempUser") : ""%>:</h1>
                        <hr class="small">
                        <!-- /.row (nested) -->
                    </div>
                    <!-- /.col-lg-10 -->
                </div>
                <!-- /.row -->

            </div>
            <div class="container" style="color: darkgray">
                <div class="row text-center">
                    <center>
                        <form id="restaurar" name="restaurar" action="srvltOA" method="GET">
                            <table border="0" style="border-spacing: 10px 20px; border-collapse: separate;">
                                <tbody>
                                    <tr>
                                        <td>
                                            <input class="form-in" size="40" type="email" placeholder="Email" id="email" name="email" required="true"
                                                   style="height: 40px;"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="submit" class="btn btn-dark btn-lg" value="Enviar"/>
                                        </td>
                                    </tr>

                                </tbody>
                            </table>


                        </form>
                    </center>
                </div>
            </div>

            <!-- /.container -->
        </section>
        <div class="alert alert-<%=request.getParameter("msgAlt") != null ? request.getParameter("msgAlt") : ""%>" role="alert">   
            <br>
            <b>
                <h2 style="text-align: center;">
                    <a style="color:red"><%= request.getParameter("msg") != null ? request.getParameter("msg") : ""%></a>
                </h2>
            </b>
        </div>
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
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>


    </body>
</html>
