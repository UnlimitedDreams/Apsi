/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Miguel Angel Lemos
 */
public interface helper {

    /**
     * Fecha de ingreso mas reciente
     */
    public Date fecha = new Date();

    /**
     * Invalidar sesion cada vez que se crea la consulta
     */
    // Initialize the Hibernate environment
    Configuration cfg = new Configuration().configure();

// Create the session factory
    SessionFactory factory = cfg.buildSessionFactory();

    Session s = factory.openSession();

    /**
     * Buscar si existe el registro
     *
     * @param id Llave primaria del objeto
     * @return Si es verdadera la ejecucion de la busqueda
     */
    public boolean buscar(String id);

    /**
     * Buscar un objeto Hibernate mediante las consultas HQL, para asi lograr 
     * la obtencion de los resultados.
     * Obtendra un Object el cual deberá realizar el debido casteo deacuerdo 
     * a su clase.
     * 
     * @param id Llave primaria del objeto
     * @return retorna un objeto unico con los valores de la busqueda
     */
    public Object leer(String id);

    /**
     * Borrar un registro
     *
     * @param Id Llave primaria del registro que se va a borrar
     * @return Verdadero en la ejecucion del borrado
     */
    public boolean borrar(String Id);

    /**
     * Agregar un registro
     *
     * @param x Objeto que se desea agregar
     * @return Verdadero si la operacion es exitosa
     */
    public boolean agregar(Object x);

    /**
     * Actualizar un registro
     *
     * @param x Registro actualizado
     * @return Verdadero si la operacion es exitosa
     */
    public boolean actualizar(Object x);

    public List listarTodo();
}
