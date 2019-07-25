package org.bitbucket.pbosko.siuvs.repository;

import org.bitbucket.pbosko.siuvs.model.TableColumn;
import org.bitbucket.pbosko.siuvs.model.TableDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableColumnRepository extends JpaRepository<TableColumn, Integer> {

    TableColumn findOne(Integer tableColumnId);

    List<TableColumn> findAllByParentOrderByOrder(TableColumn parentColumn);

    List<TableColumn> findAllByTableAndOrderGreaterThanOrderByOrder(TableDefinition tableDefinition, Integer order);

}
