package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import java.util.List;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.GrupaMTS;
import slobodan.siuvs2.model.VrstaMTS;
import slobodan.siuvs2.valueObject.VrstaMTSId;


public interface VrstaMTSService {

    VrstaMTS findOne(VrstaMTSId id);
    
    List<VrstaMTS> findAllBy();
 
     void save(VrstaMTS karton);
 

}
