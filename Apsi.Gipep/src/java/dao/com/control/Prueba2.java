/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.com.control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author USR_Toshiba
 */
public class Prueba2 {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar Diadehoy = Calendar.getInstance();
        Date date = formatter.parse("21-12-2015");
        int dias = 16;
        Diadehoy.setTime(date);
        System.err.println("fcha " + Diadehoy.getTime());
        System.err.println("mes " + Diadehoy.get(Calendar.MONTH));
        System.err.println("fecha 2 " + Diadehoy.get(Calendar.YEAR));
        System.err.println("fecha 2 " + Diadehoy.get(Calendar.DAY_OF_MONTH));
        System.err.println("fecha 2 " + Diadehoy.get(Calendar.DAY_OF_WEEK));
        System.err.println(Diadehoy.getTime());

        while ((Diadehoy.get(Calendar.MONTH)) == 2) {
            System.err.println("entro");
            if (Diadehoy.get(Calendar.DAY_OF_WEEK) == 4) {
                System.err.println("agregado con fecha " + Diadehoy.getTime());
            }

        }

        
      
//        Date hoy = new Date();
//        System.err.println("hoy " +hoy);
//        int numeroDia = 0;
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(hoy);
//        numeroDia = cal.get(Calendar.DAY_OF_WEEK);
//        System.out.println("hoy es " + numeroDia);
    }
}
