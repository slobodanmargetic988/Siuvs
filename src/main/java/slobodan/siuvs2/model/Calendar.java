/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.model;



import java.sql.Date;
import java.util.List;
import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author Sloba
 */
@Entity
@Table(name = "calendar")
@EntityListeners(AuditingEntityListener.class)
public class Calendar {

    /*
    public void copyDataFieldsFromPlan(Plan plan) {
    }
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "ime_dokumenta")
    private String dokument;
    
    @Column(name = "broj_resenja")
    private String resenje;

    @Column(name = "datum_donosenja")
    private Date doneto;

    @Column(name = "vazi_do")
    private Date vazido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDokument() {
        return dokument;
    }

    public void setDokument(String dokument) {
        this.dokument = dokument;
    }

    public String getResenje() {
        return resenje;
    }

    public void setResenje(String resenje) {
        this.resenje = resenje;
    }

    public Date getDoneto() {
        return doneto;
    }

    public void setDoneto(Date doneto) {
        this.doneto = doneto;
    }

    public Date getVazido() {
        return vazido;
    }

    public void setVazido(Date vazido) {
        this.vazido = vazido;
    }
    
    


}
