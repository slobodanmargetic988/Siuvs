package slobodan.siuvs2.facade;

import slobodan.siuvs2.service.TableDefinitionService;
import slobodan.siuvs2.service.DynamicTableService;
import slobodan.siuvs2.service.TableColumnFactory;
import slobodan.siuvs2.service.DynamicRowService;
import slobodan.siuvs2.service.DynamicGroupRowService;
import slobodan.siuvs2.service.CustomTableDefinitionService;
import slobodan.siuvs2.service.DynamicRowFactory;
import slobodan.siuvs2.service.TableColumnService;
import slobodan.siuvs2.service.DynamicGroupRowFactory;
import slobodan.siuvs2.service.PageService;
import slobodan.siuvs2.service.PhotoService;
import slobodan.siuvs2.model.DynamicData;
import slobodan.siuvs2.model.TableColumn;
import slobodan.siuvs2.model.DynamicRow;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.TableDefinition;
import slobodan.siuvs2.model.CustomTableDefinition;
import slobodan.siuvs2.model.DynamicTable;
import slobodan.siuvs2.model.DynamicGroupRow;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.valueObject.DynamicRowId;
import slobodan.siuvs2.valueObject.CustomTableDefinitionId;
import slobodan.siuvs2.valueObject.PageId;
import slobodan.siuvs2.valueObject.TableDefinitionId;
import slobodan.siuvs2.valueObject.TableColumnId;
import slobodan.siuvs2.shared.SiuvsException;
import org.springframework.ui.Model;

import java.util.List;

public class TableFacade {

    private User user;

    private Client client;

    private PageService pageService;

    private TableDefinitionService tableDefinitionService;

    private DynamicTableService dynamicTableService;

    private DynamicGroupRowService dynamicGroupRowService;

    private DynamicRowFactory dynamicRowFactory;

    private DynamicGroupRowFactory dynamicGroupRowFactory;

    private CustomTableDefinitionService customTableDefinitionService;

    private DynamicRowService dynamicRowService;

    private TableColumnFactory tableColumnFactory;

    private TableColumnService tableColumnService;

    private PhotoService photoService;

    public TableFacade(
        User user,
        Client client,
        PageService pageService,
        TableDefinitionService tableDefinitionService,
        DynamicTableService dynamicTableService,
        DynamicGroupRowService dynamicGroupRowService,
        DynamicRowFactory dynamicRowFactory,
        DynamicGroupRowFactory dynamicGroupRowFactory,
        CustomTableDefinitionService customTableDefinitionService,
        DynamicRowService dynamicRowService,
        TableColumnFactory tableColumnFactory,
        TableColumnService tableColumnService,
        PhotoService photoService
    ) throws SiuvsException {
        this.user = user;
        this.client = client;
        this.pageService = pageService;
        this.tableDefinitionService = tableDefinitionService;
        this.dynamicTableService = dynamicTableService;
        this.dynamicGroupRowService = dynamicGroupRowService;
        this.dynamicRowFactory = dynamicRowFactory;
        this.dynamicGroupRowFactory = dynamicGroupRowFactory;
        this.customTableDefinitionService = customTableDefinitionService;
        this.dynamicRowService = dynamicRowService;
        this.tableColumnFactory = tableColumnFactory;
        this.tableColumnService = tableColumnService;
        this.photoService = photoService;
        ensureUserClientAccess(user, client);
    }

    public void preparePageTablesList(PageId pageId, final Model model) {
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("tableDefinitions", tableDefinitionService.findByPage(page));
        model.addAttribute("photos", photoService.findByClientAndPage(client, page));
    }

    public void preparePageTableView(PageId pageId, TableDefinitionId tableDefinitionId, final Model model) throws SiuvsException {
        this.preparePageTableView(pageId, tableDefinitionId, null, model);
    }


