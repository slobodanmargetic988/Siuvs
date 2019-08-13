package org.bitbucket.pbosko.siuvs.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "assessment")
@EntityListeners(AuditingEntityListener.class)
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "assessment_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "page_id")
    private Page page;

    @Column(name = "consequences")
    private int consequences;

    @Column(name = "probability")
    private int probability;

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

    @Column(name = "text_pojavljivanje")
    private String textPojavljivanje;

    @Column(name = "text_prostorna_dimenzija")
    private String textProstornaDimenzija;

    @Column(name = "text_intenzitet")
    private String textIntenzitet;

    @Column(name = "text_vreme")
    private String textVreme;

    @Column(name = "text_tok")
    private String textTok;

    @Column(name = "text_trajanje")
    private String textTrajanje;

    @Column(name = "text_rana_najava")
    private String textRanaNajava;

    @Column(name = "text_pripremljenost")
    private String textPripremljenost;

    @Column(name = "text_uticaj")
    private String textUticaj;

    @Column(name = "text_generisanje_drugih_opasnosti")
    private String textGenerisanjeDrugihOpasnosti;

    @Column(name = "text_referentni_incidenti")
    private String textReferentniIncidenti;

    @Column(name = "text_informisanje_javnosti")
    private String textInformisanjeJavnosti;

    @Column(name = "text_buduce_informacije")
    private String textBuduceInformacije;
    

 
    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public int getConsequences() {
        return consequences;
    }

    public void setConsequences(int consequences) {
        this.consequences = consequences;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
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

    public void copyDataFieldsFromAssessment(Assessment assessment) {
        this.setProbability(assessment.getProbability());
        this.setConsequences(assessment.getConsequences());
        this.setTextPojavljivanje(assessment.getTextPojavljivanje());
        this.setTextProstornaDimenzija(assessment.getTextProstornaDimenzija());
        this.setTextIntenzitet(assessment.getTextIntenzitet());
        this.setTextVreme(assessment.getTextVreme());
        this.setTextTok(assessment.getTextTok());
        this.setTextTrajanje(assessment.getTextTrajanje());
        this.setTextRanaNajava(assessment.getTextRanaNajava());
        this.setTextPripremljenost(assessment.getTextPripremljenost());
        this.setTextUticaj(assessment.getTextUticaj());
        this.setTextGenerisanjeDrugihOpasnosti(assessment.getTextGenerisanjeDrugihOpasnosti());
        this.setTextReferentniIncidenti(assessment.getTextReferentniIncidenti());
        this.setTextInformisanjeJavnosti(assessment.getTextInformisanjeJavnosti());
        this.setTextBuduceInformacije(assessment.getTextBuduceInformacije());

    }

    public String getTextPojavljivanje() {
        return textPojavljivanje;
    }

    public void setTextPojavljivanje(String textPojavljivanje) {
        this.textPojavljivanje = textPojavljivanje;
    }

    public String getTextProstornaDimenzija() {
        return textProstornaDimenzija;
    }

    public void setTextProstornaDimenzija(String textProstornaDimenzija) {
        this.textProstornaDimenzija = textProstornaDimenzija;
    }

    public String getTextIntenzitet() {
        return textIntenzitet;
    }

    public void setTextIntenzitet(String textIntenzitet) {
        this.textIntenzitet = textIntenzitet;
    }

    public String getTextVreme() {
        return textVreme;
    }

    public void setTextVreme(String textVreme) {
        this.textVreme = textVreme;
    }

    public String getTextTok() {
        return textTok;
    }

    public void setTextTok(String textTok) {
        this.textTok = textTok;
    }

    public String getTextTrajanje() {
        return textTrajanje;
    }

    public void setTextTrajanje(String textTrajanje) {
        this.textTrajanje = textTrajanje;
    }

    public String getTextRanaNajava() {
        return textRanaNajava;
    }

    public void setTextRanaNajava(String textRanaNajava) {
        this.textRanaNajava = textRanaNajava;
    }

    public String getTextPripremljenost() {
        return textPripremljenost;
    }

    public void setTextPripremljenost(String textPripremljenost) {
        this.textPripremljenost = textPripremljenost;
    }

    public String getTextUticaj() {
        return textUticaj;
    }

    public void setTextUticaj(String textUticaj) {
        this.textUticaj = textUticaj;
    }

    public String getTextGenerisanjeDrugihOpasnosti() {
        return textGenerisanjeDrugihOpasnosti;
    }

    public void setTextGenerisanjeDrugihOpasnosti(String textGenerisanjeDrugihOpasnosti) {
        this.textGenerisanjeDrugihOpasnosti = textGenerisanjeDrugihOpasnosti;
    }

    public String getTextReferentniIncidenti() {
        return textReferentniIncidenti;
    }

    public void setTextReferentniIncidenti(String textReferentniIncidenti) {
        this.textReferentniIncidenti = textReferentniIncidenti;
    }

    public String getTextInformisanjeJavnosti() {
        return textInformisanjeJavnosti;
    }

    public void setTextInformisanjeJavnosti(String textInformisanjeJavnosti) {
        this.textInformisanjeJavnosti = textInformisanjeJavnosti;
    }

    public String getTextBuduceInformacije() {
        return textBuduceInformacije;
    }

    public void setTextBuduceInformacije(String textBuduceInformacije) {
        this.textBuduceInformacije = textBuduceInformacije;
    }


}
