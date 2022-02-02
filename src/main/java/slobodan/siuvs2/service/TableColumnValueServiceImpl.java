package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import slobodan.siuvs2.model.TableColumn;
import slobodan.siuvs2.model.TableColumnValue;
import slobodan.siuvs2.repository.TableColumnValueRepository;

@Service
public class TableColumnValueServiceImpl implements TableColumnValueService {

    @Autowired
    private TableColumnValueRepository tableColumnValueRepository;

    @Override
    public TableColumnValue findOne(Integer tableColumnValueId) {
        return tableColumnValueRepository.findOne(tableColumnValueId);
    }

    @Override
    @Transactional
    public void add(TableColumnValue newColumnValue) {
      tableColumnValueRepository.addData(newColumnValue.getColumn().getId(),newColumnValue.getOrder(), newColumnValue.getValue());
    }

 @Override
    
    public int countByColumn(TableColumn tableColumn){
    return tableColumnValueRepository.countByColumn(tableColumn);
    };

}
