package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import java.util.List;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.GrupaMTS;
import slobodan.siuvs2.model.OrgJedinicaMTS;
import slobodan.siuvs2.model.VlasnikMTS;
import slobodan.siuvs2.valueObject.GrupaMTSId;
import slobodan.siuvs2.valueObject.OrgJedinicaMTSId;


public interface OrgJedinicaMTSService {

    OrgJedinicaMTS findOne(OrgJedinicaMTSId id);
    
    List<OrgJedinicaMTS> findAllBy();
 
     void save(OrgJedinicaMTS karton);
 
  List <OrgJedinicaMTS> findAllByClient(Client client);
  

  List <OrgJedinicaMTS> findAllByVlasnikMTS(VlasnikMTS vlasnikMTS);

}
