package slobodan.siuvs2.service;

import java.util.List;

import slobodan.siuvs2.model.Mobileappdata;


public interface MobileappdataService {


    
    List<Mobileappdata> findAllBy();
    List<Mobileappdata> findAllByOpstina( String opstina);
    Mobileappdata findFirstByOpstinaAndOpasnost( String opstina, String opasnost);

}
