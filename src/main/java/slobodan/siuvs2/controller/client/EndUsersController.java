package slobodan.siuvs2.controller.client;

import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Roles;
import slobodan.siuvs2.model.SiuvsUserPrincipal;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.service.LoginMonitorService;
import slobodan.siuvs2.service.PasswordValidationService;
import slobodan.siuvs2.service.UserService;
import slobodan.siuvs2.shared.PasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/client")
public class EndUsersController {

    @Autowired
    private UserService userService;
    @Autowired
    private LoginMonitorService loginMonitorService;
    @Autowired
    private PasswordValidationService passwordValidationService;

    @ModelAttribute("newUser")
    public User getUser() {
        return new User();
    }

    @GetMapping(value = "/users")
    public String users(final Model model, final Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = currentUser.getClient();
        model.addAttribute("users", userService.findAllByClientOrderByNameAscLastNameAsc(client, pageable));
        return "client/users/users";
    }

    @GetMapping(value = "/users/new")
    public String createUserForm() {
        return "client/users/new";
    }

    @PostMapping(value = "/users/new")
    public String createUser(
            @Valid @ModelAttribute("newUser") final User newUser,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes,
            @RequestParam(name = "role", defaultValue = "CLIENT_READ_ONLY") String roleName,
            @RequestParam(name = "passwordRepeat") String passwordRepeat
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        User userExists = userService.findUserByEmail(newUser.getEmail());
        if (userExists != null) {
            bindingResult.rejectValue("email", "error.newUser", "Већ постоји корисник са датом имејл адресом");
        }
        try {
            passwordValidationService.validate(newUser.getPassword(), passwordRepeat);
        } catch (PasswordException e) {
            bindingResult.rejectValue("password", "error.newUser", e.getMessage());
        }
        if (roleName.equals("UNSELECTED")) {
            redirectAttributes.addFlashAttribute("roleErrorMessage", "Морате одабрати ниво приступа");
            bindingResult.reject("role");
        }
        if (bindingResult.hasErrors() || !userService.hasRole(currentUser, Roles.CLIENT)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом креирања корисника!");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newUser", bindingResult);
            redirectAttributes.addFlashAttribute("newUser", newUser);
            return "redirect:/client/users/new";
        } else {
            Client client = currentUser.getClient();
            newUser.setClient(client);
            if (Roles.CLIENT.toString().equals(roleName)) {
                userService.addRole(newUser, Roles.CLIENT);
            } else {
                userService.addRole(newUser, Roles.CLIENT_READ_ONLY);
            }
            userService.saveNewUser(newUser);
            redirectAttributes.addFlashAttribute("successMessage", "Корисник успешно креиран!");
            return "redirect:/client/users";
        }
    }

    @GetMapping(value = "/users/{userId}")
    public String showUser(
            @PathVariable final Integer userId,
            final Model model,
            final RedirectAttributes redirectAttributes
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = currentUser.getClient();
        User user = userService.findUserById(userId);
        if (user == null || user.getClient() == null || user.getClient().getId() != client.getId()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом учитавања корисника!");
            return "redirect:/client/users";
        }
        model.addAttribute("client", client);
        model.addAttribute("user", user);
        model.addAttribute("loginHistory", loginMonitorService.getLastTenLogins(user));
        return "client/users/user";
    }

}
