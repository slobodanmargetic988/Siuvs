package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import java.util.List;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.GrupaMTS;
import slobodan.siuvs2.model.PodvrstaMTS;
import slobodan.siuvs2.valueObject.PodvrstaMTSId;


public interface PodvrstaMTSService {

    PodvrstaMTS findOne(PodvrstaMTSId id);
    
    List<PodvrstaMTS> findAllBy();

     void save(PodvrstaMTS karton);
 

}
