/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bitbucket.pbosko.siuvs.model;

/**
 *
 * @author sloba
 */
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "rezultat")
public class Rezultat {
    
       @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rezultat_id")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "mera_id")
    private Mera mera;
    
    @Column(name = "rezultat_text")
    private String rezultatText;
    
    @Column(name = "redosled")
    private int redosled;
    
    @OneToMany(mappedBy = "rezultat")
    @OrderBy("redosled ASC")
    private List<PodRezultat> children;

    public List<PodRezultat> getChildren() {
        return children;
    }

    public void setChildren(List<PodRezultat> children) {
        this.children = children;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mera getMera() {
        return mera;
    }

    public void setMera(Mera mera) {
        this.mera = mera;
    }

    public String getRezultatText() {
        return rezultatText;
    }

    public void setRezultatText(String rezultatText) {
        this.rezultatText = rezultatText;
    }

    public int getRedosled() {
        return redosled;
    }

    public void setRedosled(int redosled) {
        this.redosled = redosled;
    }

   

    
    
}
