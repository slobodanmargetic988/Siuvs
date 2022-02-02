package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import java.util.List;


import slobodan.siuvs2.model.ZanimanjaPodvrsta;
import slobodan.siuvs2.valueObject.ZanimanjaPodvrstaId;


public interface ZanimanjaPodvrstaService {

    ZanimanjaPodvrsta findOne(ZanimanjaPodvrstaId ZanimanjeId);
    
    List<ZanimanjaPodvrsta> findAllByOrderByNazivAsc();

     void save(ZanimanjaPodvrsta zanimanjePodvrsta);
 

}
