package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import slobodan.siuvs2.model.DynamicData;
import slobodan.siuvs2.model.TableColumn;
import slobodan.siuvs2.model.DynamicRow;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.DynamicTable;

import java.util.List;

public interface DynamicRowFactory {

    DynamicRow empty(DynamicTable dynamicTable, Client client, boolean addFieldsetRows);

    void addFieldSetTitle(List<DynamicData> data, TableColumn column);

}
