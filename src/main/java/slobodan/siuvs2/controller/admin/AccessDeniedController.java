package slobodan.siuvs2.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccessDeniedController {

    @RequestMapping(value = "/access-denied")
    public String error() {
        return "access-denied";
    }

}
