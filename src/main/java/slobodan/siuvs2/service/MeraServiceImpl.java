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
import slobodan.siuvs2.model.Mera;
import slobodan.siuvs2.repository.MeraRepository;
import slobodan.siuvs2.valueObject.MeraID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeraServiceImpl implements MeraService {

    @Autowired
    private MeraRepository meraRepository;

    /*
    @Autowired
    private RezultatRepository rezultatRepository;
    @Autowired
    private RezultatFactory rezultatFactory;
     */
    @Override
    public Mera findOne(MeraID meraID) {
        return meraRepository.findOne(meraID.getValue());
    }

    @Override
    public void save(Mera mera) {
        //rezultatRepository.save(rezultatFactory.empty(mera));
        meraRepository.save(mera);
    }

    @Override
    // @Transactional
    public void delete(MeraID meraID) {
        meraRepository.delete(meraID.getValue());
    }
;
}
