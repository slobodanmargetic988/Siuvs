package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.Assessment;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.Mobileappdata;
import slobodan.siuvs2.repository.MobileappdataRepository;

@Service
public class MobileappdataServiceImpl implements MobileappdataService {

    @Autowired
    private MobileappdataRepository mobileappdataRepository;

    @Override
    public List<Mobileappdata> findAllBy() {
       return mobileappdataRepository.findAllBy();
    }

    @Override
    public List<Mobileappdata> findAllByOpstina(String opstina) {
       return mobileappdataRepository.findAllByOpstina( opstina);
    }

    @Override
    public Mobileappdata findFirstByOpstinaAndOpasnost(String opstina, String opasnost) {
        return mobileappdataRepository.findFirstByOpstinaAndOpasnost( opstina,opasnost);
    }


}
