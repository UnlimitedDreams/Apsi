package Entity;
// Generated 15/10/2015 01:16:32 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Rol generated by hbm2java
 */
public class Rol  implements java.io.Serializable {


     private BigDecimal codRol;
     private String estado;
     private String nombre;
     private Set rolActividads = new HashSet(0);
     private Set usuRols = new HashSet(0);

    public Rol() {
    }

	
    public Rol(BigDecimal codRol, String estado, String nombre) {
        this.codRol = codRol;
        this.estado = estado;
        this.nombre = nombre;
    }
    public Rol(BigDecimal codRol, String estado, String nombre, Set rolActividads, Set usuRols) {
       this.codRol = codRol;
       this.estado = estado;
       this.nombre = nombre;
       this.rolActividads = rolActividads;
       this.usuRols = usuRols;
    }
   
    public BigDecimal getCodRol() {
        return this.codRol;
    }
    
    public void setCodRol(BigDecimal codRol) {
        this.codRol = codRol;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Set getRolActividads() {
        return this.rolActividads;
    }
    
    public void setRolActividads(Set rolActividads) {
        this.rolActividads = rolActividads;
    }
    public Set getUsuRols() {
        return this.usuRols;
    }
    
    public void setUsuRols(Set usuRols) {
        this.usuRols = usuRols;
    }




}


