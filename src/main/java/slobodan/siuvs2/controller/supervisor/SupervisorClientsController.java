package slobodan.siuvs2.controller.supervisor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.UserService;
import slobodan.siuvs2.valueObject.ClientId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import slobodan.siuvs2.facade.TableFacade;
import slobodan.siuvs2.model.Assessment;
import slobodan.siuvs2.model.Distrikt;
import slobodan.siuvs2.model.Mera;
import slobodan.siuvs2.model.Opstina;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.model.Plan;
import slobodan.siuvs2.model.PosebanCilj;
import slobodan.siuvs2.model.Provincija;
import slobodan.siuvs2.model.Rezultat;
import slobodan.siuvs2.model.Roles;
import slobodan.siuvs2.model.SiuvsUserPrincipal;
import slobodan.siuvs2.model.TableDefinition;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.service.AssessmentFactory;
import slobodan.siuvs2.service.AssessmentService;
import slobodan.siuvs2.service.CustomTableDefinitionService;
import slobodan.siuvs2.service.DynamicGroupRowFactory;
import slobodan.siuvs2.service.DynamicGroupRowService;
import slobodan.siuvs2.service.DynamicRowFactory;
import slobodan.siuvs2.service.DynamicRowService;
import slobodan.siuvs2.service.DynamicTableService;
import slobodan.siuvs2.service.OpstinaService;
import slobodan.siuvs2.service.PageService;
import slobodan.siuvs2.service.PhotoService;
import slobodan.siuvs2.service.PlanService;
import slobodan.siuvs2.service.PosebanCiljService;
import slobodan.siuvs2.service.StorageService;
import slobodan.siuvs2.service.TableColumnFactory;
import slobodan.siuvs2.service.TableColumnService;
import slobodan.siuvs2.service.TableDefinitionService;
import slobodan.siuvs2.shared.AssesmentHelper1;
import slobodan.siuvs2.shared.SiuvsException;
import slobodan.siuvs2.valueObject.CustomTableDefinitionId;
import slobodan.siuvs2.valueObject.OpstinaID;
import slobodan.siuvs2.valueObject.PageId;
import slobodan.siuvs2.valueObject.TableDefinitionId;

@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/supervisor")
public class SupervisorClientsController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private UserService userService;
    @Autowired
    private OpstinaService opstinaService;
    @Autowired
    private PageService pageService;
    @Autowired
    private AssessmentService assessmentService;
    @Autowired
    private AssessmentFactory assessmentFactory;
    @Autowired
    private PhotoService photoService;
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
    private TableDefinitionService tableDefinitionService;
    @Autowired
    private CustomTableDefinitionService customTableDefinitionService;
    @Autowired
    private TableColumnFactory tableColumnFactory;
    @Autowired
    private TableColumnService tableColumnService;
    @Autowired
    private PlanService planService;
    @Autowired
    private PosebanCiljService posebanCiljService;

    public List<Mera> makeMeraList(Plan plan) {
        List<Mera> meralist = new ArrayList();
        for (PosebanCilj pc : plan.getChildren()) {
            for (Mera mera : pc.getChildren()) {
                meralist.add(mera);
            }
        }
        return meralist;
    }

    public List<Rezultat> makeRezultatList(Plan plan) {
        List<Rezultat> rezultatlist = new ArrayList();
        for (PosebanCilj pc : plan.getChildren()) {
            for (Mera mera : pc.getChildren()) {
                for (Rezultat rezultat : mera.getChildren()) {
                    rezultatlist.add(rezultat);
                }
            }
        }
        return rezultatlist;
    }

    @GetMapping(value = "/clients")
    public String list(final Model model, @PageableDefault final Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        if (userService.hasRole(user, Roles.DISTRIKT)) {
            Distrikt distrikt = user.getSupervising().getDistrikt();
            List<Opstina> opstina = new ArrayList<Opstina>();
            opstina = opstinaService.findAllByDistriktOrderByNameAsc(distrikt);
            model.addAttribute("clients", clientService.findAllByOpstinaInOrderByNameAsc(opstina, pageable));
        } else {
            if (userService.hasRole(user, Roles.PROVINCE)) {
                Provincija provincija = user.getSupervising().getProvincija();
                List<Opstina> opstina = new ArrayList<Opstina>();
                opstina = opstinaService.findAllByProvincijaOrderByNameAsc(provincija);
                model.addAttribute("clients", clientService.findAllByOpstinaInOrderByNameAsc(opstina, pageable));
            } else {
                model.addAttribute("clients", clientService.findAllOrderByActiveDescNameAsc(pageable));
            }
        }
        return "supervisor/clients";
    }

    @GetMapping(value = "/clients/{clientId}")
    public String view(@PathVariable final ClientId clientId, final Model model) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        return "supervisor/clientview";
    }

    //assesmentview controller
    @GetMapping(value = "/clients/{clientId}/assessment/{pageId}")
    public String assessment(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        Assessment assessment = assessmentService.findOne(client, page);
        if (assessment == null) {
            assessment = assessmentFactory.empty(client, page);
        }
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("assessment", assessment);
        model.addAttribute("photos", photoService.findByClientAndPage(client, page));
        model.addAttribute("vrsta_opasnosti", AssesmentHelper1.getOpasnost(pageId.getValue()));
        return "supervisor/assessmentview";
    }
    //tables list controller
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

    @GetMapping(value = "/clients/{clientId}/{pageId}")
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
            return "redirect:/supervisor/clients";
        }
        return "supervisor/table-list";
    }
