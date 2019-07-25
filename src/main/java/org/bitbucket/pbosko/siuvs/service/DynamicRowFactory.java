package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.*;

import java.util.List;

public interface DynamicRowFactory {

    DynamicRow empty(DynamicTable dynamicTable, Client client, boolean addFieldsetRows);

    void addFieldSetTitle(List<DynamicData> data, TableColumn column);

}
