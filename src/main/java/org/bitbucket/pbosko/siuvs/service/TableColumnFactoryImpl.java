package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.*;
import org.bitbucket.pbosko.siuvs.repository.DynamicTableRepository;
import org.bitbucket.pbosko.siuvs.repository.TableDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableColumnFactoryImpl implements TableColumnFactory {

    @Autowired
    private TableDefinitionRepository tableDefinitionRepository;

    @Autowired
    private DynamicTableRepository dynamicTableRepository;

    @Override
    public TableColumn empty(DynamicTable dynamicTable) {
        TableColumn dynamicColumn = new TableColumn();
        dynamicColumn.setTable(dynamicTable.getTableDefinition());
        dynamicColumn.setType(TableColumnTypes.STRING);
        dynamicColumn.setDynamic(true);
        return dynamicColumn;
    }

}
