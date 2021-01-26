package slobodan.siuvs2.controller;

import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import slobodan.siuvs2.model.Roles;
import slobodan.siuvs2.model.SiuvsUserPrincipal;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.WebContext;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.valueObject.OpstinaID;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(final Model model) {

        return "publicaccess/home";
    }

    @GetMapping(value = "/admin")
    public String admin() {
        return "admin/home";
    }

    @GetMapping(value = "/home")
    public String home() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/logout";
        }
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        if (userService.hasRole(user, Roles.ADMIN)
                || userService.hasRole(user, Roles.RIS)
                || userService.hasRole(user, Roles.MUP)) {
            return "redirect:/admin";
        } else {
            if (userService.hasRole(user, Roles.DISTRIKT)
                    || userService.hasRole(user, Roles.PROVINCE)) {
                return "redirect:/supervisor";
            } else {
                return "redirect:/client";
            }
        }
    }

    @GetMapping(value = "/supervisor")
    public String supervisor(final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        model.addAttribute("client", user.getClient());
        return "supervisor/home";
    }

    @GetMapping(value = "/client")
    public String client(final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        model.addAttribute("client", user.getClient());
        return "client/home";
    }

    @GetMapping(value = "/publicaccess/home")
    public String publicHome(final Model model) {
        model.addAttribute("test", "tekst koji prosledjujemo modelu");
        model.addAttribute("test2", "tekst2 koji prosledjujemo modelu");
        return "publicaccess/home";

    }

 @Autowired
    private ClientService clientService;
    
    @GetMapping(value = "/publicaccess/home/home_1")
    public String publicHome1 (
           final Model model
    ) {
        List<Client> lista=clientService.findAll();
         model.addAttribute("listaKlijenata",lista );
       return "publicaccess/home_1";
        
    }
    
      @GetMapping(value = "/publicaccess/home/clients")
    public String publicClient (
           final Model model
    ) {
       return "publicaccess/clients";
        
    }
    

    @PostMapping(value = "/publicaccess/home/pozdrav")
    public String publicHomePozdrav(
            @RequestParam(name = "name") String ime,
            @RequestParam(name = "LASTname") String prezime,
            final Model model) {
        model.addAttribute("test", "tekst koji prosledjujemo modelu");
        model.addAttribute("test2", "tekst2 koji prosledjujemo modelu");
        String pozdrav = "Dobar dan " + ime + " " + prezime;
        model.addAttribute("testPozdrav", pozdrav);

        model.addAttribute("testIme", ime);

        model.addAttribute("testPrezime", prezime);

        return "publicaccess/home";
    }

    @PostMapping(value = "/publicaccess/home/suma")
    public String publicHomeSuma(
            @RequestParam(name = "broj1") int broj1,
            @RequestParam(name = "broj2") int broj2,
            final Model model) {
        model.addAttribute("test", "tekst koji prosledjujemo modelu");
        model.addAttribute("test2", "tekst2 koji prosledjujemo modelu");

        String suma = "Suma je: " + (broj1 + broj2);
        model.addAttribute("testSuma", suma);

        model.addAttribute("testBroj1", broj1);

        model.addAttribute("testBroj2", broj2);

        return "publicaccess/home";
    }
    

}
