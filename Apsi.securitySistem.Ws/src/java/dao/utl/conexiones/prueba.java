/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.utl.conexiones;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author estudiante305
 */
public class prueba {

    public static void main(String[] args) {
        try {
            conexionPGA mc = new conexionPGA("localhost", "postgres", "3157402825", "5432", "Apsi");
            mc.conectar();
            mc.ejecutarQuery("select version()");
            while (mc.rs.next()) {
                System.out.println("Resultado: " + mc.rs.getString(1));
            }
            mc.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
