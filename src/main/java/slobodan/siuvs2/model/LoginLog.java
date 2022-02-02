package slobodan.siuvs2.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Entity

@Table(name = "login_log")
@EntityListeners(AuditingEntityListener.class)
public class LoginLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "login_log_id")
    private int id;

    @Column(name = "ip")
    private String ip;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreatedDate
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    public int getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
}
