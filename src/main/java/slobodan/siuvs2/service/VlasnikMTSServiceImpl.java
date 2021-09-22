package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.GrupaMTS;
import slobodan.siuvs2.model.VlasnikMTS;
import slobodan.siuvs2.repository.GrupaMTSRepository;
import slobodan.siuvs2.repository.VlasnikMTSRepository;
import slobodan.siuvs2.valueObject.GrupaMTSId;
import slobodan.siuvs2.valueObject.VlasnikMTSId;

@Service
public class VlasnikMTSServiceImpl implements VlasnikMTSService {

    @Autowired
    private VlasnikMTSRepository vlasnikMTSRepository;

    @Override
    public List<VlasnikMTS> findAllBy(){
       return vlasnikMTSRepository.findAllBy();
    }
    


     @Override
    public void save(VlasnikMTS karton){
    vlasnikMTSRepository.save(karton);
    }
   
        @Override
    public VlasnikMTS findOne(VlasnikMTSId id){
    return vlasnikMTSRepository.findOne(id.getValue());
    
    };
    
   @Override
    public List<VlasnikMTS> findAllByClient(Client client){
       return vlasnikMTSRepository.findAllByClient(client);
    }



}
