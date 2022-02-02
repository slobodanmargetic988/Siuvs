package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.CustomTableDefinition;
import slobodan.siuvs2.model.DynamicTable;
import slobodan.siuvs2.model.TableDefinition;

public interface DynamicTableFactory {

    DynamicTable empty(TableDefinition tableDefinition, Client client);

    DynamicTable empty(TableDefinition tableDefinition, CustomTableDefinition customTableDefinition, Client client);

}
