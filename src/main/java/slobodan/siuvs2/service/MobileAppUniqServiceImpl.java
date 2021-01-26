package slobodan.siuvs2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.MobileAppUniq;
import slobodan.siuvs2.repository.MobileAppUniqRepository;
@Service
public class MobileAppUniqServiceImpl implements MobileAppUniqService {

    @Autowired
    private MobileAppUniqRepository mobileAppUniqRepository;

    @Override
    public List<MobileAppUniq> findAllBy(){
       return mobileAppUniqRepository.findAllBy();
    }
    
    
    
     @Override
    public void save(MobileAppUniq mobileAppUniq){
    mobileAppUniqRepository.save(mobileAppUniq);
    }
      
     


}
