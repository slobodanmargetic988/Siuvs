package slobodan.siuvs2.controller.supervisor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import slobodan.siuvs2.facade.TableFacade;
import slobodan.siuvs2.model.Assessment;
import slobodan.siuvs2.model.Calendar;
import slobodan.siuvs2.model.DateKomparator;
import slobodan.siuvs2.model.DetaljiMTS;
import slobodan.siuvs2.model.Distrikt;
import slobodan.siuvs2.model.Dokument;
import slobodan.siuvs2.model.DynamicData;
import slobodan.siuvs2.model.DynamicRow;
import slobodan.siuvs2.model.DynamicTable;
import slobodan.siuvs2.model.InternationalAgreements;
import slobodan.siuvs2.model.Mera;
import slobodan.siuvs2.model.Opstina;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.model.Plan;
import slobodan.siuvs2.model.PodRezultat;
import slobodan.siuvs2.model.PosebanCilj;
import slobodan.siuvs2.model.Provincija;
import slobodan.siuvs2.model.PublicPolicyDocuments;
import slobodan.siuvs2.model.Rezultat;
import slobodan.siuvs2.model.Roles;
import slobodan.siuvs2.model.SiuvsUserPrincipal;
import slobodan.siuvs2.model.TableColumn;
import slobodan.siuvs2.model.TableDefinition;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.service.AssessmentFactory;
import slobodan.siuvs2.service.AssessmentService;
import slobodan.siuvs2.service.CalendarService;
import slobodan.siuvs2.service.CustomTableDefinitionService;
import slobodan.siuvs2.service.DetaljiMTSService;
import slobodan.siuvs2.service.DokumentService;
import slobodan.siuvs2.service.DynamicGroupRowFactory;
import slobodan.siuvs2.service.DynamicGroupRowService;
import slobodan.siuvs2.service.DynamicRowFactory;
import slobodan.siuvs2.service.DynamicRowService;
import slobodan.siuvs2.service.DynamicTableService;
import slobodan.siuvs2.service.InternationalAgreementsService;
import slobodan.siuvs2.service.OpstinaService;
import slobodan.siuvs2.service.PageService;
import slobodan.siuvs2.service.PhotoService;
import slobodan.siuvs2.service.PlanService;
import slobodan.siuvs2.service.PosebanCiljService;
import slobodan.siuvs2.service.PublicPolicyDocumentsService;
import slobodan.siuvs2.service.StorageService;
import slobodan.siuvs2.service.TableColumnFactory;
import slobodan.siuvs2.service.TableColumnService;
import slobodan.siuvs2.service.TableDefinitionService;
import slobodan.siuvs2.shared.AssesmentHelper1;
import slobodan.siuvs2.shared.SiuvsException;
import slobodan.siuvs2.valueObject.CustomTableDefinitionId;
import slobodan.siuvs2.valueObject.DokumentID;
import slobodan.siuvs2.valueObject.InternationalAgreementsID;
import slobodan.siuvs2.valueObject.OpstinaID;
import slobodan.siuvs2.valueObject.PageId;
import slobodan.siuvs2.valueObject.PhotoId;
import slobodan.siuvs2.valueObject.PublicPolicyDocumentsID;
import slobodan.siuvs2.valueObject.TableColumnId;
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
     @Autowired
    private DokumentService dokumentService;
      @Autowired
    private PublicPolicyDocumentsService PPDService;
    @Autowired
    private InternationalAgreementsService IAService;

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
            if (opstina!=null){
            model.addAttribute("clients", clientService.findAllByOpstinaInOrderByNameAsc(opstina, pageable));
        }
        } else {
            if (userService.hasRole(user, Roles.PROVINCE)) {
                Provincija provincija = user.getSupervising().getProvincija();
                List<Opstina> opstina = new ArrayList<Opstina>();
                opstina = opstinaService.findAllByProvincijaOrderByNameAsc(provincija);
                 if (opstina!=null){
                model.addAttribute("clients", clientService.findAllByOpstinaInOrderByNameAsc(opstina, pageable));
                }
            } else {
                model.addAttribute("clients", clientService.findAllOrderByActiveDescNameAsc(pageable));
            }
        }
  model.addAttribute("allclients",clientService.findAllByOrderByNameAsc());
  
     LocalDate currentDate = LocalDate.now();
        model.addAttribute("currentDate",currentDate);
        DateKomparator komparator= new DateKomparator();
        model.addAttribute("komparator",komparator);
  
        return "supervisor/clients";
    }

    @GetMapping(value = "/clients/{clientId}")
    public String view(@PathVariable final ClientId clientId, final Model model) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        PageId pageId;
        pageId=new PageId(1);//using pageid=1 to store photos for client page
        Page page = pageService.findOne(pageId);
        model.addAttribute("photos", photoService.findByClientAndPage(client, page));
         List<Dokument> dokumentlist= new ArrayList();
         dokumentlist=dokumentService.findAllByClientId(client);
         List<PublicPolicyDocuments> PPDlist= new ArrayList();
         List<InternationalAgreements> IAlist= new ArrayList();
      
        for (Dokument i : dokumentlist) {
        if (i.getIa()!=null){IAlist.add(i.getIa());}
        else{PPDlist.add(i.getPpd());
        }
        }
          model.addAttribute("docPPDlist", PPDlist);
          model.addAttribute("docIAlist", IAlist);
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
        //sume
        List<Long> PCbudzetJls= new ArrayList();;
        List<Long>PCbudzetOstalo= new ArrayList();;
        List<Long>PCbudzetNeobezbedjeno= new ArrayList();;
        
        calculateSUMList(PClist,PCbudzetJls,PCbudzetOstalo,PCbudzetNeobezbedjeno);
        
        Long TotalbudzetJls=calculateTotals(PCbudzetJls);
        Long TotalbudzetOstalo=calculateTotals(PCbudzetOstalo);
        Long TotalbudzetNeobezbedjeno=calculateTotals(PCbudzetNeobezbedjeno);
        model.addAttribute("PCbudzetJls", PCbudzetJls);
        model.addAttribute("PCbudzetOstalo", PCbudzetOstalo);
        model.addAttribute("PCbudzetNeobezbedjeno", PCbudzetNeobezbedjeno);
        model.addAttribute("TotalbudzetJls", TotalbudzetJls);
        model.addAttribute("TotalbudzetOstalo", TotalbudzetOstalo);
        model.addAttribute("TotalbudzetNeobezbedjeno", TotalbudzetNeobezbedjeno);
        model.addAttribute("SumaLabel", "Укупно финансијска средства за изабрану опасност");
        //sume
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
        model.addAttribute("ceoplan", false);
        //sume
        List<Long> PCbudzetJls= new ArrayList();;
        List<Long>PCbudzetOstalo= new ArrayList();;
        List<Long>PCbudzetNeobezbedjeno= new ArrayList();;
        
        calculateSUMList(PClist,PCbudzetJls,PCbudzetOstalo,PCbudzetNeobezbedjeno);
        
        Long TotalbudzetJls=calculateTotals(PCbudzetJls);
        Long TotalbudzetOstalo=calculateTotals(PCbudzetOstalo);
        Long TotalbudzetNeobezbedjeno=calculateTotals(PCbudzetNeobezbedjeno);
        model.addAttribute("PCbudzetJls", PCbudzetJls);
        model.addAttribute("PCbudzetOstalo", PCbudzetOstalo);
        model.addAttribute("PCbudzetNeobezbedjeno", PCbudzetNeobezbedjeno);
        model.addAttribute("TotalbudzetJls", TotalbudzetJls);
        model.addAttribute("TotalbudzetOstalo", TotalbudzetOstalo);
        model.addAttribute("TotalbudzetNeobezbedjeno", TotalbudzetNeobezbedjeno);
        model.addAttribute("SumaLabel", "Укупно финансијска средства за цео план");
        //sume
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
         List<PosebanCilj> PClistK1 = posebanCiljService.findAllByClientAndPageAndKomponenta(client, page,1);
         List<PosebanCilj> PClistK2 = posebanCiljService.findAllByClientAndPageAndKomponenta(client, page,2);
          List<PosebanCilj> PClistK3 = posebanCiljService.findAllByClientAndPageAndKomponenta(client, page,3);
           List<PosebanCilj> PClistK4 = posebanCiljService.findAllByClientAndPageAndKomponenta(client, page,4);
        String viewurl = "/supervisor/clients/" + clientId + "/plan/" + pageId;
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("plan", plan);
        model.addAttribute("PClist", PClist);
        
        model.addAttribute("PClistK1", PClistK1);
        model.addAttribute("PClistK2", PClistK2);
        model.addAttribute("PClistK3", PClistK3);
        model.addAttribute("PClistK4", PClistK4);
        model.addAttribute("planurl", viewurl);
        model.addAttribute("ceoplan", true);
        //sume
        List<Long> PCbudzetJls= new ArrayList();;
        List<Long>PCbudzetOstalo= new ArrayList();;
        List<Long>PCbudzetNeobezbedjeno= new ArrayList();;
        
        calculateSUMList(PClist,PCbudzetJls,PCbudzetOstalo,PCbudzetNeobezbedjeno);
        
        Long TotalbudzetJls=calculateTotals(PCbudzetJls);
        Long TotalbudzetOstalo=calculateTotals(PCbudzetOstalo);
        Long TotalbudzetNeobezbedjeno=calculateTotals(PCbudzetNeobezbedjeno);
        model.addAttribute("PCbudzetJls", PCbudzetJls);
        model.addAttribute("PCbudzetOstalo", PCbudzetOstalo);
        model.addAttribute("PCbudzetNeobezbedjeno", PCbudzetNeobezbedjeno);
        model.addAttribute("TotalbudzetJls", TotalbudzetJls);
        model.addAttribute("TotalbudzetOstalo", TotalbudzetOstalo);
        model.addAttribute("TotalbudzetNeobezbedjeno", TotalbudzetNeobezbedjeno);
        model.addAttribute("SumaLabel", "Укупно финансијска средства за цео план");
        //sume
        return "supervisor/planopsti";
    }
