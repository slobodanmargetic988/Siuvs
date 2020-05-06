package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.Assessment;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.DynamicData;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.repository.AssessmentRepository;
import slobodan.siuvs2.repository.DynamicDataRepository;
import slobodan.siuvs2.valueObject.ClientId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DynamicDataServiceImpl implements DynamicDataService {
     
    @Autowired
    private DynamicDataRepository dynamicDataRepository;
      
@Override
   public List<DynamicData> findAllByValue(String value){
return dynamicDataRepository.findByValue(value);
};

}
