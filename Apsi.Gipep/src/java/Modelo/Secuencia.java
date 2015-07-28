/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Family
 */
public class Secuencia {

    public static int seque(String sql) throws ClassNotFoundException {
        Conecion_postgres1.conectar();
        Conecion_postgres1.ejecuteQuery(sql);
        int cod = 0;
        try {
            while (Conecion_postgres1.rs.next()) {
                cod = Conecion_postgres1.rs.getInt(1);
            }
            Conecion_postgres1.cerrarConexion();
        } catch (Exception ex) {

        }
        Conecion_postgres1.cerrarConexion();
        return cod + 1;
    }
}
