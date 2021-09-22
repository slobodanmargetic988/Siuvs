package slobodan.siuvs2.service;

import java.util.List;


import slobodan.siuvs2.model.Notifikacije;
import slobodan.siuvs2.model.NotifikacijeIos;
import slobodan.siuvs2.model.Opstina;


public interface NotifikacijeIosService {


    
    List<NotifikacijeIos> findAllBy();
    List<NotifikacijeIos> findAllByOrderByOpstinaAsc();
    
       List<NotifikacijeIos> findAllByOpstina( Opstina opstina);
       
     List<String> findDistinctByToken();
    List<String> findAllByOpstina( String opstina);
    List<NotifikacijeIos> findAllByToken( String token);
     NotifikacijeIos findFirstByOpstinaAndToken(  String opstina, String token);
     Long deleteByToken(String token);
     Long deleteByTokenAndOpstina(String token,String opstina);
     void save(NotifikacijeIos notifikacije);
   void updateToken(String stariToken,String token);
     long countByOpstina(String opstinaName);
   
     
      void deleteByTokenIn(List<String> tokeni);
}
