/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.model;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.comba
 */
import javax.persistence.*;
import slobodan.siuvs2.valueObject.PodRezultatID;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "pod_rezultat")
@EntityListeners(AuditingEntityListener.class)
public class PodRezultat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pod_rezultat_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "rezultat_id")
    private Rezultat rezultat;

    @Column(name = "aktivnosti_text")
    private String aktivnostiText;

    @Column(name = "redosled")
    private int redosled;

    @Column(name = "indikatori_text")
    private String indikatoriText;

    @Column(name = "odgovorna_institucija_text")
    private String odgovornaInstitucijaText;

    @Column(name = "partner_institucija_text")
    private String partnerInstitucijaText;

    @Column(name = "period_text")
    private String periodText;

    @Column(name = "period_kompletiran")
    private int periodKompletiran;
    @Column(name = "kratko_obrazlozenje")
    private String kratkoObrazlozenje;

    @Column(name = "budzet_jls")
    private int budzetJls;

    @Column(name = "budzet_ostalo")
    private int budzetOstalo;

    @Column(name = "budzet_neobezbedjeno")
    private int budzetNeobezbedjeno;

    public int getId() {
        return id;
    }

    public PodRezultatID getPodRezultatId() {
        return new PodRezultatID(getId());
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKratkoObrazlozenje() {
        return kratkoObrazlozenje;
    }

    public void setKratkoObrazlozenje(String kratkoObrazlozenje) {
        this.kratkoObrazlozenje = kratkoObrazlozenje;
    }

    public String getPartnerInstitucijaText() {
        return partnerInstitucijaText;
    }

    public void setPartnerInstitucijaText(String partnerInstitucijaText) {
        this.partnerInstitucijaText = partnerInstitucijaText;
    }

    public Rezultat getRezultat() {
        return rezultat;
    }

    public void setRezultat(Rezultat rezultat) {
        this.rezultat = rezultat;
    }

    public String getAktivnostiText() {
        return aktivnostiText;
    }

    public void setAktivnostiText(String aktivnostiText) {
        this.aktivnostiText = aktivnostiText;
    }

    public int getRedosled() {
        return redosled;
    }

    public void setRedosled(int redosled) {
        this.redosled = redosled;
    }

    public String getIndikatoriText() {
        return indikatoriText;
    }

    public void setIndikatoriText(String indikatoriText) {
        this.indikatoriText = indikatoriText;
    }

    public String getOdgovornaInstitucijaText() {
        return odgovornaInstitucijaText;
    }

    public void setOdgovornaInstitucijaText(String odgovornaInstitucijaText) {
        this.odgovornaInstitucijaText = odgovornaInstitucijaText;
    }

    public String getPeriodText() {
        return periodText;
    }

    public void setPeriodText(String periodText) {
        this.periodText = periodText;
    }

    public int getPeriodKompletiran() {
        return periodKompletiran;
    }

    public void setPeriodKompletiran(int periodKompletiran) {
        this.periodKompletiran = periodKompletiran;
    }

    public int getBudzetJls() {
        return budzetJls;
    }

    public void setBudzetJls(int budzetJls) {
        this.budzetJls = budzetJls;
    }

    public int getBudzetOstalo() {
        return budzetOstalo;
    }

    public void setBudzetOstalo(int budzetOstalo) {
        this.budzetOstalo = budzetOstalo;
    }

    public int getBudzetNeobezbedjeno() {
        return budzetNeobezbedjeno;
    }

    public void setBudzetNeobezbedjeno(int budzetNeobezbedjeno) {
        this.budzetNeobezbedjeno = budzetNeobezbedjeno;
    }



}
