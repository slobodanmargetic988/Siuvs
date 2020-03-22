/*
 * 
 */
package org.bitbucket.pbosko.siuvs.service;

/**
 *
 * @author deca
 */
import java.util.ArrayList;
import java.util.List;
import org.bitbucket.pbosko.siuvs.model.Plan;
import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.model.Page;
import org.bitbucket.pbosko.siuvs.model.PosebanCilj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanFactoryImpl implements PlanFactory {
    @Autowired
    private PosebanCiljFactory posebanCiljFactory;
    @Autowired
    private PosebanCiljService posebanCiljService;
    
    @Override
     public Plan empty(Client client, Page page){
        Plan plan= new Plan();
        plan.setClient(client);
        plan.setPage(page);
        plan.setIndikator("/");
        plan.setIndikatorCv("/");
        plan.setIndikatorPv("/");
        plan.setOpstiCilj("/");
        plan.setPeriodDo("/");
        plan.setPeriodOd("/");
        plan.setPlanText("Нови план");
        /*
        PosebanCilj pc=posebanCiljFactory.empty(plan);
        posebanCiljService.save(pc);
        List<PosebanCilj> PClist=new ArrayList();
        PClist.add(pc);
        plan.setChildren(PClist);
        */
         return plan;
     }
}
