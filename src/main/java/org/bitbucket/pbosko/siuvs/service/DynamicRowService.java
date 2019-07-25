package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.DynamicData;
import org.bitbucket.pbosko.siuvs.model.DynamicRow;
import org.bitbucket.pbosko.siuvs.model.TableColumn;
import org.bitbucket.pbosko.siuvs.shared.SiuvsException;
import org.bitbucket.pbosko.siuvs.valueObject.DynamicRowId;

import java.util.List;

public interface DynamicRowService {

    DynamicRow findOne(DynamicRowId dynamicRowId);

    DynamicRow getOneForEditing(DynamicRowId dynamicRowId, List<TableColumn> tableColumns);

    void save(DynamicRow dynamicRow);

    boolean updateData(DynamicRowId dynamicRowId, List<DynamicData> newData, List<TableColumn> tableColumns) throws SiuvsException;

    void delete(DynamicRowId dynamicRowId);

    void move(DynamicRowId dynamicRowId, int direction);

}
