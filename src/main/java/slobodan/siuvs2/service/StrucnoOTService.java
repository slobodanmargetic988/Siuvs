/*
 * 
 */
package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */


import java.util.List;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.StrucnoOT;
import slobodan.siuvs2.valueObject.StrucnoOTId;

public interface StrucnoOTService {

     List<StrucnoOT> findAllByClient(Client client);
      StrucnoOT findFirstByClient(Client client);
  StrucnoOT findOne(StrucnoOTId sotId);
    void save(StrucnoOT sot);

    void delete(StrucnoOT sot);
}
