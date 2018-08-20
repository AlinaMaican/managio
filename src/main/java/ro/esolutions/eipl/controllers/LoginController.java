package ro.esolutions.eipl.controllers;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.esolutions.eipl.models.LoginFormModel;

import java.security.Principal;

@Controller
@RequestMapping
public class LoginController {

    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("loginForm", new LoginFormModel());
        return "login";
    }

    @PostMapping("/welcome")
    public String welcome(Principal principal, Model model) {
        System.out.println(principal);
        model.addAttribute("username", "red");
        return "welcome";
    }

}
