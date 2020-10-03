package slobodan.siuvs2.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import slobodan.siuvs2.model.DynamicData;
import slobodan.siuvs2.model.Mobileappdata;
import slobodan.siuvs2.model.Notifikacije;
import slobodan.siuvs2.model.Volonter;

@Repository
public interface NotifikacijeRepository extends JpaRepository<Notifikacije, Integer> {

    List<Notifikacije> findAllBy();
    List<Notifikacije> findAllByOrderByOpstinaAsc();
    
    List<Notifikacije> findAllByToken( String token);
    Notifikacije findFirstByOpstinaAndToken(  String opstina, String token);
    Long deleteByToken(String token);
    Long deleteByTokenAndOpstina(String token,String opstina);
     @Modifying
     @Query(value = "SELECT DISTINCT token  FROM notifikacije", nativeQuery = true)
    List<String> findDistinctToken();
     @Query(value = "SELECT DISTINCT token  FROM notifikacije WHERE opstina=:opstina", nativeQuery = true)
    List<String> findAllByOpstina(@Param("opstina") String opstina);
    
     
}
