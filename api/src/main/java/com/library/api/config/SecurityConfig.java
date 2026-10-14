
package com.library.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            // Disable CSRF for this learning API
            .csrf(csrf -> csrf.disable())

            // Allow GET requests without login
            // Require authentication for other requests
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    HttpMethod.GET,
                    "/api/books",
                    "/api/books/**"
                ).permitAll()
                .anyRequest().authenticated()
            )

            // Support Basic Authentication
            .httpBasic(Customizer.withDefaults())

            // Keep browser form login enabled
            .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(
            PasswordEncoder passwordEncoder) {

        UserDetails testUser = User.builder()
            .username("libraryuser")
            .password(passwordEncoder.encode("TestPass123!"))
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(testUser);
    }
}
