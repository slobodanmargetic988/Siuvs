package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import java.util.List;
import slobodan.siuvs2.model.Assessment;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;

public interface AssessmentService {

    Assessment findOne(Client client, Page page);
    Assessment findOne(Client client);
    void save(Assessment assessment);
 List<Assessment> findAll();
}
