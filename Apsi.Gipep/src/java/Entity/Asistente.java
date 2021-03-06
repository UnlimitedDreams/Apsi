package Entity;
// Generated 15/10/2015 01:16:32 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * Asistente generated by hbm2java
 */
public class Asistente  implements java.io.Serializable {


     private BigDecimal codAsis;
     private Asesoria asesoria;
     private Usuario usuario;
     private String asistencia;

    public Asistente() {
    }

    public Asistente(BigDecimal codAsis, Asesoria asesoria, Usuario usuario, String asistencia) {
       this.codAsis = codAsis;
       this.asesoria = asesoria;
       this.usuario = usuario;
       this.asistencia = asistencia;
    }
   
    public BigDecimal getCodAsis() {
        return this.codAsis;
    }
    
    public void setCodAsis(BigDecimal codAsis) {
        this.codAsis = codAsis;
    }
    public Asesoria getAsesoria() {
        return this.asesoria;
    }
    
    public void setAsesoria(Asesoria asesoria) {
        this.asesoria = asesoria;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String getAsistencia() {
        return this.asistencia;
    }
    
    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }




}


