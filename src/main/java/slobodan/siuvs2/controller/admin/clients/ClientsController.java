package slobodan.siuvs2.controller.admin.clients;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.valueObject.ClientId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import slobodan.siuvs2.model.Distrikt;
import slobodan.siuvs2.model.Dokument;
import slobodan.siuvs2.model.InternationalAgreements;
import slobodan.siuvs2.model.Opstina;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.model.Provincija;
import slobodan.siuvs2.model.PublicPolicyDocuments;
import slobodan.siuvs2.model.Tasks;
import slobodan.siuvs2.service.DistriktService;
import slobodan.siuvs2.service.DokumentService;
import slobodan.siuvs2.service.InternationalAgreementsFactory;
import slobodan.siuvs2.service.InternationalAgreementsService;
import slobodan.siuvs2.service.OpstinaService;
import slobodan.siuvs2.service.PageService;
import slobodan.siuvs2.service.PhotoService;
import slobodan.siuvs2.service.ProvincijaService;
import slobodan.siuvs2.service.PublicPolicyDocumentsFactory;
import slobodan.siuvs2.service.PublicPolicyDocumentsService;
import slobodan.siuvs2.service.StorageService;
import slobodan.siuvs2.service.TasksFactory;
import slobodan.siuvs2.service.TasksService;
import slobodan.siuvs2.valueObject.DokumentID;
import slobodan.siuvs2.valueObject.InternationalAgreementsID;
import slobodan.siuvs2.valueObject.OpstinaID;
import slobodan.siuvs2.valueObject.PageId;
import slobodan.siuvs2.valueObject.PhotoId;
import slobodan.siuvs2.valueObject.PublicPolicyDocumentsID;
import slobodan.siuvs2.valueObject.TasksID;

