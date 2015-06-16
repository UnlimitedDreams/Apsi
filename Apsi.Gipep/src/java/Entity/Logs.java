package Entity;
// Generated 19-may-2015 18:40:29 by Hibernate Tools 3.6.0


import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Logs generated by hbm2java
 */
@Entity
@Table(name="logs"
    ,schema="public"
)
public class Logs  implements java.io.Serializable {


     private LogsId id;
     private Usuario usuario;
     private Date fechaIngreso;
     private Date fechaSalida;

    public Logs() {
    }

    public Logs(LogsId id, Usuario usuario, Date fechaIngreso, Date fechaSalida) {
       this.id = id;
       this.usuario = usuario;
       this.fechaIngreso = fechaIngreso;
       this.fechaSalida = fechaSalida;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="codLogs", column=@Column(name="cod_logs", nullable=false, precision=131089, scale=0) ), 
        @AttributeOverride(name="pegeId", column=@Column(name="pege_id", nullable=false, precision=131089, scale=0) ) } )
    public LogsId getId() {
        return this.id;
    }
    
    public void setId(LogsId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pege_id", nullable=false, insertable=false, updatable=false)
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha_ingreso", nullable=false, length=13)
    public Date getFechaIngreso() {
        return this.fechaIngreso;
    }
    
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha_salida", nullable=false, length=13)
    public Date getFechaSalida() {
        return this.fechaSalida;
    }
    
    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }




}

