package slobodan.siuvs2.service;

import slobodan.siuvs2.model.TableColumn;
import slobodan.siuvs2.model.Roles;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.TableDefinition;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.repository.TableDefinitionRepository;
import slobodan.siuvs2.valueObject.TableDefinitionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TableDefinitionServiceImpl implements TableDefinitionService {

    @Autowired
    private TableDefinitionRepository tableDefinitionRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<TableDefinition> findByPage(Page page) {
        return tableDefinitionRepository.findByPageOrderByOrder(page);
    }

    @Override
    public TableDefinition findOne(TableDefinitionId tableDefinitionId) {
        return tableDefinitionRepository.findOne(tableDefinitionId.getValue());
    }

    public List<HeaderRow> getHeader(TableDefinition tableDefinition, Client client, User user) {
        int rowsCount = getRowsCount(tableDefinition, client);
        List<HeaderRow> headerRows = new ArrayList<>(rowsCount);
        for (int i = 0; i < rowsCount; i++) {
            addRow(headerRows, i, rowsCount, tableDefinition.getColumns(client), user);
        }
        return headerRows;
    }

    private int getRowsCount(TableDefinition tableDefinition, Client client) {
        int rowsCount = 1;
        for (TableColumn column : tableDefinition.getColumns(client)) {
            if ((column.getRow2() != null || column.isDynamicRoot()) && rowsCount < 2) {
                rowsCount = 2;
            }
            if (column.getRow3() != null && rowsCount < 3) {
                rowsCount = 3;
            }
            if (column.getRow4() != null) {
                rowsCount = 4;
                break;
            }
        }
        return rowsCount;
    }

    private void addRow(List<HeaderRow> headerRows, int currentRow, int rowsCount, List<TableColumn> columns, User user) {
        HeaderRow headerRow = new HeaderRow();
        if (currentRow == 0) {
            addActionsColumn(headerRow, rowsCount, user);
            addRecordNumberColumn(headerRow, rowsCount);
        }
        HeaderColumn lastDynamicColumn = null;
        for (TableColumn column : columns) {
            HeaderColumn headerColumn = createHeaderColumn(currentRow, rowsCount, column);
            if (column.isDynamicRoot()) {
                lastDynamicColumn = headerColumn;
            } else {
                if (lastDynamicColumn != null && column.isDynamic()) {
                    lastDynamicColumn.setColSpan(lastDynamicColumn.getColSpan() + 1);
                }
            }
            if (headerColumn != null) {
                headerRow.addColumn(headerColumn);
            }
        }
        headerRows.add(headerRow);
    }

    private HeaderColumn createHeaderColumn(int currentRow, int rowsCount, TableColumn column) {
        int rowSpan = 0;
        int colSpan = 0;
        String title = "";
        switch (currentRow) {
            case 0:
                if (column.getTitle() == null) {
                    break;
                }
                rowSpan++;
                title = column.getTitle();
                colSpan = column.getColSpan();
                if (column.isDynamicRoot()) {
                    colSpan = 0;
                } else {
                    if (column.getRow2() == null && rowsCount > 1) {
                        rowSpan++;
                        if (column.getRow3() == null && rowsCount > 2) {
                            rowSpan++;
                            if (column.getRow4() == null && rowsCount > 3) {
                                rowSpan++;
                            }
                        }
                    }
                }
                break;
            case 1:
                if (column.getRow2() == null || column.isDynamicRoot()) {
                    break;
                }
                rowSpan++;
                title = column.getRow2();
                colSpan = column.getColSpan2();
                if (column.getRow3() == null && rowsCount > 2) {
                    rowSpan++;
                    if (column.getRow4() == null && rowsCount > 3) {
                        rowSpan++;
                    }
                }
                break;
            case 2:
                if (column.getRow3() == null) {
                    break;
                }
                rowSpan++;
                title = column.getRow3();
                colSpan = column.getColSpan3();
                if (column.getRow4() == null && rowsCount > 3) {
                    rowSpan++;
                }
                break;
            case 3:
                if (column.getRow4() != null) {
                    rowSpan++;
                    title = column.getRow4();
                    colSpan = 1;
                }
                break;
        }
        HeaderColumn headerColumn = null;
        if (rowSpan > 0) {
            headerColumn = new HeaderColumn(rowSpan, colSpan, title);
        }
        return headerColumn;
    }

    private void addActionsColumn(HeaderRow headerRow, int rowsCount, User user) {
        if (showActionsColumn(user)) {
            HeaderColumn actionsColumn = new HeaderColumn(rowsCount, 1, "Акције");
            headerRow.addColumn(actionsColumn);
        }
    }

    private void addRecordNumberColumn(HeaderRow headerRow, int rowsCount) {
        HeaderColumn recordNumberColumn = new HeaderColumn(rowsCount, 1, "Ред. бр.");
        headerRow.addColumn(recordNumberColumn);
    }

    public boolean showActionsColumn(User user) {
        return userService.hasRole(user, Roles.ADMIN)
                || userService.hasRole(user, Roles.RIS)
                || userService.hasRole(user, Roles.CLIENT);
    }

}
