package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.DynamicGroupRow;
import slobodan.siuvs2.model.TableDefinition;
import slobodan.siuvs2.model.CustomTableDefinition;

public interface DynamicGroupRowFactory {

    DynamicGroupRow empty(TableDefinition tableDefinition, CustomTableDefinition customTableDefinition, Client client);

}
