package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import slobodan.siuvs2.model.DynamicData;
import slobodan.siuvs2.model.TableColumn;
import slobodan.siuvs2.model.DynamicRow;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.TableColumnTypes;
import slobodan.siuvs2.model.DynamicTable;
import slobodan.siuvs2.repository.DynamicTableRepository;
import slobodan.siuvs2.repository.TableDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicRowFactoryImpl implements DynamicRowFactory {

    @Autowired
    private TableDefinitionRepository tableDefinitionRepository;

    @Autowired
    private DynamicTableRepository dynamicTableRepository;

    @Override
    public DynamicRow empty(DynamicTable dynamicTable, Client client, boolean addFieldSetRows) {
        DynamicRow dynamicRow = new DynamicRow();
        dynamicRow.setDynamicTable(dynamicTable);
        List<DynamicData> data = new ArrayList<>();
        int realFieldIndex = 0;
        for (TableColumn column : dynamicTable.getTableDefinition().getColumns(client)) {
            if (addFieldSetRows) {
                addFieldSetTitle(data, column);
            }
            DynamicData dynamicData = new DynamicData();
            dynamicData.setRow(dynamicRow);
            dynamicData.setColumn(column);
            dynamicData.setOrder(realFieldIndex);
            data.add(dynamicData);
            realFieldIndex++;
        }
        dynamicRow.setData(data);
        return dynamicRow;
    }

    public void addFieldSetTitle(List<DynamicData> data, TableColumn column) {
        String fieldSetTitle = getParentRowTitle(column);
        if (fieldSetTitle != null) {
            TableColumn fieldSetColumn = new TableColumn();
            fieldSetColumn.setType(TableColumnTypes.FIELDSET);
            fieldSetColumn.setTitle(fieldSetTitle);
            DynamicData dynamicData = new DynamicData();
            dynamicData.setColumn(fieldSetColumn);
            data.add(dynamicData);
        }
    }

    private String getParentRowTitle(TableColumn column) {
        if (column.getRow2() == null && column.getRow3() == null && column.getRow4() == null) {
            return null;
        }
        if (column.getRow3() == null && column.getRow4() == null) {
            return column.getTitle();
        }
        if (column.getRow4() == null) {
            return column.getRow2();
        }
        if (column.getRow2() != null && column.getRow3() == null && column.getRow4() != null) {
            return column.getRow2();
        }
        return column.getRow3();
    }

}
