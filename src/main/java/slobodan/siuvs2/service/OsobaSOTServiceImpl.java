/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.Osoba;
import slobodan.siuvs2.model.OsobaSOT;
import slobodan.siuvs2.model.StrucnoOT;
import slobodan.siuvs2.repository.OsobaSOTRepository;
import slobodan.siuvs2.valueObject.OsobaSOTId;

@Service
public class OsobaSOTServiceImpl implements OsobaSOTService {

    @Autowired
    private OsobaSOTRepository osobaSOTRepository;



    @Override
    public List<OsobaSOT> findAllByOsoba(Osoba osoba) {
      return osobaSOTRepository.findAllByOsoba(osoba);
               }

    @Override
    public List<OsobaSOT> findAllBySot(StrucnoOT sot) {
       return  osobaSOTRepository.findAllBySot(sot);
    }

    @Override
    public void save(OsobaSOT osobasot) {
      osobaSOTRepository.save(osobasot);
    }

    @Override
    public void delete(OsobaSOT osobasot) {
       osobaSOTRepository.delete(osobasot);
    }

    @Override
    public List<OsobaSOT> findAllBy() {
          return  osobaSOTRepository.findAllBy();
    }
     @Override
    public OsobaSOT findFirstByOsobaAndSot(Osoba osoba,StrucnoOT sot){
       return  osobaSOTRepository.findFirstByOsobaAndSot(osoba, sot);
    }

    @Override
    public OsobaSOT findOne(OsobaSOTId osobaSOTId) {
       return  osobaSOTRepository.findOne(osobaSOTId.getValue());
    }
}
