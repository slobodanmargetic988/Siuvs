/*
 * 
 */
package slobodan.siuvs2.controller.admin;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import slobodan.siuvs2.model.Distrikt;
import slobodan.siuvs2.model.Provincija;
import slobodan.siuvs2.model.Supervising;
import slobodan.siuvs2.service.DistriktService;
import slobodan.siuvs2.service.ProvincijaService;
import slobodan.siuvs2.service.SupervisingService;
import slobodan.siuvs2.valueObject.DistriktID;
import slobodan.siuvs2.valueObject.ProvincijaID;

/**
 *
 * @author Sloba
 */
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/admin")
public class SupervisorsController {

    @Autowired
    private UserService userService;
    @Autowired
    private SupervisingService supervisingService;

    @Autowired
    private LoginMonitorService loginMonitorService;

    @Autowired
    private ProvincijaService provincijaService;
    @Autowired
    private DistriktService distriktService;

    @Autowired
    private PasswordValidationService passwordValidationService;

    @ModelAttribute("newUser")
    public User getUser() {
        return new User();
    }

    @GetMapping(value = "/supervisors")
    public String list(final Model model, @PageableDefault final Pageable pageable) {
        // model.addAttribute("supervisors", userService.findAllClientIsNullOrderByNameAscLastNameAsc(pageable));
        List<Integer> list = new ArrayList<Integer>();
        list.add(3);//Mup role id
        list.add(6);// distrikt role id
        list.add(7);// province role id
        model.addAttribute("supervisors", userService.findByRolesId(list, pageable));

        return "admin/supervisors/supervisors";
    }

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

    @GetMapping(value = "/supervisors/{userId}")
    public String showUser(
            @PathVariable final Integer userId,
            final Model model
    ) {
        User user = userService.findUserById(userId);
        model.addAttribute("canEdit", canCurrentUserEditUser(user));
        model.addAttribute("user", user);
        model.addAttribute("loginHistory", loginMonitorService.getLastTenLogins(user));
        return "admin/supervisors/supervisor";
    }

    @GetMapping(value = "/supervisors/new")
    public String createForm(final Model model) {
        List<Provincija> provincije = new ArrayList<Provincija>();
        provincije = provincijaService.findAllOrderByNameAsc();
        List<Distrikt> distrikti = new ArrayList<Distrikt>();
        distrikti = distriktService.findAllOrderByNameAsc();
        model.addAttribute("provincije", provincije);
        model.addAttribute("distrikti", distrikti);
        return "admin/supervisors/new";
    }

