package slobodan.siuvs2.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.IstorijaNotifikacija;

@Repository("istorijaNotifikacijaRepository")
public interface IstorijaNotifikacijaRepository extends JpaRepository<IstorijaNotifikacija, Long> {
    IstorijaNotifikacija findById(Integer istorijaNotifikacijaId);
    List<IstorijaNotifikacija> findAllBy();
    List<IstorijaNotifikacija> findAllByClient(Client client);
   
    

}
