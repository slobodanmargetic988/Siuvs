package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import slobodan.siuvs2.model.OpenData;


public interface OpenDataService {

    OpenData findFirstByToken(String token);
     void save(OpenData data);
 

}
