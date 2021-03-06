/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.Actividades;
import Entity.Correospersona;
import Entity.Persona;
import Entity.Rol;
import Entity.RolActividad;
import Entity.Telefonos;
import Entity.UsuRol;
import Entity.Usuario;
import apsi.Security.md5;
import java.math.BigDecimal;
import java.util.List;
import java.util.TreeMap;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Microinformatica
 */
public class UsuarioImple implements UsuarioDao {

    /**
     * Crear una lista para todos los usuarios del sistema
     *
     * @return Lista con todos los usuarios del sistema
     */
    public List cargarTodo() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            return session.createQuery("FROM Usuario").list();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Metodos para acceder a la información de cada usuario.
     *
     * @param usu Entrada de un usuario para obtener su informarción
     * @return Información completa de los usuarios
     */
    @Override
    public Usuario Loguin(Usuario usu) {
        Usuario temp = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        try {
            temp = (Usuario) session.createQuery("FROM Usuario WHERE usuario='" + usu.getUsuario() + "' and contrasea='" + usu.getContrasea() + "'").uniqueResult();
            t.commit();
            System.out.println("temp " + temp.getUsuario());
        } catch (Exception ex) {
            System.out.println("Error " + ex.toString());
        }
        return temp;
    }

    /**
     * Método para crear un usuario nuevo.
     *
     * @param usu Usuario que se desea crear en el sistema.
     * @return Verdadero si el usuario es creado correctamente, de lo contrario
     * falso
     */
    @Override
    public boolean CrearUsuario(Usuario usu) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        try {
            if (!BuscarUsuario(usu)) {
                session.save(usu);
                t.commit();
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            if (t != null) {
                t.rollback();
                return false;
            }
            throw e;
        }
    }

    /**
     * Buscar un usuario.
     *
     * @param usu Parámetro de entrada .Class usuario
     * @return Verdadero en caso de que el usuario exista, de lo contrario false
     */
    @Override
    public boolean BuscarUsuario(Usuario usu) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        boolean r = false;
        Usuario u = new Usuario();
        try {
            u = (Usuario) session.createQuery("FROM Usuario as usu where usu.pegeId=" + usu.getPegeId()).uniqueResult();
            if (u == null) {
                r = false;
            } else {
                r = true;
            }
            t.commit();
        } catch (Exception ex) {
            System.out.println("Error -> " + ex.getMessage());
        }
        return r;
    }

    /**
     * Cargar un usuario a la session, para procesar datos en la pagina
     *
     * @param pege Parámetro de entrada para buscar un usuario nuevo
     * @return Datos del usuario .class Usuario
     */
    public Usuario cargarUsu(String pege) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Usuario u = new Usuario();
        try {
            u = (Usuario) session.get(Usuario.class, BigDecimal.valueOf(Integer.parseInt(pege)));
            t.commit();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            throw e;
        } finally {
        }
        return u;
    }

    /**
     * Cambia la contraseña según los protocolos establecidos por la IEEE
     *
     * @param pege Codigo del usuario al que se va a cambiar la contraseña
     * @param nPass Nueva contraseña a asignar al usuario
     */
    public void CambiarContraseña(String pege, String nPass) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Usuario u = new Usuario();
        try {
            u = (Usuario) session.get(Usuario.class, BigDecimal.valueOf(Integer.parseInt(pege)));
            u.setContrasea(md5.getMD5(nPass));
            session.update(u);
            t.commit();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            throw e;
        }
    }

    /**
     * Ver información de la persona apartir del pegeId de su usuario.
     *
     * @param id identificador de la persona
     * @return Entidad de persona
     */
    public Persona verPersona(String id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Persona p = new Persona();
        try {
            p = (Persona) session.createQuery("from Persona where pege_id =" + id).uniqueResult();
            t.commit();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            throw e;
        } finally {
        }
        return p;
    }

    /**
     * Cargar lista telefonos de un usuario
     *
     * @param id Codigo del usuario
     * @return Lista con los números posibles del usuario
     */
    public List cargarTelefonos(String id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        List<Telefonos> telefonos = null;

        try {
            telefonos = session.createQuery(" from Telefonos \n"
                    + "where idPersona = " + id).list();
            t.commit();
            return telefonos;
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            return null;
        }

    }

    /**
     * Cargar la lista de correos de un usuario
     *
     * @param id Codigo del usuario
     * @return Lista de correos d un usuario
     */
    public List cargarCorreos(String id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        List<Correospersona> telefonos = null;

        try {
            telefonos = session.createQuery(" from Correospersona \n"
                    + "where idPersona = " + id).list();
            t.commit();
            return telefonos;
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            return null;
        }
    }

    /**
     * Cargar un lista de roles que posee un usuario activo.
     *
     * @param id identificador del usuario
     * @return Lista de Roles asignados al usuario
     */
    public TreeMap cargarRoles(String id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        List<UsuRol> usuRols = null;
        TreeMap roles = new TreeMap();
        try {
            usuRols = session.createQuery(" from  UsuRol\n"
                    + "where pege_Id  = " + id).list();
            for (UsuRol get : usuRols) {
                Rol r = (Rol) session.get(Rol.class, get.getRol().getCodRol());
                roles.put(r.getCodRol(), r);
            }
            t.commit();
            return roles;
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            return null;
        }
    }

    public TreeMap cargarActividades(String id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        List<RolActividad> rolActividad = null;
        TreeMap roles = new TreeMap();
        try {
            rolActividad = session.createQuery(" from RolActividad  where cod_rol = " + id).list();
            for (RolActividad get : rolActividad) {
                Actividades act = (Actividades) session.get(Actividades.class, get.getActividades().getCodigoActividad());
                roles.put(act.getCodigoActividad(), act);
            }
            t.commit();
            return roles;
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            return null;
        }
    }
}
