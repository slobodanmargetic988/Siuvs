package slobodan.siuvs2.controller.admin.clients;

import java.util.ArrayList;
import java.util.List;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.valueObject.ClientId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import slobodan.siuvs2.model.Distrikt;
import slobodan.siuvs2.model.InternationalAgreements;
import slobodan.siuvs2.model.Opstina;
import slobodan.siuvs2.model.Provincija;
import slobodan.siuvs2.model.PublicPolicyDocuments;
import slobodan.siuvs2.model.Tasks;
import slobodan.siuvs2.service.DistriktService;
import slobodan.siuvs2.service.InternationalAgreementsFactory;
import slobodan.siuvs2.service.InternationalAgreementsService;
import slobodan.siuvs2.service.OpstinaService;
import slobodan.siuvs2.service.ProvincijaService;
import slobodan.siuvs2.service.PublicPolicyDocumentsFactory;
import slobodan.siuvs2.service.PublicPolicyDocumentsService;
import slobodan.siuvs2.service.TasksFactory;
import slobodan.siuvs2.service.TasksService;
import slobodan.siuvs2.valueObject.InternationalAgreementsID;
import slobodan.siuvs2.valueObject.OpstinaID;
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

            //OpstinaID opstinaID=new OpstinaID(opstina);
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
                    return "redirect:/admin/clients/"+clientId+"/edit";
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
                return "redirect:/admin/clients/"+clientId+"/edit";
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
        Tasks task=tasksFactory.empty(name);
        try{
        tasksService.save(task);
        client.getOpstina().getTasks().add(task);
        clientService.save(client);
        redirectAttributes.addFlashAttribute("successMessage", "Надлежност и делокруг успешно додат!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/admin/clients/"+clientId+"/newNadleznost";
            }
        return "redirect:/admin/clients/" + clientId;
    }
    @PostMapping(value = "/clients/{clientId}/newIA")
    public String savenewIA(@PathVariable final ClientId clientId,
            @RequestParam(name = "name") String name,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Client client = clientService.findOne(clientId);
        InternationalAgreements ia=IAFactory.empty(name);
        try{
        IAService.save(ia);
        client.getOpstina().getInternationalAgreements().add(ia);
        clientService.save(client);
        redirectAttributes.addFlashAttribute("successMessage", "Mеђународни документ успешно додат!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/admin/clients/"+clientId+"/newIA";
            }
        return "redirect:/admin/clients/" + clientId;
    }
    @PostMapping(value = "/clients/{clientId}/newPPD")
    public String savenewPPD(@PathVariable final ClientId clientId,
            @RequestParam(name = "name") String name,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Client client = clientService.findOne(clientId);
        PublicPolicyDocuments ppd=PPDFactory.empty(name);
        try{
        PPDService.save(ppd);
        client.getOpstina().getPublicPolicyDocuments().add(ppd);
        clientService.save(client);
        redirectAttributes.addFlashAttribute("successMessage", "Jавни документ документ успешно додат!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/admin/clients/"+clientId+"/newPPD";
            }
        return "redirect:/admin/clients/" + clientId;
    }
        @GetMapping(value = "/clients/{clientId}/deleteNadleznost/{Nadleznostid}")
    public String deleteNadleznost(@PathVariable final ClientId clientId,
            @PathVariable final TasksID Nadleznostid,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Client client = clientService.findOne(clientId);
        Tasks task=tasksService.findOne(Nadleznostid);
        try{        
        client.getOpstina().getTasks().remove(task);
        clientService.save(client);
        tasksService.delete(Nadleznostid);
        redirectAttributes.addFlashAttribute("successMessage", "Надлежност и делокруг успешно уклоњен!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/admin/clients/"+clientId;
            }
        return "redirect:/admin/clients/" + clientId;
    }
    @GetMapping(value = "/clients/{clientId}/deleteIA/{IAid}")
    public String deleteIA(@PathVariable final ClientId clientId,
            @PathVariable final InternationalAgreementsID IAid,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Client client = clientService.findOne(clientId);
        InternationalAgreements ia=IAService.findOne(IAid);
        try{        
        client.getOpstina().getInternationalAgreements().remove(ia);
        clientService.save(client);
        IAService.delete(IAid);
        redirectAttributes.addFlashAttribute("successMessage", "Mеђународни документ успешно уклоњен!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/admin/clients/"+clientId;
            }
        return "redirect:/admin/clients/" + clientId;
    }
    
    @GetMapping(value = "/clients/{clientId}/deletePPD/{PPDid}")
    public String deletePPD(@PathVariable final ClientId clientId,
            @PathVariable final PublicPolicyDocumentsID PPDid,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Client client = clientService.findOne(clientId);
        PublicPolicyDocuments PPD=PPDService.findOne(PPDid);
        try{        
        client.getOpstina().getPublicPolicyDocuments().remove(PPD);
        clientService.save(client);
        PPDService.delete(PPDid);
        redirectAttributes.addFlashAttribute("successMessage", "Jавни документ успешно уклоњен!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/admin/clients/"+clientId;
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
        Tasks task=tasksService.findOne(Nadleznostid);
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
        Tasks task=tasksService.findOne(Nadleznostid);
        try{        
        task.setName(name);       
        tasksService.save(task);
        redirectAttributes.addFlashAttribute("successMessage", "Надлежност и делокруг успешно измењен!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/admin/clients/"+clientId;
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
        InternationalAgreements IA=IAService.findOne(IAid);
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
        InternationalAgreements IA=IAService.findOne(IAid);
        try{        
        IA.setName(name);       
        IAService.save(IA);
        redirectAttributes.addFlashAttribute("successMessage", "Међународни документ успешно измењен!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/admin/clients/"+clientId;
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
        PublicPolicyDocuments PPD=PPDService.findOne(PPDid);
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
        PublicPolicyDocuments PPD=PPDService.findOne(PPDid);
        try{        
        PPD.setName(name);       
        PPDService.save(PPD);
        redirectAttributes.addFlashAttribute("successMessage", "Јавни документ успешно измењен!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/admin/clients/"+clientId;
            }
        return "redirect:/admin/clients/" + clientId;
    }
}
