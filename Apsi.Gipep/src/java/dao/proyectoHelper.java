/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.Proyectos;
import Entity.Revisiones;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;
import util.HibernateUtil;

/**
 *
 * @author Miguel Angel Lemos
 */
public class proyectoHelper implements helper {

    @Override
    public boolean buscar(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean borrar(String Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean agregar(Object x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizar(Object x) {
        s.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Proyectos proyectoNew = (Proyectos) x, proyectOld;
        try {
            proyectOld = (Proyectos) s.get(Proyectos.class, BigDecimal.valueOf(Integer.parseInt(proyectoNew.getCodigoProyecto().toString())));
            proyectOld = proyectoNew;
            s.update(proyectOld);
            t.commit();
            return true;
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
                return false;
            }
            throw e;
        }
    }

    public static void reportConeciones() {
        Statistics stats = HibernateUtil.getSessionFactory().getStatistics();
        stats.setStatisticsEnabled(true);

        System.out.println("recuento de conexión: " + stats.getConnectCount());

//Numero de transacciones completadas (falladas y satisfactorias)
        System.out.println("recuento Trx: " + stats.getTransactionCount());

//Numero de transacciones completadas (solo satisfactorias)
        System.out.println("Succ trx count: " + stats.getSuccessfulTransactionCount());

// Numero de sesiones que el codigo ha abierto
        System.out.println("sesiones abiertas: " + stats.getSessionOpenCount());

// Numero de sesiones que el codigo ha cerrado
        System.out.println("sesiones  cerradas: " + stats.getSessionCloseCount());

// Numero total de queries ejecutados
        System.out.println("No. queries: " + stats.getQueryExecutionCount());
//            esta();
    }

    @Override
    public List listarTodo() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List<Proyectos> lista;
        try {
            lista = session.createQuery("from Proyectos").list();
            t.commit();
            reportConeciones();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            throw e;
        } finally {
        }
        return lista;
    }

    @Override
    public Object leer(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Proyectos x = new Proyectos();
        try {
            x = (Proyectos) session.createQuery(""
                    + "Select   proyectos.proyectos,"
                    + "from     UsuarioProyecto as proyectos "
                    + "inner    join proyectos.codUsuproyecto as usuarios "
                    + "where    usuarios.usuarioByEstudiante.pegeId = '" + id + "'").uniqueResult();
            t.commit();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            throw e;
        } finally {
        }
        return x;
    }

    /**
     * Metodo para cargar todas las revisiones relacionadas a un proyecto
     * mediante su codigo identificador, obtendra una lista y deberá ser
     * dibujada en donde se desee implementar dicha lista.
     *
     * @param id Identificador de un proyecto
     * @return retorna una lista con todas las revisiones
     */
    public List<Revisiones> cargarRevisiones(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List<Revisiones> lista;
        try {
            lista = session.createQuery("Select Revisiones.revisiones "
                    + "	from RevisionProyecto as Revisiones "
                    + "	inner join  Revisiones.codRevisionproyecto as Proyecto "
                    + "	where Proyecto.proyectos.codigoProyecto = " + id + "").list();
            t.commit();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            throw e;
        } finally {
        }
        return lista;
    }
}
