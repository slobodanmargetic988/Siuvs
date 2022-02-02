package slobodan.siuvs2.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import slobodan.siuvs2.model.Mobileappdata;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Repository
public interface MobileappdataRepository extends JpaRepository<Mobileappdata, Integer> {

    List<Mobileappdata> findAllBy();
    List<Mobileappdata> findAllByOpstina( String opstina);
    Mobileappdata findFirstByOpstinaAndOpasnost( String opstina, String opasnost);
}
