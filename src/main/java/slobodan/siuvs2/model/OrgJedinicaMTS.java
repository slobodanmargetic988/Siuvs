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
@Table(name = "org_jedinica_mts")
@EntityListeners(AuditingEntityListener.class)
public class OrgJedinicaMTS {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "naziv_org_jedinice")
    private String naziv_org_jedinice;

    @Column(name = "odgovorno_lice")
    private String odgovorno_lice;

    @Column(name = "fiksni_tel_odg_lica")
    private String fiksni_tel_odg_lica;

    @Column(name = "mobilni_tel_odg_lica")
    private String mobilni_tel_odg_lica;

    @Column(name = "email_odg_lica")
    private String email_odg_lica;

    @Column(name = "mts_adresa")
    private String mts_adresa;

    @Column(name = "geografska_sirina_mts")
    private String geografska_sirina_mts;

    @Column(name = "geografska_duzina_mts")
    private String geografska_duzina_mts;

    @Column(name = "nadlezna_org_jednica_svs")
    private String nadlezna_org_jednica_svs;

    @ManyToOne
    @JoinColumn(name = "vlasnik_id")
    private VlasnikMTS vlasnikMTS;

    @OneToMany(mappedBy = "orgJedinicaMTS")
    private List<DetaljiMTS> listaDetaljiMTS = new ArrayList<>();

//    @OneToMany(mappedBy = "orgJedinicaMTS")
//    private List<VlasnikMTS> listaVlasnikaMTS = new ArrayList<>();
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

    public String getNaziv_org_jedinice() {
        return naziv_org_jedinice;
    }

    public void setNaziv_org_jedinice(String naziv_org_jedinice) {
        this.naziv_org_jedinice = naziv_org_jedinice;
    }

    public String getOdgovorno_lice() {
        return odgovorno_lice;
    }

    public void setOdgovorno_lice(String odgovorno_lice) {
        this.odgovorno_lice = odgovorno_lice;
    }

    public String getFiksni_tel_odg_lica() {
        return fiksni_tel_odg_lica;
    }

    public void setFiksni_tel_odg_lica(String fiksni_tel_odg_lica) {
        this.fiksni_tel_odg_lica = fiksni_tel_odg_lica;
    }

    public String getMobilni_tel_odg_lica() {
        return mobilni_tel_odg_lica;
    }

    public void setMobilni_tel_odg_lica(String mobilni_tel_odg_lica) {
        this.mobilni_tel_odg_lica = mobilni_tel_odg_lica;
    }

    public String getEmail_odg_lica() {
        return email_odg_lica;
    }

    public void setEmail_odg_lica(String email_odg_lica) {
        this.email_odg_lica = email_odg_lica;
    }

    public String getMts_adresa() {
        return mts_adresa;
    }

    public void setMts_adresa(String mts_adresa) {
        this.mts_adresa = mts_adresa;
    }

    public String getGeografska_sirina_mts() {
        return geografska_sirina_mts;
    }

    public void setGeografska_sirina_mts(String geografska_sirina_mts) {
        this.geografska_sirina_mts = geografska_sirina_mts;
    }

    public String getGeografska_duzina_mts() {
        return geografska_duzina_mts;
    }

    public void setGeografska_duzina_mts(String geografska_duzina_mts) {
        this.geografska_duzina_mts = geografska_duzina_mts;
    }

    public String getNadlezna_org_jednica_svs() {
        return nadlezna_org_jednica_svs;
    }

    public void setNadlezna_org_jednica_svs(String nadlezna_org_jednica_svs) {
        this.nadlezna_org_jednica_svs = nadlezna_org_jednica_svs;
    }

    public VlasnikMTS getVlasnikMTS() {
        return vlasnikMTS;
    }

    public void setVlasnikMTS(VlasnikMTS vlasnikMTS) {
        this.vlasnikMTS = vlasnikMTS;
    }

    public List<DetaljiMTS> getListaDetaljiMTS() {
        return listaDetaljiMTS;
    }

    public void setListaDetaljiMTS(List<DetaljiMTS> listaDetaljiMTS) {
        this.listaDetaljiMTS = listaDetaljiMTS;
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
