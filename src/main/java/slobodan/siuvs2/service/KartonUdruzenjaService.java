package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Kadrovi;
import slobodan.siuvs2.model.KartonSubjekti;
import slobodan.siuvs2.model.KartonUdruzenja;


import slobodan.siuvs2.model.Volonter;
import slobodan.siuvs2.model.Zanimanja;
import slobodan.siuvs2.valueObject.KadroviId;
import slobodan.siuvs2.valueObject.KartonSubjektiId;
import slobodan.siuvs2.valueObject.KartonUdruzenjaId;
import slobodan.siuvs2.valueObject.ZanimanjaId;


public interface KartonUdruzenjaService {

    KartonUdruzenja findOne(KartonUdruzenjaId id);
    
    List<KartonUdruzenja> findAllBy();
    List<KartonUdruzenja> findAllByClientOrderByPunnazivAsc(Client client);
     void save(KartonUdruzenja karton);
 

}
