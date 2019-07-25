package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.valueObject.ClientId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    void save(Client client);

    void createNew(Client client);

    void update(ClientId clientId, Client client);

    Page<Client> findAllOrderByActiveDescNameAsc(Pageable pageable);

    Client findOne(ClientId clientId);

    Boolean isNameUsed(String name);

}
