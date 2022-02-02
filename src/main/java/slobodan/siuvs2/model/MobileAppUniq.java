package slobodan.siuvs2.model;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

@Entity
@Table(name = "mobileapp_unique_users")
@EntityListeners(AuditingEntityListener.class)
public class MobileAppUniq {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

      @Column(name = "token")
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    
}
