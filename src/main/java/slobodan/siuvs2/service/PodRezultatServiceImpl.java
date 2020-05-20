/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import slobodan.siuvs2.model.PodRezultat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.repository.PodRezultatRepository;
import slobodan.siuvs2.valueObject.PodRezultatID;

@Service
public class PodRezultatServiceImpl implements PodRezultatService {

    @Autowired
    private PodRezultatRepository podRezultatRepository;

    @Override
    public PodRezultat findOne(PodRezultatID podRezultatID) {
        return podRezultatRepository.findOne(podRezultatID.getValue());
    }

    @Override
    // @Transactional
    public void save(PodRezultat podRezultat) {
        podRezultatRepository.save(podRezultat);

    }

    @Override
    // @Transactional
    public void delete(PodRezultatID podRezultatID) {
        podRezultatRepository.delete(podRezultatID.getValue());
    }
;
}
