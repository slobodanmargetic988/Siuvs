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
import slobodan.siuvs2.model.Opstina;
import slobodan.siuvs2.model.Provincija;
import slobodan.siuvs2.service.DistriktService;
import slobodan.siuvs2.service.OpstinaService;
import slobodan.siuvs2.service.ProvincijaService;
import slobodan.siuvs2.valueObject.OpstinaID;

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
                    return "redirect:/admin/clients/{clientId}/edit";
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
                return "redirect:/admin/clients/{clientId}/edit";
            }
            return "redirect:/admin/clients/" + clientId;
        }
    }

}
