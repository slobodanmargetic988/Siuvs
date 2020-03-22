/*
 * 
 */
package org.bitbucket.pbosko.siuvs.service;

/**
 *
 * @author deca
 */
import org.bitbucket.pbosko.siuvs.model.Plan;
import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.model.Page;
import org.bitbucket.pbosko.siuvs.repository.PlanRepository;
import org.bitbucket.pbosko.siuvs.repository.PosebanCiljRepository;
import org.bitbucket.pbosko.siuvs.valueObject.PlanID;
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