//tables controller

    @GetMapping(value = "/clients/{clientId}/{pageId}/{tableDefinitionId}")
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
                return "supervisor/user-defined-list";
            } else {
                this.getTableFacade(clientId).preparePageTableView(pageId, tableDefinitionId, model);
                return "supervisor/table";
            }
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/supervisor/clients/" + clientId + "/" + pageId;
        }
    }

    //custom table controler
    @GetMapping(value = "clients/{clientId}/{pageId}/{tableDefinitionId}/{customTableDefinitionId}")
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
            return "redirect:/supervisor/clients/" + clientId + "/" + pageId;
        }
        return "supervisor/table-custom";
    }

    //plan controller
    @GetMapping(value = "clients/{clientId}/plan/{pageId}")
    public String plan(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        Plan plan = planService.findFirstByClient(client);
        List<PosebanCilj> PClist = posebanCiljService.findAllByClientAndPage(client, page);
        String viewurl = "/supervisor/clients/" + clientId + "/plan/" + pageId;
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("plan", plan);
        model.addAttribute("PClist", PClist);
        model.addAttribute("planurl", viewurl);
        return "supervisor/planview";
    }

    @GetMapping(value = "clients/{clientId}/plan/opstideo/{pageId}")
    public String planOpsti(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        Plan plan = planService.findFirstByClient(client);
        List<PosebanCilj> PClist = posebanCiljService.findAllByClientAndPage(client, page);
        String viewurl = "/supervisor/clients/" + clientId + "/plan/" + pageId;
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("plan", plan);
        model.addAttribute("PClist", PClist);
        model.addAttribute("planurl", viewurl);
        return "supervisor/planopsti";
    }

    @GetMapping(value = "clients/{clientId}/plan/ceo/{pageId}")
    public String planCeo(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        Plan plan = planService.findFirstByClient(client);
        List<PosebanCilj> PClist = posebanCiljService.findAllByPlanOrderByPagePageIdAsc(plan);
        String viewurl = "/supervisor/clients/" + clientId + "/plan/" + pageId;
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("plan", plan);
        model.addAttribute("PClist", PClist);
        model.addAttribute("planurl", viewurl);
        return "supervisor/planopsti";
    }

    //serve photo controller
    @Autowired
    private StorageService storageService;

    @GetMapping(value = "clients/{clientId}/photo/{photoId}")
    public ResponseEntity<Resource> servePhoto(
            @PathVariable final ClientId clientId,
            @PathVariable final OpstinaID photoId
    ) {
        String filename = photoService.findFileNameById(photoId);
        Resource file = storageService.loadAsResource(clientId, filename);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }
}
