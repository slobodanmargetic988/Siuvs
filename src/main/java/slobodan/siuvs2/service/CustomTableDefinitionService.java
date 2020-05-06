package slobodan.siuvs2.service;

import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.CustomTableDefinition;
import slobodan.siuvs2.model.TableDefinition;

import java.util.List;

public interface CustomTableDefinitionService {

    void save(CustomTableDefinition customTableDefinition);

    List<CustomTableDefinition> findByClientAndTableDefinition(Client client, TableDefinition tableDefinition);

}
