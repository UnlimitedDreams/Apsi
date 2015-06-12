/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conecion_Oracle {

    public static String driver = "oracle.jdbc.driver.OracleDriver";
    public static String connectString = "jdbc:oracle:thin://201.234.74.123:1521/uniajc";
    public static String user = "conciliacion";
    public static String password = "conciliacion_2014";//ya esta listo
    public static String query;
    public static Statement stat;
    public static ResultSet rs;
    public static Connection con;

    public static boolean conectar() throws ClassNotFoundException {
        boolean r = false;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@201.234.74.123:1521:uniajc", "conciliacion", "conciliacion_2014");
            stat = con.createStatement();
            System.out.println("Conecion exitosa");
            r = true;
        } catch (SQLException e) {
            System.out.println("no");
            System.out.println(e.getMessage());
        }
        return r;
    }
//METODOS PARA POSICIONAR EN EL RESULT SET
    // INDEPENDIENTE DE CUAL SEA 
    // LA TABLA

    public static boolean ejecuteQuery() {
        boolean r = false;
        try {
            rs = stat.executeQuery(query);
            r = true;
        } catch (Exception e) {
            System.out.println("Hubo un Error en Ejecute Query -> " + e);
            r = false;
        }
        return r;
    }

    public static boolean ejecuteQuery(String x) {
        boolean r = true;
        try {
            rs = stat.executeQuery(x);
        } catch (SQLException e) {
            System.out.println("ERROR AL HACER QUERY " + e.toString());
            r = false;
        }
        return r;
    }

    public static boolean ejecuteUpdate() {
        boolean r = true;
        try {
            stat.executeUpdate(query);
            r = true;
        } catch (SQLException e) {
            System.out.println("ERROR Al HACER UPDTAPE" + e.toString());
            r = false;
        }
        return r;
    }

    public static boolean ejecuteUpdate(String q) {
        query = q;
        return (ejecuteUpdate());
    }

    public static void main(String[] agrs) throws ClassNotFoundException {

        String query = "select * from academico.Unidad";
        Conecion_Oracle.conectar();
        Conecion_Oracle.ejecuteQuery(query);
        try {
            while (Conecion_Oracle.rs.next()) {
                System.out.println("ok");
            }
        } catch (Exception ex) {

        }
    }

}
