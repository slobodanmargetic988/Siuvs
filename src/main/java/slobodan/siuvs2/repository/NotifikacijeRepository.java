package slobodan.siuvs2.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import slobodan.siuvs2.model.Notifikacije;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
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
     
    
    @Query(value = "SELECT DISTINCT token  FROM notifikacije WHERE opstina=:opstina OR opstina='Svi servisi'", nativeQuery = true)
    List<String> findAllByOpstina(@Param("opstina") String opstina);
    
    @Query(value = "SELECT * FROM notifikacije WHERE opstina=:opstina ", nativeQuery = true)
     List<Notifikacije> findAllByOpstinaAsNotifikacije(@Param("opstina") String opstina);
     
     
     
     
    @Modifying
    @Query(value = "UPDATE notifikacije set token=:token WHERE token=:staritoken", nativeQuery = true)
    void updateToken(@Param("staritoken") String token,@Param("token") String staritoken);

        long countByOpstina(String opstinaName);
        
        @Transactional
         void deleteByTokenIn(List<String> tokeni);
}
