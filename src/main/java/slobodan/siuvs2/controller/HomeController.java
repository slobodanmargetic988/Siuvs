package slobodan.siuvs2.controller;


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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import slobodan.siuvs2.service.ClientService;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Controller
public class HomeController {
   

 
    @Autowired
    private UserService userService;
    @Autowired
    private ClientService clientService;
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

                if (userService.hasRole(user, Roles.MOBILE_ONLY) ) {
                    return "redirect:/mobileonly";
                } else {

                    return "redirect:/client";
                }
            }
        }
    }
    
   @GetMapping(value = "/mobileonly")
    public String mobileonly(final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
       
        return "mobileonly/home";
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
    public String publicHome() {

            return "publicaccess/home";
        
    }
  
  
}