private void calculateSUMList(List<PosebanCilj> PClist, List<Long> PCbudzetJls, List<Long> PCbudzetOstalo, List<Long> PCbudzetNeobezbedjeno) {
        int i = 0;
        Long middleStep;
        for (PosebanCilj pc : PClist) {
            PCbudzetJls.add(0L);
            PCbudzetOstalo.add(0L);
            PCbudzetNeobezbedjeno.add(0L);
            for (Mera mera : pc.getChildren()) {
                for (Rezultat rezultat : mera.getChildren()) {
                    for (PodRezultat podRezultat : rezultat.getChildren()) {
                        middleStep = PCbudzetJls.get(i) + podRezultat.getBudzetJls();
                        PCbudzetJls.set(i, middleStep);
                        middleStep = PCbudzetOstalo.get(i) + podRezultat.getBudzetOstalo();
                        PCbudzetOstalo.set(i, middleStep);
                        middleStep = PCbudzetNeobezbedjeno.get(i) + podRezultat.getBudzetNeobezbedjeno();
                        PCbudzetNeobezbedjeno.set(i, middleStep);
                    }
                }
            }
            i++;
        }
    }
        private Long calculateTotals(List<Long> longList) {
            Long total=0L;
for (Long longItem : longList) {
total+=longItem;
    }
return total;
        }
    //serve photo controller
    @Autowired
    private StorageService storageService;

    @GetMapping(value = "clients/{clientId}/photo/{photoId}")
    public ResponseEntity<Resource> servePhoto(
            @PathVariable final ClientId clientId,
            @PathVariable final PhotoId photoId
    ) {
        String filename = photoService.findFileNameById(photoId);
        Resource file = storageService.loadAsResource(clientId, filename);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }
    @RequestMapping(value = "/clients/{clientId}/downloadPPD/{PPDid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public StreamingResponseBody getSteamingFile(
            @PathVariable final ClientId clientId,
            @PathVariable final PublicPolicyDocumentsID PPDid,
            HttpServletResponse response,
            final RedirectAttributes redirectAttributes
    ) throws IOException {
        try {
        PublicPolicyDocuments PPD = PPDService.findOne(PPDid);
        Dokument dokument = dokumentService.findByPpd(PPD);
       /* if(dokument==null){
        redirectAttributes.addFlashAttribute("errorMessage","Тражени документ није уплоадован");
                return null;
        }*/
        DokumentID dokumentId = new DokumentID(dokument.getId());
        String filename = dokumentService.findFileNameById(dokumentId);
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + dokument.getTitle() + filename.substring(filename.lastIndexOf(".")) + "\"");
        InputStream inputStream = new FileInputStream(new File(storageService.load(clientId, filename).toString()));

        return outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, nRead);
            }
            inputStream.close();
        };
        } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return null;
            }
    }
        @RequestMapping(value = "/clients/{clientId}/downloadIA/{IAid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public StreamingResponseBody getSteamingFile(
            @PathVariable final ClientId clientId,
            @PathVariable final InternationalAgreementsID IAid,
            HttpServletResponse response,
            final RedirectAttributes redirectAttributes
    ) throws IOException {
        try {
        InternationalAgreements IA = IAService.findOne(IAid);
        Dokument dokument = dokumentService.findByIa(IA);
        DokumentID dokumentId = new DokumentID(dokument.getId());
        String filename = dokumentService.findFileNameById(dokumentId);
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + dokument.getTitle() + filename.substring(filename.lastIndexOf(".")) + "\"");
        InputStream inputStream = new FileInputStream(new File(storageService.load(clientId, filename).toString()));

        return outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, nRead);
            }
            inputStream.close();
        };
        } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return null;
            }
    }
    @Autowired
     CalendarService calendarService;
    
    @GetMapping(value = "/clients/{clientId}/calendar")
    public String list(@PathVariable final ClientId clientId,
            final Model model) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        List<Calendar> calendarList = calendarService.findAllByClient(client);
        Calendar calendar1 = new Calendar();
        Calendar calendar2 = new Calendar();
        Calendar calendar3 = new Calendar();
        Calendar calendar4 = new Calendar();

        if (!calendarList.isEmpty()) {
            for (Calendar calendar : calendarList) {
                if (calendar.getDokument().equals("Процена ризика")) {
                    calendar1 = calendar;
                } else {
                    if (calendar.getDokument().equals("План заштите и спасавања")) {
                        calendar2 = calendar;
                    } else {
                        if (calendar.getDokument().equals("План смањења ризика")) {
                            calendar3 = calendar;
                        } else {
                            if (calendar.getDokument().equals("Оперативни план за одбрану од поплава")) {
                                calendar4 = calendar;
                            }

                        }

                    }

                }
            }
            model.addAttribute("calendar1", calendar1);
            model.addAttribute("calendar2", calendar2);
            model.addAttribute("calendar3", calendar3);
            model.addAttribute("calendar4", calendar4);

        } else {
            model.addAttribute("calendarprazan", "Нису унети детаљи везани за период важења за ниједан документ.");

        }

        return "supervisor/calendar";
    }
    
    
    
    
    
    @GetMapping(value = "/clients/{clientId}/zbirniObrasci")
  
    public String adminZbirniObrasciJedinstveniPregled(
            @PathVariable final ClientId clientId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);

