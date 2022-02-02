package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import java.util.List;
import slobodan.siuvs2.model.Kadrovi;
import slobodan.siuvs2.model.KartonSubjekti;


import slobodan.siuvs2.model.Volonter;
import slobodan.siuvs2.model.Zanimanja;
import slobodan.siuvs2.model.ZanimanjaPodvrsta;
import slobodan.siuvs2.valueObject.KadroviId;
import slobodan.siuvs2.valueObject.ZanimanjaId;


public interface KadroviService {

    Kadrovi findOne(KadroviId kadarId);
    
    List<Kadrovi> findAllBy();

     void save(Kadrovi kadar);
   Kadrovi findFirstByZanimanjeAndKartonsubjekti(ZanimanjaPodvrsta zanimanje,KartonSubjekti kartonsubjekti);

}
