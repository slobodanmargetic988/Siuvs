package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.DynamicData;
import slobodan.siuvs2.repository.DynamicDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.DynamicRow;

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
@Override
    public DynamicData findFirstByDynamicRow(DynamicRow dr){
    return dynamicDataRepository.findFirstByRow(dr);
    };
    @Override
    public DynamicData checkIfExists(Integer table_definition_id,Integer client_id){
         return dynamicDataRepository.checkIfExists(table_definition_id,client_id);
    };
}
