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
 * RevisionProyecto generated by hbm2java
 */
@Entity
@Table(name="revision_proyecto"
    ,schema="public"
)
public class RevisionProyecto  implements java.io.Serializable {


     private BigDecimal codRevisionproyecto;
     private Revisiones revisiones;
     private Proyectos proyectos;

    public RevisionProyecto() {
    }

    public RevisionProyecto(BigDecimal codRevisionproyecto, Revisiones revisiones, Proyectos proyectos) {
       this.codRevisionproyecto = codRevisionproyecto;
       this.revisiones = revisiones;
       this.proyectos = proyectos;
    }
   
     @Id 

    
    @Column(name="cod_revisionproyecto", unique=true, nullable=false, precision=131089, scale=0)
    public BigDecimal getCodRevisionproyecto() {
        return this.codRevisionproyecto;
    }
    
    public void setCodRevisionproyecto(BigDecimal codRevisionproyecto) {
        this.codRevisionproyecto = codRevisionproyecto;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="codigo_revision", nullable=false)
    public Revisiones getRevisiones() {
        return this.revisiones;
    }
    
    public void setRevisiones(Revisiones revisiones) {
        this.revisiones = revisiones;
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


