package Entity;
// Generated 02-jun-2015 13:38:41 by Hibernate Tools 3.6.0


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Ubicacion generated by hbm2java
 */
@Entity
@Table(name="ubicacion"
    ,schema="public"
)
public class Ubicacion  implements java.io.Serializable {


     private BigDecimal ubicacion;
     private String sede;
     private String salon;
     private Set asesorias = new HashSet(0);

    public Ubicacion() {
    }

	
    public Ubicacion(BigDecimal ubicacion, String sede, String salon) {
        this.ubicacion = ubicacion;
        this.sede = sede;
        this.salon = salon;
    }
    public Ubicacion(BigDecimal ubicacion, String sede, String salon, Set asesorias) {
       this.ubicacion = ubicacion;
       this.sede = sede;
       this.salon = salon;
       this.asesorias = asesorias;
    }
   
     @Id 

    
    @Column(name="ubicacion", unique=true, nullable=false, precision=131089, scale=0)
    public BigDecimal getUbicacion() {
        return this.ubicacion;
    }
    
    public void setUbicacion(BigDecimal ubicacion) {
        this.ubicacion = ubicacion;
    }

    
    @Column(name="sede", nullable=false, length=15)
    public String getSede() {
        return this.sede;
    }
    
    public void setSede(String sede) {
        this.sede = sede;
    }

    
    @Column(name="salon", nullable=false, length=10)
    public String getSalon() {
        return this.salon;
    }
    
    public void setSalon(String salon) {
        this.salon = salon;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="ubicacion")
    public Set getAsesorias() {
        return this.asesorias;
    }
    
    public void setAsesorias(Set asesorias) {
        this.asesorias = asesorias;
    }




}

