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
import java.util.List;
import slobodan.siuvs2.model.PosebanCilj;
import slobodan.siuvs2.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;

public interface PosebanCiljRepository extends JpaRepository<PosebanCilj, Integer>{
    
    PosebanCilj findFirstByPlan(Plan plan);
    List<PosebanCilj> findAllByPlanOrderByRedosledAsc(Plan plan);
    List<PosebanCilj> findAllByPlanOrderByPageIdAsc(Plan plan);//ovo treba testirati
    PosebanCilj findFirstByClientAndPage(Client client, Page page);
    List<PosebanCilj> findAllByClientAndPage(Client client, Page page);
    
}