/////////////////////////////////////////////////////////////////SEGMENT ZA SIRENE
        TableDefinitionId tableDefinitionIdSirene = new TableDefinitionId(6002);
        DynamicTable tabelaSirene = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionIdSirene, client); //sirene 6002
        TableColumn vrstaSirene = new TableColumn();
        Integer elektronskeSirene = 0;
        Integer pneumatskeSirene = 0;
        Integer elektricneSirene = 0;
        for (TableColumn kolona : tabelaSirene.getTableDefinition().getColumns(client)) {//trazimo kolonu koja nas zanima i cuvamo je za dalju referencu
            if (kolona.getTitle().equalsIgnoreCase("Врста сирене")) {
                vrstaSirene = kolona;
            }
        }

        for (DynamicRow red : tabelaSirene.getRows()) {//prolazimo kroz sve redove tabele da bi sumirali vrednosti
            for (DynamicData data : red.getData()) {   //prolazimo kroz svaki unos u svakom redu da proverimo da li je to onaj unos koji nas zanima
                if (data.getColumn().equals(vrstaSirene)) {

                    if (data.getValue().equals("600022")) {
                        elektricneSirene++;
                    }// cuvamo broj razlicitih unosa

                    if (data.getValue().equals("600021")) {
                        elektronskeSirene++;
                    }

                    if (data.getValue().equals("600023")) {
                        pneumatskeSirene++;
                    }

                };
            }

        }
        model.addAttribute("elektronskeSirene", elektronskeSirene);
        model.addAttribute("pneumatskeSirene", pneumatskeSirene);
        model.addAttribute("elektricneSirene", elektricneSirene);
