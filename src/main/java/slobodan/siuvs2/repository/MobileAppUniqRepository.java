package slobodan.siuvs2.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import slobodan.siuvs2.model.MobileAppUniq;

import slobodan.siuvs2.model.Volonter;

@Repository
public interface MobileAppUniqRepository extends JpaRepository<MobileAppUniq, Integer> {

    List<MobileAppUniq> findAllBy();
 //@Modifying
     @Query(value = "SELECT DISTINCT token  FROM mobileapp_unique_users", nativeQuery = true)
    List<String> findDistinctToken();


}
