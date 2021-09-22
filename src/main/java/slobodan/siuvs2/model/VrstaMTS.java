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
@Table(name = "vrstaMTS")
@EntityListeners(AuditingEntityListener.class)
public class VrstaMTS {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "naziv")
    private String naziv;

    @ManyToOne
    @JoinColumn(name = "grupa_id")
    private GrupaMTS grupaMTS;

    @OneToMany(mappedBy = "vrstaMTS")
    private List<PodvrstaMTS> listaPodvrstaMTS = new ArrayList<>();

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

    public GrupaMTS getGrupaMTS() {
        return grupaMTS;
    }

    public void setGrupaMTS(GrupaMTS grupaMTS) {
        this.grupaMTS = grupaMTS;
    }

    public List<PodvrstaMTS> getListaPodvrstaMTS() {
        return listaPodvrstaMTS;
    }

    public void setListaPodvrstaMTS(List<PodvrstaMTS> listaPodvrstaMTS) {
        this.listaPodvrstaMTS = listaPodvrstaMTS;
    }

}
