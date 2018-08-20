package ro.esolutions.eipl.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, path = "/welcome")
    public String welcome(Model model) {
        // TODO why arg injection for Principal doesn't work
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", principal.getName());
        return "welcome";
    }

}
