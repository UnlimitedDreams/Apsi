package Entity;
// Generated 18-ago-2015 14:13:44 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Proyectos generated by hbm2java
 */
public class Proyectos  implements java.io.Serializable {


     private BigDecimal codigoProyecto;
     private TipoProyecto tipoProyecto;
     private String nombre;
     private Long calificacion;
     private String porcentaje;
     private Date fechaInicio;
     private Date fechaFinal;
     private Set calificacions = new HashSet(0);
     private Set revisionProyectos = new HashSet(0);
     private Set usuarioProyectos = new HashSet(0);
     private Set estadoProyectos = new HashSet(0);
     private Set versioneses = new HashSet(0);
     private Set objetivoses = new HashSet(0);

    public Proyectos() {
    }

	
    public Proyectos(BigDecimal codigoProyecto, String nombre) {
        this.codigoProyecto = codigoProyecto;
        this.nombre = nombre;
    }
    public Proyectos(BigDecimal codigoProyecto, TipoProyecto tipoProyecto, String nombre, Long calificacion, String porcentaje, Date fechaInicio, Date fechaFinal, Set calificacions, Set revisionProyectos, Set usuarioProyectos, Set estadoProyectos, Set versioneses, Set objetivoses) {
       this.codigoProyecto = codigoProyecto;
       this.tipoProyecto = tipoProyecto;
       this.nombre = nombre;
       this.calificacion = calificacion;
       this.porcentaje = porcentaje;
       this.fechaInicio = fechaInicio;
       this.fechaFinal = fechaFinal;
       this.calificacions = calificacions;
       this.revisionProyectos = revisionProyectos;
       this.usuarioProyectos = usuarioProyectos;
       this.estadoProyectos = estadoProyectos;
       this.versioneses = versioneses;
       this.objetivoses = objetivoses;
    }
   
    public BigDecimal getCodigoProyecto() {
        return this.codigoProyecto;
    }
    
    public void setCodigoProyecto(BigDecimal codigoProyecto) {
        this.codigoProyecto = codigoProyecto;
    }
    public TipoProyecto getTipoProyecto() {
        return this.tipoProyecto;
    }
    
    public void setTipoProyecto(TipoProyecto tipoProyecto) {
        this.tipoProyecto = tipoProyecto;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Long getCalificacion() {
        return this.calificacion;
    }
    
    public void setCalificacion(Long calificacion) {
        this.calificacion = calificacion;
    }
    public String getPorcentaje() {
        return this.porcentaje;
    }
    
    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }
    public Date getFechaInicio() {
        return this.fechaInicio;
    }
    
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public Date getFechaFinal() {
        return this.fechaFinal;
    }
    
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
    public Set getCalificacions() {
        return this.calificacions;
    }
    
    public void setCalificacions(Set calificacions) {
        this.calificacions = calificacions;
    }
    public Set getRevisionProyectos() {
        return this.revisionProyectos;
    }
    
    public void setRevisionProyectos(Set revisionProyectos) {
        this.revisionProyectos = revisionProyectos;
    }
    public Set getUsuarioProyectos() {
        return this.usuarioProyectos;
    }
    
    public void setUsuarioProyectos(Set usuarioProyectos) {
        this.usuarioProyectos = usuarioProyectos;
    }
    public Set getEstadoProyectos() {
        return this.estadoProyectos;
    }
    
    public void setEstadoProyectos(Set estadoProyectos) {
        this.estadoProyectos = estadoProyectos;
    }
    public Set getVersioneses() {
        return this.versioneses;
    }
    
    public void setVersioneses(Set versioneses) {
        this.versioneses = versioneses;
    }
    public Set getObjetivoses() {
        return this.objetivoses;
    }
    
    public void setObjetivoses(Set objetivoses) {
        this.objetivoses = objetivoses;
    }




}


