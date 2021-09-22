package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.DetaljiMTS;
import slobodan.siuvs2.model.GrupaMTS;
import slobodan.siuvs2.model.OrgJedinicaMTS;
import slobodan.siuvs2.model.VlasnikMTS;
import slobodan.siuvs2.valueObject.DetaljiMTSId;
import slobodan.siuvs2.valueObject.GrupaMTSId;


public interface DetaljiMTSService {

    DetaljiMTS findOne(DetaljiMTSId id);
    
    List<DetaljiMTS> findAllBy();
 
     void save(DetaljiMTS karton);
 
    List <DetaljiMTS> findAllByClient(Client client);

        List <DetaljiMTS> findAllByVlasnikMTS(VlasnikMTS vlasnikMTS);
        
               List < DetaljiMTS> findAllByOrgJedinicaMTS(OrgJedinicaMTS orgJedinicaMTS);


}
