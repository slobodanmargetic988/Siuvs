package slobodan.siuvs2.controller.client;

import slobodan.siuvs2.service.TableDefinitionService;
import slobodan.siuvs2.service.TableColumnFactory;
import slobodan.siuvs2.service.UserService;
import slobodan.siuvs2.service.DynamicGroupRowService;
import slobodan.siuvs2.service.StorageService;
import slobodan.siuvs2.service.CustomTableDefinitionService;
import slobodan.siuvs2.service.TableColumnService;
import slobodan.siuvs2.service.DynamicGroupRowFactory;
import slobodan.siuvs2.service.PhotoService;
import slobodan.siuvs2.service.DynamicTableService;
import slobodan.siuvs2.service.DynamicRowService;
import slobodan.siuvs2.service.DynamicRowFactory;
import slobodan.siuvs2.service.PageService;
import slobodan.siuvs2.model.TableColumn;
import slobodan.siuvs2.model.SiuvsUserPrincipal;
import slobodan.siuvs2.model.DynamicRow;
import slobodan.siuvs2.model.Roles;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.TableDefinition;
import slobodan.siuvs2.model.CustomTableDefinition;
import slobodan.siuvs2.model.DynamicTable;
import slobodan.siuvs2.model.DynamicGroupRow;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.valueObject.OpstinaID;
import slobodan.siuvs2.valueObject.DynamicRowId;
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

@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/client")
public class DataController {

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
    @Autowired
    private UserService userService;

