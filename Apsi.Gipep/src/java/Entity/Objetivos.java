package Entity;
// Generated 22-jul-2015 14:32:02 by Hibernate Tools 4.3.1



/**
 * Objetivos generated by hbm2java
 */
public class Objetivos  implements java.io.Serializable {


     private long codObjetivo;
     private Proyectos proyectos;
     private String descripcion;

    public Objetivos() {
    }

	
    public Objetivos(long codObjetivo, String descripcion) {
        this.codObjetivo = codObjetivo;
        this.descripcion = descripcion;
    }
    public Objetivos(long codObjetivo, Proyectos proyectos, String descripcion) {
       this.codObjetivo = codObjetivo;
       this.proyectos = proyectos;
       this.descripcion = descripcion;
    }
   
    public long getCodObjetivo() {
        return this.codObjetivo;
    }
    
    public void setCodObjetivo(long codObjetivo) {
        this.codObjetivo = codObjetivo;
    }
    public Proyectos getProyectos() {
        return this.proyectos;
    }
    
    public void setProyectos(Proyectos proyectos) {
        this.proyectos = proyectos;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }




}

