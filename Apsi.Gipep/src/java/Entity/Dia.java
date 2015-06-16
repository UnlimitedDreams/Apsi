package Entity;
// Generated 02-jun-2015 13:38:41 by Hibernate Tools 3.6.0

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Dia generated by hbm2java
 */
@Entity
@Table(name = "dia", schema = "public"
)
public class Dia implements java.io.Serializable {

    private BigDecimal codigoDia;
    private Disponibilidad disponibilidad;
    private String horaInicial;
    private String horaFinal;
    private String dia;

    public Dia() {
    }

    public Dia(BigDecimal codigoDia, Disponibilidad disponibilidad, String horaInicial, String horaFinal, String dia) {
        this.codigoDia = codigoDia;
        this.disponibilidad = disponibilidad;
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
        this.dia = dia;
    }

    @Id

    @Column(name = "codigo_dia", unique = true, nullable = false, precision = 131089, scale = 0)
    public BigDecimal getCodigoDia() {
        return this.codigoDia;
    }

    public void setCodigoDia(BigDecimal codigoDia) {
        this.codigoDia = codigoDia;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_dis", nullable = false)
    public Disponibilidad getDisponibilidad() {
        return this.disponibilidad;
    }

    public void setDisponibilidad(Disponibilidad disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    @Column(name = "hora_inicial", nullable = false)
    public String getHoraInicial() {
        return this.horaInicial;
    }

    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }

    @Column(name = "hora_final", nullable = false)
    public String getHoraFinal() {
        return this.horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    @Column(name = "dia", nullable = false)
    public String getDia() {
        return this.dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        return "" + codigoDia;
    }

}