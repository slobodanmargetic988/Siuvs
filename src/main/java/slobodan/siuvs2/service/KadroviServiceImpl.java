package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.Assessment;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.Kadrovi;
import slobodan.siuvs2.model.KartonSubjekti;
import slobodan.siuvs2.model.Mobileappdata;
import slobodan.siuvs2.model.Volonter;
import slobodan.siuvs2.model.Zanimanja;
import slobodan.siuvs2.repository.KadroviRepository;
import slobodan.siuvs2.repository.MobileappdataRepository;
import slobodan.siuvs2.repository.VolonterRepository;
import slobodan.siuvs2.repository.ZanimanjaRepository;
import slobodan.siuvs2.valueObject.KadroviId;
import slobodan.siuvs2.valueObject.MobileappdataID;
import slobodan.siuvs2.valueObject.ZanimanjaId;

@Service
public class KadroviServiceImpl implements KadroviService {

    @Autowired
    private KadroviRepository kadroviRepository;

    @Override
    public List<Kadrovi> findAllBy(){
       return kadroviRepository.findAllBy
        ();
    }
    


    
     @Override
    public void save(Kadrovi kadrovi){
    kadroviRepository.save(kadrovi);
    }
   
        @Override
    public  Kadrovi findOne(KadroviId kadroviId){
    return kadroviRepository.findOne(kadroviId.getValue());
    
    };
    
     
     @Override
    public  Kadrovi findFirstByZanimanjeAndKartonsubjekti(Zanimanja zanimanje,KartonSubjekti kartonsubjekti){
    return kadroviRepository.findFirstByZanimanjeAndKartonsubjekti(zanimanje,kartonsubjekti);
    };


}
