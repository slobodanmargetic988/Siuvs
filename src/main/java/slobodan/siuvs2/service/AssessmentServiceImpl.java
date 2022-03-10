package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import java.util.List;
import slobodan.siuvs2.model.Assessment;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Assessment findOne(Client client) {
        return assessmentRepository.findFirstByClient(client);
    }
    @Override
    public void save(Assessment assessment) {
        assessmentRepository.save(assessment);
    }
    
    @Override
    public  List<Assessment> findAll(){
     return assessmentRepository.findAll();
    };
    
    
     /*   @Override
    public  List<Object[]> vratisveskraceno(){
     return assessmentRepository.vratisveskraceno();
    };*/
    
}
