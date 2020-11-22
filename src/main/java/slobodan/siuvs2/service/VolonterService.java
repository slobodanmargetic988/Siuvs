package slobodan.siuvs2.service;

import java.util.List;


import slobodan.siuvs2.model.Volonter;


public interface VolonterService {


    
    List<Volonter> findAllBy();
    List<Volonter> findAllByOpstina( String opstina);
    Volonter findFirstByEmail( String Email);
     void save(Volonter volonter);
     void updateToken(String stariToken,String token);

}
