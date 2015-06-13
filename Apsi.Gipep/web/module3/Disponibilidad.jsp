<%-- 
    Document   : Disponibilidad
    Created on : 21/04/2015, 05:12:22 PM
    Author     : USR_Toshiba
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dao.com.modelo.disponibilidadModelo"%>
<%!ArrayList<disponibilidadModelo> Mis_dias = new ArrayList();%>
<%!ArrayList<String> hora1 = new ArrayList();%>
<%!ArrayList<String> hora2 = new ArrayList();%>




<html  xmlns="http://www.w3.org/1999/xhtml"
       xmlns:h="http://java.sun.com/jsf/html">
    <head>
        <title>Bienvenido</title>
        <link rel="stylesheet" href="../css/ingreso.css"/>
        <link rel="stylesheet" href="../css/Tablas.css"/>
        <link rel="stylesheet" href="../css/formas.css"/>
        <link rel="stylesheet" href="../css/Estilo_hoja.css"/>

    </head>
    <h:body onload="if (window.innerWidth < '400') {
                document.getElementById('slider').style.display = 'none'
            }
            ;">
        <div id="header" class="head">
            <div id="menu" style="position: fixed;top:0%">
                <img id="btn-menu" src="../img/men.png">

            </div>
            <div  style="text-align: center;">
                <b> Ajuste Tiempo</b>    
            </div>
        </div>
        <!-- <div id="submenu1" style="position: fixed;top:6%;background-color:#a4a4ff ;width: 200px">
             <ul>
                 <li id="option1"><a class="menu" href="#">Menu</a></li>
                 <li id="option2"><a class="menu" href="#">CERRAR SECCION</a></li>
 
             </ul>
         </div>-->

        <div id="layoutAjuste">
            <center>
                <br/><br/>
                <h5 id="title">DISPONIBILIDAD</h5>

                <form action="../Disponibilidad_control" method="post" id="formu">
                    <br></br>
                    <br></br>
                    <table >
                        <tr ><td><label>Fecha Inicio</label></td>
                            <td><input type="date" name="fecha" size="10" /></td></tr>
                    </table>
                    <br></br>
                    <table border="1">
                        <tr><td>Seleccion</td><td>dia</td><td>Hora Inicial</td><td>Hora final</td></tr>
                        <tr>
                            <%disponibilidadModelo dispo = new disponibilidadModelo();
                                Mis_dias = dispo.Traer_disponibilidad();
                                hora1 = dispo.TraerHoras();
                                hora2 = dispo.TraerHoras2();
                                disponibilidadModelo temp = null;
                                for (int i = 0; i < Mis_dias.size(); i++) {
                                    temp = (disponibilidadModelo) Mis_dias.get(i);
                                    out.println("<tr>");
                                    out.println("<td><input type=\"checkbox\" name=\"sele\" value="+temp.getDia()+"></td>");
                                    out.println("<td>"+temp.getDia()+"</td>");
                                    out.println("<td>");
                                    out.println("<select name=\"fec1\">");
                                    for (int k = 0; k < hora1.size(); k++) {
                                        out.println("<option value =" + hora1.get(k).toString() + ">" + hora1.get(k).toString() + "</option>");
                                    }
                                    out.println("</select>");
                                    out.println("</td>");
                                    out.println("<td>");
                                    out.println("<select name=\"fec2\">");
                                    for (int k = 0; k < hora2.size(); k++) {
                                        out.println("<option value =" + hora2.get(k).toString() + ">" + hora2.get(k).toString() + "</option>");
                                    }
                                    out.println("</select>");
                                    out.println("</td>");
                                    out.println("</tr>");

                                }


                            %>
                        </tr>
                    </table>
                        <br/><br/>
                        <input type="submit" value="enviar">
                </form>
                <script src="../Jquery/jquery-1.11.2.js"></script>
                <script type="text/javascript">

                </script>
                </h:body>
                </html>

