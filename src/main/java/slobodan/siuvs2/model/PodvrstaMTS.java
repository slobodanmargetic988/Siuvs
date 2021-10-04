/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.model;

import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author Sloba
 */
@Entity
@Table(name = "pod_vrsta_mts")
@EntityListeners(AuditingEntityListener.class)
public class PodvrstaMTS {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "naziv")
    private String naziv;

    @ManyToOne
    @JoinColumn(name = "vrsta_id")
    private VrstaMTS vrstaMTS;

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

    public VrstaMTS getVrstaMTS() {
        return vrstaMTS;
    }

    public void setVrstaMTS(VrstaMTS vrstaMTS) {
        this.vrstaMTS = vrstaMTS;
    }

}
