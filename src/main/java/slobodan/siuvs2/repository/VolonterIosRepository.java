package slobodan.siuvs2.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import slobodan.siuvs2.model.Mobileappdata;
import slobodan.siuvs2.model.Volonter;
import slobodan.siuvs2.model.VolonterIos;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Repository
public interface VolonterIosRepository extends JpaRepository<VolonterIos, Integer> {

    List<VolonterIos> findAllBy();
    List<VolonterIos> findAllByOpstina( String opstina);
    VolonterIos findFirstByEmail( String Email);
    
        @Modifying
    @Query(value = "UPDATE volonteri_ios set token=:token WHERE token=:staritoken", nativeQuery = true)
    void updateToken(@Param("staritoken") String token,@Param("token") String staritoken);
}
