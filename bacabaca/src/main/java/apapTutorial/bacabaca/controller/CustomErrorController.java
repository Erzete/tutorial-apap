package apapTutorial.bacabaca.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Profile("prod")
public class CustomErrorController {

    @GetMapping("/error/400")
    public String handle400() {
        return "error/400";
    }

    @GetMapping("/error/404")
    public String handle404() {
        return "error/404";
    }

    @GetMapping("/error/500")
    public String handle500() {
        return "error/500";
    }
}