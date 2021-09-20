package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.KartonClanovaStaba;
import slobodan.siuvs2.repository.KartonClanovaStabaRepository;
import slobodan.siuvs2.valueObject.KartonClanovaStabaId;

@Service
public class KartonClanovaStabaServiceImpl implements KartonClanovaStabaService {

    @Autowired
    private KartonClanovaStabaRepository kartonClanovaStabaRepository;

    @Override
    public List<KartonClanovaStaba> findAllBy(){
       return kartonClanovaStabaRepository.findAllBy();
    }
    

     @Override
    public List<KartonClanovaStaba> findAllByClientOrderByPunoimeAsc(Client client){
       return kartonClanovaStabaRepository.findAllByClientOrderByPunoimeAsc(client);
    };
    
     @Override
    public void save(KartonClanovaStaba karton){
    kartonClanovaStabaRepository.save(karton);
    }
   
        @Override
    public  KartonClanovaStaba findOne(KartonClanovaStabaId id){
    return kartonClanovaStabaRepository.findOne(id.getValue());
    
    };
    
  


}
