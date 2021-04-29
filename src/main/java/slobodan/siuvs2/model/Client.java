package slobodan.siuvs2.model;

import slobodan.siuvs2.valueObject.ClientId;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@EntityListeners(AuditingEntityListener.class)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "opstina_id")
    private Opstina opstina;

    @Column(name = "name", unique = true)
    @NotEmpty(message = "* Молимо унесите назив корисника")
    private String name;

    @OneToMany(mappedBy = "client")
    private List<User> users = new ArrayList<>();
    
     @OneToMany(mappedBy = "client")
    private List<Calendar> calendar= new ArrayList<>();

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

    @Column(name = "active")
    private boolean active;

    public List<Calendar> getCalendar() {
        return calendar;
    }
        public List<Calendar> getCalendarSorted() {
            List<Calendar> calendarSorted= new ArrayList<>();;
          Calendar  calendarPR= new Calendar();
           Calendar  calendarPZS= new Calendar();
           Calendar  calendarPSR= new Calendar();
          for(Calendar k : calendar){
          if (k.getDokument().equals("Процена ризика")){calendarPR=k; }
            if (k.getDokument().equals("План заштите и спасавања")){calendarPZS=k; }
              if (k.getDokument().equals("План смањења ризика")){calendarPSR=k; }
          }
           
           
            calendarSorted.add(calendarPR);
            calendarSorted.add(calendarPZS);
            calendarSorted.add(calendarPSR);
        return calendarSorted;
    }

    public void setCalendar(List<Calendar> calendar) {
        this.calendar = calendar;
    }

  

    public Opstina getOpstina() {
        return opstina;
    }

    public void setOpstina(Opstina opstina) {
        this.opstina = opstina;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClientId getClientId() {
        return new ClientId(getId());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
