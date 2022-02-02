package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import java.util.List;
import slobodan.siuvs2.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.GrupaMTS;
import slobodan.siuvs2.repository.GrupaMTSRepository;
import slobodan.siuvs2.valueObject.GrupaMTSId;

@Service
public class GrupaMTSServiceImpl implements GrupaMTSService {

    @Autowired
    private GrupaMTSRepository grupaMTSRepository;

    @Override
    public List<GrupaMTS> findAllBy(){
       return grupaMTSRepository.findAllBy();
    }
    


     @Override
    public void save(GrupaMTS karton){
    grupaMTSRepository.save(karton);
    }
   
        @Override
    public GrupaMTS findOne(GrupaMTSId id){
    return grupaMTSRepository.findOne(id.getValue());
    
    };
    
  


}
