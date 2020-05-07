/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author deca
 */
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import slobodan.siuvs2.model.Distrikt;
import slobodan.siuvs2.valueObject.DistriktID;


public interface DistriktService {
    Distrikt findOne(DistriktID distriktID);
List<Distrikt> findAllOrderByNameAsc();
Page<Distrikt> findAllOrderByNameAsc(Pageable pageable);
    void save(Distrikt distrikt);
    void delete(DistriktID distriktID);
    Distrikt findFirstByName(String name);
 Boolean isNameUsed(String name);
  Boolean isNameUsed(DistriktID distriktID,String name);
    
}
