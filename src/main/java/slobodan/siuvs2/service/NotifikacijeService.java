package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import java.util.List;


import slobodan.siuvs2.model.Notifikacije;
import slobodan.siuvs2.model.Opstina;


public interface NotifikacijeService {


    
    List<Notifikacije> findAllBy();
    List<Notifikacije> findAllByOrderByOpstinaAsc();
    
       List<Notifikacije> findAllByOpstina( Opstina opstina);
       
     List<String> findDistinctByToken();
    List<String> findAllByOpstina( String opstina);
    List<Notifikacije> findAllByToken( String token);
     Notifikacije findFirstByOpstinaAndToken(  String opstina, String token);
     Long deleteByToken(String token);
     Long deleteByTokenAndOpstina(String token,String opstina);
     void save(Notifikacije notifikacije);
   void updateToken(String stariToken,String token);
   
        long countByOpstina(String opstinaName);
   
        
         void deleteByTokenIn(List<String> tokeni);
}
