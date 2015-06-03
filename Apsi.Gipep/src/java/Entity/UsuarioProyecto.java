package Entity;
// Generated 02-jun-2015 13:38:41 by Hibernate Tools 3.6.0


import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * UsuarioProyecto generated by hbm2java
 */
@Entity
@Table(name="usuario_proyecto"
    ,schema="public"
)
public class UsuarioProyecto  implements java.io.Serializable {


     private BigDecimal codUsuproyecto;
     private Usuario usuarioByEstudiante;
     private Usuario usuarioByDirector;
     private Proyectos proyectos;

    public UsuarioProyecto() {
    }

    public UsuarioProyecto(BigDecimal codUsuproyecto, Usuario usuarioByEstudiante, Usuario usuarioByDirector, Proyectos proyectos) {
       this.codUsuproyecto = codUsuproyecto;
       this.usuarioByEstudiante = usuarioByEstudiante;
       this.usuarioByDirector = usuarioByDirector;
       this.proyectos = proyectos;
    }
   
     @Id 

    
    @Column(name="cod_usuproyecto", unique=true, nullable=false, precision=131089, scale=0)
    public BigDecimal getCodUsuproyecto() {
        return this.codUsuproyecto;
    }
    
    public void setCodUsuproyecto(BigDecimal codUsuproyecto) {
        this.codUsuproyecto = codUsuproyecto;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="estudiante", nullable=false)
    public Usuario getUsuarioByEstudiante() {
        return this.usuarioByEstudiante;
    }
    
    public void setUsuarioByEstudiante(Usuario usuarioByEstudiante) {
        this.usuarioByEstudiante = usuarioByEstudiante;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="director", nullable=false)
    public Usuario getUsuarioByDirector() {
        return this.usuarioByDirector;
    }
    
    public void setUsuarioByDirector(Usuario usuarioByDirector) {
        this.usuarioByDirector = usuarioByDirector;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="codigo_proyecto", nullable=false)
    public Proyectos getProyectos() {
        return this.proyectos;
    }
    
    public void setProyectos(Proyectos proyectos) {
        this.proyectos = proyectos;
    }




}


