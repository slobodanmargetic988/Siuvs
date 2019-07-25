package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.model.CustomTableDefinition;
import org.bitbucket.pbosko.siuvs.model.DynamicGroupRow;
import org.bitbucket.pbosko.siuvs.model.TableDefinition;

import java.util.List;

public interface DynamicGroupRowService {

    void add(DynamicGroupRow groupRow, TableDefinition tableDefinition, CustomTableDefinition customTableDefinition, Client client);

    List<DynamicGroupRow> findAll(TableDefinition tableDefinition, CustomTableDefinition customTableDefinition, Client client);

}
