package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.DynamicData;
import slobodan.siuvs2.repository.DynamicDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DynamicDataServiceImpl implements DynamicDataService {

    @Autowired
    private DynamicDataRepository dynamicDataRepository;

    @Override
    public List<DynamicData> findAllByValue(String value) {
        return dynamicDataRepository.findByValue(value);
    }

    ;
@Override
    public List<DynamicData> findByValue(String upit) {
        return dynamicDataRepository.findByValue(upit);
    }

    ;
   
   @Override
    public List<DynamicData> findByRowId(Integer rowId) {
        return dynamicDataRepository.findById(rowId);
    }
;

}
