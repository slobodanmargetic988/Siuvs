package slobodan.siuvs2.repository;

import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.CustomTableDefinition;
import slobodan.siuvs2.model.DynamicGroupRow;
import slobodan.siuvs2.model.TableDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DynamicGroupRowRepository extends JpaRepository<DynamicGroupRow, Integer> {

    List<DynamicGroupRow> findAllByTableDefinitionAndCustomTableDefinitionAndClientOrderById(TableDefinition tableDefinition, CustomTableDefinition customTableDefinition, Client client);

}
