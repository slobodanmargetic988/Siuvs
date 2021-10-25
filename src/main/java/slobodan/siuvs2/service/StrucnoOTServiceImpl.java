/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.StrucnoOT;
import slobodan.siuvs2.repository.StrucnoOTRepository;
import slobodan.siuvs2.valueObject.StrucnoOTId;

@Service
public class StrucnoOTServiceImpl implements StrucnoOTService {

    @Autowired
    private StrucnoOTRepository strucnoOTrepository;

    @Override
    public List<StrucnoOT> findAllByClient(Client client) {
      return strucnoOTrepository.findAllByClient(client);
    }

    @Override
    public StrucnoOT findFirstByClient(Client client) {
      return strucnoOTrepository.findFirstByClient(client);
    }

    @Override
    public void save(StrucnoOT sot) {
      strucnoOTrepository.save(sot);
    }

    @Override
    public void delete(StrucnoOT sot) {
     strucnoOTrepository.delete(sot);
    }

     @Override
    public StrucnoOT findOne(StrucnoOTId sotId){
     return strucnoOTrepository.findOne(sotId.getValue());
    }


}
