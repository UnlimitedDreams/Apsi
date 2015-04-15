<%-- 
    Document   : infoDeUsuario
    Created on : 18/03/2015, 05:26:58 PM
    Author     : usuario
--%>

<%@page import="dao.com.modelo.usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../css/ingreso.css"/>
        <link rel="stylesheet" href="../css/formas.css"/>
        <title>Informacion de Usuario</title>
    </head>
    <body>

        <div id="header" class="head">
            <!-- Encabezado cool!--> 
            Bienvenido: <% HttpSession S = request.getSession();
                usuario x = (usuario) S.getAttribute("ObjUsu");
                out.print(x.getUsu()+" Rol: "+x.getRol()); %>            

        </div>
        <div id="layout-principal" class="layout layout-principal">
            
            <%

                out.println(S.getAttribute("ObjUsu").toString());
            %>
            <label><p></p>Feed Rolling back: Pagina en construccion.</label>

        </div> 
    </body>
</html>
