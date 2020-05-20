package slobodan.siuvs2.controller.admin.clients;

import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.UserService;
import slobodan.siuvs2.service.LoginMonitorService;
import slobodan.siuvs2.service.PasswordValidationService;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Roles;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.shared.PasswordException;
import slobodan.siuvs2.valueObject.ClientId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/admin/clients")
public class ClientsUsersController {

    @Autowired
    private ClientService clientService;
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

    @GetMapping(value = "/{clientId}/users")
    public String users(@PathVariable final ClientId clientId, final Model model, Pageable pageable) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        model.addAttribute("users", userService.findAllByClientOrderByNameAscLastNameAsc(client, pageable));
        return "admin/clients/users/users";
    }

    @GetMapping(value = "/{clientId}/users/new")
    public String createUserForm(@PathVariable final ClientId clientId, final Model model) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        return "admin/clients/users/new";
    }

    @PostMapping(value = "/{clientId}/users/new")
    public String createUser(
            @PathVariable final ClientId clientId,
            @Valid @ModelAttribute("newUser") final User newUser,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes,
            @RequestParam(name = "role", defaultValue = "CLIENT_READ_ONLY") String roleName,
            @RequestParam(name = "passwordRepeat") String passwordRepeat
    ) {
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
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом креирања корисника!");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newUser", bindingResult);
            redirectAttributes.addFlashAttribute("newUser", newUser);
            return "redirect:/admin/clients/" + clientId + "/users/new";
        } else {
            Client client = clientService.findOne(clientId);
            newUser.setClient(client);
            if (Roles.CLIENT.toString().equals(roleName)) {
                userService.addRole(newUser, Roles.CLIENT);
            } else {
                userService.addRole(newUser, Roles.CLIENT_READ_ONLY);
            }
            userService.saveNewUser(newUser);
            redirectAttributes.addFlashAttribute("successMessage", "Корисник успешно креиран!");
            return "redirect:/admin/clients/" + clientId + "/users";
        }
    }

    @GetMapping(value = "/{clientId}/users/{userId}")
    public String showUser(
            @PathVariable final ClientId clientId,
            @PathVariable final Integer userId,
            final Model model,
            final RedirectAttributes redirectAttributes
    ) {
        Client client = clientService.findOne(clientId);
        User user = userService.findUserById(userId);
        if (user == null || user.getClient() == null || user.getClient().getId() != client.getId()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом учитавања корисника!");
            return "redirect:/admin/clients/" + clientId + "/users";
        }
        model.addAttribute("client", client);
        model.addAttribute("user", user);
        model.addAttribute("loginHistory", loginMonitorService.getLastTenLogins(user));
        return "admin/clients/users/user";
    }

    @GetMapping(value = "/{clientId}/users/{userId}/edit")
    public String editUserForm(
            @PathVariable final ClientId clientId,
            @PathVariable final Integer userId,
            final Model model,
            final RedirectAttributes redirectAttributes
    ) {
        Client client = clientService.findOne(clientId);
        User user = userService.findUserById(userId);
        if (user == null || user.getClient() == null || user.getClient().getId() != client.getId()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом учитавања корисника!");
            return "redirect:/admin/clients/" + clientId + "/users";
        }
        model.addAttribute("client", client);
        model.addAttribute("user", user);
        return "admin/clients/users/edit";
    }

    @PostMapping(value = "/{clientId}/users/{userId}/edit")
    public String editUser(
            @PathVariable final ClientId clientId,
            @PathVariable final Integer userId,
            @Valid @ModelAttribute("user") final User editUser,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes,
            @RequestParam(name = "role", defaultValue = "CLIENT_READ_ONLY") String roleName,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        User user = userService.findUserById(userId);
        if (user == null || user.getClient() == null || user.getClient().getId() != client.getId()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом учитавања корисника!");
            return "redirect:/admin/clients/" + clientId + "/users/" + userId;
        }
        if (!editUser.getEmail().equals(user.getEmail()) && userService.findUserByEmail(editUser.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Задата имејл адреса већ постоји у систему");
            return "redirect:/admin/clients/" + clientId + "/users/" + userId;
        }
        if (Roles.CLIENT.toString().equals(roleName)) {
            userService.addRole(editUser, Roles.CLIENT);
        } else {
            userService.addRole(editUser, Roles.CLIENT_READ_ONLY);
        }
        userService.updateUser(userId, editUser);
        return "redirect:/admin/clients/" + clientId + "/users/" + userId;
    }

}
