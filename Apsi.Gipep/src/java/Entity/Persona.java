package Entity;
// Generated 18-ago-2015 14:13:44 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Persona generated by hbm2java
 */
public class Persona  implements java.io.Serializable {


     private BigDecimal idpersona;
     private Usuario usuario;
     private String nombres;
     private String apellidos;
     private Set telefonoses = new HashSet(0);
     private Set correospersonas = new HashSet(0);

    public Persona() {
    }

	
    public Persona(BigDecimal idpersona, Usuario usuario, String nombres, String apellidos) {
        this.idpersona = idpersona;
        this.usuario = usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }
    public Persona(BigDecimal idpersona, Usuario usuario, String nombres, String apellidos, Set telefonoses, Set correospersonas) {
       this.idpersona = idpersona;
       this.usuario = usuario;
       this.nombres = nombres;
       this.apellidos = apellidos;
       this.telefonoses = telefonoses;
       this.correospersonas = correospersonas;
    }
   
    public BigDecimal getIdpersona() {
        return this.idpersona;
    }
    
    public void setIdpersona(BigDecimal idpersona) {
        this.idpersona = idpersona;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String getNombres() {
        return this.nombres;
    }
    
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidos() {
        return this.apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public Set getTelefonoses() {
        return this.telefonoses;
    }
    
    public void setTelefonoses(Set telefonoses) {
        this.telefonoses = telefonoses;
    }
    public Set getCorreospersonas() {
        return this.correospersonas;
    }
    
    public void setCorreospersonas(Set correospersonas) {
        this.correospersonas = correospersonas;
    }




}


