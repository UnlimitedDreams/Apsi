package Entity;
// Generated 31-jul-2015 12:11:44 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * RolActividad generated by hbm2java
 */
@Entity
@Table(name="rol_actividad"
    ,schema="public"
)
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
   
     @Id 

    
    @Column(name="cod_rolacti", unique=true, nullable=false, precision=131089, scale=0)
    public BigDecimal getCodRolacti() {
        return this.codRolacti;
    }
    
    public void setCodRolacti(BigDecimal codRolacti) {
        this.codRolacti = codRolacti;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="codigo_actividad", nullable=false)
    public Actividades getActividades() {
        return this.actividades;
    }
    
    public void setActividades(Actividades actividades) {
        this.actividades = actividades;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cod_rol", nullable=false)
    public Rol getRol() {
        return this.rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }




}


