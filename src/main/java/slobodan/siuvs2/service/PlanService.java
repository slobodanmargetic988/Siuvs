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
import slobodan.siuvs2.valueObject.ClientId;
import slobodan.siuvs2.valueObject.PlanID;

public interface PlanService {
        Plan findOne(PlanID planID);
Plan findFirstByClient(Client client);

    void save(Plan plan);
}
