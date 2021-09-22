package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.GrupaMTS;
import slobodan.siuvs2.valueObject.GrupaMTSId;


public interface GrupaMTSService {

    GrupaMTS findOne(GrupaMTSId id);
    
    List<GrupaMTS> findAllBy();
 
     void save(GrupaMTS karton);
 

}
