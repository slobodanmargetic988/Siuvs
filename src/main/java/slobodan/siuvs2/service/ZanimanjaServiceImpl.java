package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.Assessment;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.Mobileappdata;
import slobodan.siuvs2.model.Volonter;
import slobodan.siuvs2.model.Zanimanja;
import slobodan.siuvs2.repository.MobileappdataRepository;
import slobodan.siuvs2.repository.VolonterRepository;
import slobodan.siuvs2.repository.ZanimanjaRepository;
import slobodan.siuvs2.valueObject.MobileappdataID;
import slobodan.siuvs2.valueObject.ZanimanjaId;

@Service
public class ZanimanjaServiceImpl implements ZanimanjaService {

    @Autowired
    private ZanimanjaRepository zanimanjaRepository;

    @Override
    public List<Zanimanja> findAllByOrderByNazivAsc(){
       return zanimanjaRepository.findAllByOrderByNazivAsc();
    }
    


    
     @Override
    public void save(Zanimanja zanimanja){
    zanimanjaRepository.save(zanimanja);
    }
   
        @Override
    public  Zanimanja findOne(ZanimanjaId ZanimanjeId){
    return zanimanjaRepository.findOne(ZanimanjeId.getValue());
    
    };
    
  


}
