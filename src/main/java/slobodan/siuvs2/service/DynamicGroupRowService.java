package slobodan.siuvs2.service;

import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.CustomTableDefinition;
import slobodan.siuvs2.model.DynamicGroupRow;
import slobodan.siuvs2.model.TableDefinition;

import java.util.List;

public interface DynamicGroupRowService {

    void add(DynamicGroupRow groupRow, TableDefinition tableDefinition, CustomTableDefinition customTableDefinition, Client client);

    List<DynamicGroupRow> findAll(TableDefinition tableDefinition, CustomTableDefinition customTableDefinition, Client client);

}
