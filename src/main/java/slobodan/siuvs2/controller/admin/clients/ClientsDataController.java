package slobodan.siuvs2.controller.admin.clients;

import slobodan.siuvs2.service.TableDefinitionService;
import slobodan.siuvs2.service.TableColumnFactory;
import slobodan.siuvs2.service.DynamicGroupRowService;
import slobodan.siuvs2.service.StorageService;
import slobodan.siuvs2.service.CustomTableDefinitionService;
import slobodan.siuvs2.service.TableColumnService;
import slobodan.siuvs2.service.DynamicGroupRowFactory;
import slobodan.siuvs2.service.PhotoService;
import slobodan.siuvs2.service.DynamicTableService;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.DynamicRowService;
import slobodan.siuvs2.service.DynamicRowFactory;
import slobodan.siuvs2.service.PageService;
import slobodan.siuvs2.model.TableColumn;
import slobodan.siuvs2.model.SiuvsUserPrincipal;
import slobodan.siuvs2.model.DynamicRow;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.TableDefinition;
import slobodan.siuvs2.model.CustomTableDefinition;
import slobodan.siuvs2.model.DynamicTable;
import slobodan.siuvs2.model.DynamicGroupRow;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.valueObject.OpstinaID;
import slobodan.siuvs2.valueObject.DynamicRowId;
import slobodan.siuvs2.valueObject.ClientId;
import slobodan.siuvs2.valueObject.CustomTableDefinitionId;
import slobodan.siuvs2.valueObject.PageId;
import slobodan.siuvs2.valueObject.TableDefinitionId;
import slobodan.siuvs2.valueObject.TableColumnId;
import slobodan.siuvs2.facade.TableFacade;
import slobodan.siuvs2.shared.SiuvsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import slobodan.siuvs2.valueObject.PhotoId;

