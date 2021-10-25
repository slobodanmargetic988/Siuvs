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
import slobodan.siuvs2.model.StrucnoOT;
import slobodan.siuvs2.valueObject.OsobaSOTId;

public interface OsobaSOTService {

    List<OsobaSOT> findAllBy();

    List<OsobaSOT> findAllByOsoba(Osoba osoba);

    List<OsobaSOT> findAllBySot(StrucnoOT sot);

    void save(OsobaSOT osobasot);

    void delete(OsobaSOT osobasot);
    
         OsobaSOT findFirstByOsobaAndSot(Osoba osoba,StrucnoOT sot);
         OsobaSOT findOne(OsobaSOTId osobaSOTId);
         
}
