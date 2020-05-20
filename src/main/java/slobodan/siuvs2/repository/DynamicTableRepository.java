package slobodan.siuvs2.repository;

import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.CustomTableDefinition;
import slobodan.siuvs2.model.DynamicTable;
import slobodan.siuvs2.model.TableDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DynamicTableRepository extends JpaRepository<DynamicTable, Integer> {

    DynamicTable findByTableDefinitionAndClient(TableDefinition tableDefinition, Client client);

    DynamicTable findByTableDefinitionAndCustomTableDefinitionAndClient(
            TableDefinition tableDefinition,
            CustomTableDefinition customTableDefinition,
            Client client
    );

}
