package slobodan.siuvs2.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import slobodan.siuvs2.model.Mobileappdata;
import slobodan.siuvs2.model.Volonter;

@Repository
public interface VolonterRepository extends JpaRepository<Volonter, Integer> {

    List<Volonter> findAllBy();
    List<Volonter> findAllByOpstina( String opstina);
    Volonter findFirstByEmail( String Email);
}
