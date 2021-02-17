package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.MobileAppUniq;
import slobodan.siuvs2.model.MobileAppUniqIos;





public interface MobileAppUniqIosService {


    
    List<MobileAppUniqIos> findAllBy();
         void save(MobileAppUniqIos mobileAppUniq);
  void updateToken(String stariToken,String token);

   List<String> findDistinctToken();
 long count();
  
}
