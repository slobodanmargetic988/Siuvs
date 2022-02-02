/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.model;

import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import slobodan.siuvs2.repository.DokumentRepository;
import slobodan.siuvs2.service.DokumentService;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Entity
@Table(name = "public_policy_documents")
@EntityListeners(AuditingEntityListener.class)
public class PublicPolicyDocuments {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ppd_id")
    private int id;

    @Column(name = "ppd_name")
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
