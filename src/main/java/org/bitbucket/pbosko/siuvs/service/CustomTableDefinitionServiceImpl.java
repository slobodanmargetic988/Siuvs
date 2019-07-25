package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.model.CustomTableDefinition;
import org.bitbucket.pbosko.siuvs.model.TableDefinition;
import org.bitbucket.pbosko.siuvs.repository.CustomTableDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomTableDefinitionServiceImpl implements CustomTableDefinitionService {

    @Autowired
    private CustomTableDefinitionRepository customTableDefinitionRepository;

    @Override
    public void save(CustomTableDefinition customTableDefinition) {
        customTableDefinitionRepository.save(customTableDefinition);
    }

    @Override
    public List<CustomTableDefinition> findByClientAndTableDefinition(Client client, TableDefinition tableDefinition) {
        return customTableDefinitionRepository.findByClientAndTableDefinitionOrderByTitle(client, tableDefinition);
    }
}
