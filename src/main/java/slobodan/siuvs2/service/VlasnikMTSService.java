package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.GrupaMTS;
import slobodan.siuvs2.model.VlasnikMTS;
import slobodan.siuvs2.valueObject.GrupaMTSId;
import slobodan.siuvs2.valueObject.VlasnikMTSId;


public interface VlasnikMTSService {

    VlasnikMTS findOne(VlasnikMTSId id);
    
    List<VlasnikMTS> findAllBy();
 
     void save(VlasnikMTS karton);
 
  List <VlasnikMTS> findAllByClient(Client client);


}

  

    
