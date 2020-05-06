package slobodan.siuvs2.service;

import slobodan.siuvs2.model.TableColumn;
import slobodan.siuvs2.model.TableColumnTypes;
import slobodan.siuvs2.model.DynamicTable;
import slobodan.siuvs2.repository.DynamicTableRepository;
import slobodan.siuvs2.repository.TableDefinitionRepository;
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
