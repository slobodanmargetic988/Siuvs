package slobodan.siuvs2.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import slobodan.siuvs2.model.MobileAppUniq;
import slobodan.siuvs2.model.MobileAppUniqIos;

import slobodan.siuvs2.model.Volonter;

@Repository
public interface MobileAppUniqIosRepository extends JpaRepository<MobileAppUniqIos, Integer> {

    List<MobileAppUniqIos> findAllBy();

    @Modifying
    @Query(value = "UPDATE mobileapp_unique_users_ios set token=:token WHERE token=:staritoken", nativeQuery = true)
    void updateToken(@Param("staritoken") String token,@Param("token") String staritoken);

//@Modifying
     @Query(value = "SELECT DISTINCT token  FROM mobileapp_unique_users_ios", nativeQuery = true)
    List<String> findDistinctToken();
}
