package slobodan.siuvs2.controller.publicaccess;

import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.UserService;
import slobodan.siuvs2.valueObject.ClientId;
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
@RequestMapping(value = "/publicaccess")
public class PublicClientsController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/publicaccess")
    public String publicacess() {
        return "publicaccess/home";
    }

    @GetMapping(value = "/publicaccess/home")
    public String publicacess2() {
        return "publicaccess/home";
    }

    @GetMapping(value = "/clients")
    public String list(final Model model, @PageableDefault final Pageable pageable) {
        model.addAttribute("clients", clientService.findAllOrderByActiveDescNameAsc(pageable));
        return "publicaccess/clients";
    }

    @GetMapping(value = "/clients/{clientId}")
    public String view(@PathVariable final ClientId clientId, final Model model) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        return "publicaccess/clientmenu";
    }


}
