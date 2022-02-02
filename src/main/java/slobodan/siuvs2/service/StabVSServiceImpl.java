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
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.StabVS;
import slobodan.siuvs2.repository.StabVSRepository;

@Service
public class StabVSServiceImpl implements StabVSService {

    @Autowired
    private StabVSRepository stabVSrepository;

    @Override
    public List<StabVS> findAllByClient(Client client) {
       return stabVSrepository.findAllByClient(client);
    }

    @Override
    public StabVS findFirstByClient(Client client) {
      return stabVSrepository.findFirstByClient(client);
    }

    @Override
    public void save(StabVS stab) {
        stabVSrepository.save(stab);
    }

    @Override
    public void delete(StabVS stab) {
        stabVSrepository.delete(stab);
    }



}
