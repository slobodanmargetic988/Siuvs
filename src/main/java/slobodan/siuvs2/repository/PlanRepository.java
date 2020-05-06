/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.repository;

/**
 *
 * @author sloba
 */
import slobodan.siuvs2.model.Plan;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Integer>{
    Plan findFirstByClientAndPage(Client client, Page page);
}
