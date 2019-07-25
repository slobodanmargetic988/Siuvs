package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.model.CustomTableDefinition;
import org.bitbucket.pbosko.siuvs.model.TableDefinition;

import java.util.List;

public interface CustomTableDefinitionService {

    void save(CustomTableDefinition customTableDefinition);

    List<CustomTableDefinition> findByClientAndTableDefinition(Client client, TableDefinition tableDefinition);

}
