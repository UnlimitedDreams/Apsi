package Entity;
// Generated 18-ago-2015 14:13:44 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Aofestivo generated by hbm2java
 */
public class Aofestivo  implements java.io.Serializable {


     private BigDecimal codAo;
     private String ao;
     private Set festivoses = new HashSet(0);

    public Aofestivo() {
    }

	
    public Aofestivo(BigDecimal codAo, String ao) {
        this.codAo = codAo;
        this.ao = ao;
    }
    public Aofestivo(BigDecimal codAo, String ao, Set festivoses) {
       this.codAo = codAo;
       this.ao = ao;
       this.festivoses = festivoses;
    }
   
    public BigDecimal getCodAo() {
        return this.codAo;
    }
    
    public void setCodAo(BigDecimal codAo) {
        this.codAo = codAo;
    }
    public String getAo() {
        return this.ao;
    }
    
    public void setAo(String ao) {
        this.ao = ao;
    }
    public Set getFestivoses() {
        return this.festivoses;
    }
    
    public void setFestivoses(Set festivoses) {
        this.festivoses = festivoses;
    }




}


