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
@Table(name = "detalji_mts")
@EntityListeners(AuditingEntityListener.class)
public class DetaljiMTS {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "marka")
    private String marka;

    @Column(name = "broj_mts_kolicina")
    private Integer brojMTS_kolicina;

    @Column(name = "tip")
    private String tip;

    @Column(name = "registracija")
    private String registracija;

    @Column(name = "godina_proizvodnje")
    private String godina_proizvodnje;

    @Column(name = "snaga")
    private String snaga;

    @Column(name = "nosivost")
    private String nosivost;

    @Column(name = "kapacitet")
    private String kapacitet;

    @Column(name = "pogonsko_gorivo")
    private String pogonsko_gorivo;

    @Column(name = "opis_mts")
    private String opisMTS;

    @Column(name = "napomena")
    private String napomena;

    @ManyToOne
    @JoinColumn(name = "vlasnik_id")
    private VlasnikMTS vlasnikMTS;
    
    @ManyToOne
    @JoinColumn(name = "org_jedinica_mts_id")
    private OrgJedinicaMTS orgJedinicaMTS;
    
    
      @ManyToOne
    @JoinColumn(name = "grupa_id")
    private GrupaMTS grupaMTS ;

       @ManyToOne
    @JoinColumn(name = "vrsta_id")
    private VrstaMTS vrstaMTS;

       @ManyToOne
    @JoinColumn(name = "podvrsta_id")
    private PodvrstaMTS podvrstaMTS ;




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

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public Integer getBrojMTS_kolicina() {
        return brojMTS_kolicina;
    }

    public void setBrojMTS_kolicina(Integer brojMTS_kolicina) {
        this.brojMTS_kolicina = brojMTS_kolicina;
    }


    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getRegistracija() {
        return registracija;
    }

    public void setRegistracija(String registracija) {
        this.registracija = registracija;
    }

    public String getGodina_proizvodnje() {
        return godina_proizvodnje;
    }

    public void setGodina_proizvodnje(String godina_proizvodnje) {
        this.godina_proizvodnje = godina_proizvodnje;
    }

    public String getSnaga() {
        return snaga;
    }

    public void setSnaga(String snaga) {
        this.snaga = snaga;
    }

    public String getNosivost() {
        return nosivost;
    }

    public void setNosivost(String nosivost) {
        this.nosivost = nosivost;
    }

    public String getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(String kapacitet) {
        this.kapacitet = kapacitet;
    }

    public String getPogonsko_gorivo() {
        return pogonsko_gorivo;
    }

    public void setPogonsko_gorivo(String pogonsko_gorivo) {
        this.pogonsko_gorivo = pogonsko_gorivo;
    }

    public String getOpisMTS() {
        return opisMTS;
    }

    public void setOpisMTS(String opisMTS) {
        this.opisMTS = opisMTS;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public VlasnikMTS getVlasnikMTS() {
        return vlasnikMTS;
    }

    public void setVlasnikMTS(VlasnikMTS vlasnikMTS) {
        this.vlasnikMTS = vlasnikMTS;
    }

    public OrgJedinicaMTS getOrgJedinicaMTS() {
        return orgJedinicaMTS;
    }

    public void setOrgJedinicaMTS(OrgJedinicaMTS orgJedinicaMTS) {
        this.orgJedinicaMTS = orgJedinicaMTS;
    }

    public GrupaMTS getGrupaMTS() {
        return grupaMTS;
    }

    public void setGrupaMTS(GrupaMTS grupaMTS) {
        this.grupaMTS = grupaMTS;
    }

    public VrstaMTS getVrstaMTS() {
        return vrstaMTS;
    }

    public void setVrstaMTS(VrstaMTS vrstaMTS) {
        this.vrstaMTS = vrstaMTS;
    }

    public PodvrstaMTS getPodvrstaMTS() {
        return podvrstaMTS;
    }

    public void setPodvrstaMTS(PodvrstaMTS podvrstaMTS) {
        this.podvrstaMTS = podvrstaMTS;
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
