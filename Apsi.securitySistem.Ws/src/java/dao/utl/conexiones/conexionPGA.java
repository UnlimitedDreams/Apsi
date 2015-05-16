/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.utl.conexiones;

/**
 *
 * @author estudiante305
 */
public class conexionPGA extends conexion {

    public conexionPGA(String ruta, String usuario, String pss, String puerto, String Sid) {
        // driver@machineName:port:SID
        driver = "org.postgresql.Driver";
        this.sid=Sid;
        this.puerto=puerto;
        this.ruta = "jdbc:postgresql://"+ruta+":"+puerto+"/"+Sid;
        this.usuario = usuario;
        this.pass = pss;
    }

    
}
