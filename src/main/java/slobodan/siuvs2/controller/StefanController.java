package slobodan.siuvs2.controller;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/stefan")
public class StefanController {

    @Autowired
    private UserService userService;
    
  @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(final Model model) {
       
        return "stefan/home";
    }


    @GetMapping(value = "/home")
    public String home() {
  return "stefan/home";
    }

  
  
}
