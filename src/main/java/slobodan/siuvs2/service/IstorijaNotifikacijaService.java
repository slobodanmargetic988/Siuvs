package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */


import java.util.List;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.IstorijaNotifikacija;
import slobodan.siuvs2.model.User;

public interface IstorijaNotifikacijaService {

 IstorijaNotifikacija findById(Integer istorijaNotifikacijaId);
     void save(IstorijaNotifikacija in);
        List<IstorijaNotifikacija> findAllBy();
    List<IstorijaNotifikacija> findAllByClient(Client client);
     Integer countLastMonthPoslateForClientID(Integer client_id); 
     List<IstorijaNotifikacija> selectLastMonthPoslateForClientID(Integer client_id); 
    
       List<IstorijaNotifikacija> findAllByCreatedBy(Integer id);
          List<IstorijaNotifikacija> findAllByCreatedByIn( List<User> useriistogservisa);
}
