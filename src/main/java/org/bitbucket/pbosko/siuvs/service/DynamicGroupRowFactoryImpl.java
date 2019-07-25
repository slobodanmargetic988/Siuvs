package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicGroupRowFactoryImpl implements DynamicGroupRowFactory {

    @Override
    public DynamicGroupRow empty(TableDefinition tableDefinition, CustomTableDefinition customTableDefinition, Client client) {
        DynamicGroupRow groupRow = new DynamicGroupRow();
        groupRow.setTableDefinition(tableDefinition);
        groupRow.setClient(client);
        groupRow.setCustomTableDefinition(customTableDefinition);
        List<DynamicGroupData> data = new ArrayList<>();
        for (TableColumn column : tableDefinition.getColumns()) {
            if (isAggregated(column)) {
                DynamicGroupData groupData = new DynamicGroupData();
                groupData.setGroupRow(groupRow);
                groupData.setColumn(column);
                data.add(groupData);
            }
        }
        groupRow.setData(data);
        return groupRow;
    }

    private boolean isAggregated(TableColumn subject) {
        return subject.getType().equals(TableColumnTypes.AGGREGATE) || subject.getType().equals(TableColumnTypes.PART);
    }
}
