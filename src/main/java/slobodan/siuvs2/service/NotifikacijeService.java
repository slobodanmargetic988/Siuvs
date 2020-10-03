package slobodan.siuvs2.service;

import java.util.List;


import slobodan.siuvs2.model.Notifikacije;


public interface NotifikacijeService {


    
    List<Notifikacije> findAllBy();
    List<Notifikacije> findAllByOrderByOpstinaAsc();
     List<String> findDistinctByToken();
    List<String> findAllByOpstina( String opstina);
    List<Notifikacije> findAllByToken( String token);
     Notifikacije findFirstByOpstinaAndToken(  String opstina, String token);
     Long deleteByToken(String token);
     Long deleteByTokenAndOpstina(String token,String opstina);
     void save(Notifikacije notifikacije);

}
