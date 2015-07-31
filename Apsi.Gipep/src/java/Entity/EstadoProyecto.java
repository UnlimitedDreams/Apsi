package Entity;
// Generated 31-jul-2015 12:11:44 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * EstadoProyecto generated by hbm2java
 */
@Entity
@Table(name="estado_proyecto"
    ,schema="public"
)
public class EstadoProyecto  implements java.io.Serializable {


     private BigDecimal codEstadoproyec;
     private Estados estados;
     private Proyectos proyectos;

    public EstadoProyecto() {
    }

    public EstadoProyecto(BigDecimal codEstadoproyec, Estados estados, Proyectos proyectos) {
       this.codEstadoproyec = codEstadoproyec;
       this.estados = estados;
       this.proyectos = proyectos;
    }
   
     @Id 

    
    @Column(name="cod_estadoproyec", unique=true, nullable=false, precision=131089, scale=0)
    public BigDecimal getCodEstadoproyec() {
        return this.codEstadoproyec;
    }
    
    public void setCodEstadoproyec(BigDecimal codEstadoproyec) {
        this.codEstadoproyec = codEstadoproyec;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="codigo_estados", nullable=false)
    public Estados getEstados() {
        return this.estados;
    }
    
    public void setEstados(Estados estados) {
        this.estados = estados;
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


