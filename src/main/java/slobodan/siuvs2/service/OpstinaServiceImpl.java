/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import java.util.List;
import slobodan.siuvs2.model.Opstina;

import slobodan.siuvs2.repository.OpstinaRepository;

import slobodan.siuvs2.valueObject.OpstinaID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.Distrikt;
import slobodan.siuvs2.model.Provincija;

@Service
public class OpstinaServiceImpl implements OpstinaService {

    @Autowired
    OpstinaRepository opstinaRepository;

    @Override
    public Opstina findOne(OpstinaID opstinaID) {
        return opstinaRepository.findOne(opstinaID.getValue());
    }
    
    @Override
    public Opstina findFirstByName(String name) {
        return opstinaRepository.findFirstByName(name);
    }
        @Override
    public Opstina findFirstByNamelatinica(String name) {
        return opstinaRepository.findFirstByNamelatinica(name);
    }

    @Override
    public List<Opstina> findAllOrderByNameAsc() {
        return opstinaRepository.findAllByOrderByNameAsc();
    }

    @Override
    public void save(Opstina opstina) {

        opstinaRepository.save(opstina);
    }

    @Override
    public void delete(OpstinaID opstinaID) {
        opstinaRepository.delete(opstinaID.getValue());
    }

    ;
    
   @Override
    public List<Opstina> findAllByDistriktOrderByNameAsc(Distrikt distrikt) {
        return opstinaRepository.findAllByDistriktOrderByNameAsc(distrikt);
    }

    ;
    @Override
    public List<Opstina> findAllByProvincijaOrderByNameAsc(Provincija provincija) {
        return opstinaRepository.findAllByProvincijaOrderByNameAsc(provincija);
    }

    @Override
    public Page<Opstina> findAllOrderByNameAsc(Pageable pageable) {
        return opstinaRepository.findAllByOrderByNameAsc(pageable);
    }

    @Override
    public Boolean isNameUsed(String name) {
        Boolean result = false;
        List<Opstina> opstine = opstinaRepository.findByName(name);
        if (opstine.size() > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public Boolean isNameUsed(OpstinaID opstinaID, String name) {
        Boolean result = false;
        List<Opstina> opstine = opstinaRepository.findByName(name);
        for (Opstina opstina : opstine) {
            if (opstina.getId() != opstinaID.getValue()) {
                result = true;
            }
        }
        return result;
    }

}
