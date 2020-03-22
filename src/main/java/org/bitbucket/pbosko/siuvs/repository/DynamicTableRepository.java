package org.bitbucket.pbosko.siuvs.repository;

import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.model.CustomTableDefinition;
import org.bitbucket.pbosko.siuvs.model.DynamicTable;
import org.bitbucket.pbosko.siuvs.model.TableDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DynamicTableRepository extends JpaRepository<DynamicTable, Integer> {

    DynamicTable findByTableDefinitionAndClient(TableDefinition tableDefinition, Client client);

    DynamicTable findByTableDefinitionAndCustomTableDefinitionAndClient(
            TableDefinition tableDefinition,
            CustomTableDefinition customTableDefinition,
            Client client
    );
   
 }
