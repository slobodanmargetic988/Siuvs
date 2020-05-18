/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author deca
 */

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Distrikt;
import slobodan.siuvs2.model.Opstina;

import slobodan.siuvs2.model.Provincija;

import slobodan.siuvs2.valueObject.OpstinaID;


public interface OpstinaService {
    Opstina findOne(OpstinaID opstinaID);
List<Opstina> findAllOrderByNameAsc();
Page<Opstina> findAllOrderByNameAsc(Pageable pageable);
List<Opstina> findAllByDistriktOrderByNameAsc(Distrikt distrikt);
List<Opstina> findAllByProvincijaOrderByNameAsc(Provincija provincija);
    void save(Opstina opstina);
    void delete(OpstinaID opstinaID);
    Boolean isNameUsed(String name);
     Boolean isNameUsed(OpstinaID opstinaID,String name);
}