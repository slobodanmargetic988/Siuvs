package slobodan.siuvs2.repository;

import slobodan.siuvs2.model.DynamicRow;
import org.springframework.data.jpa.repository.JpaRepository;
import slobodan.siuvs2.model.DynamicTable;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
public interface DynamicRowRepository extends JpaRepository<DynamicRow, Integer> {
DynamicRow findFirstByDynamicTable(DynamicTable dt);
}
