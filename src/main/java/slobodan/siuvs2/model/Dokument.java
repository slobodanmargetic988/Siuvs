package slobodan.siuvs2.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dokument")
@EntityListeners(AuditingEntityListener.class)
public class Dokument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dokument_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "ia_id")
    private InternationalAgreements ia;
    
    @ManyToOne
    @JoinColumn(name = "ppd_id")
    private PublicPolicyDocuments ppd;

    @Column(name = "title")
    private String title;

    @Column(name = "filename")
    private String filename;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @CreatedDate
    @Column(name = "created_on")
    private LocalDateTime createdOn;

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


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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

    public InternationalAgreements getIa() {
        return ia;
    }

    public void setIa(InternationalAgreements ia) {
        this.ia = ia;
    }

    public PublicPolicyDocuments getPpd() {
        return ppd;
    }

    public void setPpd(PublicPolicyDocuments ppd) {
        this.ppd = ppd;
    }
    
    
}
