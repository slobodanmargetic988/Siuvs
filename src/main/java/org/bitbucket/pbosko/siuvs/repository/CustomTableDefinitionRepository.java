package org.bitbucket.pbosko.siuvs.repository;

import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.model.CustomTableDefinition;
import org.bitbucket.pbosko.siuvs.model.TableDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("customTableDefinitionRepository")
public interface CustomTableDefinitionRepository extends JpaRepository<CustomTableDefinition, Integer> {

    List<CustomTableDefinition> findByClientAndTableDefinitionOrderByTitle(Client client, TableDefinition tableDefinition);

}
