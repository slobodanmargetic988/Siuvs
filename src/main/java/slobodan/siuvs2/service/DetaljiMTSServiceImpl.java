package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import java.util.List;
import slobodan.siuvs2.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.DetaljiMTS;
import slobodan.siuvs2.model.GrupaMTS;
import slobodan.siuvs2.model.OrgJedinicaMTS;
import slobodan.siuvs2.model.VlasnikMTS;
import slobodan.siuvs2.repository.DetaljiMTSRepository;
import slobodan.siuvs2.repository.GrupaMTSRepository;
import slobodan.siuvs2.valueObject.DetaljiMTSId;
import slobodan.siuvs2.valueObject.GrupaMTSId;

@Service
public class DetaljiMTSServiceImpl implements DetaljiMTSService {

    @Autowired
    private DetaljiMTSRepository detaljiMTSRepository;

    @Override
    public List<DetaljiMTS> findAllBy() {
        return detaljiMTSRepository.findAllBy();
    }

    @Override
    public void save(DetaljiMTS karton) {
        detaljiMTSRepository.save(karton);
    }

    @Override
    public DetaljiMTS findOne(DetaljiMTSId id) {
        return detaljiMTSRepository.findOne(id.getValue());

    };
    
      @Override
    public List<DetaljiMTS> findAllByClient(Client client){
       return detaljiMTSRepository.findAllByClient(client);
    }

       @Override
    public List<DetaljiMTS> findAllByVlasnikMTS(VlasnikMTS vlasnikMTS){
    return detaljiMTSRepository.findAllByVlasnikMTS(vlasnikMTS);
    
    };
    
          @Override
    public List<DetaljiMTS> findAllByOrgJedinicaMTS(OrgJedinicaMTS orgJedinicaMTS){
    return detaljiMTSRepository.findAllByOrgJedinicaMTS(orgJedinicaMTS);
    
    };
    

}
