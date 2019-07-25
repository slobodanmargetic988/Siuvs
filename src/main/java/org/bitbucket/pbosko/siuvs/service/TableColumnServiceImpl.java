package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.model.TableColumn;
import org.bitbucket.pbosko.siuvs.repository.TableColumnRepository;
import org.bitbucket.pbosko.siuvs.valueObject.TableColumnId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TableColumnServiceImpl implements TableColumnService {

    @Autowired
    private TableColumnRepository tableColumnRepository;

    @Override
    public TableColumn findOne(TableColumnId tableColumnId) {
        return tableColumnRepository.findOne(tableColumnId.getValue());
    }

    @Override
    @Transactional
    public void add(TableColumnId columnId, TableColumn column, Client client) {
        TableColumn parentColumn = tableColumnRepository.findOne(columnId.getValue());
        List<TableColumn> existingDynamicColumns = tableColumnRepository.findAllByParentOrderByOrder(parentColumn);
        Integer lastOrder = parentColumn.getOrder();
        if (existingDynamicColumns.size() > 0) {
            TableColumn lastChildColumn = existingDynamicColumns.get(existingDynamicColumns.size() - 1);
            lastOrder = lastChildColumn.getOrder();
        }
        this.makeNewColumn(parentColumn, column, lastOrder, client);
    }

    private void makeNewColumn(TableColumn parentColumn, TableColumn column, Integer lastOrder, Client client) {
        List<TableColumn> columnsToFixOrder = tableColumnRepository.findAllByTableAndOrderGreaterThanOrderByOrder(parentColumn.getTable(), lastOrder);
        for (TableColumn columnToFixOrder: columnsToFixOrder) {
            columnToFixOrder.setOrder(columnToFixOrder.getOrder() + 1);
            tableColumnRepository.save(columnToFixOrder);
        }
        parentColumn.setColSpan(parentColumn.getColSpan() + 1);
        tableColumnRepository.save(parentColumn);
        TableColumn newColumn = new TableColumn();
        newColumn.setParent(parentColumn);
        newColumn.setTable(parentColumn.getTable());
        newColumn.setOrder(lastOrder + 1);
        newColumn.setType(parentColumn.getType());
        newColumn.setDynamic(true);
        newColumn.setColSpan(0);
        newColumn.setRow2(column.getTitle());
        newColumn.setColSpan2(1);
        newColumn.setClient(client);
        tableColumnRepository.save(newColumn);
    }

}
