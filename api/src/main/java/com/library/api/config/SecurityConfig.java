
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
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // Anyone can view books
                .requestMatchers(
                    HttpMethod.GET,
                    "/api/books",
                    "/api/books/**"
                ).permitAll()

                // Users can borrow and return books
                .requestMatchers(
                    HttpMethod.POST,
                    "/api/books/*/borrow",
                    "/api/books/*/return"
                ).hasAnyRole("USER", "ADMIN")

                // Only admins can manage book inventory
                .requestMatchers(
                    HttpMethod.POST,
                    "/api/books"
                ).hasRole("ADMIN")

                .requestMatchers(
                    HttpMethod.PUT,
                    "/api/books/**"
                ).hasRole("ADMIN")

                .requestMatchers(
                    HttpMethod.DELETE,
                    "/api/books/**"
                ).hasRole("ADMIN")

                // All other requests require authentication
                .anyRequest().authenticated()
            )

            .httpBasic(Customizer.withDefaults())
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

        UserDetails regularUser = User.builder()
            .username("libraryuser")
            .password(passwordEncoder.encode("TestPass123!"))
            .roles("USER")
            .build();

        UserDetails adminUser = User.builder()
            .username("libraryadmin")
            .password(passwordEncoder.encode("AdminPass123!"))
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(
            regularUser,
            adminUser
        );
    }
}
