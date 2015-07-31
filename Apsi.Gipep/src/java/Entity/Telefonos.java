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
 * Telefonos generated by hbm2java
 */
@Entity
@Table(name="telefonos"
    ,schema="public"
)
public class Telefonos  implements java.io.Serializable {


     private BigDecimal codTelefono;
     private Persona persona;
     private String telefono;
     private String ciudad;
     private boolean tipo;

    public Telefonos() {
    }

    public Telefonos(BigDecimal codTelefono, Persona persona, String telefono, String ciudad, boolean tipo) {
       this.codTelefono = codTelefono;
       this.persona = persona;
       this.telefono = telefono;
       this.ciudad = ciudad;
       this.tipo = tipo;
    }
   
     @Id 

    
    @Column(name="cod_telefono", unique=true, nullable=false, precision=131089, scale=0)
    public BigDecimal getCodTelefono() {
        return this.codTelefono;
    }
    
    public void setCodTelefono(BigDecimal codTelefono) {
        this.codTelefono = codTelefono;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idpersona", nullable=false)
    public Persona getPersona() {
        return this.persona;
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    
    @Column(name="telefono", nullable=false)
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
    @Column(name="ciudad", nullable=false)
    public String getCiudad() {
        return this.ciudad;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    
    @Column(name="tipo", nullable=false)
    public boolean isTipo() {
        return this.tipo;
    }
    
    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }




}


