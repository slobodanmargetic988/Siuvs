package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.model.CustomTableDefinition;
import org.bitbucket.pbosko.siuvs.model.DynamicTable;
import org.bitbucket.pbosko.siuvs.model.TableDefinition;
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
