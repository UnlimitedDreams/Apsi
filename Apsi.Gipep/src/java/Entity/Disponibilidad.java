package Entity;
// Generated 31-jul-2015 12:11:44 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Disponibilidad generated by hbm2java
 */
@Entity
@Table(name="disponibilidad"
    ,schema="public"
)
public class Disponibilidad  implements java.io.Serializable {


     private BigDecimal codDis;
     private Date fechaInicial;
     private Date fechaFinal;
     private BigDecimal rango;
     private BigDecimal numHoras;
     private BigDecimal periodo;
     private String estado;
     private Long horasCumplidas;
     private Set asesorias = new HashSet(0);
     private Set dispoUsuarios = new HashSet(0);
     private Set nivelDisponibles = new HashSet(0);
     private Set dias = new HashSet(0);

    public Disponibilidad() {
    }

	
    public Disponibilidad(BigDecimal codDis, Date fechaInicial, Date fechaFinal, BigDecimal rango, BigDecimal numHoras, BigDecimal periodo) {
        this.codDis = codDis;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.rango = rango;
        this.numHoras = numHoras;
        this.periodo = periodo;
    }
    public Disponibilidad(BigDecimal codDis, Date fechaInicial, Date fechaFinal, BigDecimal rango, BigDecimal numHoras, BigDecimal periodo, String estado, Long horasCumplidas, Set asesorias, Set dispoUsuarios, Set nivelDisponibles, Set dias) {
       this.codDis = codDis;
       this.fechaInicial = fechaInicial;
       this.fechaFinal = fechaFinal;
       this.rango = rango;
       this.numHoras = numHoras;
       this.periodo = periodo;
       this.estado = estado;
       this.horasCumplidas = horasCumplidas;
       this.asesorias = asesorias;
       this.dispoUsuarios = dispoUsuarios;
       this.nivelDisponibles = nivelDisponibles;
       this.dias = dias;
    }
   
     @Id 

    
    @Column(name="cod_dis", unique=true, nullable=false, precision=131089, scale=0)
    public BigDecimal getCodDis() {
        return this.codDis;
    }
    
    public void setCodDis(BigDecimal codDis) {
        this.codDis = codDis;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha_inicial", nullable=false, length=13)
    public Date getFechaInicial() {
        return this.fechaInicial;
    }
    
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha_final", nullable=false, length=13)
    public Date getFechaFinal() {
        return this.fechaFinal;
    }
    
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    
    @Column(name="rango", nullable=false, precision=131089, scale=0)
    public BigDecimal getRango() {
        return this.rango;
    }
    
    public void setRango(BigDecimal rango) {
        this.rango = rango;
    }

    
    @Column(name="num_horas", nullable=false, precision=131089, scale=0)
    public BigDecimal getNumHoras() {
        return this.numHoras;
    }
    
    public void setNumHoras(BigDecimal numHoras) {
        this.numHoras = numHoras;
    }

    
    @Column(name="periodo", nullable=false, precision=131089, scale=0)
    public BigDecimal getPeriodo() {
        return this.periodo;
    }
    
    public void setPeriodo(BigDecimal periodo) {
        this.periodo = periodo;
    }

    
    @Column(name="estado", length=10)
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    @Column(name="horas_cumplidas", precision=10, scale=0)
    public Long getHorasCumplidas() {
        return this.horasCumplidas;
    }
    
    public void setHorasCumplidas(Long horasCumplidas) {
        this.horasCumplidas = horasCumplidas;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="disponibilidad")
    public Set getAsesorias() {
        return this.asesorias;
    }
    
    public void setAsesorias(Set asesorias) {
        this.asesorias = asesorias;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="disponibilidad")
    public Set getDispoUsuarios() {
        return this.dispoUsuarios;
    }
    
    public void setDispoUsuarios(Set dispoUsuarios) {
        this.dispoUsuarios = dispoUsuarios;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="disponibilidad")
    public Set getNivelDisponibles() {
        return this.nivelDisponibles;
    }
    
    public void setNivelDisponibles(Set nivelDisponibles) {
        this.nivelDisponibles = nivelDisponibles;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="disponibilidad")
    public Set getDias() {
        return this.dias;
    }
    
    public void setDias(Set dias) {
        this.dias = dias;
    }




}


