/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Entity
@Table(name = "kadrovi")
@EntityListeners(AuditingEntityListener.class)
public class Kadrovi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "broj")
    private Integer broj;

      
    @ManyToOne
    @JoinColumn(name = "zanimanje_podvrsta_id")
    private ZanimanjaPodvrsta zanimanjepodvrsta;
    
        @ManyToOne
    @JoinColumn(name = "kartonsubjekti_id")
    private KartonSubjekti kartonsubjekti;
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getBroj() {
        return broj;
    }

    public void setBroj(Integer broj) {
        this.broj = broj;
    }



    public KartonSubjekti getKartonsubjekti() {
        return kartonsubjekti;
    }

    public void setKartonsubjekti(KartonSubjekti kartonsubjekti) {
        this.kartonsubjekti = kartonsubjekti;
    }

    public ZanimanjaPodvrsta getZanimanjepodvrsta() {
        return zanimanjepodvrsta;
    }

    public void setZanimanjepodvrsta(ZanimanjaPodvrsta zanimanjepodvrsta) {
        this.zanimanjepodvrsta = zanimanjepodvrsta;
    }




  
}
