package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import java.util.List;
import slobodan.siuvs2.model.DynamicData;
import slobodan.siuvs2.model.DynamicRow;

public interface DynamicDataService {

    List<DynamicData> findAllByValue(String value);

    List<DynamicData> findByValue(String upit);

    List<DynamicData> findByRowId(Integer rowId);
DynamicData findFirstByDynamicRow(DynamicRow dr);
DynamicData checkIfExists(Integer table_definition_id,Integer client_id);
}
