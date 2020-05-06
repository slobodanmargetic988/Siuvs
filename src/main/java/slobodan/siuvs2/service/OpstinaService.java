/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author deca
 */

import java.util.List;
import slobodan.siuvs2.model.Distrikt;
import slobodan.siuvs2.model.Opstina;
import slobodan.siuvs2.model.Provincija;

import slobodan.siuvs2.valueObject.OpstinaID;


public interface OpstinaService {
    Opstina findOne(OpstinaID opstinaID);
List<Opstina> findAllOrderByNameAsc();
List<Opstina> findAllByDistriktOrderByNameAsc(Distrikt distrikt);
List<Opstina> findAllByProvincijaOrderByNameAsc(Provincija provincija);
    void save(Opstina opstina);
    void delete(OpstinaID opstinaID);
}
