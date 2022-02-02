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
import slobodan.siuvs2.model.Delatnost;
import slobodan.siuvs2.model.Kadrovi;
import slobodan.siuvs2.model.Mobileappdata;
import slobodan.siuvs2.model.Volonter;
import slobodan.siuvs2.model.Zanimanja;
import slobodan.siuvs2.repository.DelatnostRepository;
import slobodan.siuvs2.repository.KadroviRepository;
import slobodan.siuvs2.repository.MobileappdataRepository;
import slobodan.siuvs2.repository.VolonterRepository;
import slobodan.siuvs2.repository.ZanimanjaRepository;
import slobodan.siuvs2.valueObject.DelatnostId;
import slobodan.siuvs2.valueObject.KadroviId;
import slobodan.siuvs2.valueObject.MobileappdataID;
import slobodan.siuvs2.valueObject.ZanimanjaId;

@Service
public class DelatnostServiceImpl implements DelatnostService {

    @Autowired
    private DelatnostRepository delatnostRepository;

    @Override
    public List<Delatnost> findAllByOrderByNazivAsc(){
       return delatnostRepository.findAllByOrderByNazivAsc();
    }
    


    
     @Override
    public void save(Delatnost delatnost){
    delatnostRepository.save(delatnost);
    }
   
        @Override
    public  Delatnost findOne(DelatnostId DdlatnostId){
    return delatnostRepository.findOne(DdlatnostId.getValue());
    
    };
    
  


}
