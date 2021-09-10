package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.Delatnost;
import slobodan.siuvs2.model.Kadrovi;


import slobodan.siuvs2.model.Volonter;
import slobodan.siuvs2.model.Zanimanja;
import slobodan.siuvs2.valueObject.DelatnostId;
import slobodan.siuvs2.valueObject.KadroviId;
import slobodan.siuvs2.valueObject.ZanimanjaId;


public interface DelatnostService {

    Delatnost findOne(DelatnostId delatnostId);
    
    List<Delatnost> findAllByOrderByNazivAsc();

     void save(Delatnost delatnost);
 

}
