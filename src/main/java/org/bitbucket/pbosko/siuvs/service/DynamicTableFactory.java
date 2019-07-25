package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.model.CustomTableDefinition;
import org.bitbucket.pbosko.siuvs.model.DynamicTable;
import org.bitbucket.pbosko.siuvs.model.TableDefinition;

public interface DynamicTableFactory {

    DynamicTable empty(TableDefinition tableDefinition, Client client);

    DynamicTable empty(TableDefinition tableDefinition, CustomTableDefinition customTableDefinition, Client client);

}
