package ro.esolutions.eipl.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext;
import ro.esolutions.eipl.EiplApplication;
import ro.esolutions.eipl.configurations.EmbeddedPostgreSQLConfiguration
import ro.esolutions.eipl.configurations.SecurityConfig
import ro.esolutions.eipl.models.UserRole
import spock.lang.Specification
import spock.lang.Unroll

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static ro.esolutions.eipl.controllers.LoginController.LOGIN_PATH_FULL
import static ro.esolutions.eipl.controllers.LoginController.WELCOME_PATH_FULL
import static ro.esolutions.eipl.generators.UserDetailsGenerator.*;

@ActiveProfiles('integration')
@ContextConfiguration(classes = [EiplApplication, EmbeddedPostgreSQLConfiguration, SecurityConfig])
@SpringBootTest
@SqlGroup([
        @Sql(value = ['/sql/addUsers.sql'], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = ['/sql/deleteAllUsers.sql'], executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
])
class LoginControllerITSpec extends Specification {
//        to print results remove unroll and add:
//        result.andDo(MockMvcResultHandlers.print())

    private static final String DOMAIN_MATCHER = "http://*"
    private static final ResultMatcher LOGIN_ERROR_REDIRECT_MATCHER = redirectedUrl("${LOGIN_PATH_FULL}?error")

    @Autowired
    WebApplicationContext wac
    MockMvc mockMvc

    def setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build()
    }

    @Unroll
//    @WithMockUser
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

    @Unroll
    def 'test authentication'() {
        when:
        def result = mockMvc.perform(post(LOGIN_PATH_FULL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param('username', username)
                .param('password', password))
        then:
        if (expectedRedirectUrl) {
            result.andExpect(expectedRedirectUrl)
        }
        where:
        username         | password        | expectedResultStatus        | expectedRedirectUrl
        'activeUser'     | 'asd'           | status().is3xxRedirection() | redirectedUrl(WELCOME_PATH_FULL)
        'inactiveUser'   | 'asd'           | status().is3xxRedirection() | LOGIN_ERROR_REDIRECT_MATCHER
        'inexistentUser' | 'asd'           | status().is3xxRedirection() | LOGIN_ERROR_REDIRECT_MATCHER
        'activeUser'     | 'wrongPassword' | status().is3xxRedirection() | LOGIN_ERROR_REDIRECT_MATCHER
    }

//  page lacks role-based security
    def 'test welcome page'() {
        when:
        def result = mockMvc.perform(get(WELCOME_PATH_FULL).with(user(userDetails)))
        then:
        result.andExpect(expectedResultStatus)
        where:
        userDetails                                  | expectedResultStatus
        aUserDetails([authorities: [UserRole.USER]]) | status().isOk()
    }
}
