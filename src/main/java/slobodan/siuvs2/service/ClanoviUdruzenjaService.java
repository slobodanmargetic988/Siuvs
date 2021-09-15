package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.ClanoviUdruzenja;
import slobodan.siuvs2.model.Kadrovi;
import slobodan.siuvs2.model.KartonSubjekti;
import slobodan.siuvs2.model.KartonUdruzenja;
import slobodan.siuvs2.model.Specijalnost;


import slobodan.siuvs2.model.Volonter;
import slobodan.siuvs2.model.Zanimanja;
import slobodan.siuvs2.valueObject.ClanoviUdruzenjaId;
import slobodan.siuvs2.valueObject.KadroviId;
import slobodan.siuvs2.valueObject.ZanimanjaId;


public interface ClanoviUdruzenjaService {

    ClanoviUdruzenja findOne(ClanoviUdruzenjaId clanoviUdruzenjaId);
    
    List<ClanoviUdruzenja> findAllBy();

     void save(ClanoviUdruzenja clanov);
     
   ClanoviUdruzenja findFirstBySpecijalnostAndKartonudruzenja(Specijalnost specijalnost,KartonUdruzenja kartonudruzenja);

}
