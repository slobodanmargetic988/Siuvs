/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.model;

import java.util.List;
import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author Sloba
 */
@Entity
@Table(name = "plan")
@EntityListeners(AuditingEntityListener.class)
public class Plan {

    /*
    public void copyDataFieldsFromPlan(Plan plan) {
    }
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "plan_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "plan_text")
    private String planText;

    @Column(name = "period_od")
    private String periodOd;

    @Column(name = "period_do")
    private String periodDo;

    @Column(name = "opsti_cilj")
    private String opstiCilj;

    @Column(name = "indikator")
    private String indikator;

    @Column(name = "indikator_pv")
    private String indikatorPv;

    @Column(name = "indikator_cv")
    private String indikatorCv;

    @OneToMany(mappedBy = "plan")
    @OrderBy("redosled ASC")
    private List<PosebanCilj> children;

    public void copyDataFieldsFromPlan(Plan plan) {
        this.setPlanText(plan.getPlanText());
        this.setPeriodOd(plan.getPeriodOd());
        this.setPeriodDo(plan.getPeriodDo());
        this.setOpstiCilj(plan.getOpstiCilj());
        this.setIndikator(plan.getIndikator());
        this.setIndikatorPv(plan.getIndikatorPv());
        this.setIndikatorCv(plan.getIndikatorCv());
    }

    public List<PosebanCilj> getChildren() {
        return children;
    }

    public void setChildren(List<PosebanCilj> children) {
        this.children = children;
    }

    public String getIndikator() {
        return indikator;
    }

    public void setIndikator(String indikator) {
        this.indikator = indikator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getPlanText() {
        return planText;
    }

    public void setPlanText(String planText) {
        this.planText = planText;
    }

    public String getPeriodOd() {
        return periodOd;
    }

    public void setPeriodOd(String periodOd) {
        this.periodOd = periodOd;
    }

    public String getPeriodDo() {
        return periodDo;
    }

    public void setPeriodDo(String periodDo) {
        this.periodDo = periodDo;
    }

    public String getOpstiCilj() {
        return opstiCilj;
    }

    public void setOpstiCilj(String opstiCilj) {
        this.opstiCilj = opstiCilj;
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

}