    @PostMapping(value = "/supervisors/new")
    public String create(
            @Valid @ModelAttribute("newUser") final User user,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes,
            @RequestParam(name = "passwordRepeat") String passwordRepeat,
            @RequestParam(name = "role", defaultValue = "MUP") String roleName,
            @RequestParam(name = "supervisingprovince", defaultValue = "0") String provincija,
            @RequestParam(name = "supervisingdistrikt", defaultValue = "0") String distrikt
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
        if (roleName.equals("UNSELECTED")) {
            redirectAttributes.addFlashAttribute("roleErrorMessage", "Морате одабрати ниво приступа");
            bindingResult.reject("role");
        }
        if (Roles.DISTRIKT.toString().equals(roleName)) {
            if (distrikt.equals("0")) {
                redirectAttributes.addFlashAttribute("roleErrorMessage", "Морате одабрати округ");
                bindingResult.reject("supervisingdistrikt");
            }

        }
        if (Roles.PROVINCE.toString().equals(roleName)) {
            if (provincija.equals("0")) {
                redirectAttributes.addFlashAttribute("roleErrorMessage", "Морате одабрати регион");
                bindingResult.reject("supervisingprovince");
            }

        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом креирања корисника!");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newUser", bindingResult);
            redirectAttributes.addFlashAttribute("newUser", user);
            return "redirect:/admin/supervisors/new";
        } else {
//set role based on selection

            if (Roles.MUP.toString().equals(roleName)) {
                userService.addRole(user, Roles.MUP);
            }

            if (Roles.RIS.toString().equals(roleName)) {
                userService.addRole(user, Roles.RIS);
            }
//if distrikt or province are selected roles, set role and set selected province/distrikt
            Supervising supervising;
            if (Roles.DISTRIKT.toString().equals(roleName)) {
                DistriktID distriktID= new DistriktID(distrikt);
                supervising = supervisingService.findFirstByDistrikt(distriktService.findOne(distriktID));//.findFirstById(distrikt));
                userService.setSupervising(user, supervising);
                userService.addRole(user, Roles.DISTRIKT);
            }

            if (Roles.PROVINCE.toString().equals(roleName)) {
                ProvincijaID provincijaID= new ProvincijaID(provincija);
                
                supervising = supervisingService.findFirstByProvincija(provincijaService.findOne(provincijaID));//.findFirstById(provincija));
                userService.setSupervising(user, supervising);
                userService.addRole(user, Roles.PROVINCE);
            }

            userService.saveNewUser(user);
            redirectAttributes.addFlashAttribute("successMessage", "Корисник успешно креиран!");
            return "redirect:/admin/supervisors";
        }
    }

    @GetMapping(value = "/supervisors/{userId}/edit")
    public String editUserForm(
            @PathVariable final Integer userId,
            final Model model,
            final RedirectAttributes redirectAttributes
    ) {
        User user = userService.findUserById(userId);
        if (user == null || user.getClient() != null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом учитавања корисника!");
            return "redirect:/admin/supervisors";
        }
        if (!canCurrentUserEditUser(user)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Недозвољен приступ измени корисника");
            return "redirect:/admin/supervisors";
        }
        model.addAttribute("client", null);
        model.addAttribute("user", user);
        List<Provincija> provincije = new ArrayList<Provincija>();
        provincije = provincijaService.findAllOrderByNameAsc();
        List<Distrikt> distrikti = new ArrayList<Distrikt>();
        distrikti = distriktService.findAllOrderByNameAsc();
        model.addAttribute("provincije", provincije);
        model.addAttribute("distrikti", distrikti);
        return "admin/supervisors/edit";
    }

    @PostMapping(value = "/supervisors/{userId}/edit")
    public String editUser(
            @PathVariable final Integer userId,
            @Valid @ModelAttribute("user") final User editUser,
            @RequestParam(name = "role", defaultValue = "MUP") String roleName,
             @RequestParam(name = "supervisingprovince", defaultValue = "0") String provincija,
            @RequestParam(name = "supervisingdistrikt", defaultValue = "0") String distrikt,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        User user = userService.findUserById(userId);
        if (user == null || user.getClient() != null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом учитавања корисника!");
            return "redirect:/admin/supervisors/" + userId;
        }
        if (!canCurrentUserEditUser(user)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Недозвољен приступ измени корисника");
            return "redirect:/admin/supervisors";
        }
        if (!editUser.getEmail().equals(user.getEmail()) && userService.findUserByEmail(editUser.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Задата имејл адреса већ постоји у систему");
            return "redirect:/admin/supervisors/" + userId;
        }
                if (roleName.equals("UNSELECTED")) {
            redirectAttributes.addFlashAttribute("roleErrorMessage", "Морате одабрати ниво приступа");
            bindingResult.reject("role");
        }
        if (Roles.DISTRIKT.toString().equals(roleName)) {
            if (distrikt.equals("0")) {
                redirectAttributes.addFlashAttribute("roleErrorMessage", "Морате одабрати округ");
                bindingResult.reject("supervisingdistrikt");
            }

        }
        if (Roles.PROVINCE.toString().equals(roleName)) {
            if (provincija.equals("0")) {
                redirectAttributes.addFlashAttribute("roleErrorMessage", "Морате одабрати регион");
                bindingResult.reject("supervisingprovince");
            }}
                if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом креирања корисника!");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newUser", bindingResult);
            redirectAttributes.addFlashAttribute("newUser", user);
            return "redirect:/admin/supervisors/{userId}/edit";
        } else {
        
         Supervising supervising=new Supervising();
        if (Roles.MUP.toString().equals(roleName)) {
            //user.setSupervising(null);
userService.setSupervising(user, null);
            userService.addRole(editUser, Roles.MUP);
        }

        if (Roles.RIS.toString().equals(roleName)) {
            userService.addRole(editUser, Roles.RIS);
        }

         
            if (Roles.DISTRIKT.toString().equals(roleName)) {
                DistriktID distriktID= new DistriktID(distrikt);
                supervising = supervisingService.findFirstByDistrikt(distriktService.findOne(distriktID));//.findFirstById(distrikt));
                userService.setSupervising(user, supervising);
            userService.addRole(editUser, Roles.DISTRIKT);
        }

        if (Roles.PROVINCE.toString().equals(roleName)) {
            ProvincijaID provincijaID= new ProvincijaID(provincija);
                
                supervising = supervisingService.findFirstByProvincija(provincijaService.findOne(provincijaID));//.findFirstById(provincija));
                userService.setSupervising(user, supervising);
            userService.addRole(editUser, Roles.PROVINCE);
        }
        // userService.addRole(editUser, Roles.RIS);
        userService.updateUser(userId, editUser);
        return "redirect:/admin/supervisors/" + userId;
    }
        }
}
