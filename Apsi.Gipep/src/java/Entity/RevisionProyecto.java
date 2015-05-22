package Entity;
// Generated 19-may-2015 18:40:29 by Hibernate Tools 3.6.0


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


     private BigDecimal reviProyec;
     private Revisiones revisiones;
     private Proyectos proyectos;

    public RevisionProyecto() {
    }

    public RevisionProyecto(BigDecimal reviProyec, Revisiones revisiones, Proyectos proyectos) {
       this.reviProyec = reviProyec;
       this.revisiones = revisiones;
       this.proyectos = proyectos;
    }
   
     @Id 

    
    @Column(name="revi_proyec", unique=true, nullable=false, precision=131089, scale=0)
    public BigDecimal getReviProyec() {
        return this.reviProyec;
    }
    
    public void setReviProyec(BigDecimal reviProyec) {
        this.reviProyec = reviProyec;
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


