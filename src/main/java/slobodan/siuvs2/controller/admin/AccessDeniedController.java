package slobodan.siuvs2.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Controller
public class AccessDeniedController {

    @RequestMapping(value = "/access-denied")
    public String error() {
        return "access-denied";
    }

}
