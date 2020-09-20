package slobodan.siuvs2.service;

import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.CustomTableDefinition;
import slobodan.siuvs2.model.TableDefinition;
import slobodan.siuvs2.repository.CustomTableDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import slobodan.siuvs2.valueObject.CustomTableDefinitionId;

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
  @Override
    public CustomTableDefinition findOne(CustomTableDefinitionId customTableDefinitionId){
    return customTableDefinitionRepository.findOne(customTableDefinitionId.getValue());
    };
}