/////////////////////////////////////////////////////////////////SEGMENT ZA SIRENE

  
        /////////////////////////////////////////////////////////////////SEGMENT ZA POVERENIKE CIVILNE ZASTITE
        TableDefinitionId tableDefinitionIdPoverenici = new TableDefinitionId(6003);
        DynamicTable tabelaPoverenici = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionIdPoverenici, client); //poverenici 6003
        TableColumn povereniciKolona1 = tableColumnService.findOne(new TableColumnId(60032));
        TableColumn povereniciKolona2 = tableColumnService.findOne(new TableColumnId(60033));
        TableColumn povereniciKolona3 = tableColumnService.findOne(new TableColumnId(60034));
        TableColumn povereniciKolona4 = tableColumnService.findOne(new TableColumnId(60035));
        TableColumn povereniciKolona5 = tableColumnService.findOne(new TableColumnId(60036));
        TableColumn povereniciKolona6 = tableColumnService.findOne(new TableColumnId(60038));
        TableColumn povereniciKolona7 = tableColumnService.findOne(new TableColumnId(60039));
        Integer povereniciBrojac1 = 0;
        Integer povereniciBrojac2 = 0;
        Integer povereniciBrojac3 = 0;
        Integer povereniciBrojac4 = 0;
        Integer povereniciBrojac5 = 0;
        Integer povereniciBrojac6 = 0;
        Integer povereniciBrojac7 = 0;
        //   Integer        povereniciBrojacSuma=0;

        for (DynamicRow red : tabelaPoverenici.getRows()) {//prolazimo kroz sve redove tabele da bi sumirali vrednosti
            for (DynamicData data : red.getData()) {
                try {
                    //prolazimo kroz svaki unos u svakom redu da proverimo da li je to onaj unos koji nas zanima
                    if (data.getColumn().equals(povereniciKolona1)) {
                        povereniciBrojac1 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().equals(povereniciKolona2)) {
                        povereniciBrojac2 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().equals(povereniciKolona3)) {
                        povereniciBrojac3 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().equals(povereniciKolona4)) {
                        povereniciBrojac4 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().equals(povereniciKolona5)) {
                        povereniciBrojac5 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().equals(povereniciKolona6)) {
                        povereniciBrojac6 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().equals(povereniciKolona7)) {
                        povereniciBrojac7 += Integer.parseInt(data.getValue());
                    };
                } catch (Exception e) {
                    System.out.println("neki input nije brojka");
                }
            }

        }
        model.addAttribute("povereniciBrojac1", povereniciBrojac1);
        model.addAttribute("povereniciBrojac2", povereniciBrojac2);
        model.addAttribute("povereniciBrojac3", povereniciBrojac3);
        model.addAttribute("povereniciBrojac4", povereniciBrojac4);
        model.addAttribute("povereniciBrojac5", povereniciBrojac5);
        model.addAttribute("povereniciBrojac6", povereniciBrojac6);
        model.addAttribute("povereniciBrojac7", povereniciBrojac7);
        model.addAttribute("povereniciBrojacSuma", povereniciBrojac1 + povereniciBrojac2 + povereniciBrojac3 + povereniciBrojac4 + povereniciBrojac5 + povereniciBrojac6);

