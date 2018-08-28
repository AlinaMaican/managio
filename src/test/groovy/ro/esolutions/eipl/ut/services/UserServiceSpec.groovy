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

}
