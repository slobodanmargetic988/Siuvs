package slobodan.siuvs2.service;

import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.CustomTableDefinition;
import slobodan.siuvs2.model.DynamicTable;
import slobodan.siuvs2.model.TableDefinition;
import org.springframework.stereotype.Service;

@Service
public class DynamicTableFactoryImpl implements DynamicTableFactory {

    @Override
    public DynamicTable empty(TableDefinition tableDefinition, Client client) {
        DynamicTable dynamicTable = new DynamicTable();
        dynamicTable.setTableDefinition(tableDefinition);
        dynamicTable.setClient(client);
        return dynamicTable;
    }

    @Override
    public DynamicTable empty(
            TableDefinition tableDefinition,
            CustomTableDefinition customTableDefinition,
            Client client
    ) {
        DynamicTable dynamicTable = empty(tableDefinition, client);
        dynamicTable.setCustomTableDefinition(customTableDefinition);
        return dynamicTable;
    }
}
