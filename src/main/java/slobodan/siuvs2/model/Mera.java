/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.model;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import java.util.List;
import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "mera")
@EntityListeners(AuditingEntityListener.class)
public class Mera {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mera_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "poseban_cilj_id")
    private PosebanCilj posebanCilj;

    @Column(name = "mera_text")
    private String meraText;

    @Column(name = "redosled")
    private int redosled;

    @OneToMany(mappedBy = "mera")
    @OrderBy("redosled ASC")
    private List<Rezultat> children;

    public List<Rezultat> getChildren() {
        return children;
    }

    public void setChildren(List<Rezultat> children) {
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRedosled() {
        return redosled;
    }

    public void setRedosled(int redosled) {
        this.redosled = redosled;
    }

    public PosebanCilj getPosebanCilj() {
        return posebanCilj;
    }

    public void setPosebanCilj(PosebanCilj posebanCilj) {
        this.posebanCilj = posebanCilj;
    }

    public String getMeraText() {
        return meraText;
    }

    public void setMeraText(String meraText) {
        this.meraText = meraText;
    }

}
