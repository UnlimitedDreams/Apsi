package Entity;
// Generated 15/10/2015 01:16:32 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Revisiones generated by hbm2java
 */
public class Revisiones  implements java.io.Serializable {


     private BigDecimal codigoRevision;
     private String descripcion;
     private Date fecha;
     private Set revisionProyectos = new HashSet(0);

    public Revisiones() {
    }

	
    public Revisiones(BigDecimal codigoRevision, String descripcion) {
        this.codigoRevision = codigoRevision;
        this.descripcion = descripcion;
    }
    public Revisiones(BigDecimal codigoRevision, String descripcion, Date fecha, Set revisionProyectos) {
       this.codigoRevision = codigoRevision;
       this.descripcion = descripcion;
       this.fecha = fecha;
       this.revisionProyectos = revisionProyectos;
    }
   
    public BigDecimal getCodigoRevision() {
        return this.codigoRevision;
    }
    
    public void setCodigoRevision(BigDecimal codigoRevision) {
        this.codigoRevision = codigoRevision;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Set getRevisionProyectos() {
        return this.revisionProyectos;
    }
    
    public void setRevisionProyectos(Set revisionProyectos) {
        this.revisionProyectos = revisionProyectos;
    }




}


