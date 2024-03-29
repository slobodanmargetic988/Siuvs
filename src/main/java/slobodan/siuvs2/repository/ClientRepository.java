package slobodan.siuvs2.repository;

import slobodan.siuvs2.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import slobodan.siuvs2.model.Opstina;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Repository("clientRepository")
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Page<Client> findAllByOrderByActiveDescNameAsc(Pageable pageable);

    List<Client> findByName(String name);

    Client findFirstByName(String name);

    List<Client> findAllBy();

    List<Client> findAllByOrderByNameAsc();

    Page<Client> findByOpstinaIdInOrderByNameAsc(List<Integer> opstinaId, Pageable pageable);

    Page<Client> findByOpstinaInOrderByNameAsc(List<Opstina> opstina, Pageable pageable);

    Client findFirstByOpstina(Opstina opstina);

    Client findFirstByOpendataid(Integer id);

}
