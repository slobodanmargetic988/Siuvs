package slobodan.siuvs2.controller;

import slobodan.siuvs2.model.ChangeEmailEntity;
import slobodan.siuvs2.model.ChangePasswordEntity;
import slobodan.siuvs2.model.SiuvsUserPrincipal;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.service.PasswordValidationService;
import slobodan.siuvs2.service.UserService;
import slobodan.siuvs2.shared.PasswordException;
import slobodan.siuvs2.shared.SiuvsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordValidationService passwordValidationService;

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
    }

    @GetMapping(value = "/profile")
    public String profile(
            @ModelAttribute("changePasswordEntity") final ChangePasswordEntity changePasswordEntity
    ) {
        return "profile/profile";
    }

    @GetMapping(value = "/profile/changepassword")
    public String changePasswordForm(
            @ModelAttribute("changePasswordEntity") final ChangePasswordEntity changePasswordEntity
    ) {
        return "profile/changepassword";
    }

    @GetMapping(value = "/profile/changeemail")
    public String changeEmailForm(
            @ModelAttribute("changeEmailEntity") final ChangeEmailEntity changeEmailEntity
    ) {
        return "profile/changeemail";
    }

    @PostMapping(value = "/profile/changepassword")
    public String changepassword(
            @Valid @ModelAttribute("changePasswordEntity") final ChangePasswordEntity changePasswordEntity,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка при промени лозинке");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.changePasswordEntity", bindingResult);
            redirectAttributes.addFlashAttribute("changePasswordEntity", changePasswordEntity);
            return "redirect:/profile/changepassword";
        }
        User user = getCurrentUser();
        changePasswordEntity.setUser(user);
        if (!changePasswordEntity.validate()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Невалидан захтев за промену лозинке");
            return "redirect:/profile/changepassword";
        }
        try {
            passwordValidationService.validate(changePasswordEntity.getNewPassword(), changePasswordEntity.getNewPasswordRepeat());
        } catch (PasswordException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/profile/changepassword";
        }
        try {
            boolean changed = userService.performChangePassword(changePasswordEntity);
            if (changed) {
                redirectAttributes.addFlashAttribute("successMessage", "Лозинка је промењена");
                return "redirect:/profile";
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Грешка при промени лозинке");
            }
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка при промени лозинке");
        }
        return "redirect:/profile/changepassword";
    }

    @PostMapping(value = "/profile/changeemail")
    public String changeemail(
            @Valid @ModelAttribute("changeEmailEntity") final ChangeEmailEntity changeEmailEntity,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка при промени имејл адресе");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.changeEmailEntity", bindingResult);
            redirectAttributes.addFlashAttribute("changeEmailEntity", changeEmailEntity);
            return "redirect:/profile/changeemail";
        }
        User user = getCurrentUser();
        changeEmailEntity.setUser(user);
        if (!changeEmailEntity.validate()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Невалидан захтев за промену имејл адресе");
            return "redirect:/profile/changeemail";
        }
        if (userService.findUserByEmail(changeEmailEntity.getNewEmail()) != null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Задата имејл адреса већ постоји у систему");
            return "redirect:/profile/changeemail";
        }
        try {
            boolean changed = userService.performChangeEmail(changeEmailEntity);
            if (changed) {
                redirectAttributes.addFlashAttribute("successMessage", "Имејл адреса је промењена");
                return "redirect:/profile";
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Грешка при промени имејл адресе");
            }
        } catch (SiuvsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка при промени имејл адресе");
        }
        return "redirect:/profile/changeemail";
    }

}