    private TableFacade tableFacade;

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
    }

    private Client getCurrentUserClient() {
        Client client = null;
        User user = getCurrentUser();
        if (user != null) {
            client = user.getClient();
        }
        return client;
    }

    private TableFacade getTableFacade() throws SiuvsException {
        if (this.tableFacade == null) {
            this.tableFacade = new TableFacade(
                    this.getCurrentUser(),
                    this.getCurrentUserClient(),
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
        }
        return this.tableFacade;
    }

    @GetMapping(value = "/{pageId}")
    public String tablesList(
            @PathVariable final PageId pageId,
            final Model model,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade().preparePageTablesList(pageId, model);
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/client";
        }
        return "client/data/table-list";
    }

    @GetMapping(value = "/{pageId}/edit")
    public String save(@PathVariable final PageId pageId, final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("photos", photoService.findByClientAndPage(client, page));
        return "client/data/edit-photo";
    }

    @PostMapping(value = "/{pageId}/upload")
    public String upload(
            @PathVariable final PageId pageId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            final RedirectAttributes redirectAttributes
    ) {
        Page page = pageService.findOne(pageId);
        if (title.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Молимо Вас да унесете назив слике");
            return "redirect:/client/" + pageId + "/edit";
        } else if (!page.isAttachablePhotos()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ова страна не подржава унос слика");
            return "redirect:/client/" + pageId + "/edit";
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
            Client client = user.getClient();
            if (!userService.hasRole(user, Roles.CLIENT)) {
                redirectAttributes.addFlashAttribute("errorMessage", "Немате права за унос слике");
                return "redirect:/client/" + pageId;
            }
            String filename = storageService.store(file, client.getClientId());
            photoService.save(client, pageService.findOne(pageId), title, filename);
            redirectAttributes.addFlashAttribute("successMessage", "Слика је успешно сачувана!");
            return "redirect:/client/" + pageId;
        }
    }

    @PostMapping(value = "/{pageId}/delete/{photoId}")
    public String deletePhoto(
            @PathVariable final PageId pageId,
            @PathVariable final OpstinaID photoId,
            final RedirectAttributes redirectAttributes
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        String filename = photoService.findFileNameById(photoId);
        photoService.delete(client, photoId);
        storageService.delete(client.getClientId(), filename);
        redirectAttributes.addFlashAttribute("successMessage", "Слика је успешно обрисана!");
        return "redirect:/client/" + pageId;
    }

    @GetMapping(value = "/{pageId}/{tableDefinitionId}")
    public String table(
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            final Model model,
            final RedirectAttributes redirectAttributes
    ) {
        TableDefinition tableDefinition = tableDefinitionService.findOne(tableDefinitionId);
        try {
            if (tableDefinition.isUserDefined()) {
                this.getTableFacade().preparePageCustomTablesList(pageId, tableDefinitionId, model);
                return "client/data/user-defined-list";
            } else {
                this.getTableFacade().preparePageTableView(pageId, tableDefinitionId, model);
                return "client/data/table";
            }
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/client/" + pageId;
        }
    }

    @PostMapping(value = "/{pageId}/{tableDefinitionId}")
    public String addRow(
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @ModelAttribute("newRow") final DynamicRow dynamicRow,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade().addRow(tableDefinitionId, dynamicRow);
            redirectAttributes.addFlashAttribute("successMessage", "Додали сте нови ред!");
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/client/" + pageId + "/" + tableDefinitionId + "#new-row";
    }

    @PostMapping(value = "/{pageId}/{tableDefinitionId}/group")
    public String addGroupRow(
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @ModelAttribute("newGroup") final DynamicGroupRow groupRow,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade().addGroupRow(tableDefinitionId, groupRow);
            redirectAttributes.addFlashAttribute("successMessage", "Додали сте нову групу!");
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/client/" + pageId + "/" + tableDefinitionId;
    }

    @PostMapping(value = "/{pageId}/{tableDefinitionId}/description")
    public String updateDescription(
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @ModelAttribute("dynamicTable") final DynamicTable dynamicTable,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade().updateDescription(tableDefinitionId, dynamicTable.getDescription());
            redirectAttributes.addFlashAttribute("successMessage", "Изменили сте напомену!");
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/client/" + pageId + "/" + tableDefinitionId;
    }

    @PostMapping(value = "/{pageId}/{tableDefinitionId}/{customTableDefinitionId}/description")
    public String updateCustomTableDescription(
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @PathVariable final CustomTableDefinitionId customTableDefinitionId,
            @ModelAttribute("dynamicTable") final DynamicTable dynamicTable,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade().updateDescription(tableDefinitionId, customTableDefinitionId, dynamicTable.getDescription());
            redirectAttributes.addFlashAttribute("successMessage", "Изменили сте напомену!");
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/client/" + pageId + "/" + tableDefinitionId + "/" + customTableDefinitionId;
    }

    @PostMapping(value = "/{pageId}/{tableDefinitionId}/column/{columnId}")
    public String addDynamicColumn(
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
            return "redirect:/client/" + pageId + "/" + tableDefinitionId;
        }
        if (newColumn.getTitle().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Назив колоне не може бити празан");
            redirectAttributes.addFlashAttribute("newColumn", newColumn);
            return "redirect:/client/" + pageId + "/" + tableDefinitionId;
        }
        try {
            this.getTableFacade().addDynamicColumn(columnId, newColumn);
            redirectAttributes.addFlashAttribute("successMessage", "Додали сте нову колону!");
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/client/" + pageId + "/" + tableDefinitionId;
    }

    @PostMapping(value = "/{pageId}/{tableDefinitionId}/new")
    public String createNewCustomTableDefinition(
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
                this.getTableFacade().addNewCustomTable(tableDefinitionId, customTableDefinition);
                redirectAttributes.addFlashAttribute("successMessage", "Додали сте нову табелу!");
            } catch (SiuvsException e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            }
        }
        return "redirect:/client/" + pageId + "/" + tableDefinitionId;
    }

    @GetMapping(value = "/{pageId}/{tableDefinitionId}/edit/{dynamicRowId}")
    public String editGenericTableRow(
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @PathVariable final DynamicRowId dynamicRowId,
            final Model model,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade().preparePageEditRow(pageId, tableDefinitionId, dynamicRowId, model);
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/client/" + pageId + "/" + tableDefinitionId;
        }
        return "client/data/table-editrow";
    }

    @PostMapping(value = "/{pageId}/{tableDefinitionId}/edit/{dynamicRowId}")
    public String editGenericTableRowSave(
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @PathVariable final DynamicRowId dynamicRowId,
            @ModelAttribute("dynamicRow") final DynamicRow dynamicRow,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        boolean saved = false;
        try {
            saved = this.getTableFacade().editRow(dynamicRowId, dynamicRow.getData());
            if (saved) {
                redirectAttributes.addFlashAttribute("successMessage", "Изменили сте ред!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом измене реда!");
            }
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/client/" + pageId + "/" + tableDefinitionId;
    }

    @PostMapping(value = "/{pageId}/{tableDefinitionId}/deleterow")
    public String deleteGenericTableRowSave(
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @RequestParam("rowId") final DynamicRowId dynamicRowId,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade().deleteRow(dynamicRowId);
            redirectAttributes.addFlashAttribute("successMessage", "Обрисали сте ред!");
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/client/" + pageId + "/" + tableDefinitionId;
    }

    @GetMapping(value = "/{pageId}/{tableDefinitionId}/moverow/{dynamicRowId}/{direction}")
    public String moveRow(
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @PathVariable final DynamicRowId dynamicRowId,
            @PathVariable final String direction,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade().moveRow(dynamicRowId, direction.equals("up") ? -1 : 1);
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/client/" + pageId + "/" + tableDefinitionId;
    }

    @GetMapping(value = "/{pageId}/{tableDefinitionId}/{customTableDefinitionId}")
    public String customTable(
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @PathVariable final CustomTableDefinitionId customTableDefinitionId,
            final Model model,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade().preparePageTableView(pageId, tableDefinitionId, customTableDefinitionId, model);
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/client/" + pageId;
        }
        return "client/data/table-custom";
    }

    @PostMapping(value = "/{pageId}/{tableDefinitionId}/{customTableDefinitionId}/group")
    public String addGroupRowWithCustomTableDefinition(
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @PathVariable final CustomTableDefinitionId customTableDefinitionId,
            @ModelAttribute("newGroup") final DynamicGroupRow groupRow,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade().addGroupRow(tableDefinitionId, customTableDefinitionId, groupRow);
            redirectAttributes.addFlashAttribute("successMessage", "Додали сте нову групу!");
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/client/" + pageId + "/" + tableDefinitionId + "/" + customTableDefinitionId;
    }

    @PostMapping(value = "/{pageId}/{tableDefinitionId}/{customTableDefinitionId}/column/{columnId}")
    public String addDynamicColumnWithCustomTableDefinition(
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
                this.getTableFacade().addDynamicColumn(columnId, newColumn);
                redirectAttributes.addFlashAttribute("successMessage", "Додали сте нову колону!");
            } catch (SiuvsException e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            }
        }
        return "redirect:/client/" + pageId + "/" + tableDefinitionId + "/" + customTableDefinitionId;
    }

    @PostMapping(value = "/{pageId}/{tableDefinitionId}/{customTableDefinitionId}")
    public String addRowWithCustomTableDefinition(
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @PathVariable final CustomTableDefinitionId customTableDefinitionId,
            @ModelAttribute("newRow") final DynamicRow dynamicRow,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade().addRow(tableDefinitionId, customTableDefinitionId, dynamicRow);
            redirectAttributes.addFlashAttribute("successMessage", "Додали сте нови ред!");
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/client/" + pageId + "/" + tableDefinitionId + "/" + customTableDefinitionId + "#new-row";
    }

    @GetMapping(value = "/{pageId}/{tableDefinitionId}/{customTableDefinitionId}/edit/{dynamicRowId}")
    public String editCustomTableRow(
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @PathVariable final CustomTableDefinitionId customTableDefinitionId,
            @PathVariable final DynamicRowId dynamicRowId,
            final Model model,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade().preparePageEditRow(pageId, tableDefinitionId, customTableDefinitionId, dynamicRowId, model);
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/client/" + pageId + "/" + tableDefinitionId + "/" + customTableDefinitionId;
        }
        return "client/data/table-custom-editrow";
    }

    @PostMapping(value = "/{pageId}/{tableDefinitionId}/{customTableDefinitionId}/edit/{dynamicRowId}")
    public String editCustomTableRowSave(
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
            saved = this.getTableFacade().editRow(dynamicRowId, dynamicRow.getData());
            if (saved) {
                redirectAttributes.addFlashAttribute("successMessage", "Изменили сте ред!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом измене реда!");
            }
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/client/" + pageId + "/" + tableDefinitionId + "/" + customTableDefinitionId;
    }

    @PostMapping(value = "/{pageId}/{tableDefinitionId}/{customTableDefinitionId}/deleterow")
    public String deleteCustomTableRowSave(
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @PathVariable final CustomTableDefinitionId customTableDefinitionId,
            @RequestParam("rowId") final DynamicRowId dynamicRowId,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade().deleteRow(dynamicRowId);
            redirectAttributes.addFlashAttribute("successMessage", "Обрисали сте ред!");
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/client/" + pageId + "/" + tableDefinitionId + "/" + customTableDefinitionId;
    }

    @GetMapping(value = "/{pageId}/{tableDefinitionId}/{customTableDefinitionId}/moverow/{dynamicRowId}/{direction}")
    public String moveRow(
            @PathVariable final PageId pageId,
            @PathVariable final TableDefinitionId tableDefinitionId,
            @PathVariable final CustomTableDefinitionId customTableDefinitionId,
            @PathVariable final DynamicRowId dynamicRowId,
            @PathVariable final String direction,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            this.getTableFacade().moveRow(dynamicRowId, direction.equals("up") ? -1 : 1);
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/client/" + pageId + "/" + tableDefinitionId;
    }

}