@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/admin/clients")
public class ClientsDataController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private DynamicTableService dynamicTableService;
    @Autowired
    private DynamicRowService dynamicRowService;
    @Autowired
    private DynamicGroupRowService dynamicGroupRowService;
    @Autowired
    private DynamicRowFactory dynamicRowFactory;
    @Autowired
    private DynamicGroupRowFactory dynamicGroupRowFactory;
    @Autowired
    private PageService pageService;
    @Autowired
    private TableDefinitionService tableDefinitionService;
    @Autowired
    private CustomTableDefinitionService customTableDefinitionService;
    @Autowired
    private TableColumnFactory tableColumnFactory;
    @Autowired
    private TableColumnService tableColumnService;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private StorageService storageService;

    private Map<Integer, TableFacade> tableFacadeCache = new HashMap<>();

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
    }

    private TableFacade getTableFacade(ClientId clientId) throws SiuvsException {
        if (!tableFacadeCache.containsKey(clientId.getValue())) {
            Client client = clientService.findOne(clientId);
            TableFacade tableFacade = new TableFacade(
                    this.getCurrentUser(),
                    client,
                    this.pageService,
                    this.tableDefinitionService,
                    this.dynamicTableService,
                    this.dynamicGroupRowService,
                    this.dynamicRowFactory,
                    this.dynamicGroupRowFactory,
                    this.customTableDefinitionService,
                    this.dynamicRowService,
                    this.tableColumnFactory,
                    this.tableColumnService,
                    this.photoService
            );
            tableFacadeCache.put(clientId.getValue(), tableFacade);
        }
        return tableFacadeCache.get(clientId.getValue());
    }

    @ModelAttribute("newCustomTableDefinition")
    public CustomTableDefinition newCustomTableDefinition() {
        return new CustomTableDefinition();
    }

    @GetMapping(value = "/{clientId}/{pageId}")
    public String tablesList(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            final Model model,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade(clientId).preparePageTablesList(pageId, model);
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/clients";
        }
        return "admin/clients/data/table-list";
    }

    @GetMapping(value = "/{clientId}/{pageId}/edit")
    public String editPhotoForm(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("photos", photoService.findByClientAndPage(client, page));
        return "admin/clients/data/edit-photo";
    }

    @PostMapping(value = "/{clientId}/{pageId}/upload")
    public String upload(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            final RedirectAttributes redirectAttributes
    ) {
        Page page = pageService.findOne(pageId);
        if (title.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Молимо Вас да унесете назив слике");
            return "redirect:/admin/clients/" + clientId + "/" + pageId + "/edit";
        } else if (!page.isAttachablePhotos()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ова страна не подржава унос слика");
            return "redirect:/admin/clients/" + clientId + "/" + pageId + "/edit";
        } else {
            Client client = clientService.findOne(clientId);
            String filename = storageService.store(file, client.getClientId());
            photoService.save(client, pageService.findOne(pageId), title, filename);
            redirectAttributes.addFlashAttribute("successMessage", "Слика је успешно сачувана!");
            return "redirect:/admin/clients/" + clientId + "/" + pageId;
        }
    }

    @PostMapping(value = "/{clientId}/{pageId}/delete/{photoId}")
    public String deletePhoto(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final PhotoId photoId,
            final RedirectAttributes redirectAttributes
    ) {
        Client client = clientService.findOne(clientId);
        String filename = photoService.findFileNameById(photoId);
        photoService.delete(client, photoId);
        storageService.delete(clientId, filename);
        redirectAttributes.addFlashAttribute("successMessage", "Слика је успешно обрисана!");
        return "redirect:/admin/clients/" + clientId + "/" + pageId;
    }

    @GetMapping(value = "/{clientId}/{pageId}/{tableDefinitionId}")
    public String table(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            final Model model,
            final RedirectAttributes redirectAttributes
    ) {
        TableDefinition tableDefinition = tableDefinitionService.findOne(tableDefinitionId);
        try {
            if (tableDefinition.isUserDefined()) {
                this.getTableFacade(clientId).preparePageCustomTablesList(pageId, tableDefinitionId, model);
                return "admin/clients/data/user-defined-list";
            } else {
                this.getTableFacade(clientId).preparePageTableView(pageId, tableDefinitionId, model);
                return "admin/clients/data/table";
            }
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/clients/" + clientId + "/" + pageId;
        }
    }

    @PostMapping(value = "/{clientId}/{pageId}/{tableDefinitionId}")
    public String addRow(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @ModelAttribute("newRow") final DynamicRow dynamicRow,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade(clientId).addRow(tableDefinitionId, dynamicRow);
            redirectAttributes.addFlashAttribute("successMessage", "Додали сте нови ред!");
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/" + pageId + "/" + tableDefinitionId + "#new-row";
    }

    @PostMapping(value = "/{clientId}/{pageId}/{tableDefinitionId}/group")
    public String addGroupRow(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @ModelAttribute("newGroup") final DynamicGroupRow groupRow,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade(clientId).addGroupRow(tableDefinitionId, groupRow);
            redirectAttributes.addFlashAttribute("successMessage", "Додали сте нову групу!");
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/" + pageId + "/" + tableDefinitionId;
    }

    @PostMapping(value = "/{clientId}/{pageId}/{tableDefinitionId}/description")
    public String updateDescription(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @ModelAttribute("dynamicTable") final DynamicTable dynamicTable,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade(clientId).updateDescription(tableDefinitionId, dynamicTable.getDescription());
            redirectAttributes.addFlashAttribute("successMessage", "Изменили сте напомену!");
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/" + pageId + "/" + tableDefinitionId;
    }

    @PostMapping(value = "/{clientId}/{pageId}/{tableDefinitionId}/{customTableDefinitionId}/description")
    public String updateCustomTableDescription(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @PathVariable final CustomTableDefinitionId customTableDefinitionId,
            @ModelAttribute("dynamicTable") final DynamicTable dynamicTable,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade(clientId).updateDescription(tableDefinitionId, customTableDefinitionId, dynamicTable.getDescription());
            redirectAttributes.addFlashAttribute("successMessage", "Изменили сте напомену!");
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/" + pageId + "/" + tableDefinitionId + "/" + customTableDefinitionId;
    }

    @PostMapping(value = "/{clientId}/{pageId}/{tableDefinitionId}/column/{columnId}")
    public String addDynamicColumn(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @PathVariable final TableColumnId columnId,
            @ModelAttribute("newColumn") final TableColumn newColumn,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом додавања колоне!");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newColumn", bindingResult);
            redirectAttributes.addFlashAttribute("newColumn", newColumn);
            return "redirect:/admin/clients/" + clientId + "/" + pageId + "/" + tableDefinitionId;
        }
        if (newColumn.getTitle().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Назив колоне не може бити празан");
            redirectAttributes.addFlashAttribute("newColumn", newColumn);
            return "redirect:/admin/clients/" + clientId + "/" + pageId + "/" + tableDefinitionId;
        }
        try {
            this.getTableFacade(clientId).addDynamicColumn(columnId, newColumn);
            redirectAttributes.addFlashAttribute("successMessage", "Додали сте нову колону!");
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/" + pageId + "/" + tableDefinitionId;
    }

    @PostMapping(value = "/{clientId}/{pageId}/{tableDefinitionId}/new")
    public String createNewCustomTableDefinition(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @Valid @ModelAttribute("newCustomTableDefinition") final CustomTableDefinition customTableDefinition,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом креирања нове табеле!");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newCustomTableDefinition", bindingResult);
            redirectAttributes.addFlashAttribute("newCustomTableDefinition", customTableDefinition);
        } else {
            try {
                this.getTableFacade(clientId).addNewCustomTable(tableDefinitionId, customTableDefinition);
                redirectAttributes.addFlashAttribute("successMessage", "Додали сте нову табелу!");
            } catch (SiuvsException e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            }
        }
        return "redirect:/admin/clients/" + clientId + "/" + pageId + "/" + tableDefinitionId;
    }

    @GetMapping(value = "/{clientId}/{pageId}/{tableDefinitionId}/edit/{dynamicRowId}")
    public String editGenericTableRow(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @PathVariable final DynamicRowId dynamicRowId,
            final Model model,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade(clientId).preparePageEditRow(pageId, tableDefinitionId, dynamicRowId, model);
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/clients/" + clientId + "/" + pageId + "/" + tableDefinitionId;
        }
        return "admin/clients/data/table-editrow";
    }

    @PostMapping(value = "/{clientId}/{pageId}/{tableDefinitionId}/edit/{dynamicRowId}")
    public String editGenericTableRowSave(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @PathVariable final DynamicRowId dynamicRowId,
            @ModelAttribute("dynamicRow") final DynamicRow dynamicRow,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        boolean saved = false;
        try {
            saved = this.getTableFacade(clientId).editRow(dynamicRowId, dynamicRow.getData());
            if (saved) {
                redirectAttributes.addFlashAttribute("successMessage", "Изменили сте ред!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом измене реда!");
            }
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/" + pageId + "/" + tableDefinitionId;
    }

    @PostMapping(value = "/{clientId}/{pageId}/{tableDefinitionId}/deleterow")
    public String deleteGenericTableRowSave(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @RequestParam("rowId") final DynamicRowId dynamicRowId,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade(clientId).deleteRow(dynamicRowId);
            redirectAttributes.addFlashAttribute("successMessage", "Обрисали сте ред!");
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/" + pageId + "/" + tableDefinitionId;
    }

    @GetMapping(value = "/{clientId}/{pageId}/{tableDefinitionId}/moverow/{dynamicRowId}/{direction}")
    public String moveRow(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @PathVariable final DynamicRowId dynamicRowId,
            @PathVariable final String direction,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade(clientId).moveRow(dynamicRowId, direction.equals("up") ? -1 : 1);
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/" + pageId + "/" + tableDefinitionId;
    }

    @GetMapping(value = "/{clientId}/{pageId}/{tableDefinitionId}/{customTableDefinitionId}")
    public String customTable(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @PathVariable final CustomTableDefinitionId customTableDefinitionId,
            final Model model,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade(clientId).preparePageTableView(pageId, tableDefinitionId, customTableDefinitionId, model);
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/clients/" + clientId + "/" + pageId;
        }
        return "admin/clients/data/table-custom";
    }

    @PostMapping(value = "/{clientId}/{pageId}/{tableDefinitionId}/{customTableDefinitionId}/group")
    public String addGroupRowWithCustomTableDefinition(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @PathVariable final CustomTableDefinitionId customTableDefinitionId,
            @ModelAttribute("newGroup") final DynamicGroupRow groupRow,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade(clientId).addGroupRow(tableDefinitionId, customTableDefinitionId, groupRow);
            redirectAttributes.addFlashAttribute("successMessage", "Додали сте нову групу!");
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/" + pageId + "/" + tableDefinitionId + "/" + customTableDefinitionId;
    }

    @PostMapping(value = "/{clientId}/{pageId}/{tableDefinitionId}/{customTableDefinitionId}/column/{columnId}")
    public String addDynamicColumnWithCustomTableDefinition(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @PathVariable final CustomTableDefinitionId customTableDefinitionId,
            @PathVariable final TableColumnId columnId,
            @ModelAttribute("newColumn") final TableColumn newColumn,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом додавања колоне!");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newColumn", bindingResult);
            redirectAttributes.addFlashAttribute("newColumn", newColumn);
        } else {
            try {
                this.getTableFacade(clientId).addDynamicColumn(columnId, newColumn);
                redirectAttributes.addFlashAttribute("successMessage", "Додали сте нову колону!");
            } catch (SiuvsException e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            }
        }
        return "redirect:/admin/clients/" + clientId + "/" + pageId + "/" + tableDefinitionId + "/" + customTableDefinitionId;
    }

    @PostMapping(value = "/{clientId}/{pageId}/{tableDefinitionId}/{customTableDefinitionId}")
    public String addRowWithCustomTableDefinition(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @PathVariable final CustomTableDefinitionId customTableDefinitionId,
            @ModelAttribute("newRow") final DynamicRow dynamicRow,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade(clientId).addRow(tableDefinitionId, customTableDefinitionId, dynamicRow);
            redirectAttributes.addFlashAttribute("successMessage", "Додали сте нови ред!");
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/" + pageId + "/" + tableDefinitionId + "/" + customTableDefinitionId + "#new-row";
    }

    @GetMapping(value = "/{clientId}/{pageId}/{tableDefinitionId}/{customTableDefinitionId}/edit/{dynamicRowId}")
    public String editCustomTableRow(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @PathVariable final CustomTableDefinitionId customTableDefinitionId,
            @PathVariable final DynamicRowId dynamicRowId,
            final Model model,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade(clientId).preparePageEditRow(pageId, tableDefinitionId, customTableDefinitionId, dynamicRowId, model);
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/clients/" + clientId + "/" + pageId + "/" + tableDefinitionId + "/" + customTableDefinitionId;
        }
        return "admin/clients/data/table-custom-editrow";
    }

    @PostMapping(value = "/{clientId}/{pageId}/{tableDefinitionId}/{customTableDefinitionId}/edit/{dynamicRowId}")
    public String editCustomTableRowSave(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @PathVariable final CustomTableDefinitionId customTableDefinitionId,
            @PathVariable final DynamicRowId dynamicRowId,
            @ModelAttribute("dynamicRow") final DynamicRow dynamicRow,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        boolean saved = false;
        try {
            saved = this.getTableFacade(clientId).editRow(dynamicRowId, dynamicRow.getData());
            if (saved) {
                redirectAttributes.addFlashAttribute("successMessage", "Изменили сте ред!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом измене реда!");
            }
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/" + pageId + "/" + tableDefinitionId + "/" + customTableDefinitionId;
    }

    @PostMapping(value = "/{clientId}/{pageId}/{tableDefinitionId}/{customTableDefinitionId}/deleterow")
    public String deleteCustomTableRowSave(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @PathVariable final CustomTableDefinitionId customTableDefinitionId,
            @RequestParam("rowId") final DynamicRowId dynamicRowId,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade(clientId).deleteRow(dynamicRowId);
            redirectAttributes.addFlashAttribute("successMessage", "Обрисали сте ред!");
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/" + pageId + "/" + tableDefinitionId + "/" + customTableDefinitionId;
    }

    @GetMapping(value = "/{clientId}/{pageId}/{tableDefinitionId}/{customTableDefinitionId}/moverow/{dynamicRowId}/{direction}")
    public String moveCustomTableRow(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @PathVariable final CustomTableDefinitionId customTableDefinitionId,
            @PathVariable final DynamicRowId dynamicRowId,
            @PathVariable final String direction,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade(clientId).moveRow(dynamicRowId, direction.equals("up") ? -1 : 1);
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/" + pageId + "/" + tableDefinitionId + "/" + customTableDefinitionId;
    }

}
