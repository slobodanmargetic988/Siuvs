package slobodan.siuvs2.model;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Entity
@Table(name = "mobileapp")
@EntityListeners(AuditingEntityListener.class)
public class Mobileappdata {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

   

    @Column(name = "opstina")
    private String opstina;

    @Column(name = "opasnost")
    private String opasnost;
    
    @Column(name = "klasifikacija")
    private String klasifikacija;

     @Column(name = "tekst")
    private String tekst;
     
      @Column(name = "link")
    private String link;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpstina() {
        return opstina;
    }

    public void setOpstina(String opstina) {
        this.opstina = opstina;
    }

    public String getOpasnost() {
        return opasnost;
    }

    public void setOpasnost(String opasnost) {
        this.opasnost = opasnost;
    }

    public String getKlasifikacija() {
        return klasifikacija;
    }

    public void setKlasifikacija(String klasifikacija) {
        this.klasifikacija = klasifikacija;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
      
      
}
