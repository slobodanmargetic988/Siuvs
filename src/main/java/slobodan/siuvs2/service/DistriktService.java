/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author deca
 */
import java.util.List;
import slobodan.siuvs2.model.Distrikt;
import slobodan.siuvs2.valueObject.DistriktID;


public interface DistriktService {
    Distrikt findOne(DistriktID distriktID);
List<Distrikt> findAllOrderByNameAsc();
    void save(Distrikt distrikt);
    void delete(DistriktID distriktID);
    Distrikt findFirstByName(String name);
}
