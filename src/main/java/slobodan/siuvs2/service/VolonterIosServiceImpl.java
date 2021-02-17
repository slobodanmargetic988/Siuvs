package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.Assessment;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.Mobileappdata;
import slobodan.siuvs2.model.Volonter;
import slobodan.siuvs2.model.VolonterIos;
import slobodan.siuvs2.repository.MobileappdataRepository;
import slobodan.siuvs2.repository.VolonterIosRepository;
import slobodan.siuvs2.repository.VolonterRepository;
import slobodan.siuvs2.valueObject.MobileappdataID;

@Service
public class VolonterIosServiceImpl implements VolonterIosService {

    @Autowired
    private VolonterIosRepository volonterRepository;

    @Override
    public List<VolonterIos> findAllBy(){
       return volonterRepository.findAllBy();
    }
    
    @Override
    public List<VolonterIos> findAllByOpstina( String opstina){
       return volonterRepository.findAllByOpstina( opstina);
    }
    
    @Override
    public VolonterIos findFirstByEmail( String Email){
       return volonterRepository.findFirstByEmail( Email);
    }
    
     @Override
    public void save(VolonterIos volonter){
    volonterRepository.save(volonter);
    }
          @Override
    public void updateToken(String stariToken,String token){
    volonterRepository.updateToken(stariToken,token);
    }
     


}
