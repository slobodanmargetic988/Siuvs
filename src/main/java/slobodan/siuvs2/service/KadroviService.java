package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.Kadrovi;
import slobodan.siuvs2.model.KartonSubjekti;


import slobodan.siuvs2.model.Volonter;
import slobodan.siuvs2.model.Zanimanja;
import slobodan.siuvs2.valueObject.KadroviId;
import slobodan.siuvs2.valueObject.ZanimanjaId;


public interface KadroviService {

    Kadrovi findOne(KadroviId kadarId);
    
    List<Kadrovi> findAllBy();

     void save(Kadrovi kadar);
   Kadrovi findFirstByZanimanjeAndKartonsubjekti(Zanimanja zanimanje,KartonSubjekti kartonsubjekti);

}