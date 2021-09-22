package slobodan.siuvs2.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import slobodan.siuvs2.model.DynamicData;
import slobodan.siuvs2.model.Mobileappdata;
import slobodan.siuvs2.model.Notifikacije;
import slobodan.siuvs2.model.NotifikacijeIos;
import slobodan.siuvs2.model.Opstina;
import slobodan.siuvs2.model.Volonter;

@Repository
public interface NotifikacijeIosRepository extends JpaRepository<NotifikacijeIos, Integer> {

    List<NotifikacijeIos> findAllBy();
    List<NotifikacijeIos> findAllByOrderByOpstinaAsc();
    
    List<NotifikacijeIos> findAllByToken( String token);
    NotifikacijeIos findFirstByOpstinaAndToken(  String opstina, String token);
    Long deleteByToken(String token);
    Long deleteByTokenAndOpstina(String token,String opstina);
     @Modifying
     @Query(value = "SELECT DISTINCT token  FROM notifikacije_ios", nativeQuery = true)
    List<String> findDistinctToken();
     @Query(value = "SELECT DISTINCT token  FROM notifikacije_ios WHERE opstina=:opstina OR opstina='Svi servisi'", nativeQuery = true)
    List<String> findAllByOpstina(@Param("opstina") String opstina);
    
    @Query(value = "SELECT * FROM notifikacije_ios WHERE opstina=:opstina ", nativeQuery = true)
     List<NotifikacijeIos> findAllByOpstinaAsNotifikacije(@Param("opstina") String opstina);
     
     
     
     
    @Modifying
    @Query(value = "UPDATE notifikacije_ios set token=:token WHERE token=:staritoken", nativeQuery = true)
    void updateToken(@Param("staritoken") String token,@Param("token") String staritoken);

     long countByOpstina(String opstinaName);
     
     @Transactional
     void deleteByTokenIn(List<String> tokeni);
}
