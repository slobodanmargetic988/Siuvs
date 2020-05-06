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
 * @author deca
 */
@Entity
@Table(name = "supervising")
@EntityListeners(AuditingEntityListener.class)
public class Supervising {
      
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "supervising_id")
    private int id;
      
     
         @ManyToOne
    @JoinColumn(name = "distrikt_id")
    private Distrikt distrikt;
    
         @ManyToOne
    @JoinColumn(name = "provincija_id")
    private Provincija provincija;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Distrikt getDistrikt() {
        return distrikt;
    }

    public void setDistrikt(Distrikt distrikt) {
        this.distrikt = distrikt;
    }

    public Provincija getProvincija() {
        return provincija;
    }

    public void setProvincija(Provincija provincija) {
        this.provincija = provincija;
    }
         
         
}
