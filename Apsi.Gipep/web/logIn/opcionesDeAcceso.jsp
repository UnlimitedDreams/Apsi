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


                                    <button type="button" class="btn btn-light" data-toggle="modal" data-target="#modalVentana1">  Recordarme
                                    </button>
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
                                    <button type="button" class="btn btn-light" data-toggle="modal" data-target="#modalVentana2">  Ver mas
                                    </button>
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
                                    <p>La &uacute;nica forma de acceder a ApSi&REG;, es estando registrado en UNIAJC. Matric&uacute;late pronto!.</p>
                                    <button type="button" class="btn btn-light" data-toggle="modal" data-target="#modalVentana3">  &iquest;C&oacute;mo registrarme?
                                    </button>
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
                                        <button type="button" class="btn btn-light" data-toggle="modal" data-target="#modalVentana4"> ¡Ay&uacute;dame ya!
                                        </button>
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

        <!-- MODALES -->
        <!-- MODAL 1 -->
        <div id="modalVentana1" class="modal fade " tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <center>
                            <h1>Empecemos</h1>
                            <h2>Digita tu n&uacute;mero de identificaci&oacute;n:</h2>
                            <hr> 
                            <form id="recuperar" name="recuperar" action="srvltOA" method="post">
                                <input id="pegeId" name="pegeId" type="number" placeholder="C&eacute;dula o TI" required=""/>
                                <br>  <br>                                         
                                <input class="btn btn-dark" type="reset" data-dismiss="modal" value="Cerrar"> 
                                <input id="proceso" name="proceso" class="btn btn-dark" type="submit" value="Enviar">  
                            </form> 
                        </center> 
                    </div>
                </div>
            </div>
        </div>

        <!-- MODAL 2 -->
        <div id="modalVentana2" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <center>
                            <h1>Informaci&oacute;n de contacto</h1>
                            <h2>Recuerda los horarios de atención de tus directores, ellos podr&aacute;n resolver tus inquietudes.</h2>
                            <hr> 
                            <h4>

                                <p><b> Contacto 1:</b> +## ### ### ## ##<br>
                                    e-Mail: mailto@correo.com</p>

                                <p>  <b> Contacto 2:</b> +## ### ### ## ##<br>
                                    e-Mail: mailto@correo.com</p>

                                <p><b> Contacto 3:</b> +## ### ### ## ##<br>
                                    e-Mail: mailto@correo.com<p>

                                <p> <b> Contacto 4:</b> +## ### ### ## ##<br>
                                    e-Mail: mailto@correo.com</p>
                                <hr> <br>
                                <p>Alguna de estas personas podr&aacute; ayudarte a solucionar tus problemas. 
                                    <br> Para mas informaci&oacute;n <a href="http://www.uniajc.edu.co/index.php/pqrs">haz clic aqu&iacute;</a></p>
                            </h4>
                            <button id="proceso" name="proceso" data-dismiss="modal"  class="btn btn-dark" >  Cerrar</button>
                        </center> 
                    </div>
                </div>
            </div>
        </div>
        <!-- MODAL 3 -->
        <div id="modalVentana3" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <center>
                            <h1>¡A&uacute;n sin usuario!</h1>
                            <h2>Para acceder ApSi&REG; debes de estar matr&iacute;culado y activo en el sistema Academusoft&reg; de la Universidad de Pamplona,
                                acontinuaci&oacute;n te daremos algunas herramientas para que verficiques tu inscripci&oacute;n con la universidad.</h2>
                            <hr> 
                            <h4>
                                <p>¡Soy nuevo y quiero inscribirme! <a class="" href="http://www.uniajc.edu.co/index.php/aspirantes">¡Llev&aacute;me ah&iacute;!</a> </p>

                                <p>Verificar mi usuario <a class="" href="http://201.234.74.142:8090/uniajc/hermesoft/vortal/general/paswords/hms_ini.jsp">¡Llev&aacute;me ah&iacute;!</a></p>

                                <p>Entrar a mi campus <a class="" href="http://www.uniajc.edu.co/index.php/estudiantes?id=394">¡Llev&aacute;me ah&iacute;!</a></p>



                                <p>¡Mi usuario esta bloqueado!<a class="" href="http://www.uniajc.edu.co/index.php?option=com_content&view=article&id=394">¡Llev&aacute;me ah&iacute;!</a></p>
                                <hr> <br>
                                <p>Accede a estas opciones, procura ir con calma, lee con atenci&oacute;n y verfica tu usuario. 
                                    <br> Para mas informaci&oacute;n <a href="http://www.uniajc.edu.co/index.php/pqrs">haz clic aqu&iacute;</a></p>
                            </h4>
                            <button id="proceso" name="proceso" data-dismiss="modal"  class="btn btn-dark" >  Cerrar</button>
                        </center> 
                    </div>
                </div>
            </div>
        </div>
        <!-- MODAL 4 -->
        <div id="modalVentana4" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <center>
                            <h1>Antes de:</h1>
                            <h4>Aqui hay una lista de posibles problemas con sus soluciones; mira si esta aqu&iacute; tu problema:
                                <hr>
                                <ul>
                                    <li>
                                        No puedo ingresar, y mi usuario esta activo:<br>
                                        En ese caso, verifica que este bien escrito tu usuario; sin errores. Si el problema persiste consulta tu usuario en la opci&oacute;n <a href="#">¡Pide tu usuario!</a>
                                        y verifica que este bien escrito.
                                    </li>
                                    <li>
                                        El correo de recuperaci&oacute;n de contrase&ntilde;a no es mi correo: <br>
                                        Debes dirigirte con el administrador de contenido en la oficina de tecnolog&iacute;a de la instituci&oacute;n, el se encargar&aacute; de cambiar tu constrase&ntilde;a en caso de emergencia
                                    </li>
                                    <li>
                                        Mi usuario fue hurtado, hackeado o modificado: <br>
                                        Para este caso, por favor usa el conducto normativo, y dirigete a la oficina de tecnolog&iacute;a, pero antes env&iacute;a un correo con la siguiente opci&oacute;n
                                    </li>
                                    <li>
                                        Mi problema no aparece <br>
                                        <form> 
                                            <table style=" border-spacing: 8px 15px; border-collapse: separate;">
                                                <thead style="color: #fff; background: #337ab7">
                                                    <tr>
                                                        <td>
                                                            Env&iacute;anos a detalle que te sucedi&oacute; para llevar seguimiento del error.<br>
                                                        </td>
                                                    </tr>
                                                </thead>
                                                <tbody style="text-align: center;">
                                                    <tr>
                                                        <td><input type="email" placeholder="T&uacute; correo electr&oacute;nico" style="width: 220px;" required=""/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td><input type="tex t" placeholder="T&uacute; nombre"  style="width: 220px;" required=""/>
                                                        </td>
                                                    </tr>
                                                    <tr><td>
                                                            <textarea placeholder="Tu problema aqu&iacute;" style=" resize: none;  height:150px;
                                                                      width:100%;" required="" ></textarea> 
                                                        </td>
                                                    </tr>
                                                </tbody>
                                                <tfoot>
                                                    <tr>
                                                        <td>
                                                            <button id="proceso" name="proceso" data-dismiss="modal"  class="btn btn-dark" >  Cerrar</button>
                                                            <input class="btn btn-dark" type="submit" value="Solicitar Ayuda">
                                                        </td>
                                                    </tr>
                                                </tfoot>
                                            </table>

                                        </form>
                                    </li>
                                </ul>
                            </h4>

                        </center> 
                    </div>
                </div>
            </div>
        </div>
        <!-- FIN MODALES -->





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

