/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.Dia;
import Entity.DispoUsuario;
import Entity.Disponibilidad;
import Modelo.MDias;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Microinformatica
 */
public class DisponibilidadImple implements DisponibilidadDao {

    @Override
    public boolean Insert_Dispo(Disponibilidad dis) {
        System.out.println("entro al insert dispo " + dis.getCodDis());
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        boolean r = false;
        try {
            session.save(dis);
            t.commit();
            r = true;
        } catch (Exception ex) {
            System.out.println("Entro a false " + ex.toString());
            r = false;
        }
        return r;
    }

    @Override
    public boolean InsertDispo_Usu(DispoUsuario dis_usu) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        boolean r = false;
        try {
            session.save(dis_usu);
            t.commit();
            r = true;
        } catch (Exception ex) {
            System.out.println("Entro a false " + ex.toString());
            r = false;
        }
        return r;
    }

    @Override
    public boolean Insert_Dias(Disponibilidad dis, ArrayList dias) {
        boolean r = false;
        MDias temp = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        int cod_dia = 0;
        System.out.println("codigo de dispo " + dis.getCodDis());
        try {
            for (int i = 0; i < dias.size(); i++) {
                temp = (MDias) dias.get(i);
                Dia d = new Dia();
                Query query = session.createQuery("FROM Dia order by codigoDia DESC ").setMaxResults(1);
                if (query.uniqueResult() == null) {
                    cod_dia = 1;
                } else {
                    String cod_salvador = "" + query.uniqueResult();
                    cod_dia = Integer.parseInt(cod_salvador);
                }
                d.setCodigoDia(new BigDecimal(cod_dia + 1));
                d.setDia(temp.getDia());
                d.setHoraInicial(temp.getHora_inicio());
                d.setHoraFinal(temp.getHora_final());
                d.setDisponibilidad(dis);
                session.save(d);

                r = true;
            }
            t.commit();
        } catch (Exception ex) {
            System.out.println("Entro a false " + ex.toString());
            r = false;
        }
        return r;
    }

}
