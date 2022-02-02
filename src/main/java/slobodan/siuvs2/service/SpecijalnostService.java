package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import java.util.List;
import slobodan.siuvs2.model.Specijalnost;


import slobodan.siuvs2.valueObject.SpecijalnostId;


public interface SpecijalnostService {

    Specijalnost findOne(SpecijalnostId specijalnostId);
    
    List<Specijalnost> findAllByOrderByNazivAsc();

     void save(Specijalnost specijalnost);
 

}
