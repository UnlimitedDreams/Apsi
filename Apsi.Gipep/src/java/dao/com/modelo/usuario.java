/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.com.modelo;

/**
 *
 * @author USR_Toshiba
 */
public class usuario {

    private String usu, pass, rol;
    private int idUsu;

    public usuario(String usu, String pass, String rol, int idUsu) {
        this.usu = usu;
        this.pass = pass;
        this.rol = rol;
        this.idUsu = idUsu;
    }

    public String getUsu() {
        return usu;
    }

    public void setUsu(String usu) {
        this.usu = usu;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(int idUsu) {
        this.idUsu = idUsu;
    }

    @Override
    public String toString() {
        return "Credenciales de:{"+ " idUsu=" + idUsu + " usu:" + usu + ", pass=" + pass + ", rol=" + rol  + '}';
    }
    
    
}
