package slobodan.siuvs2.repository;

import slobodan.siuvs2.model.TableColumn;
import slobodan.siuvs2.model.TableDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
public interface TableColumnRepository extends JpaRepository<TableColumn, Integer> {

    TableColumn findOne(Integer tableColumnId);

    List<TableColumn> findAllByParentOrderByOrder(TableColumn parentColumn);

    List<TableColumn> findAllByTableAndOrderGreaterThanOrderByOrder(TableDefinition tableDefinition, Integer order);

}
