package slobodan.siuvs2.service;

import slobodan.siuvs2.repository.DynamicRowRepository;
import slobodan.siuvs2.repository.DynamicGroupRowRepository;
import slobodan.siuvs2.repository.TableDefinitionRepository;
import slobodan.siuvs2.repository.DynamicDataRepository;
import slobodan.siuvs2.repository.CustomTableDefinitionRepository;
import slobodan.siuvs2.repository.DynamicTableRepository;
import slobodan.siuvs2.model.DynamicData;
import slobodan.siuvs2.model.TableColumn;
import slobodan.siuvs2.model.DynamicRow;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.TableDefinition;
import slobodan.siuvs2.model.TableColumnTypes;
import slobodan.siuvs2.model.CustomTableDefinition;
import slobodan.siuvs2.model.DynamicTable;
import slobodan.siuvs2.model.DynamicGroupRow;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.shared.NumberHelper;
import slobodan.siuvs2.shared.SiuvsException;
import slobodan.siuvs2.valueObject.CustomTableDefinitionId;
import slobodan.siuvs2.valueObject.TableDefinitionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicTableServiceImpl implements DynamicTableService {

    @Autowired
    private TableDefinitionRepository tableDefinitionRepository;

    @Autowired
    private TableDefinitionService tableDefinitionService;

    @Autowired
    private CustomTableDefinitionRepository customTableDefinitionRepository;

    @Autowired
    private DynamicTableRepository dynamicTableRepository;

    @Autowired
    private DynamicRowRepository dynamicRowRepository;

    @Autowired
    private DynamicDataRepository dynamicDataRepository;

    @Autowired
    private DynamicRowFactory dynamicRowFactory;

    @Autowired
    private DynamicTableFactory dynamicTableFactory;

    @Autowired
    private DynamicGroupRowRepository dynamicGroupRowRepository;

    /**
     * Finds existing dynamic table or creates new if one wasn't created earlier
     */
    @Override
    public DynamicTable findByTableDefinitionIdAndClient(TableDefinitionId tableDefinitionId, Client client) {
        TableDefinition tableDefinition = tableDefinitionRepository.findOne(tableDefinitionId.getValue());
        DynamicTable dynamicTable = dynamicTableRepository.findByTableDefinitionAndClient(tableDefinition, client);
        if (dynamicTable == null) {
            dynamicTable = dynamicTableFactory.empty(tableDefinition, client);
            dynamicTableRepository.save(dynamicTable);
            dynamicTable = dynamicTableRepository.findByTableDefinitionAndClient(tableDefinition, client);
        }
        return dynamicTable;
    }

    @Override
    public DynamicTable findByTableDefinitionIdAndCustomTableDefinitionIdAndClient(
            TableDefinitionId tableDefinitionId,
            CustomTableDefinitionId customTableDefinitionId,
            Client client
    ) {
        TableDefinition tableDefinition = tableDefinitionRepository.findOne(tableDefinitionId.getValue());
        CustomTableDefinition customTableDefinition = customTableDefinitionRepository.findOne(customTableDefinitionId.getValue());
        DynamicTable dynamicTable = dynamicTableRepository.findByTableDefinitionAndCustomTableDefinitionAndClient(
                tableDefinition, customTableDefinition, client
        );
        if (dynamicTable == null) {
            dynamicTable = dynamicTableFactory.empty(tableDefinition, customTableDefinition, client);
            dynamicTableRepository.save(dynamicTable);
            dynamicTable = dynamicTableRepository.findByTableDefinitionAndCustomTableDefinitionAndClient(
                    tableDefinition, customTableDefinition, client
            );
        }
        return dynamicTable;
    }

    @Override
    public void save(DynamicTable dynamicTable) {
        dynamicTableRepository.save(dynamicTable);
    }

    @Override
    @Transactional
    public void addRow(DynamicTable dynamicTable, DynamicRow dynamicRow, Client client) throws SiuvsException {
        DynamicRow newRow = dynamicRowFactory.empty(dynamicTable, client, false);
        newRow = copyValuesToANewRow(dynamicRow, newRow);
        newRow.setOrder(dynamicTable.getMaxRowOrder() + 1);
        dynamicRowRepository.save(newRow);
        for (DynamicData data : newRow.getData()) {
            dynamicDataRepository.addData(data.getRow().getId(), data.getColumn().getId(), data.getValue());
        }
    }

    private DynamicRow copyValuesToANewRow(DynamicRow dynamicRow, DynamicRow newRow) throws SiuvsException {
        int index = 0;
        for (DynamicData data : newRow.getData()) {
            if (data.getColumn().getType().equals(TableColumnTypes.AGGREGATE)) {
                String groupRowId = dynamicRow.getData().get(index).getValue();
                DynamicGroupRow groupRow = dynamicGroupRowRepository.findOne(Integer.valueOf(groupRowId));
                newRow.setGroupRow(groupRow);
                data.setValue("");
            } else if (data.getColumn().getType().equals(TableColumnTypes.PART)) {
                data.setValue("");
            } else if (data.getColumn().getType().equals(TableColumnTypes.SUM)) {
                data.setValue("");
            } else if (data.getColumn().getType().equals(TableColumnTypes.AUTOSUM)) {
                data.setValue("");
            } else if (data.getColumn().getType().equals(TableColumnTypes.NUMBER)) {
                if (index < dynamicRow.getData().size()) {
                    String value = dynamicRow.getData().get(index).getValue();
                    if (!value.isEmpty() && !NumberHelper.isValidNumber(value)) {
                        throw new SiuvsException("Неисправно унет број " + value);
                    }
                    data.setValue(value);
                }
            } else {
                if (index < dynamicRow.getData().size()) {
                    data.setValue(dynamicRow.getData().get(index).getValue());
                }
            }
            if (data.getValue() == null) {
                data.setValue("");
            }
            index++;
        }
        return newRow;
    }

    @Override
    public FooterRow getFooter(DynamicTable dynamicTable, User user) {
        List<TableColumnTypes> numericTypes = new ArrayList<TableColumnTypes>();
        numericTypes.add(TableColumnTypes.NUMBER);
        numericTypes.add(TableColumnTypes.SUM);
        numericTypes.add(TableColumnTypes.AUTOSUM);
        FooterRow footer = null;
        List<FooterField> fields = new ArrayList<>();
        FooterField field = new FooterField(new TableColumn());
        if (tableDefinitionService.showActionsColumn(user)) {
            field.incColSpan();
        }
        fields.add(field);
        for (TableColumn column : dynamicTable.getTableDefinition().getColumns()) {
            if (numericTypes.contains(column.getType())) {
                field = new FooterField(column);
                fields.add(field);
            } else {
                field.incColSpan();
            }
        }
        for (DynamicRow row : dynamicTable.getRows()) {
            for (DynamicData data : row.getData()) {
                if (numericTypes.contains(data.getColumn().getType())) {
                    for (FooterField currentField : fields) {
                        if (data.getColumn().equals(currentField.getColumn())) {
                            String valueToAdd = data.getValue();
                            if (data.getColumn().getType().equals(TableColumnTypes.SUM)
                                || data.getColumn().getType().equals(TableColumnTypes.AUTOSUM)
                            ) {
                                valueToAdd = data.getVirtualValue();
                            }
                            currentField.addValue(valueToAdd);
                            break;
                        }
                    }
                }
            }
        }
        if (fields.size() > 1) {
            footer = new FooterRow();
            footer.setFields(fields);
        }
        return footer;
    }

}
