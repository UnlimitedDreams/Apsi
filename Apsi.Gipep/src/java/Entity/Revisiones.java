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
 * Revisiones generated by hbm2java
 */
@Entity
@Table(name="revisiones"
    ,schema="public"
)
public class Revisiones  implements java.io.Serializable {


     private BigDecimal codigoRevision;
     private String descripcion;
     private Set revisionProyectos = new HashSet(0);

    public Revisiones() {
    }

	
    public Revisiones(BigDecimal codigoRevision, String descripcion) {
        this.codigoRevision = codigoRevision;
        this.descripcion = descripcion;
    }
    public Revisiones(BigDecimal codigoRevision, String descripcion, Set revisionProyectos) {
       this.codigoRevision = codigoRevision;
       this.descripcion = descripcion;
       this.revisionProyectos = revisionProyectos;
    }
   
     @Id 

    
    @Column(name="codigo_revision", unique=true, nullable=false, precision=131089, scale=0)
    public BigDecimal getCodigoRevision() {
        return this.codigoRevision;
    }
    
    public void setCodigoRevision(BigDecimal codigoRevision) {
        this.codigoRevision = codigoRevision;
    }

    
    @Column(name="descripcion", nullable=false, length=500)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="revisiones")
    public Set getRevisionProyectos() {
        return this.revisionProyectos;
    }
    
    public void setRevisionProyectos(Set revisionProyectos) {
        this.revisionProyectos = revisionProyectos;
    }




}


