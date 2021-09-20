package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.KartonClanovaStaba;
import slobodan.siuvs2.valueObject.KartonClanovaStabaId;


public interface KartonClanovaStabaService {

    KartonClanovaStaba findOne(KartonClanovaStabaId id);
    
    List<KartonClanovaStaba> findAllBy();
    List<KartonClanovaStaba> findAllByClientOrderByPunoimeAsc(Client client);
     void save(KartonClanovaStaba karton);
 

}
