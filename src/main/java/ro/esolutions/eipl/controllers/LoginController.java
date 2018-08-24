package ro.esolutions.eipl.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.esolutions.eipl.models.LoginFormModel;

@Controller
@RequestMapping
public class LoginController {

    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("loginForm", new LoginFormModel());
        return "login";
    }

    @GetMapping(path = "/welcome")
    public String welcome(Model model) {
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", principal.getName());
        return "welcome";
    }
}
