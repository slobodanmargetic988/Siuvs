package slobodan.siuvs2.controller;

import slobodan.siuvs2.model.PasswordResetToken;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.service.EmailNotificationService;
import slobodan.siuvs2.service.PasswordResetTokenService;
import slobodan.siuvs2.service.PasswordValidationService;
import slobodan.siuvs2.service.UserService;
import slobodan.siuvs2.shared.PasswordException;
import slobodan.siuvs2.shared.SiuvsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Controller
public class LoginController {

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordValidationService passwordValidationService;

    @Autowired
    public EmailNotificationService emailNotificationService;
/*
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(final Model model, final HttpServletRequest request) {
        if (request.getSession().getAttribute("errorMessage") != null) {
            String errorMessage = (String) request.getSession().getAttribute("errorMessage");
            model.addAttribute("errorMessage", errorMessage);
            request.getSession().setAttribute("errorMessage", null);
        } else {
            model.addAttribute("errorMessage", null);
        }
        return "login";
    }*/
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login2(final Model model, final HttpServletRequest request) {
        if (request.getSession().getAttribute("errorMessage") != null) {
            String errorMessage = (String) request.getSession().getAttribute("errorMessage");
            model.addAttribute("errorMessage", errorMessage);
            request.getSession().setAttribute("errorMessage", null);
        } else {
            model.addAttribute("errorMessage", null);
        }
        return "login";
    }

    @PostMapping(value = "/forgotpassword")
    public String forgotPassword(
            final RedirectAttributes redirectAttributes,
            @RequestParam(name = "email") String email
    ) {
        try {
            User user = userService.findUserByEmail(email);
            if (user != null) {
                PasswordResetToken token = passwordResetTokenService.generateNew(user);
                emailNotificationService.sendForgotPasswordEmail(token);
            }
        } catch (SiuvsException e) {
            //Internal error with email shouldn't be reported to the user
            //Add logging?
redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        redirectAttributes.addFlashAttribute("requestedPasswordReset", true);
        return "redirect:/";
    }

    @GetMapping(value = "/forgotpassword/{tokenString}")
    public String forgotPasswordResetPage(
            @PathVariable final String tokenString,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        PasswordResetToken token = passwordResetTokenService.findValidToken(tokenString);
        if (token != null) {
            model.addAttribute("passwordResetToken", token.getToken());
            return "resetpassword";
        } else {
            redirectAttributes.addFlashAttribute("badPasswordResetToken", true);
            return "redirect:/";
        }
    }

    @Transactional
    @PostMapping(value = "/forgotpassword/{tokenString}")
    public String forgotPasswordResetAction(
            @PathVariable final String tokenString,
            final RedirectAttributes redirectAttributes,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "passwordRepeat") String passwordRepeat
    ) {
        PasswordResetToken token = passwordResetTokenService.findValidToken(tokenString);
        if (token != null) {
            User user = userService.findUserByEmail(email);
            if (user != null && user.getId() == token.getUser().getId()) {
                try {
                    passwordValidationService.validate(password, passwordRepeat);
                    userService.forceChangePassword(user, password);
                    passwordResetTokenService.invalidateToken(token);
                    redirectAttributes.addFlashAttribute("successMessage", "Нова лозинка је снимљена");
                    return "redirect:/";
                } catch (PasswordException e) {
                    redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                    return "redirect:/forgotpassword/" + tokenString;
                } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("errorMessage", "Дошло је до грешке у промени лозинке");
                    return "redirect:/forgotpassword/" + tokenString;
                }
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Дошло је до грешке у промени лозинке");
                return "redirect:/forgotpassword/" + tokenString;
            }
        } else {
            redirectAttributes.addFlashAttribute("badPasswordResetToken", true);
            return "redirect:/";
        }
    }

}
