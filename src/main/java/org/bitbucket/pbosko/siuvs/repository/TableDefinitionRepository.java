package org.bitbucket.pbosko.siuvs.repository;

import org.bitbucket.pbosko.siuvs.model.Page;
import org.bitbucket.pbosko.siuvs.model.TableDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableDefinitionRepository extends JpaRepository<TableDefinition, Integer> {

    List<TableDefinition> findByPageOrderByOrder(Page page);

    TableDefinition findOne(Integer tableDefinitionId);

}
