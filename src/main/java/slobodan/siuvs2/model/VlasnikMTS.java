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
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Entity
@Table(name = "vlasnik_mts")
@EntityListeners(AuditingEntityListener.class)
public class VlasnikMTS {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "vlasnik_naziv")
    private String vlasnik_naziv;

    @Column(name = "adresa")
    private String adresa;

    @Column(name = "grad_opstina")
    private String gradOpstina;

    @Column(name = "upravni_okrug")
    private String upravni_okrug;

    @Column(name = "telefon")
    private String telefon;

    @Column(name = "email")
    private String email;

    @Column(name = "odgovorno_lice")
    private String odgovorno_lice;

    @Column(name = "fiksni_tel_odg_lica")
    private String fiksni_tel_odg_lica;

    @Column(name = "mobilni_tel_odg_lica")
    private String mobilni_tel_odg_lica;

    @Column(name = "email_odg_lica")
    private String email_odg_lica;

    @OneToMany(mappedBy = "vlasnikMTS")
    private List<OrgJedinicaMTS> listaOrgJedinicaMTS = new ArrayList<>();
    
    @OneToMany(mappedBy = "vlasnikMTS")
    private List<DetaljiMTS> listaDetaljiMTS = new ArrayList<>();
    
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

    public String getVlasnik_naziv() {
        return vlasnik_naziv;
    }

    public void setVlasnik_naziv(String vlasnik_naziv) {
        this.vlasnik_naziv = vlasnik_naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getGradOpstina() {
        return gradOpstina;
    }

    public void setGradOpstina(String gradOpstina) {
        this.gradOpstina = gradOpstina;
    }

    public String getUpravni_okrug() {
        return upravni_okrug;
    }

    public void setUpravni_okrug(String upravni_okrug) {
        this.upravni_okrug = upravni_okrug;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<OrgJedinicaMTS> getListaOrgJedinicaMTS() {
        return listaOrgJedinicaMTS;
    }

    public void setListaOrgJedinicaMTS(List<OrgJedinicaMTS> listaOrgJedinicaMTS) {
        this.listaOrgJedinicaMTS = listaOrgJedinicaMTS;
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
