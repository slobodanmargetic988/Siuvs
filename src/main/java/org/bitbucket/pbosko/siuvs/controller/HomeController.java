package org.bitbucket.pbosko.siuvs.controller;

import org.bitbucket.pbosko.siuvs.model.Roles;
import org.bitbucket.pbosko.siuvs.model.SiuvsUserPrincipal;
import org.bitbucket.pbosko.siuvs.model.User;
import org.bitbucket.pbosko.siuvs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

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
        User user = ((SiuvsUserPrincipal)authentication.getPrincipal()).getUser();
        if (userService.hasRole(user, Roles.ADMIN)
                || userService.hasRole(user, Roles.RIS)
                || userService.hasRole(user, Roles.MUP)
        ) {
            return "redirect:/admin";
        } else {
            return "redirect:/client";
        }
    }

    @GetMapping(value = "/client")
    public String client(final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal)authentication.getPrincipal()).getUser();
        model.addAttribute("client", user.getClient());
        return "client/home";
    }

}
