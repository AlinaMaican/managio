package ro.esolutions.eipl.ut.controllers

import org.springframework.ui.Model
import ro.esolutions.eipl.controllers.LoginController
import ro.esolutions.eipl.models.LoginFormModel
import spock.lang.Specification
import spock.lang.Subject

class LoginControllerSpec extends Specification {

    @Subject
    def loginController = new LoginController();
    Model model = Mock()

    def "GetLogin"() {
        when:
        def result = loginController.getLogin(model)
        then:
        result == loginController.LOGIN_PATH
        1 * model.addAttribute("loginForm", new LoginFormModel())
        0 * _
    }
}
