/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import slobodan.siuvs2.model.Distrikt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.Provincija;
import slobodan.siuvs2.model.Supervising;
import slobodan.siuvs2.repository.SupervisingRepository;

@Service
public class SupervisingServiceImpl implements SupervisingService {

    @Autowired
    SupervisingRepository supervisingRepository;

    @Override
    public Supervising findFirstByDistrikt(Distrikt distrikt) {
        // return supervisingRepository.findFirstByDistriktId(distrikt.getId());
        return supervisingRepository.findFirstByDistrikt(distrikt);
    }

    @Override
    public Supervising findFirstByProvincija(Provincija provincija) {
        //return supervisingRepository.findFirstByProvincijaId(provincija.getId());
        return supervisingRepository.findFirstByProvincija(provincija);
    }

}
