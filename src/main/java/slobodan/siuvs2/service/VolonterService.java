package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import java.util.List;


import slobodan.siuvs2.model.Volonter;


public interface VolonterService {


    
    List<Volonter> findAllBy();
    List<Volonter> findAllByOpstina( String opstina);
    Volonter findFirstByEmail( String Email);
     void save(Volonter volonter);
     void updateToken(String stariToken,String token);

}
