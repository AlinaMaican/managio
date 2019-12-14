package ro.esolutions.eipl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.esolutions.eipl.models.LoginFormModel;
import ro.esolutions.eipl.models.UserModel;
import ro.esolutions.eipl.services.UserService;

import static ro.esolutions.eipl.configurations.WebConfig.ROOT_PATH;

@Controller(ROOT_PATH)
@RequestMapping
public class LoginController {
    public static final String LOGIN_PATH = "login";
    public static final String LOGIN_PATH_FULL = ROOT_PATH + LOGIN_PATH;

    @Autowired
    UserService userService;

    @GetMapping(LOGIN_PATH)
    public String getLogin(final Model model) {
        model.addAttribute("loginForm", new LoginFormModel());
        return LOGIN_PATH;
    }
}
