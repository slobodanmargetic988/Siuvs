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
import slobodan.siuvs2.valueObject.PlanID;

public interface PlanService {

    Plan findOne(PlanID planID);

    Plan findFirstByClient(Client client);

    void save(Plan plan);
}
