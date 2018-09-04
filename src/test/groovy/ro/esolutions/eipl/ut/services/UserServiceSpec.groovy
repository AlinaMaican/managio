package ro.esolutions.eipl.ut.services

import org.springframework.security.crypto.password.PasswordEncoder
import ro.esolutions.eipl.exceptions.ResourceNotFound
import ro.esolutions.eipl.mappers.UserMapper
import ro.esolutions.eipl.mappers.UserWithPasswordMapper
import ro.esolutions.eipl.repositories.UserRepository
import ro.esolutions.eipl.services.UserService
import spock.lang.Specification
import spock.lang.Subject

import static ro.esolutions.eipl.generators.UserGenerator.aUser
import static ro.esolutions.eipl.generators.UserModelGenerator.aUserModel
import static ro.esolutions.eipl.generators.UserModelWithPassswordGenerator.aUserModelWithPassword

@SuppressWarnings("GroovyAssignabilityCheck")
class UserServiceSpec extends Specification {

    def passwordEncoder = Mock(PasswordEncoder)
    def userRepository = Mock(UserRepository)
    @Subject
    def userService = new UserService(userRepository, passwordEncoder)

    def "addUser"() {
        given:
        def userModel = aUserModelWithPassword()
        def encodedUser = UserWithPasswordMapper.fromModelToEntity(aUserModelWithPassword(password: 'gigiEncoded'))
        when: 'I add a user'
        def result = userService.addUser(userModel)
        then: 'The password is first encoded'
        1 * passwordEncoder.encode(_) >> 'gigiEncoded'
        then: 'The user is saved with its password encoded'
        1 * userRepository.save(encodedUser) >> encodedUser
        0 * _
        and:
        result == UserMapper.fromEntityToModel(encodedUser)
    }

    def "editUserById"() {
        given:
        def userModel = aUserModel()
        def user = aUser()
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
        thrown(ResourceNotFound)

        and:
        1 * userRepository.findById(userId) >> Optional.empty()
    }

    //  TODO stefan.popescu - 2018-09-03T15:16 - power mock
//    def "changePasswordById"() {
//        given:
//        def userModel = aUserModel([password: "newPassword"])
//        def id = 1L
//        def user = aUser()
//        def newPassword = "newPassword"
//
//        when:
//        def result = userService.changePasswordById(id, newPassword)
//
//        then:
//        result == userModel
//        0 * _
//
//        and:
//        1 * userRepository.findById(id) >> Optional.of(user)
//        1 * userRepository.save(user) >> user
//    }

    def "changePasswordByIdError"() {
        given:
        def id = 0
        def newPassword = "newPassword"

        when:
        def result = userService.changePasswordById(id, newPassword)

        then:
        result == null
        0 * _
        thrown(ResourceNotFound)

        and:
        1 * userRepository.findById(id) >> Optional.empty()
    }
}
