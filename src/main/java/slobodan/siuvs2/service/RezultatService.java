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
import slobodan.siuvs2.model.Rezultat;
import slobodan.siuvs2.valueObject.RezultatID;

public interface RezultatService {

    Rezultat findOne(RezultatID rezultatID);

    void save(Rezultat rezultat);

    void delete(RezultatID rezultatID);
}
