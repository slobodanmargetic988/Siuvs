/*
 * 
 */
package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.Osoba;
import slobodan.siuvs2.repository.OsobaRepository;
import slobodan.siuvs2.valueObject.OsobaId;

@Service
public class OsobaServiceImpl implements OsobaService {

    @Autowired
    private OsobaRepository osobaRepository;

    @Override
    public List<Osoba> findAllBy() {
       return osobaRepository.findAllBy();
    }

    @Override
    public Osoba findFirstByEmail(String email) {
         return osobaRepository.findFirstByEmail(email);
    }

    @Override
    public void save(Osoba osoba) {
          osobaRepository.save(osoba);
    }

    @Override
    public void delete(Osoba osoba) {
         osobaRepository.delete(osoba);
    }

    @Override
    public Osoba findOne(OsobaId spodobaId){
    return osobaRepository.findOne(spodobaId.getValue());
    }
    
}
