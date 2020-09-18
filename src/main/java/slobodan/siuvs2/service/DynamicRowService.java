package slobodan.siuvs2.service;

import slobodan.siuvs2.model.DynamicData;
import slobodan.siuvs2.model.DynamicRow;
import slobodan.siuvs2.model.TableColumn;
import slobodan.siuvs2.shared.SiuvsException;
import slobodan.siuvs2.valueObject.DynamicRowId;

import java.util.List;
import slobodan.siuvs2.model.DynamicTable;

public interface DynamicRowService {

    DynamicRow findOne(DynamicRowId dynamicRowId);
    DynamicRow findFirstByDynamicTable(DynamicTable dt);
    
    DynamicRow getOneForEditing(DynamicRowId dynamicRowId, List<TableColumn> tableColumns);

    void save(DynamicRow dynamicRow);

    boolean updateData(DynamicRowId dynamicRowId, List<DynamicData> newData, List<TableColumn> tableColumns) throws SiuvsException;

    void delete(DynamicRowId dynamicRowId);

    void move(DynamicRowId dynamicRowId, int direction);

}
