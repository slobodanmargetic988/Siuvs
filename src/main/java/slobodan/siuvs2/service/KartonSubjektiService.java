package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import java.util.List;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Kadrovi;
import slobodan.siuvs2.model.KartonSubjekti;


import slobodan.siuvs2.model.Volonter;
import slobodan.siuvs2.model.Zanimanja;
import slobodan.siuvs2.valueObject.KadroviId;
import slobodan.siuvs2.valueObject.KartonSubjektiId;
import slobodan.siuvs2.valueObject.ZanimanjaId;


public interface KartonSubjektiService {

    KartonSubjekti findOne(KartonSubjektiId id);
    
    List<KartonSubjekti> findAllBy();
    List<KartonSubjekti> findAllByClientOrderByPunnazivAsc(Client client);
     void save(KartonSubjekti karton);
 

}