    public void preparePageTableView(PageId pageId, TableDefinitionId tableDefinitionId, CustomTableDefinitionId customTableDefinitionId, final Model model) throws SiuvsException {
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        DynamicTable dynamicTable;
        CustomTableDefinition customTableDefinition = null;
        if (customTableDefinitionId == null) {
            dynamicTable = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionId, client);
        } else {
            dynamicTable = dynamicTableService.findByTableDefinitionIdAndCustomTableDefinitionIdAndClient(
                    tableDefinitionId, customTableDefinitionId, client
            );
            customTableDefinition = dynamicTable.getCustomTableDefinition();
        }
        ensureTableBelongsToPage(dynamicTable.getTableDefinition(), page);
        model.addAttribute("tableDefinition", dynamicTable.getTableDefinition());
        model.addAttribute("customTableDefinition", customTableDefinition);
        model.addAttribute("newGroup", dynamicGroupRowFactory.empty(dynamicTable.getTableDefinition(), customTableDefinition, client));
        model.addAttribute("groupOptions", dynamicGroupRowService.findAll(dynamicTable.getTableDefinition(), customTableDefinition, client));
        model.addAttribute("tableHeader", tableDefinitionService.getHeader(dynamicTable.getTableDefinition(), client, user));
        model.addAttribute("dynamicTable", dynamicTable);
        model.addAttribute("tableFooter", dynamicTableService.getFooter(dynamicTable, user));
        model.addAttribute("newRow", dynamicRowFactory.empty(dynamicTable, client, true));
        model.addAttribute("newColumn", tableColumnFactory.empty(dynamicTable));
    }

    public void preparePageCustomTablesList(PageId pageId, TableDefinitionId tableDefinitionId, final Model model) throws SiuvsException {
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        TableDefinition tableDefinition = tableDefinitionService.findOne(tableDefinitionId);
        ensureTableBelongsToPage(tableDefinition, page);
        model.addAttribute("newCustomTableDefinition", new CustomTableDefinition());
        model.addAttribute("tableDefinition", tableDefinition);
        model.addAttribute(
                "customTableDefinitions",
                customTableDefinitionService.findByClientAndTableDefinition(client, tableDefinition)
        );
    }

    public void addRow(TableDefinitionId tableDefinitionId, final DynamicRow dynamicRow) throws SiuvsException {
        DynamicTable dynamicTable = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionId, client);
        ensureUserTableWriteAccess(dynamicTable, user);
        ensureDynamicDataNotEmpty(dynamicRow.getData());
        dynamicTableService.addRow(dynamicTable, dynamicRow, client);
    }

    public void addRow(TableDefinitionId tableDefinitionId, CustomTableDefinitionId customTableDefinitionId, final DynamicRow dynamicRow) throws SiuvsException {
        DynamicTable dynamicTable = dynamicTableService.findByTableDefinitionIdAndCustomTableDefinitionIdAndClient(
                tableDefinitionId, customTableDefinitionId, client
        );
        ensureUserTableWriteAccess(dynamicTable, user);
        ensureDynamicDataNotEmpty(dynamicRow.getData());
        dynamicTableService.addRow(dynamicTable, dynamicRow, client);
    }

    public void addGroupRow(TableDefinitionId tableDefinitionId, final DynamicGroupRow groupRow) throws SiuvsException {
        TableDefinition tableDefinition = tableDefinitionService.findOne(tableDefinitionId);
        DynamicTable dynamicTable = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionId, client);
        ensureUserTableWriteAccess(dynamicTable, user);
        dynamicGroupRowService.add(groupRow, tableDefinition, null, client);
    }

    public void addGroupRow(TableDefinitionId tableDefinitionId, CustomTableDefinitionId customTableDefinitionId, final DynamicGroupRow groupRow) throws SiuvsException {
        DynamicTable dynamicTable = dynamicTableService.findByTableDefinitionIdAndCustomTableDefinitionIdAndClient(
                tableDefinitionId, customTableDefinitionId, client
        );
        ensureUserTableWriteAccess(dynamicTable, user);
        dynamicGroupRowService.add(groupRow, dynamicTable.getTableDefinition(), dynamicTable.getCustomTableDefinition(), client);
    }

    public void addDynamicColumn(TableColumnId columnId, TableColumn newColumn) throws SiuvsException {
        TableColumn column = tableColumnService.findOne(columnId);
        TableDefinitionId tableDefinitionId = new TableDefinitionId(column.getTable().getId());
        DynamicTable dynamicTable = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionId, client);
        ensureUserTableWriteAccess(dynamicTable, user);
        tableColumnService.add(columnId, newColumn, client);
    }

    public void addNewCustomTable(TableDefinitionId tableDefinitionId, final CustomTableDefinition customTableDefinition) {
        TableDefinition tableDefinition = tableDefinitionService.findOne(tableDefinitionId);
        customTableDefinition.setClient(client);
        customTableDefinition.setTableDefinition(tableDefinition);
        customTableDefinitionService.save(customTableDefinition);
    }

    public void preparePageEditRow(PageId pageId, TableDefinitionId tableDefinitionId, DynamicRowId dynamicRowId, final Model model) {
        this.preparePageEditRow(pageId, tableDefinitionId, null, dynamicRowId, model);
    }

    public void preparePageEditRow(PageId pageId, TableDefinitionId tableDefinitionId, CustomTableDefinitionId customTableDefinitionId, DynamicRowId dynamicRowId, final Model model) {
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        DynamicTable dynamicTable;
        CustomTableDefinition customTableDefinition = null;
        if (customTableDefinitionId == null) {
            dynamicTable = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionId, client);
        } else {
            dynamicTable = dynamicTableService.findByTableDefinitionIdAndCustomTableDefinitionIdAndClient(
                    tableDefinitionId, customTableDefinitionId, client
            );
            customTableDefinition = dynamicTable.getCustomTableDefinition();
        }
        model.addAttribute("tableDefinition", dynamicTable.getTableDefinition());
        model.addAttribute("customTableDefinition", dynamicTable.getCustomTableDefinition());
        model.addAttribute("groupOptions", dynamicGroupRowService.findAll(dynamicTable.getTableDefinition(), customTableDefinition, client));
        model.addAttribute("tableHeader", tableDefinitionService.getHeader(dynamicTable.getTableDefinition(), client, user));
        model.addAttribute("dynamicTable", dynamicTable);
        model.addAttribute("tableFooter", dynamicTableService.getFooter(dynamicTable, user));
        List<TableColumn> tableColumns = dynamicTable.getTableDefinition().getColumns(client);
        model.addAttribute("dynamicRow", dynamicRowService.getOneForEditing(dynamicRowId, tableColumns));
    }

    public boolean editRow(DynamicRowId dynamicRowId, List<DynamicData> newData) throws SiuvsException {
        ensureDynamicDataNotEmpty(newData);
        DynamicRow row = dynamicRowService.findOne(dynamicRowId);
        ensureUserTableWriteAccess(row.getDynamicTable(), user);
        List<TableColumn> tableColumns = row.getDynamicTable().getTableDefinition().getColumns(client);
        return dynamicRowService.updateData(dynamicRowId, newData, tableColumns);
    }

    public void deleteRow(DynamicRowId dynamicRowId) throws SiuvsException {
        DynamicRow row = dynamicRowService.findOne(dynamicRowId);
        ensureUserTableWriteAccess(row.getDynamicTable(), user);
        dynamicRowService.delete(dynamicRowId);
    }

    public void moveRow(DynamicRowId dynamicRowId, int direction) {
        dynamicRowService.move(dynamicRowId, direction);
    }

    public void updateDescription(TableDefinitionId tableDefinitionId, String description) {
        DynamicTable dynamicTable = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionId, client);
        dynamicTable.setDescription(description);
        dynamicTableService.save(dynamicTable);
    }

    public void updateDescription(TableDefinitionId tableDefinitionId, CustomTableDefinitionId customTableDefinitionId, String description) {
        DynamicTable dynamicTable = dynamicTableService.findByTableDefinitionIdAndCustomTableDefinitionIdAndClient(tableDefinitionId, customTableDefinitionId, client);
        dynamicTable.setDescription(description);
        dynamicTableService.save(dynamicTable);
    }

    private void ensureUserClientAccess(User user, Client client) throws SiuvsException {
        if (user.getClient() != null && user.getClient().getId() != client.getId()) {
            throw new SiuvsException("Немате привилегија за ову акцију");
        }
    }

    private void ensureUserTableWriteAccess(DynamicTable dynamicTable, User user) throws SiuvsException {
        ensureUserClientAccess(user, dynamicTable.getClient());
    }

    private void ensureTableBelongsToPage(TableDefinition tableDefinition, Page page) throws SiuvsException {
        if (tableDefinition.getPage().getId() != page.getId()) {
            throw new SiuvsException("Табела није пронађена");
        }
    }

    private void ensureDynamicDataNotEmpty(List<DynamicData> data) throws SiuvsException {
        boolean empty = true;
        for (DynamicData entry: data) {
            if (!entry.isEmpty()) {
                empty = false;
                break;
            }
        }
        if (empty) {
            throw new SiuvsException("Није могуће снимити празан ред");
        }
    }

}
