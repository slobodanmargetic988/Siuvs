package slobodan.siuvs2.service;

import java.util.List;


import slobodan.siuvs2.model.Volonter;
import slobodan.siuvs2.model.Zanimanja;
import slobodan.siuvs2.valueObject.ZanimanjaId;


public interface ZanimanjaService {

    Zanimanja findOne(ZanimanjaId ZanimanjeId);
    
    List<Zanimanja> findAllByOrderByNazivAsc();

     void save(Zanimanja zanimanje);
 

}
