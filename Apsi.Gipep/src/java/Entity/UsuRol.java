package Entity;
// Generated 15/10/2015 01:16:32 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * UsuRol generated by hbm2java
 */
public class UsuRol  implements java.io.Serializable {


     private BigDecimal codUsurol;
     private Rol rol;
     private Usuario usuario;

    public UsuRol() {
    }

    public UsuRol(BigDecimal codUsurol, Rol rol, Usuario usuario) {
       this.codUsurol = codUsurol;
       this.rol = rol;
       this.usuario = usuario;
    }
   
    public BigDecimal getCodUsurol() {
        return this.codUsurol;
    }
    
    public void setCodUsurol(BigDecimal codUsurol) {
        this.codUsurol = codUsurol;
    }
    public Rol getRol() {
        return this.rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }




}


