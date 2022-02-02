package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import java.util.List;
import slobodan.siuvs2.model.CiljeviUdruzenja;
import slobodan.siuvs2.model.ClanoviUdruzenja;
import slobodan.siuvs2.model.Kadrovi;
import slobodan.siuvs2.model.KartonSubjekti;
import slobodan.siuvs2.model.KartonUdruzenja;
import slobodan.siuvs2.model.Specijalnost;


import slobodan.siuvs2.model.Volonter;
import slobodan.siuvs2.model.Zanimanja;
import slobodan.siuvs2.valueObject.CiljeviUdruzenjaId;
import slobodan.siuvs2.valueObject.ClanoviUdruzenjaId;
import slobodan.siuvs2.valueObject.KadroviId;
import slobodan.siuvs2.valueObject.ZanimanjaId;


public interface CiljeviUdruzenjaService {

    CiljeviUdruzenja findOne(CiljeviUdruzenjaId ciljeviUdruzenjaId);
    
    List<CiljeviUdruzenja> findAllBy();

     void save(CiljeviUdruzenja ciljeviUdruzenja);
     
  List<CiljeviUdruzenja> findAllByKartonudruzenja(KartonUdruzenja kartonudruzenja) ;
}
