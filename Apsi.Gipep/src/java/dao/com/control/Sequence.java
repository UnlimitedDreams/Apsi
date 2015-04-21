/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.com.control;

/**
 *
 * @author USR_Toshiba
 */
public class Sequence {

    public static int Sequen(String sql) throws ClassNotFoundException {
        Control.conectar();
        Control.ejecuteQuery(sql);
        int codigo = 0;
        try {
            while (Control.rs.next()) {
                codigo = Control.rs.getInt(1);
            }
        } catch (Exception ex) {

        }
        return codigo + 1;
    }
}
