package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.repository.ClientRepository;
import org.bitbucket.pbosko.siuvs.valueObject.ClientId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
