package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.DynamicTable;
import org.bitbucket.pbosko.siuvs.model.TableColumn;

public interface TableColumnFactory {

    TableColumn empty(DynamicTable dynamicTable);

}
