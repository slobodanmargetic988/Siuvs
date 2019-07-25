package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.Assessment;
import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.model.Page;

public interface AssessmentService {

    Assessment findOne(Client client, Page page);

    void save(Assessment assessment);

}
