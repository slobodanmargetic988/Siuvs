package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.model.DynamicGroupRow;
import org.bitbucket.pbosko.siuvs.model.TableDefinition;
import org.bitbucket.pbosko.siuvs.model.CustomTableDefinition;

public interface DynamicGroupRowFactory {

    DynamicGroupRow empty(TableDefinition tableDefinition, CustomTableDefinition customTableDefinition, Client client);

}
