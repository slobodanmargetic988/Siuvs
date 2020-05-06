/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author deca
 */

import java.util.List;
import slobodan.siuvs2.model.Provincija;
import slobodan.siuvs2.repository.ProvincijaRepository;
import slobodan.siuvs2.valueObject.ProvincijaID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvincijaServiceImpl implements ProvincijaService{
@Autowired
ProvincijaRepository provincijaRepository;

        @Override
    public Provincija findOne(ProvincijaID provincijaID) {
        return provincijaRepository.findOne(provincijaID.getValue());
    }

    @Override
    public void save(Provincija distrikt) {
      
        provincijaRepository.save(distrikt);
    }
    
    @Override
    public List<Provincija> findAllOrderByNameAsc(){
    return provincijaRepository.findAllByOrderByNameAsc() ;
            }
    
            @Override
    public void delete(ProvincijaID provincijaID){
   provincijaRepository.delete(provincijaID.getValue());
    };
     @Override
    public Provincija findFirstByName(String name){
    return provincijaRepository.findFirstByName(name) ;
            }
}
