package slobodan.siuvs2.service;


import slobodan.siuvs2.model.TableColumn;
import slobodan.siuvs2.model.TableColumnValue;


public interface TableColumnValueService {

    TableColumnValue findOne(Integer tableColumnValueId);

    void add(TableColumnValue newColumn);
 int countByColumn(TableColumn tableColumn);
}
