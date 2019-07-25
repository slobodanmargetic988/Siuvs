package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.Assessment;
import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.model.Page;
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
