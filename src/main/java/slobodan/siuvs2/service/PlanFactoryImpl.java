/*
 * 
 */
package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import slobodan.siuvs2.model.Plan;
import slobodan.siuvs2.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanFactoryImpl implements PlanFactory {

    @Autowired
    private PosebanCiljFactory posebanCiljFactory;
    @Autowired
    private PosebanCiljService posebanCiljService;

    @Override
    public Plan empty(Client client) {
        Plan plan = new Plan();
        plan.setClient(client);
        plan.setIndikator("/");
        plan.setIndikatorCv("/");
        plan.setIndikatorPv("/");
        plan.setOpstiCilj("/");
        plan.setPeriodDo("/");
        plan.setPeriodOd("/");
        plan.setPlanText("План још увек није унет у базу");
        return plan;
    }
}
