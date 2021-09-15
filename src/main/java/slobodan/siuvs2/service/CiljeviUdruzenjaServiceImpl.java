package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.Assessment;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.CiljeviUdruzenja;
import slobodan.siuvs2.model.ClanoviUdruzenja;
import slobodan.siuvs2.model.Kadrovi;
import slobodan.siuvs2.model.KartonSubjekti;
import slobodan.siuvs2.model.KartonUdruzenja;
import slobodan.siuvs2.model.Mobileappdata;
import slobodan.siuvs2.model.Specijalnost;
import slobodan.siuvs2.model.Volonter;
import slobodan.siuvs2.model.Zanimanja;
import slobodan.siuvs2.repository.CiljeviUdruzenjaRepository;
import slobodan.siuvs2.repository.ClanoviUdruzenjaRepository;
import slobodan.siuvs2.repository.KadroviRepository;
import slobodan.siuvs2.repository.MobileappdataRepository;
import slobodan.siuvs2.repository.VolonterRepository;
import slobodan.siuvs2.repository.ZanimanjaRepository;
import slobodan.siuvs2.valueObject.CiljeviUdruzenjaId;
import slobodan.siuvs2.valueObject.ClanoviUdruzenjaId;
import slobodan.siuvs2.valueObject.KadroviId;
import slobodan.siuvs2.valueObject.MobileappdataID;
import slobodan.siuvs2.valueObject.ZanimanjaId;

@Service
public class CiljeviUdruzenjaServiceImpl implements CiljeviUdruzenjaService {

    @Autowired
    private  CiljeviUdruzenjaRepository  ciljeviUdruzenjaRepository;

    @Override
    public List< CiljeviUdruzenja> findAllBy(){
       return ciljeviUdruzenjaRepository.findAllBy();
    }
    


    
     @Override
    public void save( CiljeviUdruzenja  ciljeviUdruzenja){
    ciljeviUdruzenjaRepository.save(ciljeviUdruzenja);
    }
   
        @Override
    public   CiljeviUdruzenja findOne( CiljeviUdruzenjaId  ciljeviUdruzenjaId){
    return ciljeviUdruzenjaRepository.findOne(ciljeviUdruzenjaId.getValue());
    
    };

    @Override
    public List<CiljeviUdruzenja> findAllByKartonudruzenja(KartonUdruzenja kartonudruzenja) {
        return ciljeviUdruzenjaRepository.findAllByKartonudruzenjaOrderByNazivAsc(kartonudruzenja);
    }
    
     


}
