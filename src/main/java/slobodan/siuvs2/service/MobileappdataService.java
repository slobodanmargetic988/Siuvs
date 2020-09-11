package slobodan.siuvs2.service;

import java.util.List;

import slobodan.siuvs2.model.Mobileappdata;
import slobodan.siuvs2.valueObject.MobileappdataID;


public interface MobileappdataService {


    
    List<Mobileappdata> findAllBy();
    List<Mobileappdata> findAllByOpstina( String opstina);
    Mobileappdata findFirstByOpstinaAndOpasnost( String opstina, String opasnost);
    void save(Mobileappdata mobileappdata);
    void update(MobileappdataID mobileappdataId, Mobileappdata mobileappdata);
    Mobileappdata findOne(MobileappdataID mobileappdataId);
}
