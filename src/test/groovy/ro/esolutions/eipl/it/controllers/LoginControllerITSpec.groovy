package ro.esolutions.eipl.it.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.transaction.annotation.Transactional
import ro.esolutions.eipl.EiplApplication
import ro.esolutions.eipl.configurations.EmbeddedPostgreSQLConfiguration
import ro.esolutions.eipl.configurations.SecurityConfig
import ro.esolutions.eipl.models.UserRole
import spock.lang.Specification
import spock.lang.Unroll

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static ro.esolutions.eipl.controllers.LoginController.LOGIN_PATH_FULL
import static ro.esolutions.eipl.configurations.WebConfig.ROOT_PATH
import static ro.esolutions.eipl.generators.UserDetailsGenerator.aUserDetails

@ActiveProfiles('integration')
@ContextConfiguration(classes = [EiplApplication, EmbeddedPostgreSQLConfiguration, SecurityConfig])
@SpringBootTest
@AutoConfigureMockMvc
@SqlGroup([
        @Sql(value = ['/sql/addUsers.sql'], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
])
@Transactional
class LoginControllerITSpec extends Specification {
//        to print results remove unroll and add:
//        result.andDo(MockMvcResultHandlers.print())

    private static final String DOMAIN_MATCHER = "http://*"
    private static final String LOGIN_ERROR_PATH = "${LOGIN_PATH_FULL}?error"
    private static final ResultMatcher LOGIN_ERROR_REDIRECT_MATCHER = redirectedUrl(LOGIN_ERROR_PATH)

    @Autowired
    MockMvc mockMvc


    @Unroll
    def 'test login page with anonymous'() {
        when:
        def result = mockMvc.perform(get(getLoginUrl).with(csrf()).with(anonymous()))
        then:
        result.andExpect(expectedResult)
        if (expectedRedirectUrl) {
            result.andExpect(expectedRedirectUrl)
        }
        where:
        getLoginUrl     | expectedResult              | expectedRedirectUrl
        LOGIN_PATH_FULL | status().isOk()             | null
        '/asd'          | status().is3xxRedirection() | redirectedUrlPattern(DOMAIN_MATCHER + LOGIN_PATH_FULL)
    }

    @Unroll("Authentication with #username/#password should redirect to #redirectUrl")
    def 'test authentication'() {
        when:
        def result = mockMvc.perform(post(LOGIN_PATH_FULL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param('username', username)
                .param('password', password))
        then:
        if (expectedRedirectUrlMatcher) {
            result.andExpect(expectedRedirectUrlMatcher)
        }
        where:
        username         | password    | expectedResultStatus        | expectedRedirectUrlMatcher   | redirectUrl
        'activeUser'     | 'asd'       | status().is3xxRedirection() | redirectedUrl(ROOT_PATH)     | ROOT_PATH
        'inactiveUser'   | 'asd'       | status().is3xxRedirection() | LOGIN_ERROR_REDIRECT_MATCHER | LOGIN_ERROR_PATH
        'inexistentUser' | 'asd'       | status().is3xxRedirection() | LOGIN_ERROR_REDIRECT_MATCHER | LOGIN_ERROR_PATH
        'activeUser'     | 'wrongPass' | status().is3xxRedirection() | LOGIN_ERROR_REDIRECT_MATCHER | LOGIN_ERROR_PATH
    }

//  page lacks role-based security
    def 'test welcome page'() {
        given:
        def userDetails = aUserDetails([authorities: [UserRole.USER]])
        def expectedResultStatus = status().isOk()
        when:
        def result = mockMvc.perform(get(ROOT_PATH).with(user(userDetails)))
        then:
        result.andExpect(expectedResultStatus)
    }
}
