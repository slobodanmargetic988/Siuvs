package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.ZanimanjaPodvrsta;
import slobodan.siuvs2.repository.ZanimanjaPodvrstaRepository;
import slobodan.siuvs2.valueObject.ZanimanjaPodvrstaId;

@Service
public class ZanimanjaPodvrstaServiceImpl implements ZanimanjaPodvrstaService {

    @Autowired
    private ZanimanjaPodvrstaRepository zanimanjaPodvrstaRepository;

    @Override
    public List<ZanimanjaPodvrsta> findAllByOrderByNazivAsc(){
       return zanimanjaPodvrstaRepository.findAllByOrderByNazivAsc();
    }
    


    
     @Override
    public void save(ZanimanjaPodvrsta zanimanja){
    zanimanjaPodvrstaRepository.save(zanimanja);
    }
   
        @Override
    public  ZanimanjaPodvrsta findOne(ZanimanjaPodvrstaId ZanimanjeId){
    return zanimanjaPodvrstaRepository.findOne(ZanimanjeId.getValue());
    
    };
    
  


}
