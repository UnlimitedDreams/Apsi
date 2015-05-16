/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.utl.conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author estudiante305
 */
public class conexion {

    String driver;
    String ruta;
    String puerto;
    String sid;
    String usuario;
    String pass;

    ResultSet rs;
    Statement sta;
    Connection cnn;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public boolean conectar() {
        try {
            Class.forName(driver).newInstance();

            cnn = DriverManager.getConnection(ruta, usuario, pass);
            // driver@machineName:port:SID           ,  userid,  password

            sta = cnn.createStatement();
            return true;
        } catch (ClassNotFoundException ex) {
            System.out.println("Falta driver");
            return false;
        } catch (InstantiationException ex) {
            System.out.println("Mala composicion");
            return false;
        } catch (IllegalAccessException ex) {
            System.out.println("No se puede acceder");
            return false;
        } catch (SQLException ex) {
            System.out.println("Usuario o pass incorrecto");
            return false;
        }

    }

    public boolean desconectar() {
        try {
            cnn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean ejecutarQuery(String Query) {
        try {
            rs = sta.executeQuery(Query);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean ejecutarUpdate(String Update) {
        try {
            sta.executeUpdate(Update);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
