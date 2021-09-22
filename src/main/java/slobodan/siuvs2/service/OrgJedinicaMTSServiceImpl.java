package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.GrupaMTS;
import slobodan.siuvs2.model.OrgJedinicaMTS;
import slobodan.siuvs2.model.VlasnikMTS;
import slobodan.siuvs2.repository.GrupaMTSRepository;
import slobodan.siuvs2.repository.OrgJedinicaMTSRepository;
import slobodan.siuvs2.valueObject.GrupaMTSId;
import slobodan.siuvs2.valueObject.OrgJedinicaMTSId;

@Service
public class OrgJedinicaMTSServiceImpl implements OrgJedinicaMTSService {

    @Autowired
    private OrgJedinicaMTSRepository orgJedinicaMTSRepository;

    @Override
    public List<OrgJedinicaMTS> findAllBy(){
       return orgJedinicaMTSRepository.findAllBy();
    }
    


     @Override
    public void save(OrgJedinicaMTS karton){
    orgJedinicaMTSRepository.save(karton);
    }
   
        @Override
    public OrgJedinicaMTS findOne(OrgJedinicaMTSId id){
    return orgJedinicaMTSRepository.findOne(id.getValue());
    
    };
    
    @Override
    public List<OrgJedinicaMTS> findAllByClient(Client client){
       return orgJedinicaMTSRepository.findAllByClient(client);
    }

    
//       @Override
//    public OrgJedinicaMTS findAllByVlasnikMTS(OrgJedinicaMTSId id){
//    return orgJedinicaMTSRepository.findAllByVlasnikMTS(id.getValue());
//    
//    };
    
     @Override
    public List<OrgJedinicaMTS> findAllByVlasnikMTS(VlasnikMTS vlasnikMTS){
       return orgJedinicaMTSRepository.findAllByVlasnikMTS(vlasnikMTS);
    }




}
