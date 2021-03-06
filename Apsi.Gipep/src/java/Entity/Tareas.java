package Entity;
// Generated 15/10/2015 01:16:32 AM by Hibernate Tools 4.3.1



/**
 * Tareas generated by hbm2java
 */
public class Tareas  implements java.io.Serializable {


     private long codTarea;
     private Asesoria asesoria;
     private String responsable;
     private String tarea;

    public Tareas() {
    }

	
    public Tareas(long codTarea, String responsable, String tarea) {
        this.codTarea = codTarea;
        this.responsable = responsable;
        this.tarea = tarea;
    }
    public Tareas(long codTarea, Asesoria asesoria, String responsable, String tarea) {
       this.codTarea = codTarea;
       this.asesoria = asesoria;
       this.responsable = responsable;
       this.tarea = tarea;
    }
   
    public long getCodTarea() {
        return this.codTarea;
    }
    
    public void setCodTarea(long codTarea) {
        this.codTarea = codTarea;
    }
    public Asesoria getAsesoria() {
        return this.asesoria;
    }
    
    public void setAsesoria(Asesoria asesoria) {
        this.asesoria = asesoria;
    }
    public String getResponsable() {
        return this.responsable;
    }
    
    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
    public String getTarea() {
        return this.tarea;
    }
    
    public void setTarea(String tarea) {
        this.tarea = tarea;
    }




}


