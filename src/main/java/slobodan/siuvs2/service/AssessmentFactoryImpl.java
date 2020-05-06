package slobodan.siuvs2.service;

import slobodan.siuvs2.model.Assessment;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;
import org.springframework.stereotype.Service;

@Service
public class AssessmentFactoryImpl implements AssessmentFactory {

    @Override
    public Assessment empty(Client client, Page page) {
        Assessment assessment = new Assessment();
        assessment.setClient(client);
        assessment.setPage(page);
        assessment.setConsequences(1);
        assessment.setProbability(1);
        return assessment;
    }
}
