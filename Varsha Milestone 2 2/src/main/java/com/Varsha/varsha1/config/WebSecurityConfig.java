
package com.Varsha.varsha1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Disable CSRF for simplicity
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**","/auth/user").permitAll()  // Allow access to H2 console
                .anyRequest().permitAll()  // Allow all other requests without authentication
            )
            .headers(headers -> headers.frameOptions().disable())  // Disable frame options to allow H2 console to load
            .formLogin().disable();  // Disable default login

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // BCrypt for password encoding
    }
}
