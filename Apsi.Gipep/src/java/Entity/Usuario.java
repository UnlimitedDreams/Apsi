package Entity;
// Generated 02-jun-2015 13:38:41 by Hibernate Tools 3.6.0

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
@Table(name = "usuario", schema = "public"
)
public class Usuario implements java.io.Serializable {

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

    @Id

    @Column(name = "pege_id", unique = true, nullable = false, precision = 131089, scale = 0)
    public BigDecimal getPegeId() {
        return this.pegeId;
    }

    public void setPegeId(BigDecimal pegeId) {
        this.pegeId = pegeId;
    }

    @Column(name = "contrasea", nullable = false, length = 40)
    public String getContrasea() {
        return this.contrasea;
    }

    public void setContrasea(String contrasea) {
        this.contrasea = contrasea;
    }

    @Column(name = "usuario", nullable = false)
    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    public Set getUsuRols() {
        return this.usuRols;
    }

    public void setUsuRols(Set usuRols) {
        this.usuRols = usuRols;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    public Set getPersonas() {
        return this.personas;
    }

    public void setPersonas(Set personas) {
        this.personas = personas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuarioByProfesor")
    public Set getDispoUsuariosForProfesor() {
        return this.dispoUsuariosForProfesor;
    }

    public void setDispoUsuariosForProfesor(Set dispoUsuariosForProfesor) {
        this.dispoUsuariosForProfesor = dispoUsuariosForProfesor;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuarioByAdmon")
    public Set getDispoUsuariosForAdmon() {
        return this.dispoUsuariosForAdmon;
    }

    public void setDispoUsuariosForAdmon(Set dispoUsuariosForAdmon) {
        this.dispoUsuariosForAdmon = dispoUsuariosForAdmon;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuarioByEstudiante")
    public Set getUsuarioProyectosForEstudiante() {
        return this.usuarioProyectosForEstudiante;
    }

    public void setUsuarioProyectosForEstudiante(Set usuarioProyectosForEstudiante) {
        this.usuarioProyectosForEstudiante = usuarioProyectosForEstudiante;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    public Set getAsistentes() {
        return this.asistentes;
    }

    public void setAsistentes(Set asistentes) {
        this.asistentes = asistentes;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    public Set getCalendarios() {
        return this.calendarios;
    }

    public void setCalendarios(Set calendarios) {
        this.calendarios = calendarios;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuarioByDirector")
    public Set getUsuarioProyectosForDirector() {
        return this.usuarioProyectosForDirector;
    }

    public void setUsuarioProyectosForDirector(Set usuarioProyectosForDirector) {
        this.usuarioProyectosForDirector = usuarioProyectosForDirector;
    }

    @Override
    public String toString() {
        return "Pege ID:" + pegeId + " Usuario: " + usuario + " Contraseña" + contrasea;
    }

}
