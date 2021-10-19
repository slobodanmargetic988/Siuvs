package slobodan.siuvs2.service;

import slobodan.siuvs2.model.OpenData;


public interface OpenDataService {

    OpenData findFirstByToken(String token);
     void save(OpenData data);
 

}
