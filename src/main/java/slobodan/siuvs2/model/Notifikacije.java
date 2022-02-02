package slobodan.siuvs2.model;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

@Entity
@Table(name = "notifikacije")
@EntityListeners(AuditingEntityListener.class)
public class Notifikacije {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "opstina")
    private String opstina;


      @Column(name = "token")
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpstina() {
        return opstina;
    }

    public void setOpstina(String opstina) {
        this.opstina = opstina;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    
}
