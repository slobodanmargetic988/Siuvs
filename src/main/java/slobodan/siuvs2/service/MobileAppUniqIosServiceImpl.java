package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.MobileAppUniq;
import slobodan.siuvs2.model.MobileAppUniqIos;
import slobodan.siuvs2.repository.MobileAppUniqIosRepository;
import slobodan.siuvs2.repository.MobileAppUniqRepository;
@Service
public class MobileAppUniqIosServiceImpl implements MobileAppUniqIosService {

    @Autowired
    private MobileAppUniqIosRepository mobileAppUniqRepository;

    @Override
    public List<MobileAppUniqIos> findAllBy(){
       return mobileAppUniqRepository.findAllBy();
    }
    
    
    
     @Override
    public void save(MobileAppUniqIos mobileAppUniq){
    mobileAppUniqRepository.save(mobileAppUniq);
    }
      
              @Override
    public void updateToken(String stariToken,String token){
    mobileAppUniqRepository.updateToken(stariToken,token);
    }

     @Override
    public List<String> findDistinctToken(){
    return mobileAppUniqRepository.findDistinctToken();
    };

    @Override
    public  long count(){
    return mobileAppUniqRepository.count();
    };
}
