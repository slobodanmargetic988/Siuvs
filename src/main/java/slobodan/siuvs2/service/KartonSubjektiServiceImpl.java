package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.KartonSubjekti;
import slobodan.siuvs2.repository.KartonSubjektiRepository;
import slobodan.siuvs2.valueObject.KartonSubjektiId;

@Service
public class KartonSubjektiServiceImpl implements KartonSubjektiService {

    @Autowired
    private KartonSubjektiRepository kartonSubjektiRepository;

    @Override
    public List<KartonSubjekti> findAllBy(){
       return kartonSubjektiRepository.findAllBy();
    }
    

     @Override
    public List<KartonSubjekti> findAllByClientOrderByPunnazivAsc(Client client){
       return kartonSubjektiRepository.findAllByClientOrderByPunnazivAsc(client);
    };
    
     @Override
    public void save(KartonSubjekti karton){
    kartonSubjektiRepository.save(karton);
    }
   
        @Override
    public  KartonSubjekti findOne(KartonSubjektiId id){
    return kartonSubjektiRepository.findOne(id.getValue());
    
    };
    
  


}
