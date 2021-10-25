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
import slobodan.siuvs2.model.OsobaStab;
import slobodan.siuvs2.model.StabVS;
import slobodan.siuvs2.model.StrucnoOT;
import slobodan.siuvs2.repository.OsobaSOTRepository;
import slobodan.siuvs2.repository.OsobaStabRepository;
import slobodan.siuvs2.valueObject.OsobaStabId;

@Service
public class OsobaStabServiceImpl implements OsobaStabService {

    @Autowired
    private OsobaStabRepository osobaStabRepository;

    @Override
    public List<OsobaStab> findAllBy() {
        return osobaStabRepository.findAllBy();
    }

    @Override
    public List<OsobaStab> findAllByOsoba(Osoba osoba) {
        return osobaStabRepository.findAllByOsoba(osoba);
    }

    @Override
    public List<OsobaStab> findAllBySot(StabVS stab) {
        return osobaStabRepository.findAllByStab(stab);
    }

    @Override
    public void save(OsobaStab osobastab) {
        osobaStabRepository.save(osobastab);
    }

    @Override
    public void delete(OsobaStab osobastab) {
        osobaStabRepository.delete(osobastab);
    }

 @Override
    public OsobaStab findFirstByOsobaAndStab(Osoba osoba,StabVS stab){
       return osobaStabRepository.findFirstByOsobaAndStab(osoba, stab);
    }

    @Override
    public OsobaStab findOne(OsobaStabId osobaStabId) {
         return osobaStabRepository.findOne(osobaStabId.getValue());
    }


}
