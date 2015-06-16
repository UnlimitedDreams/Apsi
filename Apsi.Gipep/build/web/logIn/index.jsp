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
                    <a href="#about">Acerca de Apsi</a>
                </li>
                <li>
                    <a href="#services">Solicitar Registro</a>
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
                <br>
                <a href="#about" class="btn btn-dark btn-lg">Aprende m&aacute;s sobre Apsi.</a>
            </div>
        </header>

        <!-- About -->
        <section id="about" class="about">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2>Conseguir una cita con una persona, 
                            bien sea para cualquier tarea a 
                            la cual se someten muchas personas del com&uacute;n, 
                            bien sea solo una cita para conseguir informaci&oacute;n, 
                            o hasta una cita tan compleja como la asignaci&oacute;n 
                            de un horario de una operaci&oacute;n a coraz&oacute;n abierto.
                            Dirigirse a una oficina, a un hospital, ni siquiera ser&aacute; 
                            necesario llamar a un call-center para que sus dudas e 
                            inquietudes sean solucionadas, m&eacute;todos como los podcast 
                            o la mensajer&iacute;a instant&aacute;nea ser&aacute;n 
                            herramientas de gran utilidad para dar soluci&oacute;n 
                            a los problemas del cliente. 
                            Actualmente qu&eacute; m&eacute;todos se usan, qu&eacute; m&eacute;todos 
                            son populares entre las empresas?. Es normal que en una 
                            empresa, una secretaria o un auxiliar realice tareas de esta 
                            &iacute;ndole, es decir se ven muy relacionados con la 
                            asignaci&oacute;n de citas, lo que por lo general es eficiente, 
                            en una escala peque&ntilde;a; cuando crece el volumen de personas 
                            que puede atender una persona, o bien, estos procesos 
                            implican una p&eacute;rdida de esfuerzo importante para 
                            los auxiliares; se toman medidas al respecto. La soluci&oacute;n m&aacute;s 
                            com&uacute;n es crear un centro de soluciones, que simplemente consiste en una oficina encargada de dar soluci&oacute;n a cada uno de los incovenientes de las personas, claro esta sin atenci&oacute;n personalizada; es decir, no tienen contacto directo con el cliente. Los centros m&eacute;dicos no se pueden dar el lujo de atender un paciente por v&iacute;a telef&oacute;nica; que suceder&iacute;a si se creara un centro de soluciones para un centro m&eacute;dico: "Muy buen dia apreciado usuario, debe abrirse el vientre y retirar al beb&eacute; de sus entra&ntilde;as", todos est&aacute;n en capacidad, sin embargo ser&iacute;a un riesgo innecesario; de esta forma es mejor que cada paciente tenga una atenci&oacute;n especializada de acuerdo a su dolencia. La soluci&oacute;n fue sencilla, crear un cronograma, programemos un horario de atenci&oacute;n, disponibilidad, y solucionemos problemas dentro de nuestros alcances y habilidades, adem&aacute;s mantener una comunicaci&oacute;n entre especialistas ha sido el salvamento de muchos pacientes, un historial se hizo evidente dentro de las necesidades.
                            La propuesta dentro de este proyecto, consiste en una aplicaci&oacute;n que dara solucion a uno de los problemas que surgen en un entorno de educaci&oacute;n virtual y semipresencial, sin embargo es inspirado en m&uacute;ltiples soluciones arcaicas que actualmente son implementadas, solo que en este momento no habr&aacute; m&aacute;s el factor humano como error, esta parte no har&aacute; parte m&aacute;s de la ecuaci&oacute;n de este sistema, no dejando atr&aacute;s su supervisi&oacute;n y colaboraci&oacute;n claro est&aacute;.
                            Ahora bien la educaci&oacute;n virtual pronto ser&aacute; el modelo de educaci&oacute;n de mayor calidad, uno de los m&aacute;s populares y potencialmente el mejor. Esto es referente a que no todas las personas tienen la facilidad de movilidad para acceder a un edificio y estar en clases magistrales con un tutor o docente de &aacute;rea, o bien los costos son demasiado altos para lograr acceder a el aprendizaje superior. A pesar de ello, el surgimiento de las nuevas tecnolog&iacute;as, las m&uacute;ltiples mejoras a el broadcast y a las redes de comunicaci&oacute;n ha permitido que poco a poco m&aacute;s personas accedan a la educaci&oacute;n, sin embargo; la experiencia de humana que se logra al estar con un tutor presencialmente a&uacute;n no se iguala y el modelo actual sigue dominante.
                            .</h2>
                        <p class="lead">&quot;Las m&aacute;quinas no son nada sin una mente humana que las opere, los cuerpos humanos no pueden hacer lograr la perfecci&oacute;n sin una m&aacute;quina que ayude en las labores m&aacute;s arduas e imposibles para la humanidad&quot;.</p>
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
                        <h2>A&uacute;n sin registrarte?</h2>
                        <hr class="small">
                        <div class="row">
                            <div class="col-md-3 col-sm-6">
                                <div class="service-item">

                                </div>

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
                                    <p>Logra que tu proyecto sea el mejor.</p>
                                    <a href="logIn.jsp" class="btn btn-light">Accede</a>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="service-item">
                                    <span class="fa-stack fa-4x">
                                        <i class="fa fa-circle fa-stack-2x"></i>
                                        <i class="fa fa-cloud fa-stack-1x text-primary"></i>
                                    </span>
                                    <h4>
                                        <strong>Pide tu usuario!</strong>
                                    </h4>
                                    <p>La unica forma de acceder a esta herramienta es que estes registrado. Haslo pronto!.</p>
                                    <a href="http://uniajc.edu.co/index.php/estudiantes" class="btn btn-light">Como registrarse</a>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="service-item">

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
