package org.practice.SpringStarter.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    public String Home(Model model) {
        return "home";
    }
}
