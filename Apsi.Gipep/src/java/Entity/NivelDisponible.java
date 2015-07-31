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
 * NivelDisponible generated by hbm2java
 */
@Entity
@Table(name="nivel_disponible"
    ,schema="public"
)
public class NivelDisponible  implements java.io.Serializable {


     private BigDecimal codigoNivel;
     private Disponibilidad disponibilidad;
     private String semestre;

    public NivelDisponible() {
    }

    public NivelDisponible(BigDecimal codigoNivel, Disponibilidad disponibilidad, String semestre) {
       this.codigoNivel = codigoNivel;
       this.disponibilidad = disponibilidad;
       this.semestre = semestre;
    }
   
     @Id 

    
    @Column(name="codigo_nivel", unique=true, nullable=false, precision=131089, scale=0)
    public BigDecimal getCodigoNivel() {
        return this.codigoNivel;
    }
    
    public void setCodigoNivel(BigDecimal codigoNivel) {
        this.codigoNivel = codigoNivel;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cod_dis", nullable=false)
    public Disponibilidad getDisponibilidad() {
        return this.disponibilidad;
    }
    
    public void setDisponibilidad(Disponibilidad disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    
    @Column(name="semestre", nullable=false, length=2)
    public String getSemestre() {
        return this.semestre;
    }
    
    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }




}