/////////////////////////////////////////////////////////////////SEGMENT ZA POVERENIKE CIVILNE ZASTITE  

/////////////////////////////////////////////////////////////////SEGMENT ZA JEDINICE CIVILNE ZASTITE  
        TableDefinitionId tableDefinitionIdJedinice = new TableDefinitionId(6004);
        DynamicTable tabelaJedinice = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionIdJedinice, client); //poverenici 6003
       TableColumn vrstaJedinice = tableColumnService.findOne(new TableColumnId(60042));
        TableColumn jediniceKolona2 = tableColumnService.findOne(new TableColumnId(60044));
        TableColumn jediniceKolona3 = tableColumnService.findOne(new TableColumnId(60045));
        TableColumn jediniceKolona4 = tableColumnService.findOne(new TableColumnId(60046));
        TableColumn jediniceKolona5 = tableColumnService.findOne(new TableColumnId(60047));
        
        

        Integer jediniceBrojac1 = 0;
        Integer jediniceBrojac2 = 0;
        Integer jediniceBrojac3 = 0;
        Integer jediniceBrojac4 = 0;
        Integer jediniceBrojac5 = 0;
        Integer jediniceBrojac6 = 0;
        Integer jediniceBrojac7 = 0;
        Integer jediniceBrojac8 = 0;
        Integer jediniceBrojac9 = 0;
        Integer jediniceBrojac10 = 0;
        
                for (DynamicRow red : tabelaJedinice.getRows()) {//prolazimo kroz sve redove tabele da bi sumirali vrednosti
            for (DynamicData data : red.getData()) {
                try {
                    //prolazimo kroz svaki unos u svakom redu da proverimo da li je to onaj unos koji nas zanima
                    if (data.getColumn().equals(vrstaJedinice)) {

                    if (data.getValue().equals("600041")) {
                        jediniceBrojac1++;
                          for (DynamicData data2prolaz : red.getData()) {//drugi put prolazimo kroz red kad znamo koji je tip jedinice da saberemo ostale kolone
                          if (data2prolaz.getColumn().equals(jediniceKolona2)) {
                             jediniceBrojac2+=Integer.parseInt(data2prolaz.getValue()); 
                          }
                              if (data2prolaz.getColumn().equals(jediniceKolona3)) {
                             jediniceBrojac3+=Integer.parseInt(data2prolaz.getValue()); 
                          }
                                 if (data2prolaz.getColumn().equals(jediniceKolona4)) {
                             jediniceBrojac4+=Integer.parseInt(data2prolaz.getValue()); 
                          }
                                    if (data2prolaz.getColumn().equals(jediniceKolona5)) {
                             jediniceBrojac5+=Integer.parseInt(data2prolaz.getValue()); 
                          }
                          }
                        
                    }// cuvamo broj razlicitih unosa

                    if (data.getValue().equals("600042")) {
                        jediniceBrojac6++;
                                   for (DynamicData data2prolaz : red.getData()) {//drugi put prolazimo kroz red kad znamo koji je tip jedinice da saberemo ostale kolone
                          if (data2prolaz.getColumn().equals(jediniceKolona2)) {
                             jediniceBrojac7+=Integer.parseInt(data2prolaz.getValue()); 
                          }
                              if (data2prolaz.getColumn().equals(jediniceKolona3)) {
                             jediniceBrojac8+=Integer.parseInt(data2prolaz.getValue()); 
                          }
                                 if (data2prolaz.getColumn().equals(jediniceKolona4)) {
                             jediniceBrojac9+=Integer.parseInt(data2prolaz.getValue()); 
                          }
                                    if (data2prolaz.getColumn().equals(jediniceKolona5)) {
                             jediniceBrojac10+=Integer.parseInt(data2prolaz.getValue()); 
                          }
                          }
                    }
                };
                } catch (Exception e) {
                    System.out.println("neki input nije brojka");
                }
            }

        }
        model.addAttribute("jediniceBrojac1", jediniceBrojac1);
        model.addAttribute("jediniceBrojac2", jediniceBrojac2);
        model.addAttribute("jediniceBrojac3", jediniceBrojac3);
        model.addAttribute("jediniceBrojac4", jediniceBrojac4);
        model.addAttribute("jediniceBrojac5", jediniceBrojac5);
        model.addAttribute("jediniceBrojac6", jediniceBrojac6);
        model.addAttribute("jediniceBrojac7", jediniceBrojac7);
        model.addAttribute("jediniceBrojac8", jediniceBrojac8);
        model.addAttribute("jediniceBrojac9", jediniceBrojac9);
        model.addAttribute("jediniceBrojac10", jediniceBrojac10);
        
