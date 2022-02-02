package slobodan.siuvs2.repository;

import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.model.TableDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
public interface TableDefinitionRepository extends JpaRepository<TableDefinition, Integer> {

    List<TableDefinition> findByPageOrderByOrder(Page page);

    TableDefinition findOne(Integer tableDefinitionId);

}
