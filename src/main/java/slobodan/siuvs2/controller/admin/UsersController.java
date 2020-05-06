package slobodan.siuvs2.controller.admin;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import slobodan.siuvs2.model.Role;

@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/admin")
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginMonitorService loginMonitorService;

    @Autowired
    private PasswordValidationService passwordValidationService;

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
    }

    private boolean canCurrentUserEditUser(User user) {
        boolean canEdit = true;
        if (user.getFirstRole().getRole() == Roles.ADMIN) {
            User currentUser = getCurrentUser();
            if (currentUser.getFirstRole().getRole() != Roles.ADMIN) {
                canEdit = false;
            }
        }
        return canEdit;
    }

    @ModelAttribute("newUser")
    public User getUser() {
        return new User();
    }

    @GetMapping(value = "/users")
    public String list(final Model model, @PageableDefault final Pageable pageable) {

        // model.addAttribute("users", userService.findAllClientIsNullOrderByNameAscLastNameAsc(pageable));
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);//admin role id
        list.add(4);// Ris role id
        model.addAttribute("users", userService.findByRolesId(list, pageable));

        return "admin/users/users";
    }

    @GetMapping(value = "/users/new")
    public String createForm() {
        return "admin/users/new";
    }

    @PostMapping(value = "/users/new")
    public String create(
            @Valid @ModelAttribute("newUser") final User user,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes,
            @RequestParam(name = "passwordRepeat") String passwordRepeat
    ) {
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult.rejectValue("email", "error.newUser", "Већ постоји корисник са датом мејл адресом");
        }
        try {
            passwordValidationService.validate(user.getPassword(), passwordRepeat);
        } catch (PasswordException e) {
            bindingResult.rejectValue("password", "error.newUser", e.getMessage());
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом креирања корисника!");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newUser", bindingResult);
            redirectAttributes.addFlashAttribute("newUser", user);
            return "redirect:/admin/users/new";
        } else {

            userService.addRole(user, Roles.RIS);
            userService.saveNewUser(user);
            redirectAttributes.addFlashAttribute("successMessage", "Корисник успешно креиран!");
            return "redirect:/admin/users";
        }
    }

    @GetMapping(value = "/users/{userId}")
    public String showUser(
            @PathVariable final Integer userId,
            final Model model
    ) {
        User user = userService.findUserById(userId);
        model.addAttribute("canEdit", canCurrentUserEditUser(user));
        model.addAttribute("user", user);
        model.addAttribute("loginHistory", loginMonitorService.getLastTenLogins(user));
        return "admin/users/user";
    }

    @GetMapping(value = "/users/{userId}/edit")
    public String editUserForm(
            @PathVariable final Integer userId,
            final Model model,
            final RedirectAttributes redirectAttributes
    ) {
        User user = userService.findUserById(userId);
        if (user == null || user.getClient() != null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом учитавања корисника!");
            return "redirect:/admin/users";
        }
        if (!canCurrentUserEditUser(user)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Недозвољен приступ измени корисника");
            return "redirect:/admin/users";
        }
        model.addAttribute("client", null);
        model.addAttribute("user", user);
        return "admin/users/edit";
    }

    @PostMapping(value = "/users/{userId}/edit")
    public String editUser(
            @PathVariable final Integer userId,
            @Valid @ModelAttribute("user") final User editUser,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        User user = userService.findUserById(userId);
        if (user == null || user.getClient() != null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом учитавања корисника!");
            return "redirect:/admin/users/" + userId;
        }
        if (!canCurrentUserEditUser(user)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Недозвољен приступ измени корисника");
            return "redirect:/admin/users";
        }
        if (!editUser.getEmail().equals(user.getEmail()) && userService.findUserByEmail(editUser.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Задата имејл адреса већ постоји у систему");
            return "redirect:/admin/users/" + userId;
        }

        userService.addRole(editUser, Roles.RIS);
        userService.updateUser(userId, editUser);
        return "redirect:/admin/users/" + userId;
    }

}
