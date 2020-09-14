package slobodan.siuvs2.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import slobodan.siuvs2.model.Mobileappdata;
import slobodan.siuvs2.model.Notifikacije;
import slobodan.siuvs2.model.Volonter;

@Repository
public interface NotifikacijeRepository extends JpaRepository<Notifikacije, Integer> {

    List<Notifikacije> findAllBy();
    List<Notifikacije> findAllByOpstina( String opstina);
    List<Notifikacije> findAllByToken( String token);
    Notifikacije findFirstByOpstinaAndToken(  String opstina, String token);
    Long deleteByToken(String token);
    Long deleteByTokenAndOpstina(String token,String opstina);
    
}
