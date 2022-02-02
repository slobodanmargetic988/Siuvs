package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.Specijalnost;
import slobodan.siuvs2.repository.SpecijalnostRepository;
import slobodan.siuvs2.valueObject.SpecijalnostId;

@Service
public class SpecijalnostServiceImpl implements SpecijalnostService {

    @Autowired
    private SpecijalnostRepository specijalnostRepository;

    @Override
    public List<Specijalnost> findAllByOrderByNazivAsc(){
       return specijalnostRepository.findAllByOrderByNazivAsc();
    }
    


    
     @Override
    public void save(Specijalnost specijalnost){
    specijalnostRepository.save(specijalnost);
    }
   
        @Override
    public  Specijalnost findOne(SpecijalnostId specijalnostId){
    return specijalnostRepository.findOne(specijalnostId.getValue());
    
    };
    
  


}
