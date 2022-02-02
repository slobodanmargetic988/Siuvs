package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import java.util.List;
import slobodan.siuvs2.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.PodvrstaMTS;
import slobodan.siuvs2.repository.PodvrstaMTSRepository;
import slobodan.siuvs2.valueObject.PodvrstaMTSId;

@Service
public class PodvrstaMTSServiceImpl implements PodvrstaMTSService {

    @Autowired
    private PodvrstaMTSRepository podvrstaMTSRepository;

    @Override
    public List<PodvrstaMTS> findAllBy(){
       return podvrstaMTSRepository.findAllBy();
    }
    

 
    
     @Override
    public void save(PodvrstaMTS karton){
    podvrstaMTSRepository.save(karton);
    }
   
        @Override
    public PodvrstaMTS findOne(PodvrstaMTSId id){
    return podvrstaMTSRepository.findOne(id.getValue());
    
    };
    
  


}
