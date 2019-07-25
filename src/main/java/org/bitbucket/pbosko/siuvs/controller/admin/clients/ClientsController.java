package org.bitbucket.pbosko.siuvs.controller.admin.clients;

import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.service.ClientService;
import org.bitbucket.pbosko.siuvs.service.UserService;
import org.bitbucket.pbosko.siuvs.valueObject.ClientId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/admin")
public class ClientsController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

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
    public String createForm() {
        return "admin/clients/new";
    }

    @PostMapping(value = "/clients/new")
    public String create(
            @Valid @ModelAttribute("newClient") final Client newClient,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом креирања клијента!");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newClient", bindingResult);
            redirectAttributes.addFlashAttribute("newClient", newClient);
            return "redirect:/admin/clients/new";
        } else {
            if (clientService.isNameUsed(newClient.getName())) {
                redirectAttributes.addFlashAttribute("errorMessage", "Клијент са истим називом већ постоји!");
                return "redirect:/admin/clients/new";
            } else {
                clientService.createNew(newClient);
                redirectAttributes.addFlashAttribute("successMessage", "Клијент успешно креиран!");
                return "redirect:/admin/clients";
            }
        }
    }

    @GetMapping(value = "/clients/{clientId}")
    public String view(@PathVariable final ClientId clientId, final Model model) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        return "admin/clients/view";
    }

    @GetMapping(value = "/clients/{clientId}/edit")
    public String edit(@PathVariable final ClientId clientId, final Model model) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("editClient", client);
        return "admin/clients/edit";
    }

    @PostMapping(value = "/clients/{clientId}/edit")
    public String update(
            @PathVariable final ClientId clientId,
            @Valid @ModelAttribute("editClient") final Client editClient,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом снимања клијента!");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newClient", bindingResult);
            redirectAttributes.addFlashAttribute("editClient", editClient);
        } else {
            clientService.update(clientId, editClient);
            redirectAttributes.addFlashAttribute("successMessage", "Клијент успешно измењен!");
        }
        return "redirect:/admin/clients/" + clientId;
    }

}
