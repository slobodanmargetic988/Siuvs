/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import java.util.List;
import slobodan.siuvs2.model.Osoba;
import slobodan.siuvs2.model.OsobaSOT;
import slobodan.siuvs2.model.OsobaStab;
import slobodan.siuvs2.model.StabVS;
import slobodan.siuvs2.model.StrucnoOT;
import slobodan.siuvs2.valueObject.OsobaStabId;

public interface OsobaStabService {

       List<OsobaStab> findAllBy();
      List<OsobaStab> findAllByOsoba(Osoba osoba);
       List<OsobaStab> findAllBySot(StabVS sot);
OsobaStab findFirstByOsobaAndStab(Osoba osoba,StabVS stab);

    void save(OsobaStab osobastab);

    void delete(OsobaStab osobastab);
    OsobaStab findOne(OsobaStabId osobaStabId);
}

