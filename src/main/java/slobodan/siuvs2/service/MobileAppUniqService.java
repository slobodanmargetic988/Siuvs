package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.MobileAppUniq;





public interface MobileAppUniqService {


    
    List<MobileAppUniq> findAllBy();
         void save(MobileAppUniq mobileAppUniq);
          void updateToken(String stariToken,String token);
          
 List<String> findDistinctToken();
 long count();

}
