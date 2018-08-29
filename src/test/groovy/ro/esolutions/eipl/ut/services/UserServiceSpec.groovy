package groovy.ro.esolutions.eipl.ut.services


import ro.esolutions.eipl.exceptions.UserNotFoundException
import ro.esolutions.eipl.repositories.UserRepository
import ro.esolutions.eipl.services.UserService
import spock.lang.Specification

import static ro.esolutions.eipl.generator.UserGenerator.aUser
import static ro.esolutions.eipl.generator.UserModelGenerator.aUserModel

class UserServiceSpec extends Specification {

    def userRepository = Mock(UserRepository)
    def userService = new UserService(userRepository)

    def "editUserById"() {
        given:
        def userModel = aUserModel()
        def user = aUser();
        def userId = 0

        when:
        def result = userService.editUserById(userId, userModel)

        then:
        result == userModel
        0 * _

        and:
        1 * userRepository.findById(userId) >> Optional.of(user)
        1 * userRepository.save(user) >> user

    }

    def "editUserByIdError"() {
        given:
        def userModel = aUserModel()
        def userId = 0

        when:
        def result = userService.editUserById(userId, userModel)

        then:
        result == null
        0 * _
        thrown(UserNotFoundException)

        and:
        1 * userRepository.findById(userId) >> Optional.empty()
    }

    def "getFirstUser"() {
        given:
        def user = aUser()
        def id = 1L
        def userModel = aUserModel()

        when:
        def result = userService.getFirstUser()

        then:
        result == userModel
        0*_

        and:
        1 * userRepository.findById(id) >> Optional.of(user)
    }

    def "changePasswordById"() {
        given:
        def userModel = aUserModel([password: "newPassword"])
        def id = 1L
        def user = aUser()
        def newPassword = "newPassword"

        when:
        def result = userService.changePasswordById(id, newPassword)

        then:
        result == userModel
        0 * _

        and:
        1 * userRepository.findById(id) >> Optional.of(user)
        1 * userRepository.save(user) >> user
    }

    def "changePasswordByIdError"() {
        given:
        def id = 0
        def newPassword = "newPassword"

        when:
        def result = userService.changePasswordById(id, newPassword)

        then:
        result == null
        0 * _
        thrown(UserNotFoundException)

        and:
        1 * userRepository.findById(id) >> Optional.empty()
    }
}
