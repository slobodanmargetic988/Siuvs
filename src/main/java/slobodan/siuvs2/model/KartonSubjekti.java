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
@Table(name = "kartonsubjekti")
@EntityListeners(AuditingEntityListener.class)
public class KartonSubjekti {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "maticnibroj")
    private Integer maticnibroj;
 @Column(name = "pib")
    private Integer pib;
      
 
  @Column(name = "punnaziv")
    private String punnaziv;
 
    @Column(name = "skraceninaziv")
    private String skraceninaziv;
    
      @Column(name = "brojtekucegracuna")
    private String brojtekucegracuna;
      
        @Column(name = "posta_ime_pb")
    private String posta_ime_pb;
        
          @Column(name = "adresa")
    private String adresa;
          
            @Column(name = "geosirina")
    private String geosirina;
            
              @Column(name = "geoduzina")
    private String geoduzina;
              
                @Column(name = "tel")
    private String tel;
                
                  @Column(name = "fax")
    private String fax;
                  
                    @Column(name = "email")
    private String email;
                    
                      @Column(name = "website")
    private String website;
                      
                        @Column(name = "rukovodilac_ime")
    private String rukovodilac_ime;
                          @Column(name = "rukovodilac_tel")
    private String rukovodilac_tel;
                            @Column(name = "rukovodilac_mob")
    private String rukovodilac_mob;
                              @Column(name = "oblik_organizovanja")
    private String oblik_organizovanja;
                                @Column(name = "nivo_odredjivanja")
    private String nivo_odredjivanja;
   
         @Column(name = "kontakt_ime")
    private String kontakt_ime;
             @Column(name = "kontakt_adresa")
    private String kontakt_adresa;
                 @Column(name = "kontakt_telposao")
    private String kontakt_telposao;
                     @Column(name = "kontakt_telstan")
    private String kontakt_telstan;
                         @Column(name = "kontakt_mob")
    private String kontakt_mob
                                 ;
                         
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
 
    @ManyToOne
    @JoinColumn(name = "delatnost_id")
    private Delatnost delatnost;
    

         @OneToMany(mappedBy = "kartonsubjekti")
    private List<Kadrovi> kadrovi= new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getMaticnibroj() {
        return maticnibroj;
    }

    public void setMaticnibroj(Integer maticnibroj) {
        this.maticnibroj = maticnibroj;
    }

    public Integer getPib() {
        return pib;
    }

    public void setPib(Integer pib) {
        this.pib = pib;
    }

    public String getPunnaziv() {
        return punnaziv;
    }

    public void setPunnaziv(String punnaziv) {
        this.punnaziv = punnaziv;
    }

    public String getSkraceninaziv() {
        return skraceninaziv;
    }

    public void setSkraceninaziv(String skraceninaziv) {
        this.skraceninaziv = skraceninaziv;
    }

    public String getBrojtekucegracuna() {
        return brojtekucegracuna;
    }

    public void setBrojtekucegracuna(String brojtekucegracuna) {
        this.brojtekucegracuna = brojtekucegracuna;
    }

    public String getPosta_ime_pb() {
        return posta_ime_pb;
    }

    public void setPosta_ime_pb(String posta_ime_pb) {
        this.posta_ime_pb = posta_ime_pb;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getGeosirina() {
        return geosirina;
    }

    public void setGeosirina(String geosirina) {
        this.geosirina = geosirina;
    }

    public String getGeoduzina() {
        return geoduzina;
    }

    public void setGeoduzina(String geoduzina) {
        this.geoduzina = geoduzina;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getRukovodilac_ime() {
        return rukovodilac_ime;
    }

    public void setRukovodilac_ime(String rukovodilac_ime) {
        this.rukovodilac_ime = rukovodilac_ime;
    }

    public String getRukovodilac_tel() {
        return rukovodilac_tel;
    }

    public void setRukovodilac_tel(String rukovodilac_tel) {
        this.rukovodilac_tel = rukovodilac_tel;
    }

    public String getRukovodilac_mob() {
        return rukovodilac_mob;
    }

    public void setRukovodilac_mob(String rukovodilac_mob) {
        this.rukovodilac_mob = rukovodilac_mob;
    }

    public String getOblik_organizovanja() {
        return oblik_organizovanja;
    }

    public void setOblik_organizovanja(String oblik_organizovanja) {
        this.oblik_organizovanja = oblik_organizovanja;
    }

    public String getNivo_odredjivanja() {
        return nivo_odredjivanja;
    }

    public void setNivo_odredjivanja(String nivo_odredjivanja) {
        this.nivo_odredjivanja = nivo_odredjivanja;
    }

 

    public String getKontakt_ime() {
        return kontakt_ime;
    }

    public void setKontakt_ime(String kontakt_ime) {
        this.kontakt_ime = kontakt_ime;
    }

    public String getKontakt_adresa() {
        return kontakt_adresa;
    }

    public void setKontakt_adresa(String kontakt_adresa) {
        this.kontakt_adresa = kontakt_adresa;
    }

    public String getKontakt_telposao() {
        return kontakt_telposao;
    }

    public void setKontakt_telposao(String kontakt_telposao) {
        this.kontakt_telposao = kontakt_telposao;
    }

    public String getKontakt_telstan() {
        return kontakt_telstan;
    }

    public void setKontakt_telstan(String kontakt_telstan) {
        this.kontakt_telstan = kontakt_telstan;
    }

    public String getKontakt_mob() {
        return kontakt_mob;
    }

    public void setKontakt_mob(String kontakt_mob) {
        this.kontakt_mob = kontakt_mob;
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

    public Delatnost getDelatnost() {
        return delatnost;
    }

    public void setDelatnost(Delatnost delatnost) {
        this.delatnost = delatnost;
    }

    public List<Kadrovi> getKadrovi() {
        return kadrovi;
    }

    public void setKadrovi(List<Kadrovi> kadrovi) {
        this.kadrovi = kadrovi;
    }
    

}
