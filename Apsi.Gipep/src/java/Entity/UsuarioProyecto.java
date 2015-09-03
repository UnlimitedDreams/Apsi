package Entity;
// Generated 15/10/2015 01:16:32 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * UsuarioProyecto generated by hbm2java
 */
public class UsuarioProyecto  implements java.io.Serializable {


     private BigDecimal codUsuproyecto;
     private Proyectos proyectos;
     private Usuario usuarioByEstudiante;
     private Usuario usuarioByDirector;

    public UsuarioProyecto() {
    }

    public UsuarioProyecto(BigDecimal codUsuproyecto, Proyectos proyectos, Usuario usuarioByEstudiante, Usuario usuarioByDirector) {
       this.codUsuproyecto = codUsuproyecto;
       this.proyectos = proyectos;
       this.usuarioByEstudiante = usuarioByEstudiante;
       this.usuarioByDirector = usuarioByDirector;
    }
   
    public BigDecimal getCodUsuproyecto() {
        return this.codUsuproyecto;
    }
    
    public void setCodUsuproyecto(BigDecimal codUsuproyecto) {
        this.codUsuproyecto = codUsuproyecto;
    }
    public Proyectos getProyectos() {
        return this.proyectos;
    }
    
    public void setProyectos(Proyectos proyectos) {
        this.proyectos = proyectos;
    }
    public Usuario getUsuarioByEstudiante() {
        return this.usuarioByEstudiante;
    }
    
    public void setUsuarioByEstudiante(Usuario usuarioByEstudiante) {
        this.usuarioByEstudiante = usuarioByEstudiante;
    }
    public Usuario getUsuarioByDirector() {
        return this.usuarioByDirector;
    }
    
    public void setUsuarioByDirector(Usuario usuarioByDirector) {
        this.usuarioByDirector = usuarioByDirector;
    }




}


