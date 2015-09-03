package Entity;
// Generated 15/10/2015 01:16:32 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Estados generated by hbm2java
 */
public class Estados  implements java.io.Serializable {


     private BigDecimal codigoEstados;
     private String descripcion;
     private Set estadoProyectos = new HashSet(0);
     private Set estadosAsesorias = new HashSet(0);

    public Estados() {
    }

	
    public Estados(BigDecimal codigoEstados, String descripcion) {
        this.codigoEstados = codigoEstados;
        this.descripcion = descripcion;
    }
    public Estados(BigDecimal codigoEstados, String descripcion, Set estadoProyectos, Set estadosAsesorias) {
       this.codigoEstados = codigoEstados;
       this.descripcion = descripcion;
       this.estadoProyectos = estadoProyectos;
       this.estadosAsesorias = estadosAsesorias;
    }
   
    public BigDecimal getCodigoEstados() {
        return this.codigoEstados;
    }
    
    public void setCodigoEstados(BigDecimal codigoEstados) {
        this.codigoEstados = codigoEstados;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Set getEstadoProyectos() {
        return this.estadoProyectos;
    }
    
    public void setEstadoProyectos(Set estadoProyectos) {
        this.estadoProyectos = estadoProyectos;
    }
    public Set getEstadosAsesorias() {
        return this.estadosAsesorias;
    }
    
    public void setEstadosAsesorias(Set estadosAsesorias) {
        this.estadosAsesorias = estadosAsesorias;
    }




}


