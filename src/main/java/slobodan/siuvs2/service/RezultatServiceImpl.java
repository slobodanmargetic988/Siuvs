/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import slobodan.siuvs2.model.Rezultat;
import slobodan.siuvs2.repository.RezultatRepository;
import slobodan.siuvs2.valueObject.RezultatID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RezultatServiceImpl implements RezultatService {

    @Autowired
    private RezultatRepository rezultatRepository;

    /*
    @Autowired
    private PodRezultatRepository podRezultatRepository;
    @Autowired
    private PodRezultatFactory podRezultatFactory;
     */
    @Override
    public Rezultat findOne(RezultatID rezultatID) {
        return rezultatRepository.findOne(rezultatID.getValue());
    }

    @Override
    public void save(Rezultat rezultat) {

        // podRezultatRepository.save(podRezultatFactory.empty(rezultat));
        rezultatRepository.save(rezultat);

    }

    @Override
    // @Transactional
    public void delete(RezultatID rezultatID) {
        rezultatRepository.delete(rezultatID.getValue());
    }
;
}
