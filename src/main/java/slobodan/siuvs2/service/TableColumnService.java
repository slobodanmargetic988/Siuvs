package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import slobodan.siuvs2.model.TableColumn;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.valueObject.TableColumnId;

public interface TableColumnService {

    TableColumn findOne(TableColumnId tableColumnId);

    void add(TableColumnId columnId, TableColumn newColumn, Client client);

}
