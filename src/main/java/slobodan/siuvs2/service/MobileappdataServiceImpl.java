package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.Assessment;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.Mobileappdata;
import slobodan.siuvs2.repository.MobileappdataRepository;
import slobodan.siuvs2.valueObject.MobileappdataID;

@Service
public class MobileappdataServiceImpl implements MobileappdataService {

    @Autowired
    private MobileappdataRepository mobileappdataRepository;

    @Override
    public List<Mobileappdata> findAllBy() {
       return mobileappdataRepository.findAllBy();
    }

    @Override
    public List<Mobileappdata> findAllByOpstina(String opstina) {
       return mobileappdataRepository.findAllByOpstina( opstina);
    }

    @Override
    public Mobileappdata findFirstByOpstinaAndOpasnost(String opstina, String opasnost) {
        return mobileappdataRepository.findFirstByOpstinaAndOpasnost( opstina,opasnost);
    }
 @Override
    public void save(Mobileappdata mobileappdata){
     mobileappdataRepository.save(mobileappdata);
    }
    
    @Override
    public void update(MobileappdataID mobileappdataId, Mobileappdata editMobileappdata){
        Mobileappdata mobileappdata= findOne(mobileappdataId);
         mobileappdata.setKlasifikacija(editMobileappdata.getKlasifikacija());
         mobileappdata.setLink(editMobileappdata.getLink());
         mobileappdata.setOpasnost(editMobileappdata.getOpasnost());
       mobileappdata.setOpstina(editMobileappdata.getOpstina());
        mobileappdata.setTekst(editMobileappdata.getTekst());
        save(mobileappdata);
    
    }
    
    
    @Override
    public Mobileappdata findOne(MobileappdataID mobileappdataId) {
        return mobileappdataRepository.findOne(mobileappdataId.getValue());
    }
}
