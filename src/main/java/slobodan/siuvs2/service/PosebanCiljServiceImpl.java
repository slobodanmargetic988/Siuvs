/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import java.util.List;
import slobodan.siuvs2.model.PosebanCilj;
import slobodan.siuvs2.model.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.repository.PosebanCiljRepository;
import slobodan.siuvs2.valueObject.PosebanCiljID;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;

@Service
public class PosebanCiljServiceImpl implements PosebanCiljService {

    @Autowired
    private PosebanCiljRepository posebanCiljRepository;

    @Override
    public PosebanCilj findOne(PosebanCiljID posebanCiljID) {
        return posebanCiljRepository.findOne(posebanCiljID.getValue());
    }

    @Override
    public List<PosebanCilj> findAllByPlanOrderByPagePageIdAsc(Plan plan) {
        return posebanCiljRepository.findAllByPlanOrderByPageIdAsc(plan);
    }

    ;


@Override
    public List<PosebanCilj> findAllByPlanOrderByRedosledAsc(Plan plan) {
        return posebanCiljRepository.findAllByPlanOrderByRedosledAsc(plan);
    }

    ;
  
   @Override
    // @Transactional
    public void save(PosebanCilj posebanCilj) {

        posebanCiljRepository.save(posebanCilj);

    }

    @Override
    public void delete(PosebanCiljID posebanCiljID) {
        posebanCiljRepository.delete(posebanCiljID.getValue());
    }

    ;
 
    @Override
    public List<PosebanCilj> findAllByClientAndPage(Client client, Page page) {
        return posebanCiljRepository.findAllByClientAndPage(client, page);
    }
;
}
