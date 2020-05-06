/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author deca
 */
import slobodan.siuvs2.model.PodRezultat;
import slobodan.siuvs2.model.Rezultat;
import slobodan.siuvs2.valueObject.PodRezultatID;

public interface PodRezultatService {
    PodRezultat findOne(PodRezultatID podRezultatID);

    void save(PodRezultat podRezultat);
     void delete(PodRezultatID podRezultatID);
}
