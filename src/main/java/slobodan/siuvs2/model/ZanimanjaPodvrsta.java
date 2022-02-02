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
@Table(name = "zanimanja_podvrsta")
@EntityListeners(AuditingEntityListener.class)
public class ZanimanjaPodvrsta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "naziv")
    private String naziv;
    
 @ManyToOne
    @JoinColumn(name = "zanimanje_id")
    private Zanimanja zanimanje;
  
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

    public Zanimanja getZanimanje() {
        return zanimanje;
    }

    public void setZanimanje(Zanimanja zanimanje) {
        this.zanimanje = zanimanje;
    }



  
}
