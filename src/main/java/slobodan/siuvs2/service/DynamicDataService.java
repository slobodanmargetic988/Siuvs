package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.DynamicData;
import slobodan.siuvs2.model.DynamicRow;

public interface DynamicDataService {

    List<DynamicData> findAllByValue(String value);

    List<DynamicData> findByValue(String upit);

    List<DynamicData> findByRowId(Integer rowId);
DynamicData findFirstByDynamicRow(DynamicRow dr);
}
