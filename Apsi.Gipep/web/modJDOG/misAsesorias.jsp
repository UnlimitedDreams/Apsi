<%-- 
    Document   : opcionesDeAcceso
    Created on : 16-jun-2015, 10:02:23
    Author     : Miguel Angel Lemos
--%>
<%@page import="Entity.Persona"%>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:f="http://xmlns.jcp.org/jsf/core"
      xmlns:p="http://primefaces.org/ui">
    <head>
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta name="description" content=""/>
        <meta name="author" content=""/>
        <title>ApSi.GiPep</title>
        <!-- Bootstrap Core CSS -->
        <link href="../css/bootstrap.min.css" rel="stylesheet"/>

        <!-- Custom CSS -->
        <link rel="stylesheet" href="../css/styles.css" />
        <link href="../css/stylish-portfolio.css" rel="stylesheet"/>
        <!-- Link para panel menu-->
        <link href="../css/style.css" rel="stylesheet"/>
        <!-- Custom Fonts -->
        <link href="../font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
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
    <h:body>
        <%Persona p = new Persona();%>
        <%session = request.getSession();
            try {
                if (session.isNew() | session.getAttribute("user").equals(null)) {
                    response.sendRedirect("../logIn/index.jsp");
                } else {
                    p = (Persona) session.getAttribute("persona");
                }
            } catch (java.lang.NullPointerException e) {
                response.sendRedirect("../logIn/index.jsp");
            }
        %>
        <nav id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <a id="menu-close" href="#" class="btn btn-light btn-lg pull-right toggle"><i class="fa fa-times"></i></a>
                <li class="sidebar-brand">
                    <a onclick="document.location = 'index.jsp'">ApSi</a>
                </li>
                <li>
                    <a href="index.jsp"><i class="fa fa-home fa-stack-1x text-primary"></i>Inicio</a>
                </li>
                <li>
                    <a id="opt1"><i class="fa fa-user-md fa-stack-1x text-primary"></i><%= session.getAttribute("user")%> </a>
                </li>
                <div id="ingresa">
                    <ul>
                        <li><a href="#profile">Mi perfil</a></li> 
                        <li><a href="mainMenu.jsp">Mi actividad</a></li>
                    </ul>
                    <form name="log" action="../ingresar" method="GET">
                        <table style="border-spacing: 6px 12px; border-collapse: separate">
                            <tr style="text-align: right;">
                                <td></td>
                                <td><input type="submit" class="btn btn-light btn-log" name='operar' value="Cerrar sesi&oacute;n"></td>
                            </tr>
                        </table>
                    </form>
                </div>
                <li>
                    <a href="#services"><i class="fa fa-info fa-stack-1x text-primary"></i>Solicitar Asesor&iacute;as</a>
                </li>

                <li>
                    <a href="#contacto"><i class="fa fa-comment fa-stack-1x text-primary"></i>Contacto</a>
                </li>
            </ul>
        </nav>
        <section class="container-fluid" id="section6">
            <div class="container-fluid">
                <div class="row" style="text-align: left;">                   
                    <h3>
                        <i class="fa fa-user"> </i> Usuario: <%=p.getNombres()%> <%=p.getApellidos()%>

                    </h3> 
                </div>
            </div>
        </section>
        <section class="container-fluid" id="section2">
            <div class="row">
                <div class="col-sm-12">
                    <div class="panel panel-default slideInUp animate">
                        <div class="panel-heading">
                            <h4>Lista de Asesorias por ver:</h4>
                        </div>
                    </div>
                </div>
            </div>
        </section>


        <footer id="footer">
            <div class="container">
                <div class="row">
                    <div class="col-lg-10 col-lg-offset-1 text-center">
                        <h4><strong>ApSi&REG;</strong>
                        </h4>
                        <p>Instituci&oacute;n Universitaria Antonio Jos&eacute; Camacho<br></br>Santiago de Cali, Colombia</p>
                        <ul class="list-unstyled">
                            <li><i class="fa fa-phone fa-fw"></i> (318) 265-7417</li>
                            <li><i class="fa fa-envelope-o fa-fw"></i>  <a href="mailto:unlimitedDreamsTi@gmail.com">unlimitedDreamsTi@gmail.com</a>
                            </li>
                        </ul>
                        <br></br>
                        <ul class="list-inline">
                            <li>
                                <a href="#"><i class="fa fa-facebook fa-fw fa-3x"></i></a>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-twitter fa-fw fa-3x"></i></a>
                            </li>
                        </ul>
                        <hr class="small"></hr>
                        <p class="text-muted">Powered By Unlimited Dreams <br></br>Copyright &copy; Unlimited Dreams, Todos los derechos reservados.</p>
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
    </h:body>
</html>