@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/admin")
public class ClientsController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private OpstinaService opstinaService;
    @Autowired
    private ProvincijaService provincijaService;
    @Autowired
    private DistriktService distriktService;
    @Autowired
    private TasksService tasksService;
    @Autowired
    private PublicPolicyDocumentsService PPDService;
    @Autowired
    private InternationalAgreementsService IAService;
    @Autowired
    private TasksFactory tasksFactory;
    @Autowired
    private PublicPolicyDocumentsFactory PPDFactory;
    @Autowired
    private InternationalAgreementsFactory IAFactory;
    @Autowired
    private PageService pageService;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private DokumentService dokumentService;

    @ModelAttribute("newClient")
    public Client getClient() {
        return new Client();
    }

    @GetMapping(value = "/clients")
    public String list(final Model model, @PageableDefault final Pageable pageable) {
        model.addAttribute("clients", clientService.findAllOrderByActiveDescNameAsc(pageable));
        return "admin/clients/clients";
    }

    @GetMapping(value = "/clients/new")
    public String createForm(final Model model) {
        List<Opstina> listaopstina = new ArrayList<Opstina>();
        listaopstina = opstinaService.findAllOrderByNameAsc();
        model.addAttribute("listaopstina", listaopstina);
        return "admin/clients/new";
    }

    @PostMapping(value = "/clients/new")
    public String create(
            @RequestParam(name = "LSG") OpstinaID opstina,
            @RequestParam(name = "name") String name,
            final RedirectAttributes redirectAttributes
    ) {
        if (opstina.getValue() == 0) {
            redirectAttributes.addFlashAttribute("LSGErrorMessage", "Морате одабрати ниво приступа");
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом креирања корисника!");
            return "redirect:/admin/clients/new";
        } else {
            if (name.isEmpty()) {
                redirectAttributes.addFlashAttribute("NameErrorMessage", "Морате унети име корисника");
                redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом креирања корисника!");
                return "redirect:/admin/clients/new";
            }
        }

        if (clientService.isNameUsed(name)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Корисник са истим називом већ постоји!");
            return "redirect:/admin/clients/new";
        } else {

        
            Client newClient = new Client();
            newClient.setName(name);
            newClient.setOpstina(opstinaService.findOne(opstina));
            clientService.createNew(newClient);

            redirectAttributes.addFlashAttribute("successMessage", "Корисник успешно креиран!");
            return "redirect:/admin/clients";
        }

    }

    @GetMapping(value = "/clients/{clientId}")
    public String view(@PathVariable final ClientId clientId, final Model model) {
        Client client = clientService.findOne(clientId);
        PageId pageId;
        pageId = new PageId(1);//using pageid=1 to store photos for client page
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
        model.addAttribute("client", client);

        return "admin/clients/view";
    }

    @GetMapping(value = "/clients/{clientId}/edit")
    public String edit(
            @PathVariable final ClientId clientId,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        List<Opstina> opstine = new ArrayList();
        opstine = opstinaService.findAllOrderByNameAsc();
        List<Distrikt> districts = new ArrayList();
        districts = distriktService.findAllOrderByNameAsc();
        List<Provincija> provincije = new ArrayList();
        provincije = provincijaService.findAllOrderByNameAsc();

        model.addAttribute("editClient", client);
        model.addAttribute("opstine", opstine);
        model.addAttribute("districts", districts);
        model.addAttribute("provincije", provincije);
        PageId pageId;
        pageId = new PageId(1);//using pageid=1 to store photos for client page
        Page page = pageService.findOne(pageId);
        model.addAttribute("photos", photoService.findByClientAndPage(client, page));

        return "admin/clients/edit";
    }

    @PostMapping(value = "/clients/{clientId}/edit")
    public String update(
            @PathVariable final ClientId clientId,
            @RequestParam(name = "opstinaId") OpstinaID opstinaId,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "aktivan", required = false) boolean aktivan,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        if (opstinaId.getValue() == 0) {
            redirectAttributes.addFlashAttribute("LSGErrorMessage", "Морате одабрати ниво приступа");
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом креирања корисника!");
            return "redirect:/admin/clients/{clientId}/edit";
        } else {
            if (name.isEmpty()) {
                redirectAttributes.addFlashAttribute("NameErrorMessage", "Морате унети име корисника");
                redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом креирања корисника!");
                return "redirect:/admin/clients/{clientId}/edit";
            } else {
                if (clientService.isNameUsed(clientId, name)) {
                    redirectAttributes.addFlashAttribute("errorMessage", "Корисник са истим називом већ постоји!");
                    return "redirect:/admin/clients/" + clientId + "/edit";
                }
            }

            Client client = clientService.findOne(clientId);
            client.setName(name);
            if (aktivan == true) {
                client.setActive(true);
            } else {
                client.setActive(false);
            }
            client.setOpstina(opstinaService.findOne(opstinaId));
            try {
                clientService.update(clientId, client);
                redirectAttributes.addFlashAttribute("successMessage", "Корисник успешно измењен!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/admin/clients/" + clientId + "/edit";
            }
            return "redirect:/admin/clients/" + clientId;
        }
    }

    @GetMapping(value = "/clients/{clientId}/newNadleznost")
    public String newNadleznost(@PathVariable final ClientId clientId, final Model model) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        return "admin/clients/newNadleznost";
    }

    @GetMapping(value = "/clients/{clientId}/newIA")
    public String newIA(@PathVariable final ClientId clientId, final Model model) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        return "admin/clients/newIA";
    }

    @GetMapping(value = "/clients/{clientId}/newPPD")
    public String newPPD(@PathVariable final ClientId clientId, final Model model) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        return "admin/clients/newPPD";
    }

    @PostMapping(value = "/clients/{clientId}/newNadleznost")
    public String savenewNadleznost(@PathVariable final ClientId clientId,
            @RequestParam(name = "name") String name,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Client client = clientService.findOne(clientId);
        Tasks task = tasksFactory.empty(name);
        try {
            tasksService.save(task);
            client.getOpstina().getTasks().add(task);
            clientService.save(client);
            redirectAttributes.addFlashAttribute("successMessage", "Надлежност и делокруг успешно додат!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/clients/" + clientId + "/newNadleznost";
        }
        return "redirect:/admin/clients/" + clientId;
    }

    @PostMapping(value = "/clients/{clientId}/newIA")
    public String savenewIA(@PathVariable final ClientId clientId,
            @RequestParam(name = "name") String name,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Client client = clientService.findOne(clientId);
        InternationalAgreements ia = IAFactory.empty(name);
        try {
            IAService.save(ia);
            client.getOpstina().getInternationalAgreements().add(ia);
            clientService.save(client);
            redirectAttributes.addFlashAttribute("successMessage", "Mеђународни документ успешно додат!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/clients/" + clientId + "/newIA";
        }
        return "redirect:/admin/clients/" + clientId;
    }

    @PostMapping(value = "/clients/{clientId}/newPPD")
    public String savenewPPD(@PathVariable final ClientId clientId,
            @RequestParam(name = "name") String name,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Client client = clientService.findOne(clientId);
        PublicPolicyDocuments ppd = PPDFactory.empty(name);
        try {
            PPDService.save(ppd);
            client.getOpstina().getPublicPolicyDocuments().add(ppd);
            clientService.save(client);
            redirectAttributes.addFlashAttribute("successMessage", "Jавни документ документ успешно додат!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/clients/" + clientId + "/newPPD";
        }
        return "redirect:/admin/clients/" + clientId;
    }

    @GetMapping(value = "/clients/{clientId}/deleteNadleznost/{Nadleznostid}")
    public String deleteNadleznost(@PathVariable final ClientId clientId,
            @PathVariable final TasksID Nadleznostid,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Client client = clientService.findOne(clientId);
        Tasks task = tasksService.findOne(Nadleznostid);
        try {
            client.getOpstina().getTasks().remove(task);
            clientService.save(client);
            tasksService.delete(Nadleznostid);
            redirectAttributes.addFlashAttribute("successMessage", "Надлежност и делокруг успешно уклоњен!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/clients/" + clientId;
        }
        return "redirect:/admin/clients/" + clientId;
    }

    @GetMapping(value = "/clients/{clientId}/deleteIA/{IAid}")
    public String deleteIA(@PathVariable final ClientId clientId,
            @PathVariable final InternationalAgreementsID IAid,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Client client = clientService.findOne(clientId);
        InternationalAgreements ia = IAService.findOne(IAid);
        
        try {
            client.getOpstina().getInternationalAgreements().remove(ia);
            clientService.save(client);
            Dokument dokument=dokumentService.findByIa(ia);
            dokumentService.delete(client, dokument.getId());
            IAService.delete(IAid);
            redirectAttributes.addFlashAttribute("successMessage", "Mеђународни документ успешно уклоњен!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/clients/" + clientId;
        }
        return "redirect:/admin/clients/" + clientId;
    }

    @GetMapping(value = "/clients/{clientId}/deletePPD/{PPDid}")
    public String deletePPD(@PathVariable final ClientId clientId,
            @PathVariable final PublicPolicyDocumentsID PPDid,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Client client = clientService.findOne(clientId);
        PublicPolicyDocuments PPD = PPDService.findOne(PPDid);
        try {
            client.getOpstina().getPublicPolicyDocuments().remove(PPD);
            clientService.save(client);
            Dokument dokument=dokumentService.findByPpd(PPD);
            dokumentService.delete(client, dokument.getId());
            PPDService.delete(PPDid);
            redirectAttributes.addFlashAttribute("successMessage", "Jавни документ успешно уклоњен!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/clients/" + clientId;
        }
        return "redirect:/admin/clients/" + clientId;
    }

    @GetMapping(value = "/clients/{clientId}/editNadleznost/{Nadleznostid}")
    public String editNadleznost(@PathVariable final ClientId clientId,
            @PathVariable final TasksID Nadleznostid,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        Tasks task = tasksService.findOne(Nadleznostid);
        model.addAttribute("task", task);
        return "admin/clients/editNadleznost";
    }

    @PostMapping(value = "/clients/{clientId}/editNadleznost/{Nadleznostid}")
    public String saveEditNadleznost(@PathVariable final ClientId clientId,
            @PathVariable final TasksID Nadleznostid,
            @RequestParam(name = "name") String name,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Client client = clientService.findOne(clientId);
        Tasks task = tasksService.findOne(Nadleznostid);
        try {
            task.setName(name);
            tasksService.save(task);
            redirectAttributes.addFlashAttribute("successMessage", "Надлежност и делокруг успешно измењен!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/clients/" + clientId;
        }
        return "redirect:/admin/clients/" + clientId;
    }

    @GetMapping(value = "/clients/{clientId}/editIA/{IAid}")
    public String editIA(@PathVariable final ClientId clientId,
            @PathVariable final InternationalAgreementsID IAid,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        InternationalAgreements IA = IAService.findOne(IAid);
        model.addAttribute("IA", IA);
        return "admin/clients/editIA";
    }

    @PostMapping(value = "/clients/{clientId}/editIA/{IAid}")
    public String saveEditIA(@PathVariable final ClientId clientId,
            @PathVariable final InternationalAgreementsID IAid,
            @RequestParam(name = "name") String name,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Client client = clientService.findOne(clientId);
        InternationalAgreements IA = IAService.findOne(IAid);
        try {
            IA.setName(name);
            IAService.save(IA);
            redirectAttributes.addFlashAttribute("successMessage", "Међународни документ успешно измењен!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/clients/" + clientId;
        }
        return "redirect:/admin/clients/" + clientId;
    }

    @GetMapping(value = "/clients/{clientId}/editPPD/{PPDid}")
    public String editPPD(@PathVariable final ClientId clientId,
            @PathVariable final PublicPolicyDocumentsID PPDid,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        PublicPolicyDocuments PPD = PPDService.findOne(PPDid);
        model.addAttribute("PPD", PPD);
        return "admin/clients/editPPD";
    }

    @PostMapping(value = "/clients/{clientId}/editPPD/{PPDid}")
    public String saveeditPPD(@PathVariable final ClientId clientId,
            @PathVariable final PublicPolicyDocumentsID PPDid,
            @RequestParam(name = "name") String name,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Client client = clientService.findOne(clientId);
        PublicPolicyDocuments PPD = PPDService.findOne(PPDid);
        try {
            PPD.setName(name);
            PPDService.save(PPD);
            redirectAttributes.addFlashAttribute("successMessage", "Јавни документ успешно измењен!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/clients/" + clientId;
        }
        return "redirect:/admin/clients/" + clientId;
    }

    @PostMapping(value = "clients/{clientId}/uploadPhoto")
    public String uploadPhoto(
            @PathVariable final ClientId clientId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            final RedirectAttributes redirectAttributes
    ) {
        PageId pageId;
        pageId = new PageId(1);//using pageid=1 to store photos for client page
        Page page = pageService.findOne(pageId);
        if (title.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Молимо Вас да унесете назив слике");
            return "redirect:/admin/clients/" + clientId + "/edit";
        } else {
            Client client = clientService.findOne(clientId);
            try {
                String filename = storageService.store(file, client.getClientId());
                photoService.save(client, pageService.findOne(pageId), title, filename);
                redirectAttributes.addFlashAttribute("successMessage", "Слика је успешно сачувана!");
                return "redirect:/admin/clients/" + clientId;
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/admin/clients/" + clientId + "/edit";
            }
        }
    }

    @PostMapping(value = "clients/{clientId}/deletePhoto/{photoId}")
    public String deletePhoto(
            @PathVariable final ClientId clientId,
            @PathVariable final PhotoId photoId,
            final RedirectAttributes redirectAttributes
    ) {
        PageId pageId;
        pageId = new PageId(1);//using pageid=1 to store photos for client page
        Page page = pageService.findOne(pageId);
        Client client = clientService.findOne(clientId);
        String filename = photoService.findFileNameById(photoId);
        photoService.delete(client, photoId);
        storageService.delete(clientId, filename);
        redirectAttributes.addFlashAttribute("successMessage", "Слика је успешно обрисана!");
        return "redirect:/admin/clients/" + clientId;
    }

    @GetMapping(value = "/clients/{clientId}/uploadDokumentIA/{IAid}")
    public String uploadDokumentIA(
            @PathVariable final ClientId clientId,
            @PathVariable final InternationalAgreementsID IAid,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        InternationalAgreements IA = IAService.findOne(IAid);
        model.addAttribute("IA", IA);
        return "admin/clients/uploadDokumentIA";
    }

    @GetMapping(value = "/clients/{clientId}/uploadDokumentPPD/{PPDid}")
    public String uploadDokumentPPD(
            @PathVariable final ClientId clientId,
            @PathVariable final PublicPolicyDocumentsID PPDid,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        PublicPolicyDocuments PPD = PPDService.findOne(PPDid);
        model.addAttribute("PPD", PPD);
        return "admin/clients/uploadDokumentPPD";
    }

    @PostMapping(value = "/clients/{clientId}/uploadDokumentIA/{IAid}")
    public String uploadDokumentIA(
            @PathVariable final ClientId clientId,
            @PathVariable final InternationalAgreementsID IAid,
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            final RedirectAttributes redirectAttributes
    ) {

        if (title.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Молимо Вас да унесете назив документа");
            return "redirect:/admin/clients/" + clientId + "/uploadDokumentIA/" + IAid;
        } else {
            Client client = clientService.findOne(clientId);
            InternationalAgreements ia = IAService.findOne(IAid);
            try {
                Dokument existing = dokumentService.findByIa(ia);
                if (existing != null) {
                    DokumentID dokumentId = new DokumentID(existing.getId());
                    String filename = dokumentService.findFileNameById(dokumentId);
                    dokumentService.delete(client, existing.getId());
                    storageService.delete(clientId, filename);
                }
                String filename = storageService.storeDOC(file, clientId);
                dokumentService.save(client, ia, title, filename);

                redirectAttributes.addFlashAttribute("successMessage", "Документ је успешно сачуван!");
                return "redirect:/admin/clients/" + clientId;
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/admin/clients/" + clientId + "/edit";
            }
        }
    }

    @PostMapping(value = "/clients/{clientId}/uploadDokumentPPD/{PPDid}")
    public String uploadDokumentPPD(
            @PathVariable final ClientId clientId,
            @PathVariable final PublicPolicyDocumentsID PPDid,
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            final RedirectAttributes redirectAttributes
    ) {

        if (title.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Молимо Вас да унесете назив документа");
            return "redirect:/admin/clients/" + clientId + "/uploadDokumentPPD/" + PPDid;
        } else {
            Client client = clientService.findOne(clientId);
            PublicPolicyDocuments PPD = PPDService.findOne(PPDid);
            try {
                Dokument existing = dokumentService.findByPpd(PPD);
                if (existing != null) {
                    DokumentID dokumentId = new DokumentID(existing.getId());
                    String filename = dokumentService.findFileNameById(dokumentId);
                    dokumentService.delete(client, existing.getId());
                    storageService.delete(clientId, filename);
                }
                String filename = storageService.storeDOC(file, clientId);
                dokumentService.save(client, PPD, title, filename);

                redirectAttributes.addFlashAttribute("successMessage", "Документ је успешно сачуван!");
                return "redirect:/admin/clients/" + clientId;
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/admin/clients/" + clientId + "/edit";
            }
        }
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
}
