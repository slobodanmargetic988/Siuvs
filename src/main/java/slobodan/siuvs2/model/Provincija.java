/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.model;


import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
/**
 *
 * @author Sloba
 */
@Entity
@Table(name = "provincija")
@EntityListeners(AuditingEntityListener.class)
public class Provincija {

      
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "provincija_id")
    private int id;
    

    
    @Column(name = "provincija_name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
     
}

