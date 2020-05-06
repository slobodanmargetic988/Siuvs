/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author deca
 */
import slobodan.siuvs2.model.Mera;
import slobodan.siuvs2.model.Rezultat;
import slobodan.siuvs2.valueObject.RezultatID;

public interface RezultatService {
    Rezultat findOne(RezultatID rezultatID);

    void save(Rezultat rezultat);
    void delete(RezultatID rezultatID);
}
