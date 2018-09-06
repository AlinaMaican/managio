package ro.esolutions.eipl.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;

import static ro.esolutions.eipl.configurations.WebConfig.ROOT_PATH;
import static ro.esolutions.eipl.controllers.LoginController.LOGIN_PATH_FULL;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String LOGOUT_PATH_FULL = "/logout";

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage(LOGIN_PATH_FULL)
                .successHandler((request, response, authentication) ->
                        new DefaultRedirectStrategy().sendRedirect(request, response, ROOT_PATH))
                .permitAll()
                .and()
                .logout()
                .logoutUrl(LOGOUT_PATH_FULL)
                .logoutSuccessHandler((request, response, authentication) ->
                        new DefaultRedirectStrategy().sendRedirect(request, response, LOGIN_PATH_FULL))
                .permitAll();

    }

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth, final PasswordEncoder passwordEncoder, final UserDetailsService userDetailsService) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
