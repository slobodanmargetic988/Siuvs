package org.bitbucket.pbosko.siuvs.service;

import java.util.List;
import org.bitbucket.pbosko.siuvs.model.Assessment;
import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.model.DynamicData;
import org.bitbucket.pbosko.siuvs.model.Page;
import org.bitbucket.pbosko.siuvs.repository.AssessmentRepository;
import org.bitbucket.pbosko.siuvs.repository.DynamicDataRepository;
import org.bitbucket.pbosko.siuvs.valueObject.ClientId;
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
