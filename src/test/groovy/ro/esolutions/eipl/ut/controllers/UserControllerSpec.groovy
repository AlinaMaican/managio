package ro.esolutions.eipl.ut.controllers

import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import ro.esolutions.eipl.controllers.UserController
import ro.esolutions.eipl.exceptions.BindingValidationException
import ro.esolutions.eipl.models.validators.UsernameEmailValidator
import ro.esolutions.eipl.services.UserService
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import static ro.esolutions.eipl.generators.UserModelGenerator.aUserModel
import static ro.esolutions.eipl.generators.UserModelWithPassswordGenerator.aUserModelWithPassword

class UserControllerSpec extends Specification {

    def userService = Mock(UserService)
    def usernameEmailValidator = Mock(UsernameEmailValidator)
    @Subject
    def userController = new UserController(userService, usernameEmailValidator)

    @Unroll
    def "editUserById"() {
        given:
        def userModel = aUserModel()
        def bindingResult = Mock(BindingResult)
        def userId = 0

        when:
        def result = userController.editUserById(userModel, bindingResult, userId)

        then: 'id is initialized correctly'
        userModel.id == userId
        0 * _

        and: 'model is validated'
        1 * usernameEmailValidator.validate(userModel, bindingResult)
        1 * bindingResult.hasErrors() >> false

        then: 'validation throws an error or returns okay after editing'
        0 * _

        1 * userService.editUserById(userId, userModel) >> aUserModel()
        result == ResponseEntity.ok(aUserModel())
    }

    def 'editUserByIdThrows'() {
        given:
        def userModel = aUserModel()
        def bindingResult = Mock(BindingResult)
        def userId = 0

        when:
        def result = userController.editUserById(userModel, bindingResult, userId)

        then: 'id is initialized correctly'
        userModel.id == userId
        0 * _

        and: 'model is validated'
        1 * usernameEmailValidator.validate(userModel, bindingResult)
        1 * bindingResult.hasErrors() >> true

        then: 'validation throws an error or returns okay after editing'
        0 * _
        BindingValidationException ex = thrown(BindingValidationException)
        ex.bindingResult == bindingResult
    }

    //  TODO stefan.popescu - 2018-09-03T58:29 - power mock
//    @Unroll
//    def "resetPassword"() {
//        given:
//        def bindingResult = Mock(BindingResult)
//        def newPassword = 'newPassword'
//
//        when:
//        def responseEntity = userController.resetPassword(newPassword, bindingResult)
//
//        then:
//        responseEntity == ResponseEntity.ok(aUserModelWithPassword([password: newPassword]))
//
//        and:
//        1 * bindingResult.hasErrors() >> false
//        1 * userService.changePasswordById(1L, newPassword) >> aUserModelWithPassword([password: newPassword])
//        0 * _
//    }

    //  TODO stefan.popescu - 2018-09-03T59:26 - power mock
//    def 'resetPasswordThrows'() {
//        given:
//        def bindingResult = Mock(BindingResult)
//        when:
//        def responseEntity = userController.resetPassword(' ', bindingResult)
//        then:
//        BindingValidationException ex = thrown(BindingValidationException)
//        ex.bindingResult == bindingResult
//        1 * bindingResult.hasErrors()
//        0 * _
//    }
}
