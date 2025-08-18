package dev.ayush.authenticationservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
SecurityFilterChain is an object that handles what all end points of the APIs should be authenticated
        v/s what all should not be authenticated

        we did this because the dependencies of spring security we added by default start to authenticate
        the end points of all the APIs


a @Bean annotation is used for the already existing classes while all the @Component annotations such as
@Repository @Service @Controller etc are used on the classes we create
*/

@Configuration
public class SpringSecurity {

    @Bean
    @Order(1)
    public SecurityFilterChain filteringCriteria(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())// Disables CSRF using the new method
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );
        return http.build();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
