package slobodan.siuvs2.service;

import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.repository.ClientRepository;
import slobodan.siuvs2.valueObject.ClientId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import slobodan.siuvs2.model.Opstina;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void createNew(Client client) {
        client.setActive(true);
        save(client);
    }

    @Override
    public void update(ClientId clientId, Client editClient) {
        Client client = findOne(clientId);
        client.setName(editClient.getName());
        client.setActive(editClient.isActive());
        save(client);
    }

    @Override
    public Page<Client> findAllOrderByActiveDescNameAsc(Pageable pageable) {
        return clientRepository.findAllByOrderByActiveDescNameAsc(pageable);
    }

    @Override
    public Client findOne(ClientId clientId) {
        return clientRepository.findOne(clientId.getValue());
    }

    @Override
    public Boolean isNameUsed(String name) {
        Boolean result = false;
        List<Client> clients = clientRepository.findByName(name);
        if (clients.size() > 0) {
            result = true;
        }
        return result;
    }
        @Override
    public Boolean isNameUsed(ClientId clientId,String name) {
        Boolean result = false;
        List<Client> clients = clientRepository.findByName(name);
        for (Client client : clients){
            if (client.getId()!=clientId.getValue()){result = true;}
        }   
        return result;
    }
    
    
@Override
    public Page<Client> findAllByOpstinaIdOrderByNameAsc(List<Integer> opstinaId, Pageable pageable){
    return clientRepository.findByOpstinaIdInOrderByNameAsc(opstinaId,pageable);
    }
    
    @Override
    public Page<Client> findAllByOpstinaInOrderByNameAsc(List<Opstina> opstina, Pageable pageable){
    return clientRepository.findByOpstinaInOrderByNameAsc(opstina,pageable);
    }
}
