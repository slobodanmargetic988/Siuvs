/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author Sloba
 */
@Entity
@Table(name = "stab_za_vanredne_situacije")
@EntityListeners(AuditingEntityListener.class)
public class StabVS {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "naziv")
    private String naziv;
    @Column(name = "adresa_staba")
    private String adresa;
    @Column(name = "fiksni_telefon")
    private String fiksni;
    @Column(name = "mobilni_telefon")
    private String mobilni;
    @Column(name = "email")
    private String email;
    
@OneToMany(mappedBy = "stab")
    private List<OsobaStab> osobe= new ArrayList<>();


   @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
     
      @CreatedBy
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @CreatedDate
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "modified_by")
    private User modifiedBy;

    @LastModifiedDate
    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getFiksni() {
        return fiksni;
    }

    public void setFiksni(String fiksni) {
        this.fiksni = fiksni;
    }

    public String getMobilni() {
        return mobilni;
    }

    public void setMobilni(String mobilni) {
        this.mobilni = mobilni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<OsobaStab> getOsobe() {
        return osobe;
    }

    public void setOsobe(List<OsobaStab> osobe) {
        this.osobe = osobe;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
    
    
    
}