/////////////////////////////////////////////////////////////////SEGMENT ZA JEDINICE CIVILNE ZASTITE  

/////////////////////////////////////////////////////////////////SEGMENT ZA SITUACIONE CENTRE
  TableDefinitionId tableDefinitionIdSCentar = new TableDefinitionId(6001);
        DynamicTable tabelaSCentar = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionIdSCentar, client);
        model.addAttribute("scentarRedovi", tabelaSCentar.getRows());
        
        
        TableColumn csentarKolona1 = tableColumnService.findOne(new TableColumnId(60011));
        TableColumn csentarKolona2 = tableColumnService.findOne(new TableColumnId(60012));
        TableColumn csentarKolona3 = tableColumnService.findOne(new TableColumnId(60013));
        TableColumn csentarKolona4 = tableColumnService.findOne(new TableColumnId(60014));
         TableColumn csentarKolona5 = tableColumnService.findOne(new TableColumnId(60015));
     
       
        model.addAttribute("scentar1", csentarKolona1);
        model.addAttribute("scentar2", csentarKolona2);
        model.addAttribute("scentar3", csentarKolona3);
        model.addAttribute("scentar4", csentarKolona4);
        model.addAttribute("scentar5", csentarKolona5);
/////////////////////////////////////////////////////////////////SEGMENT ZA SITUACIONE CENTRE


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////SEGMENT ZA MERE CIVILNE ZASTITE
     Integer evakuacija1=0;
        Integer evakuacija2=0;
        Integer evakuacija3=0;
        Integer evakuacija4=0;
        Integer evakuacija5=0;
        Integer evakuacija6=0;
       
         Integer zbirnjavanje1=0;
         Integer zbirnjavanje2=0;
         Integer zbirnjavanje3=0;
         Integer zbirnjavanje4=0;
         Integer zbirnjavanje5=0;
         Integer zbirnjavanje6=0;
         Integer zbirnjavanje7=0;
         
         Integer prvaPomoc1=0;
         Integer prvaPomoc2=0;
         Integer prvaPomoc3=0;
         Integer prvaPomoc4=0;
         Integer prvaPomoc5=0;
         Integer prvaPomoc6=0;
         Integer prvaPomoc7=0;
         Integer prvaPomoc8=0;
         Integer prvaPomoc9=0;
         Integer prvaPomoc10=0;
         Integer prvaPomoc11=0;
         Integer prvaPomoc12=0;
         
         Integer asanacija1=0;
         Integer asanacija2=0;
         Integer asanacija3=0;
         Integer asanacija4=0;
         Integer asanacija5=0;

