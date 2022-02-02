package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import java.util.List;
import slobodan.siuvs2.model.MobileAppUniq;





public interface MobileAppUniqService {


    
    List<MobileAppUniq> findAllBy();
         void save(MobileAppUniq mobileAppUniq);
          void updateToken(String stariToken,String token);
          
 List<String> findDistinctToken();
 long count();

}
