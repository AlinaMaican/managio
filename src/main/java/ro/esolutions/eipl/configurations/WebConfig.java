package ro.esolutions.eipl.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    public static final String ROOT_PATH = "/";
    public static final String ANGULAR_FORWARD_VIEW_NAME = "forward: index.html";

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController(ROOT_PATH).setViewName(ANGULAR_FORWARD_VIEW_NAME);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
            }
        };
    }
}
