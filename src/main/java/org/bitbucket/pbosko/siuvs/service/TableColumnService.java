package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.*;
import org.bitbucket.pbosko.siuvs.valueObject.TableColumnId;

public interface TableColumnService {

    TableColumn findOne(TableColumnId tableColumnId);

    void add(TableColumnId columnId, TableColumn newColumn, Client client);

}
