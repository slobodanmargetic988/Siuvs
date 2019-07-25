package org.bitbucket.pbosko.siuvs.repository;

import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.model.CustomTableDefinition;
import org.bitbucket.pbosko.siuvs.model.DynamicGroupRow;
import org.bitbucket.pbosko.siuvs.model.TableDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DynamicGroupRowRepository extends JpaRepository<DynamicGroupRow, Integer> {

    List<DynamicGroupRow> findAllByTableDefinitionAndCustomTableDefinitionAndClientOrderById(TableDefinition tableDefinition, CustomTableDefinition customTableDefinition, Client client);

}