/////////////////////////////////////////////////////////////////SEGMENT ZA EVAKUACIJU
TableDefinitionId tableDefinitionIdEvakuacija = new TableDefinitionId(5001);
DynamicTable tabelaEvakuacija = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionIdEvakuacija, client);
        for (DynamicRow red : tabelaEvakuacija.getRows()) {//prolazimo kroz sve redove tabele da bi sumirali vrednosti
            for (DynamicData data : red.getData()) {
                try {
                    //prolazimo kroz svaki unos u svakom redu da proverimo da li je to onaj unos koji nas zanima
                    if (data.getColumn().getOrder()==14) {
                        evakuacija1 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==15) {
                        evakuacija2 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==16) {
                        evakuacija3 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==17) {
                        evakuacija4 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==18) {
                        evakuacija5 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==19) {
                        evakuacija6 += Integer.parseInt(data.getValue());
                    };
                  
                } catch (Exception e) {
                    System.out.println("neki input nije brojka");
                }
            }

        }

model.addAttribute("evakuacija1", evakuacija1);
model.addAttribute("evakuacija2", evakuacija2);
model.addAttribute("evakuacija3", evakuacija3);
model.addAttribute("evakuacija4", evakuacija4);
model.addAttribute("evakuacija5", evakuacija5);
model.addAttribute("evakuacija6", evakuacija6);


/////////////////////////////////////////////////////////////////SEGMENT ZA EVAKUACIJU
/////////////////////////////////////////////////////////////////SEGMENT ZA ZBRINJAVANJE
  TableDefinitionId tableDefinitionIdZbrinjavanje = new TableDefinitionId(5002);
        DynamicTable tabelaZbrinjavanje = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionIdZbrinjavanje, client);


for (DynamicRow red : tabelaZbrinjavanje.getRows()) {//prolazimo kroz sve redove tabele da bi sumirali vrednosti
            for (DynamicData data : red.getData()) {
                try {
                    //prolazimo kroz svaki unos u svakom redu da proverimo da li je to onaj unos koji nas zanima
                    if (data.getColumn().getOrder()==14) {
                        zbirnjavanje1 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==15) {
                        zbirnjavanje2 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==16) {
                        zbirnjavanje3 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==17) {
                        zbirnjavanje4 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==18) {
                        zbirnjavanje5 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==19) {
                        zbirnjavanje6 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==20) {
                        zbirnjavanje7 += Integer.parseInt(data.getValue());
                    };
                } catch (Exception e) {
                    System.out.println("neki input nije brojka");
                }
            }

        }

