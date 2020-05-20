/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.repository;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.comba
 */
import slobodan.siuvs2.model.PublicPolicyDocuments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicPolicyDocumentsRepository extends JpaRepository<PublicPolicyDocuments, Integer> {

    PublicPolicyDocuments findFirstByName(String name);

}
