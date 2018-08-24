package ro.esolutions.eipl.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.esolutions.eipl.models.LoginFormModel;

import static ro.esolutions.eipl.configurations.WebConfig.ROOT_PATH;

@Controller(ROOT_PATH)
@RequestMapping
public class LoginController {
    public static final String WELCOME_PATH = "welcome";
    public static final String LOGIN_PATH = "login";
    public static final String LOGIN_PATH_FULL = ROOT_PATH + LOGIN_PATH;

    @GetMapping(LOGIN_PATH)
    public String getLogin(final Model model) {
        model.addAttribute("loginForm", new LoginFormModel());
        return LOGIN_PATH;
    }

    @GetMapping(path = WELCOME_PATH)
    public String welcome(final Model model) {
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
        return WELCOME_PATH;
    }
}