model.addAttribute("zbirnjavanje1", zbirnjavanje1);
model.addAttribute("zbirnjavanje2", zbirnjavanje2);
model.addAttribute("zbirnjavanje3", zbirnjavanje3);
model.addAttribute("zbirnjavanje4", zbirnjavanje4);
model.addAttribute("zbirnjavanje5", zbirnjavanje5);
model.addAttribute("zbirnjavanje6", zbirnjavanje6);
model.addAttribute("zbirnjavanje7", zbirnjavanje7);
/////////////////////////////////////////////////////////////////SEGMENT ZA ZBRINJAVANJE
/////////////////////////////////////////////////////////////////SEGMENT ZA PRVU POMOC

  TableDefinitionId tableDefinitionIdPrvaPomoc = new TableDefinitionId(5003);
        DynamicTable tabelaPrvaPomoc = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionIdPrvaPomoc, client);


for (DynamicRow red : tabelaPrvaPomoc.getRows()) {//prolazimo kroz sve redove tabele da bi sumirali vrednosti
            for (DynamicData data : red.getData()) {
                try {
//prolazimo kroz svaki unos u svakom redu da proverimo da li je to onaj unos koji nas zanima
if (data.getColumn().getOrder()==14) {
prvaPomoc1 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==15) {
prvaPomoc2 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==16) {
prvaPomoc3 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==17) {
prvaPomoc4 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==18) {
prvaPomoc5 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==19) {
prvaPomoc6 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==20) {
prvaPomoc7 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==21) {
prvaPomoc8 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==22) {
prvaPomoc9 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==23) {
prvaPomoc10 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==24) {
prvaPomoc11 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==25) {
prvaPomoc12 += Integer.parseInt(data.getValue());
};

                } catch (Exception e) {
                    System.out.println("neki input nije brojka");
                }
            }

        }

model.addAttribute("prvaPomoc1", prvaPomoc1);
model.addAttribute("prvaPomoc2", prvaPomoc2);
model.addAttribute("prvaPomoc3", prvaPomoc3);
model.addAttribute("prvaPomoc4", prvaPomoc4);
model.addAttribute("prvaPomoc5", prvaPomoc5);
model.addAttribute("prvaPomoc6", prvaPomoc6);
model.addAttribute("prvaPomoc7", prvaPomoc7);
model.addAttribute("prvaPomoc8", prvaPomoc8);
model.addAttribute("prvaPomoc9", prvaPomoc9);
model.addAttribute("prvaPomoc10", prvaPomoc10);
model.addAttribute("prvaPomoc11", prvaPomoc11);
model.addAttribute("prvaPomoc12", prvaPomoc12);


/////////////////////////////////////////////////////////////////SEGMENT ZA PRVU POMOC
/////////////////////////////////////////////////////////////////SEGMENT ZA ASANACIJU
 TableDefinitionId tableDefinitionIdAsanacija = new TableDefinitionId(5004);
        DynamicTable tabelaAsanacija = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionIdAsanacija, client);


for (DynamicRow red : tabelaAsanacija.getRows()) {//prolazimo kroz sve redove tabele da bi sumirali vrednosti
            for (DynamicData data : red.getData()) {
                try {
//prolazimo kroz svaki unos u svakom redu da proverimo da li je to onaj unos koji nas zanima
if (data.getColumn().getOrder()==14) {
asanacija1 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==15) {
asanacija2 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==16) {
asanacija3 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==17) {
asanacija4 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==18) {
asanacija5 += Integer.parseInt(data.getValue());
};

                } catch (Exception e) {
                    System.out.println("neki input nije brojka");
                }
            }

        }

model.addAttribute("asanacija1", asanacija1);
model.addAttribute("asanacija2", asanacija2);
model.addAttribute("asanacija3", asanacija3);
model.addAttribute("asanacija4", asanacija4);
model.addAttribute("asanacija5", asanacija5);

/////////////////////////////////////////////////////////////////SEGMENT ZA ASANACIJU

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////SEGMENT ZA MERE CIVILNE ZASTITE
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////SEGMENT ZA MATERIJALNO TEHNICKA SREDSTVA

        List<DetaljiMTS> listaKartonaMTS = detaljiMTSService.findAllByClient(client);
        model.addAttribute("listaKartonaMTS", listaKartonaMTS);
       
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////SEGMENT ZA MATERIJALNO TEHNICKA SREDSTVA

        return "supervisor/jedinstveniPregledZbirnihObrazaca";
    }
    
    @Autowired
    private DetaljiMTSService detaljiMTSService;
}
