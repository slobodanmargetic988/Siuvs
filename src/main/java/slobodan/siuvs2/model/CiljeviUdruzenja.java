/*
 * 
 */
package slobodan.siuvs2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author Slobodan
 */

@Entity
@Table(name = "ciljevi_udruzenja")
@EntityListeners(AuditingEntityListener.class)
public class CiljeviUdruzenja {
      @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
      
      
         @Column(name = "naziv")
    private String naziv;
      
    @ManyToOne
    @JoinColumn(name = "udruzenje_id")
    private KartonUdruzenja kartonudruzenja;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public KartonUdruzenja getKartonUdruzenja() {
        return kartonudruzenja;
    }

    public void setKartonUdruzenja(KartonUdruzenja kartonUdruzenja) {
        this.kartonudruzenja = kartonUdruzenja;
    }
    
    
      
}
