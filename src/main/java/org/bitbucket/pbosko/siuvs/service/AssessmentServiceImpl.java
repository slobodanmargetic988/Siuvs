package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.Assessment;
import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.model.Page;
import org.bitbucket.pbosko.siuvs.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    @Autowired
    private AssessmentRepository assessmentRepository;


    @Override
    public Assessment findOne(Client client, Page page) {
        return assessmentRepository.findFirstByClientAndPage(client, page);
    }

    @Override
    public void save(Assessment assessment) {
        assessmentRepository.save(assessment);
    }
}
