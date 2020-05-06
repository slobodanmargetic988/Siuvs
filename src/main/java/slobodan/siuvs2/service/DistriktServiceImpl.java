/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author deca
 */

import java.util.List;
import slobodan.siuvs2.model.Distrikt;
import slobodan.siuvs2.repository.DistriktRepository;
import slobodan.siuvs2.valueObject.DistriktID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistriktServiceImpl implements DistriktService{
@Autowired
DistriktRepository distriktRepository;

        @Override
    public Distrikt findOne(DistriktID distriktID) {
        return distriktRepository.findOne(distriktID.getValue());
    }

    @Override
    public void save(Distrikt distrikt) {
      
        distriktRepository.save(distrikt);
    }
    @Override
    public List<Distrikt> findAllOrderByNameAsc(){
    return distriktRepository.findAllByOrderByNameAsc();
    }
    
            @Override
    public void delete(DistriktID distriktID){
   distriktRepository.delete(distriktID.getValue());
    };
 @Override
    public Distrikt findFirstByName(String name){
    return distriktRepository.findFirstByName(name);
    }
}
