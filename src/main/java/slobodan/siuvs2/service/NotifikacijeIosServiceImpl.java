package slobodan.siuvs2.service;

import java.util.List;
import javax.transaction.Transactional;
import slobodan.siuvs2.model.Assessment;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.Mobileappdata;
import slobodan.siuvs2.model.Notifikacije;
import slobodan.siuvs2.model.NotifikacijeIos;
import slobodan.siuvs2.model.Opstina;
import slobodan.siuvs2.model.Volonter;
import slobodan.siuvs2.repository.MobileappdataRepository;
import slobodan.siuvs2.repository.NotifikacijeIosRepository;
import slobodan.siuvs2.repository.NotifikacijeRepository;
import slobodan.siuvs2.repository.VolonterRepository;
import slobodan.siuvs2.valueObject.MobileappdataID;

@Service
public class NotifikacijeIosServiceImpl implements NotifikacijeIosService {

    @Autowired
    private NotifikacijeIosRepository notifikacijeRepository;

    @Override
    public List<NotifikacijeIos> findAllBy(){
       return notifikacijeRepository.findAllBy();
    }
    
      @Override
    public List<NotifikacijeIos> findAllByOrderByOpstinaAsc(){
       return notifikacijeRepository.findAllByOrderByOpstinaAsc();
    }
          @Override
    public List<String> findDistinctByToken(){
       return notifikacijeRepository.findDistinctToken();
    }

    
    @Override
    public List<String> findAllByOpstina( String opstina){
       return notifikacijeRepository.findAllByOpstina( opstina);
    }
    
    @Override
    public List<NotifikacijeIos> findAllByOpstina( Opstina opstina){
       return notifikacijeRepository.findAllByOpstinaAsNotifikacije( opstina.getNamelatinica());
    }
    
    
    @Override
    public List<NotifikacijeIos> findAllByToken( String token){
       return notifikacijeRepository.findAllByToken( token);
    }
    
    @Override
    public NotifikacijeIos findFirstByOpstinaAndToken(  String opstina, String token){
     return notifikacijeRepository.findFirstByOpstinaAndToken(opstina, token);
    }
    @Override
    @Transactional
    public Long deleteByToken(String token){
    return notifikacijeRepository.deleteByToken(token);
    }
        @Override
        @Transactional
    public Long deleteByTokenAndOpstina(String token, String opstina){
    return notifikacijeRepository.deleteByTokenAndOpstina(token,opstina);
    }
     @Override
    public void save(NotifikacijeIos notifikacije){
    notifikacijeRepository.save(notifikacije);
    }
     
          @Override
    public void updateToken(String stariToken,String token){
    notifikacijeRepository.updateToken(stariToken,token);
    }

   

}
