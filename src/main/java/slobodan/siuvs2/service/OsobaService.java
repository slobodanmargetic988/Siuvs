/*
 * 
 */
package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import java.util.List;
import slobodan.siuvs2.model.Osoba;
import slobodan.siuvs2.valueObject.OsobaId;

public interface OsobaService {

     List<Osoba> findAllBy();
      Osoba findFirstByEmail(String email);
Osoba findOne(OsobaId spodobaId);
    void save(Osoba osoba);

    void delete(Osoba osoba);
}
