/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bitbucket.pbosko.siuvs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bitbucket.pbosko.siuvs.facade.TableFacade;
import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.model.DynamicData;
import org.bitbucket.pbosko.siuvs.model.Roles;
import org.bitbucket.pbosko.siuvs.model.SearchResults;
import org.bitbucket.pbosko.siuvs.model.SiuvsUserPrincipal;
import org.bitbucket.pbosko.siuvs.model.TableColumn;
import org.bitbucket.pbosko.siuvs.model.TableDefinition;
import org.bitbucket.pbosko.siuvs.model.User;
import org.bitbucket.pbosko.siuvs.repository.DynamicDataRepository;
import org.bitbucket.pbosko.siuvs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Slobodan
 */
@Controller
public class SearchController {

    @Autowired
    private UserService userService;

    @Autowired
    private DynamicDataRepository dynamicDataRepository;

    private Map<Integer, TableFacade> tableFacadeCache = new HashMap<>();

    
    @PostMapping("/search-results")
    public String performSearch(final Model model, @RequestParam(name = "upit") String upit, @PageableDefault final Pageable pageable, final RedirectAttributes redir) {
        User user = getCurrentUser();
        boolean skip = false;
        String navigation = "fragments/navigation :: navigation";
        if (user.getFirstRole().getId() == 2 || user.getFirstRole().getId() == 5) {
            skip = true;
            navigation += "-end-user";
        }

        List<DynamicData> dynd = dynamicDataRepository.findByValue(upit);
        List<DynamicData> dyndshort = new ArrayList<>();
        List<DynamicData> dataseach = new ArrayList<>();
        List<String> links = new ArrayList<>();
        List< List<HeaderRow>> tableheaders = new ArrayList<List<HeaderRow>>();
        List< Client> clients = new ArrayList<>();
        List<List<DynamicData>> datas = new ArrayList<List<DynamicData>>();

        if (!dynd.isEmpty()) {
            for (DynamicData member : dynd) {
                Client cli = member.getRow().getDynamicTable().getClient();
                if (!skip) {
                    dyndshort.add(member);
                    links.add("/admin/clients/" + member.getRow().getDynamicTable().getClient().getClientId() + "/" + member.getRow().getDynamicTable().getTableDefinition().getPage().getId() + "/" + member.getRow().getDynamicTable().getTableDefinition().getId());
                    tableheaders.add(getHeader(member.getRow().getDynamicTable().getTableDefinition(), cli, user));
                   // clients.add(cli);
                    dataseach = dynamicDataRepository.findById(member.getRow().getId());
                    datas.add(dataseach);

                } else if (user.getClient().getId() == cli.getId()) {
                    dyndshort.add(member);
                    links.add("/client/" + member.getRow().getDynamicTable().getTableDefinition().getPage().getId() + "/" + member.getRow().getDynamicTable().getTableDefinition().getId());

                    tableheaders.add(getHeader(member.getRow().getDynamicTable().getTableDefinition(), cli, user));
                   // clients.add(cli);
                    dataseach = dynamicDataRepository.findById(member.getRow().getId());
                    datas.add(dataseach);
                }

            }
int numberofPages=(int) Math.ceil((double)dyndshort.size()/20);
            model.addAttribute("links", links);
          
            model.addAttribute("PagesNo", numberofPages);
            model.addAttribute("dynamicDataList", dyndshort);
            model.addAttribute("tableheaders", tableheaders);
          //  model.addAttribute("clients", clients);
            model.addAttribute("datas", datas);
        }
        model.addAttribute("query", upit);//this one is outside the loop so that the user can see the query string even if result set is empty
        model.addAttribute("navigation", navigation);//this one is outside the loop so that the user can see the navigation even if result set is empty
        return "results"; //view
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
    }

    public class HeaderRow {

        private List<HeaderColumn> columns = new ArrayList<>();

        public List<HeaderColumn> getColumns() {
            return columns;
        }

        void addColumn(HeaderColumn headerColumn) {
            columns.add(headerColumn);
        }
    }

    class HeaderColumn {

        private String title;

        private int rowSpan;

        private int colSpan;

        HeaderColumn(int rowSpan, int colSpan, String title) {
            setRowSpan(rowSpan);
            setColSpan(colSpan);
            setTitle(title);
        }

        public String getTitle() {
            return title;
        }

        void setTitle(String title) {
            this.title = title;
        }

        public int getRowSpan() {
            return rowSpan;
        }

        void setRowSpan(int rowSpan) {
            this.rowSpan = rowSpan;
        }

        public int getColSpan() {
            return colSpan;
        }

        void setColSpan(int colSpan) {
            this.colSpan = colSpan;
        }
    }

    List<HeaderRow> getHeader(TableDefinition tableDefinition, Client client, User user) {
        int rowsCount = getRowsCount(tableDefinition, client);
        List<HeaderRow> headerRows = new ArrayList<>(rowsCount);
        for (int i = 0; i < rowsCount; i++) {
            addRow(headerRows, i, rowsCount, tableDefinition.getColumns(client), user);
        }
        return headerRows;
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

    private void addRow(List<HeaderRow> headerRows, int currentRow, int rowsCount, List<TableColumn> columns, User user) {
        HeaderRow headerRow = new HeaderRow();
        if (currentRow == 0) {
            // addActionsColumn(headerRow, rowsCount, user);
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

}
