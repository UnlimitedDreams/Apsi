package Entity;
// Generated 18-ago-2015 14:13:44 by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * RolActividad generated by hbm2java
 */
public class RolActividad  implements java.io.Serializable {


     private BigDecimal codRolacti;
     private Actividades actividades;
     private Rol rol;

    public RolActividad() {
    }

    public RolActividad(BigDecimal codRolacti, Actividades actividades, Rol rol) {
       this.codRolacti = codRolacti;
       this.actividades = actividades;
       this.rol = rol;
    }
   
    public BigDecimal getCodRolacti() {
        return this.codRolacti;
    }
    
    public void setCodRolacti(BigDecimal codRolacti) {
        this.codRolacti = codRolacti;
    }
    public Actividades getActividades() {
        return this.actividades;
    }
    
    public void setActividades(Actividades actividades) {
        this.actividades = actividades;
    }
    public Rol getRol() {
        return this.rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }




}


