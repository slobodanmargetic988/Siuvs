
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
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

@Entity
@Table(name = "clanovi_udruzenja")
@EntityListeners(AuditingEntityListener.class)
public class ClanoviUdruzenja {
    
      @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
      
        @ManyToOne
    @JoinColumn(name = "kartonudruzenja_id")
    private KartonUdruzenja kartonudruzenja;
        
        @Column(name = "broj")
    private Integer broj;
        
        @ManyToOne
    @JoinColumn(name = "specijalnost_id")
    private Specijalnost specijalnost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public KartonUdruzenja getKartonUdruzenja() {
        return kartonudruzenja;
    }

    public void setKartonUdruzenja(KartonUdruzenja kartonUdruzenja) {
        this.kartonudruzenja = kartonUdruzenja;
    }

    public Integer getBroj() {
        return broj;
    }

    public void setBroj(Integer broj) {
        this.broj = broj;
    }

    public Specijalnost getSpecijalnost() {
        return specijalnost;
    }

    public void setSpecijalnost(Specijalnost specijalnost) {
        this.specijalnost = specijalnost;
    }
        
        
    
}
