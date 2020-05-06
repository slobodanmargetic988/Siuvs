/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author deca
 */
import slobodan.siuvs2.model.Plan;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.repository.PlanRepository;
import slobodan.siuvs2.repository.PosebanCiljRepository;
import slobodan.siuvs2.valueObject.PlanID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl implements PlanService{
        @Autowired
    private PlanRepository planRepository;
        /*
@Autowired
    private PosebanCiljRepository posebanCiljRepository;
@Autowired
    private PosebanCiljFactory posebanCiljFactory;
*/
    @Override
    public Plan findOne(PlanID planID) {
        return planRepository.findOne(planID.getValue());
    }

    @Override
    public void save(Plan plan) {
       // posebanCiljRepository.save(posebanCiljFactory.empty(plan));
        planRepository.save(plan);
    }
    
}
