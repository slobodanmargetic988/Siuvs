package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import slobodan.siuvs2.model.DynamicData;
import slobodan.siuvs2.model.TableColumn;
import slobodan.siuvs2.model.DynamicRow;
import slobodan.siuvs2.model.TableColumnTypes;
import slobodan.siuvs2.model.DynamicTable;
import slobodan.siuvs2.model.DynamicGroupRow;
import slobodan.siuvs2.repository.DynamicDataRepository;
import slobodan.siuvs2.repository.DynamicGroupRowRepository;
import slobodan.siuvs2.repository.DynamicRowRepository;
import slobodan.siuvs2.shared.NumberHelper;
import slobodan.siuvs2.shared.SiuvsException;
import slobodan.siuvs2.valueObject.DynamicRowId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class DynamicRowServiceImpl implements DynamicRowService {

    @Autowired
    private DynamicRowRepository dynamicRowRepository;

    @Autowired
    private DynamicGroupRowRepository dynamicGroupRowRepository;

    @Autowired
    private DynamicRowFactory dynamicRowFactory;

    @Autowired
    private DynamicDataRepository dynamicDataRepository;

    @Override
    public DynamicRow findOne(DynamicRowId dynamicRowId) {
        return dynamicRowRepository.findOne(dynamicRowId.getValue());
    }
    @Override
    public DynamicRow findFirstByDynamicTable(DynamicTable dt){
    return dynamicRowRepository.findFirstByDynamicTable( dt);
    };

    @Override
    public DynamicRow getOneForEditing(DynamicRowId dynamicRowId, List<TableColumn> tableColumns) {
        DynamicRow dynamicRow = this.findOne(dynamicRowId);
        List<DynamicData> data = new ArrayList<>();
        int realFieldIndex = 0;
        for (DynamicData dynamicData : dynamicRow.getDataForColumns(tableColumns)) {
            dynamicRowFactory.addFieldSetTitle(data, dynamicData.getColumn());
            if (dynamicData.getColumn().getType().equals(TableColumnTypes.AGGREGATE)) {
                dynamicData.setValue(Integer.toString(dynamicRow.getGroupRow().getId()));
            }
            dynamicData.setOrder(realFieldIndex);
            data.add(dynamicData);
            realFieldIndex++;
        }
        dynamicRow.setData(data);
        return dynamicRow;
    }

    @Override
    public void save(DynamicRow dynamicRow) {
        dynamicRowRepository.save(dynamicRow);
    }

    @Override
    @Transactional
    public boolean updateData(DynamicRowId dynamicRowId, List<DynamicData> newData, List<TableColumn> tableColumns) throws SiuvsException {
        boolean result = false;
        DynamicRow currentRow = this.findOne(dynamicRowId);
        if (currentRow != null) {
            List<DynamicData> originalData = currentRow.getDataForColumns(tableColumns);
            for (int i = 0; i < originalData.size(); i++) {
                DynamicData originalDataAtIndex = originalData.get(i);
                DynamicData newDataAtIndex = new DynamicData();
                try {
                    newDataAtIndex = newData.get(i);
                } catch (IndexOutOfBoundsException e) {
                    continue;
                }
                if (originalDataAtIndex.getColumn().getType().equals(TableColumnTypes.AGGREGATE)) {
                    String groupRowId = newDataAtIndex.getValue();
                    DynamicGroupRow groupRow = dynamicGroupRowRepository.findOne(Integer.valueOf(groupRowId));
                    currentRow.setGroupRow(groupRow);
                    originalData.get(i).setValue("");
                } else if (originalDataAtIndex.getColumn().getType().equals(TableColumnTypes.PART)) {
                    originalDataAtIndex.setValue("");
                } else if (originalDataAtIndex.getColumn().getType().equals(TableColumnTypes.SUM)) {
                    originalDataAtIndex.setValue("");
                } else if (originalDataAtIndex.getColumn().getType().equals(TableColumnTypes.AUTOSUM)) {
                    originalDataAtIndex.setValue("");
                } else if (originalDataAtIndex.getColumn().getType().equals(TableColumnTypes.NUMBER)) {
                    String value = newDataAtIndex.getValue();
                    if (!value.isEmpty() && !NumberHelper.isValidNumber(value)) {
                        throw new SiuvsException("Неисправно унет број " + value);
                    }
                    originalDataAtIndex.setValue(value);
                } else {
                    originalDataAtIndex.setValue(newDataAtIndex.getValue());
                }
                if (originalDataAtIndex.getValue() == null) {
                    originalDataAtIndex.setValue("");
                }
                if (originalDataAtIndex.getRow() == null) {
                    dynamicDataRepository.addData(currentRow.getId(), originalDataAtIndex.getColumn().getId(), originalDataAtIndex.getValue());
                    originalDataAtIndex.setRow(currentRow);
                }
            }
            currentRow.setModifiedOn(LocalDateTime.now());
            currentRow.setData(originalData);
            this.save(currentRow);
            result = true;
        }
        return result;
    }

    @Override
    public void delete(DynamicRowId dynamicRowId) {
        dynamicRowRepository.delete(dynamicRowId.getValue());
    }

    public void move(DynamicRowId dynamicRowId, int direction) {
        DynamicRow rowToMove = dynamicRowRepository.findOne(dynamicRowId.getValue());
        DynamicTable table = rowToMove.getDynamicTable();
        List<DynamicRow> rows = table.getRows();
        Iterator rowIterator = rows.iterator();
        DynamicRow previous = null;
        while (rowIterator.hasNext()) {
            DynamicRow current = (DynamicRow) rowIterator.next();
            boolean shouldPerformSwitch = direction < 0
                    ? (current.getId() == rowToMove.getId() && previous != null)
                    : (previous != null && previous.getId() == rowToMove.getId());
            if (shouldPerformSwitch) {
                if (current.getGroupRow() == previous.getGroupRow()) {
                    exchangeRowPositions(current, previous);
                } else {
                    if (current.getGroupRow() != null && previous.getGroupRow() != null) {
                        exchangeGroupPositions(current.getGroupRow(), previous.getGroupRow());
                    }
                }
                break;
            }
            previous = current;
        }
    }

    private void exchangeRowPositions(DynamicRow row1, DynamicRow row2) {
        int row1Order = row1.getOrder();
        row1.setOrder(row2.getOrder());
        row2.setOrder(row1Order);
        dynamicRowRepository.save(row1);
        dynamicRowRepository.save(row2);
    }

    private void exchangeGroupPositions(DynamicGroupRow groupRow1, DynamicGroupRow groupRow2) {
        int groupRow1Order = groupRow1.getOrder();
        groupRow1.setOrder(groupRow2.getOrder());
        groupRow2.setOrder(groupRow1Order);
        dynamicGroupRowRepository.save(groupRow1);
        dynamicGroupRowRepository.save(groupRow2);
    }

}
