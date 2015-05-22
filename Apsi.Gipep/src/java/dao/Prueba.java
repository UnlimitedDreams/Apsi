/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Asesoria;
import Entity.Asistente;
import Entity.Calendario;
import Entity.Dias;
import Entity.Festivos;
import Entity.ModeloDias;
import Entity.Usuario;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.IntegerType;
import util.HibernateUtil;

/**
 *
 * @author Microinformatica
 */
public class Prueba {

    public static void main(String[] agrs) {
//        FestivosDao f = new FestivosImple();
//        Festivos temp = new Festivos();
//        Date fecha = new Date();
//        fecha.setDate(23);
//        fecha.setMonth(03);
//        System.out.println("---" + fecha.toString());
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        String fechaConFormato = sdf.format(fecha);
//        System.out.println("+++ " + fechaConFormato);
//        temp=f.BuscarFestivo(fechaConFormato);
////        System.out.println("--" + temp.toString());
//        if(temp==null){
//            System.out.println("Nan");
//        }else{
//            System.out.println("yes");
//        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Asesoria asesoria = new Asesoria();
        asesoria.setCodAsesoria(new BigDecimal(1));
        Asistente asistente = new Asistente();
        asistente.setAsesoria(asesoria);
//        Dias d = null;
        Usuario usu = new Usuario();
        usu.setPegeId(new BigDecimal(1));
        try {
//            Query query = session.createQuery("FROM Festivos order by festivo DESC ").setMaxResults(1);
//            System.out.println("-" + query.uniqueResult());
//asesoria disponibilidad DispoUsuario Usuario
            // select A.codAsesoria from Asesoria A INNER JOIN A.disponibilidad D
            // INNER JOIN D.dispoUsuarios DU INNER JOIN DU.usuario Usu where
            //Usu.pegeId=1
            //
            //
            //
            boolean r = false;
            ArrayList<Asesoria> calen = new ArrayList();
            try {
                calen = (ArrayList) session.createQuery("select A from Asesoria A INNER JOIN A.disponibilidad D "
                        + " INNER JOIN D.dispoUsuarios UD "
                        + " INNER JOIN UD.usuario Usu WHERE Usu.pegeId=1").list();

                for (int i = 0; i < calen.size(); i++) {
                    System.out.println("-" + calen.get(i));
                }
                t.commit();
                r = true;
            } catch (Exception ex) {

            }
        } catch (Exception ex) {

            System.out.println("---- " + ex.getMessage());
        }

    }
 
           
}
