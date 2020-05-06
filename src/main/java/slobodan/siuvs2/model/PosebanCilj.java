/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.model;

/**
 *
 * @author sloba
 */
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "poseban_cilj")
public class PosebanCilj {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "poseban_cilj_id")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @Column(name = "poseban_cilj_text")
    private String posebanCiljText;
    
    @Column(name = "redosled")
    private int redosled;
    
    @Column(name = "indikator")
    private String indikator;
    
    @Column(name = "indikator_pv")
    private String indikatorPv;
     
    @Column(name = "indikator_cv")
    private String indikatorCv;
   
    @OneToMany(mappedBy = "posebanCilj")
    @OrderBy("redosled ASC")
    private List<Mera> children;

    public List<Mera> getChildren() {
        return children;
    }

    public void setChildren(List<Mera> children) {
        this.children = children;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public String getPosebanCiljText() {
        return posebanCiljText;
    }

    public void setPosebanCiljText(String posebanCiljText) {
        this.posebanCiljText = posebanCiljText;
    }

    public int getRedosled() {
        return redosled;
    }

    public void setRedosled(int redosled) {
        this.redosled = redosled;
    }

    public String getIndikatorPv() {
        return indikatorPv;
    }

    public void setIndikatorPv(String indikatorPv) {
        this.indikatorPv = indikatorPv;
    }

    public String getIndikatorCv() {
        return indikatorCv;
    }

    public void setIndikatorCv(String indikatorCv) {
        this.indikatorCv = indikatorCv;
    }

    public String getIndikator() {
        return indikator;
    }

    public void setIndikator(String indikator) {
        this.indikator = indikator;
    }

   
    
}
