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
 * @author Sloba
 */
@Entity
@Table(name = "zanimanja")
@EntityListeners(AuditingEntityListener.class)
public class Zanimanja {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "naziv")
    private String naziv;
    
 @OneToMany(mappedBy = "zanimanje")
    private List<ZanimanjaPodvrsta> zanimanjepodvrste= new ArrayList<>();
  
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

    public List<ZanimanjaPodvrsta> getZanimanjepodvrsta() {
        return zanimanjepodvrste;
    }

    public void setZanimanjepodvrsta(List<ZanimanjaPodvrsta> zanimanjepodvrste) {
        this.zanimanjepodvrste = zanimanjepodvrste;
    }



  
}
