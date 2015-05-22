package Entity;
// Generated 19-may-2015 18:40:29 by Hibernate Tools 3.6.0


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Rol generated by hbm2java
 */
@Entity
@Table(name="rol"
    ,schema="public"
)
public class Rol  implements java.io.Serializable {


     private BigDecimal codRol;
     private Usuario usuario;
     private String estado;
     private String nombre;
     private Set rolActividads = new HashSet(0);

    public Rol() {
    }

	
    public Rol(BigDecimal codRol, Usuario usuario, String estado, String nombre) {
        this.codRol = codRol;
        this.usuario = usuario;
        this.estado = estado;
        this.nombre = nombre;
    }
    public Rol(BigDecimal codRol, Usuario usuario, String estado, String nombre, Set rolActividads) {
       this.codRol = codRol;
       this.usuario = usuario;
       this.estado = estado;
       this.nombre = nombre;
       this.rolActividads = rolActividads;
    }
   
     @Id 

    
    @Column(name="cod_rol", unique=true, nullable=false, precision=131089, scale=0)
    public BigDecimal getCodRol() {
        return this.codRol;
    }
    
    public void setCodRol(BigDecimal codRol) {
        this.codRol = codRol;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pege_id", nullable=false)
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    @Column(name="estado", nullable=false, length=40)
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    @Column(name="nombre", nullable=false)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="rol")
    public Set getRolActividads() {
        return this.rolActividads;
    }
    
    public void setRolActividads(Set rolActividads) {
        this.rolActividads = rolActividads;
    }




}


