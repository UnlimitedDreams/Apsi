package Entity;
// Generated 18-ago-2015 14:13:44 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private BigDecimal pegeId;
     private String contrasea;
     private String usuario;
     private Set usuRols = new HashSet(0);
     private Set personas = new HashSet(0);
     private Set dispoUsuariosForProfesor = new HashSet(0);
     private Set dispoUsuariosForAdmon = new HashSet(0);
     private Set usuarioProyectosForEstudiante = new HashSet(0);
     private Set asistentes = new HashSet(0);
     private Set calendarios = new HashSet(0);
     private Set usuarioProyectosForDirector = new HashSet(0);

    public Usuario() {
    }

	
    public Usuario(BigDecimal pegeId, String contrasea, String usuario) {
        this.pegeId = pegeId;
        this.contrasea = contrasea;
        this.usuario = usuario;
    }
    public Usuario(BigDecimal pegeId, String contrasea, String usuario, Set usuRols, Set personas, Set dispoUsuariosForProfesor, Set dispoUsuariosForAdmon, Set usuarioProyectosForEstudiante, Set asistentes, Set calendarios, Set usuarioProyectosForDirector) {
       this.pegeId = pegeId;
       this.contrasea = contrasea;
       this.usuario = usuario;
       this.usuRols = usuRols;
       this.personas = personas;
       this.dispoUsuariosForProfesor = dispoUsuariosForProfesor;
       this.dispoUsuariosForAdmon = dispoUsuariosForAdmon;
       this.usuarioProyectosForEstudiante = usuarioProyectosForEstudiante;
       this.asistentes = asistentes;
       this.calendarios = calendarios;
       this.usuarioProyectosForDirector = usuarioProyectosForDirector;
    }
   
    public BigDecimal getPegeId() {
        return this.pegeId;
    }
    
    public void setPegeId(BigDecimal pegeId) {
        this.pegeId = pegeId;
    }
    public String getContrasea() {
        return this.contrasea;
    }
    
    public void setContrasea(String contrasea) {
        this.contrasea = contrasea;
    }
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public Set getUsuRols() {
        return this.usuRols;
    }
    
    public void setUsuRols(Set usuRols) {
        this.usuRols = usuRols;
    }
    public Set getPersonas() {
        return this.personas;
    }
    
    public void setPersonas(Set personas) {
        this.personas = personas;
    }
    public Set getDispoUsuariosForProfesor() {
        return this.dispoUsuariosForProfesor;
    }
    
    public void setDispoUsuariosForProfesor(Set dispoUsuariosForProfesor) {
        this.dispoUsuariosForProfesor = dispoUsuariosForProfesor;
    }
    public Set getDispoUsuariosForAdmon() {
        return this.dispoUsuariosForAdmon;
    }
    
    public void setDispoUsuariosForAdmon(Set dispoUsuariosForAdmon) {
        this.dispoUsuariosForAdmon = dispoUsuariosForAdmon;
    }
    public Set getUsuarioProyectosForEstudiante() {
        return this.usuarioProyectosForEstudiante;
    }
    
    public void setUsuarioProyectosForEstudiante(Set usuarioProyectosForEstudiante) {
        this.usuarioProyectosForEstudiante = usuarioProyectosForEstudiante;
    }
    public Set getAsistentes() {
        return this.asistentes;
    }
    
    public void setAsistentes(Set asistentes) {
        this.asistentes = asistentes;
    }
    public Set getCalendarios() {
        return this.calendarios;
    }
    
    public void setCalendarios(Set calendarios) {
        this.calendarios = calendarios;
    }
    public Set getUsuarioProyectosForDirector() {
        return this.usuarioProyectosForDirector;
    }
    
    public void setUsuarioProyectosForDirector(Set usuarioProyectosForDirector) {
        this.usuarioProyectosForDirector = usuarioProyectosForDirector;
    }




}


