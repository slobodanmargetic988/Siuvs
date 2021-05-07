package slobodan.siuvs2.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.IstorijaNotifikacija;
import slobodan.siuvs2.model.User;

@Repository("istorijaNotifikacijaRepository")
public interface IstorijaNotifikacijaRepository extends JpaRepository<IstorijaNotifikacija, Long> {
    IstorijaNotifikacija findById(Integer istorijaNotifikacijaId);
    List<IstorijaNotifikacija> findAllBy();
    List<IstorijaNotifikacija> findAllByClient(Client client);
  List<IstorijaNotifikacija> findAllByCreatedBy(Integer id);
   
   List<IstorijaNotifikacija> findAllByCreatedByIn( List<User> useriistogservisa);
   
   
    @Query(value = "SELECT COUNT(`id`) FROM `istorija_notifikacija` WHERE YEAR(`created_on`) = YEAR(CURRENT_DATE - INTERVAL 1 MONTH) AND MONTH(`created_on`) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH) AND `client_id`=:client_id", nativeQuery = true)
    Integer countLastMonthPoslateForClientID(@Param("client_id") Integer client_id); 

    @Query(value = "SELECT * FROM `istorija_notifikacija` WHERE YEAR(`created_on`) = YEAR(CURRENT_DATE - INTERVAL 1 MONTH) AND MONTH(`created_on`) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH) AND `client_id`=:client_id", nativeQuery = true)
    List<IstorijaNotifikacija> selectLastMonthPoslateForClientID(@Param("client_id") Integer client_id); 
    
}
