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
import slobodan.siuvs2.model.PodRezultat;
import slobodan.siuvs2.valueObject.PodRezultatID;

public interface PodRezultatService {

    PodRezultat findOne(PodRezultatID podRezultatID);
    List<PodRezultat> findAll();

    void save(PodRezultat podRezultat);

    void delete(PodRezultatID podRezultatID);
}
