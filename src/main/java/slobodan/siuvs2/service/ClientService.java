package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.valueObject.ClientId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import slobodan.siuvs2.model.Opstina;

public interface ClientService {

    void save(Client client);

    void createNew(Client client);

    void update(ClientId clientId, Client client);

    Page<Client> findAllOrderByActiveDescNameAsc(Pageable pageable);
    
    List<Client> findAll();
    List<Client> findAllByOrderByNameAsc();
    
    Client findOne(ClientId clientId);

    Boolean isNameUsed(String name);

    Boolean isNameUsed(ClientId clientId, String name);

    Page<Client> findAllByOpstinaIdOrderByNameAsc(List<Integer> opstinaId, Pageable pageable);

    Page<Client> findAllByOpstinaInOrderByNameAsc(List<Opstina> opstina, Pageable pageable);
      Client findFirstByOpstina(Opstina opstina);
       Client findFirstByName(String name);
         Client findFirstByOpendataid(Integer id);
}
