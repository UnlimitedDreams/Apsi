/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;


import Entity.Usuario;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
//        Asesoria asesoria = new Asesoria();
//        asesoria.setCodAsesoria(new BigDecimal(1));
//        Asistente asistente = new Asistente();
//        asistente.setAsesoria(asesoria);
////        Dias d = null;
//        Usuario usu = new Usuario();
//        usu.setPegeId(new BigDecimal(1));
//        try {
//            Query query = session.createQuery("FROM Disponibilidad order by codDis DESC ").setMaxResults(1);
//            System.out.println("-" + query.uniqueResult());
//asesoria disponibilidad DispoUsuario Usuario
        // select A.codAsesoria from Asesoria A INNER JOIN A.disponibilidad D
        // INNER JOIN D.dispoUsuarios DU INNER JOIN DU.usuario Usu where
        //Usu.pegeId=1
        //
        //
        //
//            boolean r = false;
        ArrayList<Usuario> calen = new ArrayList();
        try {
            calen = (ArrayList) session.createQuery("select UD.usuarioByProfesor from Disponibilidad D "
                    + " INNER JOIN D.dispoUsuarios UD "
                    + " INNER JOIN UD.usuarioByAdmon Usu WHERE Usu.pegeId=1 and D.periodo=1 "
                    + " and D.fechaInicial>='01-01-2015' and  D.fechaFinal<='01-12-2015'").list();

            for (int i = 0; i < calen.size(); i++) {
                System.out.println("<li><a href=\"#\"><i class=\"fa fa-plus\"></i>Actividad</a></li>");
            }
            t.commit();
 
        } catch (Exception ex) {

        }
//        } catch (Exception ex) {
//
//            System.out.println("---- " + ex.getMessage());
//        }

    }

}
