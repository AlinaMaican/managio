package ro.esolutions.eipl.ut.controllers

import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import ro.esolutions.eipl.controllers.UserController
import ro.esolutions.eipl.generator.UserModelGenerator
import ro.esolutions.eipl.services.UserService
import spock.lang.Specification
import spock.lang.Unroll

import static ro.esolutions.eipl.generator.UserModelGenerator.aUserModel


class UserControllerSpec extends Specification {

    def userService = Mock(UserService)
    def userController = new UserController(userService)

    @Unroll
    def "editUserById"() {
        given:
        def userModel = aUserModel()
        def bindingResult = Mock(BindingResult)
        def userId = 0

        when:
        def result = userController.editUserById(userModel, bindingResult, userId)

        then:
        result == expectedResult
        0 * _

        and:
        1 * bindingResult.hasErrors() >> hasErrors
        no1 * userService.editUserById(userId, userModel) >> aUserModel()

        where:
        hasErrors | no1 | expectedResult
        true      | 0   | ResponseEntity.badRequest().body(Collections.singletonMap("error", UserController.BINDING_RESULT_ERROR_MESSAGE))
        false     | 1   | ResponseEntity.ok(aUserModel())
    }
}
