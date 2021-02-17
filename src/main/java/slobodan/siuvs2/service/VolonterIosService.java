package slobodan.siuvs2.service;

import java.util.List;


import slobodan.siuvs2.model.Volonter;
import slobodan.siuvs2.model.VolonterIos;


public interface VolonterIosService {


    
    List<VolonterIos> findAllBy();
    List<VolonterIos> findAllByOpstina( String opstina);
    VolonterIos findFirstByEmail( String Email);
     void save(VolonterIos volonter);
     void updateToken(String stariToken,String token);

}
