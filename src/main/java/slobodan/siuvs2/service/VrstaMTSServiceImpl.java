package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import java.util.List;
import slobodan.siuvs2.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.VrstaMTS;
import slobodan.siuvs2.repository.VrstaMTSRepository;
import slobodan.siuvs2.valueObject.VrstaMTSId;

@Service
public class VrstaMTSServiceImpl implements VrstaMTSService {

    @Autowired
    private VrstaMTSRepository vrstaMTSRepository;

    @Override
    public List<VrstaMTS> findAllBy(){
       return vrstaMTSRepository.findAllBy();
    }
    

    
     @Override
    public void save(VrstaMTS karton){
    vrstaMTSRepository.save(karton);
    }
   
        @Override
    public VrstaMTS findOne(VrstaMTSId id){
    return vrstaMTSRepository.findOne(id.getValue());
    
    };
    
  


}
