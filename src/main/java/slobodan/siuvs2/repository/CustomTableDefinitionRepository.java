package slobodan.siuvs2.repository;

import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.CustomTableDefinition;
import slobodan.siuvs2.model.TableDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import slobodan.siuvs2.valueObject.CustomTableDefinitionId;

@Repository("customTableDefinitionRepository")
public interface CustomTableDefinitionRepository extends JpaRepository<CustomTableDefinition, Integer> {

    List<CustomTableDefinition> findByClientAndTableDefinitionOrderByTitle(Client client, TableDefinition tableDefinition);
CustomTableDefinition findOne(Integer id);
}
