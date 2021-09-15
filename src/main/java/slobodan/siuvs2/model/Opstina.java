/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.model;

import java.util.Set;
import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author Sloba
 */
@Entity
@Table(name = "opstina")
@EntityListeners(AuditingEntityListener.class)
public class Opstina {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "opstina_id")
    private int id;
                             
     @Column(name = "nadleznaSVS")
    private String nadleznaSVS;
     
    @ManyToOne
    @JoinColumn(name = "distrikt_id")
    private Distrikt distrikt;

    @ManyToOne
    @JoinColumn(name = "provincija_id")
    private Provincija provincija;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "opstina_tasks",
            joinColumns = {
                @JoinColumn(name = "opstina_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "tasks_id")}
    )
    private Set<Tasks> tasks;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "opstina_ppd",
            joinColumns = {
                @JoinColumn(name = "opstina_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "ppd_id")}
    )
    private Set<PublicPolicyDocuments> publicPolicyDocuments;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "opstina_ia",
            joinColumns = {
                @JoinColumn(name = "opstina_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "ia_id")}
    )
    private Set<InternationalAgreements> internationalAgreements;

    @Column(name = "opstina_name")
    private String name;

    @Column(name = "opstina_name_latinica")
    private String namelatinica;

    public String getNadleznaSVS() {
        return nadleznaSVS;
    }

    public void setNadleznaSVS(String nadleznaSVS) {
        this.nadleznaSVS = nadleznaSVS;
    }

    
    
    
    public String getNamelatinica() {
        return namelatinica;
    }

    public void setNamelatinica(String namelatinica) {
        this.namelatinica = namelatinica;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<Tasks> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Tasks> tasks) {
        this.tasks = tasks;
    }

    public Set<PublicPolicyDocuments> getPublicPolicyDocuments() {
        return publicPolicyDocuments;
    }

    public void setPublicPolicyDocuments(Set<PublicPolicyDocuments> publicPolicyDocuments) {
        this.publicPolicyDocuments = publicPolicyDocuments;
    }

    public Set<InternationalAgreements> getInternationalAgreements() {
        return internationalAgreements;
    }

    public void setInternationalAgreements(Set<InternationalAgreements> internationalAgreements) {
        this.internationalAgreements = internationalAgreements;
    }

}
