package org.bitbucket.pbosko.siuvs.service;

import java.util.List;
import org.bitbucket.pbosko.siuvs.model.Assessment;
import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.model.DynamicData;
import org.bitbucket.pbosko.siuvs.model.Page;
import org.springframework.data.domain.Pageable;

public interface DynamicDataService {
    
List<DynamicData> findAllByValue(String value);
}
