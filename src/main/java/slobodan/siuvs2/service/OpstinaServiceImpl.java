/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author deca
 */


import java.util.List;
import slobodan.siuvs2.model.Opstina;

import slobodan.siuvs2.repository.OpstinaRepository;

import slobodan.siuvs2.valueObject.OpstinaID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.Distrikt;
import slobodan.siuvs2.model.Provincija;

@Service
public class OpstinaServiceImpl implements OpstinaService{
@Autowired
OpstinaRepository opstinaRepository;

        @Override
    public Opstina findOne(OpstinaID opstinaID) {
        return opstinaRepository.findOne(opstinaID.getValue());
    }
    @Override
    public List<Opstina> findAllOrderByNameAsc(){
    return opstinaRepository.findAllByOrderByNameAsc();
    }
    
    @Override
    public void save(Opstina opstina) {
      
        opstinaRepository.save(opstina);
    }
    
            @Override
    public void delete(OpstinaID opstinaID){
   opstinaRepository.delete(opstinaID.getValue());
    };
    
   @Override
    public List<Opstina> findAllByDistriktOrderByNameAsc(Distrikt distrikt){
    return opstinaRepository.findAllByDistriktOrderByNameAsc(distrikt);
    };
    @Override
    public List<Opstina> findAllByProvincijaOrderByNameAsc(Provincija provincija){
        return opstinaRepository.findAllByProvincijaOrderByNameAsc(provincija);
    }
}
