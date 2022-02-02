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
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.StabVS;

public interface StabVSService {

    List<StabVS> findAllByClient(Client client);

    StabVS findFirstByClient(Client client);

    void save(StabVS stab);

    void delete(StabVS stab);
}
