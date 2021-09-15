package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.KartonSubjekti;
import slobodan.siuvs2.model.KartonUdruzenja;
import slobodan.siuvs2.repository.KartonSubjektiRepository;
import slobodan.siuvs2.repository.KartonUdruzenjaRepository;
import slobodan.siuvs2.valueObject.KartonSubjektiId;
import slobodan.siuvs2.valueObject.KartonUdruzenjaId;

@Service
public class KartonUdruzenjaServiceImpl implements KartonUdruzenjaService {

    @Autowired
    private KartonUdruzenjaRepository kartonUdruzenjaRepository;

    @Override
    public List<KartonUdruzenja> findAllBy(){
       return kartonUdruzenjaRepository.findAllBy();
    }
    

     @Override
    public List<KartonUdruzenja> findAllByClientOrderByPunnazivAsc(Client client){
       return kartonUdruzenjaRepository.findAllByClientOrderByPunnazivAsc(client);
    };
    
     @Override
    public void save(KartonUdruzenja karton){
    kartonUdruzenjaRepository.save(karton);
    }
   
        @Override
    public  KartonUdruzenja findOne(KartonUdruzenjaId id){
    return kartonUdruzenjaRepository.findOne(id.getValue());
    
    };
    
  


}
