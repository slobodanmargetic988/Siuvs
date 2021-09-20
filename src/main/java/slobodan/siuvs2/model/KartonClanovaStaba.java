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
@Table(name = "karton_clanova_staba")
@EntityListeners(AuditingEntityListener.class)
public class KartonClanovaStaba {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;    
    
    @Column(name = "naziv_staba_vanr_situacije")
    private String naziv_staba_vanr_situacije;

    @Column(name = "adresa_staba")
    private String adresa_staba;

    @Column(name = "fiksni_telefon_staba")
    private String fiksni_telefon_staba;

    @Column(name = "mobilni_telefon_staba")
    private String mobilni_telefon_staba;

    @Column(name = "email_staba")
    private String email_staba;

    @Column(name = "naziv_operativnog_staba")
    private String naziv_operativnog_staba;

    @Column(name = "naziv_strucno_operativnog_tima")
    private String naziv_strucno_operativnog_tima;

    @Column(name = "funkcija_u_stabu")
    private String funkcija_u_stabu;

    @Column(name = "puno_ime")
    private String punoime;

    @Column(name = "naziv_organa_gde_radi")
    private String naziv_organa_gde_radi;

    @Column(name = "adresa_organa_gde_radi")
    private String adresa_organa_gde_radi;

    @Column(name = "funkcija_na_radu")
    private String funkcija_na_radu;

    @Column(name = "fiksni_telefon_sluzbeni")
    private String fiksni_telefon_sluzbeni;

    @Column(name = "mobilni_telefon_sluzbeni")
    private String mobilni_telefon_sluzbeni;

    @Column(name = "email_sluzbeni")
    private String email_sluzbeni;
    
     @Column(name = "email_licni")
    private String email_licni;

    @Column(name = "adresa_stanovanja")
    private String adresa_stanovanja;

    @Column(name = "telefon_u_stanu")
    private String telefon_u_stanu;

    @Column(name = "ucesce_na_obukama")
    private String ucesce_na_obukama;

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

    public String getNaziv_staba_vanr_situacije() {
        return naziv_staba_vanr_situacije;
    }

    public void setNaziv_staba_vanr_situacije(String naziv_staba_vanr_situacije) {
        this.naziv_staba_vanr_situacije = naziv_staba_vanr_situacije;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresa_staba() {
        return adresa_staba;
    }

    public void setAdresa_staba(String adresa_staba) {
        this.adresa_staba = adresa_staba;
    }

    public String getFiksni_telefon_staba() {
        return fiksni_telefon_staba;
    }

    public void setFiksni_telefon_staba(String fiksni_telefon_staba) {
        this.fiksni_telefon_staba = fiksni_telefon_staba;
    }

    public String getMobilni_telefon_staba() {
        return mobilni_telefon_staba;
    }

    public void setMobilni_telefon_staba(String mobilni_telefon_staba) {
        this.mobilni_telefon_staba = mobilni_telefon_staba;
    }

    public String getEmail_staba() {
        return email_staba;
    }

    public void setEmail_staba(String email_staba) {
        this.email_staba = email_staba;
    }

    public String getNaziv_operativnog_staba() {
        return naziv_operativnog_staba;
    }

    public void setNaziv_operativnog_staba(String naziv_operativnog_staba) {
        this.naziv_operativnog_staba = naziv_operativnog_staba;
    }

    public String getNaziv_strucno_operativnog_tima() {
        return naziv_strucno_operativnog_tima;
    }

    public void setNaziv_strucno_operativnog_tima(String naziv_strucno_operativnog_tima) {
        this.naziv_strucno_operativnog_tima = naziv_strucno_operativnog_tima;
    }

    public String getFunkcija_u_stabu() {
        return funkcija_u_stabu;
    }

    public void setFunkcija_u_stabu(String funkcija_u_stabu) {
        this.funkcija_u_stabu = funkcija_u_stabu;
    }

    public String getPunoime() {
        return punoime;
    }

    public void setPunoime(String punoime) {
        this.punoime = punoime;
    }

   

    public String getNaziv_organa_gde_radi() {
        return naziv_organa_gde_radi;
    }

    public void setNaziv_organa_gde_radi(String naziv_organa_gde_radi) {
        this.naziv_organa_gde_radi = naziv_organa_gde_radi;
    }

    public String getEmail_licni() {
        return email_licni;
    }

    public void setEmail_licni(String email_licni) {
        this.email_licni = email_licni;
    }

    public String getAdresa_organa_gde_radi() {
        return adresa_organa_gde_radi;
    }

    public void setAdresa_organa_gde_radi(String adresa_organa_gde_radi) {
        this.adresa_organa_gde_radi = adresa_organa_gde_radi;
    }

    public String getFunkcija_na_radu() {
        return funkcija_na_radu;
    }

    public void setFunkcija_na_radu(String funkcija_na_radu) {
        this.funkcija_na_radu = funkcija_na_radu;
    }

    public String getFiksni_telefon_sluzbeni() {
        return fiksni_telefon_sluzbeni;
    }

    public void setFiksni_telefon_sluzbeni(String fiksni_telefon_sluzbeni) {
        this.fiksni_telefon_sluzbeni = fiksni_telefon_sluzbeni;
    }

    public String getMobilni_telefon_sluzbeni() {
        return mobilni_telefon_sluzbeni;
    }

    public void setMobilni_telefon_sluzbeni(String mobilni_telefon_sluzbeni) {
        this.mobilni_telefon_sluzbeni = mobilni_telefon_sluzbeni;
    }

    public String getEmail_sluzbeni() {
        return email_sluzbeni;
    }

    public void setEmail_sluzbeni(String email_sluzbeni) {
        this.email_sluzbeni = email_sluzbeni;
    }

    public String getAdresa_stanovanja() {
        return adresa_stanovanja;
    }

    public void setAdresa_stanovanja(String adresa_stanovanja) {
        this.adresa_stanovanja = adresa_stanovanja;
    }

    public String getTelefon_u_stanu() {
        return telefon_u_stanu;
    }

    public void setTelefon_u_stanu(String telefon_u_stanu) {
        this.telefon_u_stanu = telefon_u_stanu;
    }

    public String getUcesce_na_obukama() {
        return ucesce_na_obukama;
    }

    public void setUcesce_na_obukama(String ucesce_na_obukama) {
        this.ucesce_na_obukama = ucesce_na_obukama;
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
