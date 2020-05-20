/*
 * 
 */
package slobodan.siuvs2.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.SiuvsUserPrincipal;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.service.ClientService;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/client")
public class OverviewController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/overview")
    public String list(final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = currentUser.getClient();

        model.addAttribute("client", clientService.findOne(client.getClientId()));
        return "client/overview/view";
    }

}
