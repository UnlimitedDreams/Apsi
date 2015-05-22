package Entity;
// Generated 19-may-2015 18:40:29 by Hibernate Tools 3.6.0


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name="usuario"
    ,schema="public"
)
public class Usuario  implements java.io.Serializable {


     private BigDecimal pegeId;
     private String contrasea;
     private String usuario;
     private Set dispoUsuarios = new HashSet(0);
     private Set usuarioProyectos = new HashSet(0);
     private Set ajustes = new HashSet(0);
     private Set asistentes = new HashSet(0);
     private Set rols = new HashSet(0);
     private Set calendarios = new HashSet(0);
     private Set logses = new HashSet(0);

    public Usuario() {
    }

	
    public Usuario(BigDecimal pegeId, String contrasea, String usuario) {
        this.pegeId = pegeId;
        this.contrasea = contrasea;
        this.usuario = usuario;
    }
    public Usuario(BigDecimal pegeId, String contrasea, String usuario, Set dispoUsuarios, Set usuarioProyectos, Set ajustes, Set asistentes, Set rols, Set calendarios, Set logses) {
       this.pegeId = pegeId;
       this.contrasea = contrasea;
       this.usuario = usuario;
       this.dispoUsuarios = dispoUsuarios;
       this.usuarioProyectos = usuarioProyectos;
       this.ajustes = ajustes;
       this.asistentes = asistentes;
       this.rols = rols;
       this.calendarios = calendarios;
       this.logses = logses;
    }
   
     @Id 

    
    @Column(name="pege_id", unique=true, nullable=false, precision=131089, scale=0)
    public BigDecimal getPegeId() {
        return this.pegeId;
    }
    
    public void setPegeId(BigDecimal pegeId) {
        this.pegeId = pegeId;
    }

    
    @Column(name="contrasea", nullable=false, length=40)
    public String getContrasea() {
        return this.contrasea;
    }
    
    public void setContrasea(String contrasea) {
        this.contrasea = contrasea;
    }

    
    @Column(name="usuario", nullable=false)
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getDispoUsuarios() {
        return this.dispoUsuarios;
    }
    
    public void setDispoUsuarios(Set dispoUsuarios) {
        this.dispoUsuarios = dispoUsuarios;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getUsuarioProyectos() {
        return this.usuarioProyectos;
    }
    
    public void setUsuarioProyectos(Set usuarioProyectos) {
        this.usuarioProyectos = usuarioProyectos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getAjustes() {
        return this.ajustes;
    }
    
    public void setAjustes(Set ajustes) {
        this.ajustes = ajustes;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getAsistentes() {
        return this.asistentes;
    }
    
    public void setAsistentes(Set asistentes) {
        this.asistentes = asistentes;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getRols() {
        return this.rols;
    }
    
    public void setRols(Set rols) {
        this.rols = rols;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getCalendarios() {
        return this.calendarios;
    }
    
    public void setCalendarios(Set calendarios) {
        this.calendarios = calendarios;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getLogses() {
        return this.logses;
    }
    
    public void setLogses(Set logses) {
        this.logses = logses;
    }




}


