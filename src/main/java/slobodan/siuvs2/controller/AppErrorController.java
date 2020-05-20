package slobodan.siuvs2.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppErrorController implements ErrorController {

    private final static String ERROR_PATH = "/error";

    @RequestMapping(value = "/error")
    public String error() {
        return "error";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
