/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orm.me.control;

import dao.utl.conexiones.conexionPGA;
import dao.utl.conexiones.prueba;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author usuario
 */
@WebService(serviceName = "securityWS")
@Stateless()
public class securityWS {

    /**
     * Web service operation
     *
     * @param user
     * @param password
     * @return
     */
    @WebMethod(operationName = "validarUsuario")
    public boolean validarUsuario(@WebParam(name = "user") String user, @WebParam(name = "password") String password) {
        //TODO write your implementation code here:
        try {
            conexionPGA mc = new conexionPGA("localhost", "postgres", "3157402825", "5432", "Apsi");
            mc.conectar();
            mc.ejecutarQuery("select * from usuario where usuario='" + user + "' and contrasea='" + password + "'");
            mc.desconectar();
            return mc.getRs().next();
        } catch (SQLException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
}
