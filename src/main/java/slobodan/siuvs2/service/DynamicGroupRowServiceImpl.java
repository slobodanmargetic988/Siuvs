package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.TableDefinition;
import slobodan.siuvs2.model.CustomTableDefinition;
import slobodan.siuvs2.model.DynamicGroupData;
import slobodan.siuvs2.model.DynamicGroupRow;
import slobodan.siuvs2.repository.DynamicGroupDataRepository;
import slobodan.siuvs2.repository.DynamicGroupRowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DynamicGroupRowServiceImpl implements DynamicGroupRowService {

    @Autowired
    private DynamicGroupRowRepository dynamicGroupRowRepository;

    @Autowired
    private DynamicGroupDataRepository dynamicGroupDataRepository;

    @Autowired
    private DynamicGroupRowFactory dynamicGroupRowFactory;

    @Override
    @Transactional
    public void add(DynamicGroupRow groupRow, TableDefinition tableDefinition, CustomTableDefinition customTableDefinition, Client client) {
        DynamicGroupRow emptyRow = dynamicGroupRowFactory.empty(tableDefinition, customTableDefinition, client);
        groupRow.setTableDefinition(tableDefinition);
        groupRow.setCustomTableDefinition(customTableDefinition);
        groupRow.setClient(client);
        List<DynamicGroupRow> existingRows = findAll(tableDefinition, customTableDefinition, client);
        int order = 0;
        for (DynamicGroupRow existingRow : existingRows) {
            if (existingRow.getOrder() > order) {
                order = existingRow.getOrder();
            }
        }
        groupRow.setOrder(order + 1);
        dynamicGroupRowRepository.save(groupRow);
        int index = 0;
        for (DynamicGroupData data : groupRow.getData()) {
            data.setGroupRow(groupRow);
            data.setColumn(emptyRow.getData().get(index).getColumn());
            dynamicGroupDataRepository.save(data);
            index++;
        }
    }

    @Override
    public List<DynamicGroupRow> findAll(TableDefinition tableDefinition, CustomTableDefinition customTableDefinition, Client client) {
        return dynamicGroupRowRepository.findAllByTableDefinitionAndCustomTableDefinitionAndClientOrderById(tableDefinition, customTableDefinition, client);
    }
}
