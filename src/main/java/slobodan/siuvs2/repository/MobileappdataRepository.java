package slobodan.siuvs2.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import slobodan.siuvs2.model.Mobileappdata;

@Repository
public interface MobileappdataRepository extends JpaRepository<Mobileappdata, Integer> {

    List<Mobileappdata> findAllBy();
    List<Mobileappdata> findAllByOpstina( String opstina);
    Mobileappdata findFirstByOpstinaAndOpasnost( String opstina, String opasnost);
}
