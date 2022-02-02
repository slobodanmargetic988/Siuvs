package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import java.util.List;
import slobodan.siuvs2.model.Assessment;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.Mobileappdata;
import slobodan.siuvs2.model.Volonter;
import slobodan.siuvs2.repository.MobileappdataRepository;
import slobodan.siuvs2.repository.VolonterRepository;
import slobodan.siuvs2.valueObject.MobileappdataID;

@Service
public class VolonterServiceImpl implements VolonterService {

    @Autowired
    private VolonterRepository volonterRepository;

    @Override
    public List<Volonter> findAllBy(){
       return volonterRepository.findAllBy();
    }
    
    @Override
    public List<Volonter> findAllByOpstina( String opstina){
       return volonterRepository.findAllByOpstina( opstina);
    }
    
    @Override
    public Volonter findFirstByEmail( String Email){
       return volonterRepository.findFirstByEmail( Email);
    }
    
     @Override
    public void save(Volonter volonter){
    volonterRepository.save(volonter);
    }
          @Override
    public void updateToken(String stariToken,String token){
    volonterRepository.updateToken(stariToken,token);
    }
     


}
