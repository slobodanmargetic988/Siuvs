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
@Table(name = "osoba")
@EntityListeners(AuditingEntityListener.class)
public class Osoba {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    
    @Column(name = "puno_ime")
    private String ime;

    @Column(name = "naziv_radnog_mesta")
    private String radnomesto;
    @Column(name = "adresa_radnog_mesta")
    private String adresaposao;
    @Column(name = "funkcija_radno_mesto")
    private String funkcijaposao;
    @Column(name = "telefon_sluzbeni_fiksni")
    private String fiksniposao;
    @Column(name = "telefon_sluzbeni_mobilni")
    private String mobilniposao;
    @Column(name = "email_sluzbeni")
    private String emailposao;
    @Column(name = "email_licni")
    private String email;
    @Column(name = "adresa_stanovanja")
    private String adresa;
    @Column(name = "telefon_u_stanu")
    private String telefon;
    @Column(name = "ucesce_u_obukama")
    private String obuke;
  
     @OneToMany(mappedBy = "osoba")
    private List<OsobaSOT> sotovi= new ArrayList<>();
     
  @OneToMany(mappedBy = "osoba")
    private List<OsobaStab> stabovi= new ArrayList<>();
    
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

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getRadnomesto() {
        return radnomesto;
    }

    public void setRadnomesto(String radnomesto) {
        this.radnomesto = radnomesto;
    }

    public String getAdresaposao() {
        return adresaposao;
    }

    public void setAdresaposao(String adresaposao) {
        this.adresaposao = adresaposao;
    }

    public String getFunkcijaposao() {
        return funkcijaposao;
    }

    public void setFunkcijaposao(String funkcijaposao) {
        this.funkcijaposao = funkcijaposao;
    }

    public String getFiksniposao() {
        return fiksniposao;
    }

    public void setFiksniposao(String fiksniposao) {
        this.fiksniposao = fiksniposao;
    }

    public String getMobilniposao() {
        return mobilniposao;
    }

    public void setMobilniposao(String mobilniposao) {
        this.mobilniposao = mobilniposao;
    }

    public String getEmailposao() {
        return emailposao;
    }

    public void setEmailposao(String emailposao) {
        this.emailposao = emailposao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getObuke() {
        return obuke;
    }

    public void setObuke(String obuke) {
        this.obuke = obuke;
    }

    public List<OsobaSOT> getSotovi() {
        return sotovi;
    }

    public void setSotovi(List<OsobaSOT> sotovi) {
        this.sotovi = sotovi;
    }

    public List<OsobaStab> getStabovi() {
        return stabovi;
    }

    public void setStabovi(List<OsobaStab> stabovi) {
        this.stabovi = stabovi;
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
