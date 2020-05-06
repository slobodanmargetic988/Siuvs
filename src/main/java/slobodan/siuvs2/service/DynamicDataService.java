package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.Assessment;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.DynamicData;
import slobodan.siuvs2.model.Page;
import org.springframework.data.domain.Pageable;

public interface DynamicDataService {
    
List<DynamicData> findAllByValue(String value);
}
