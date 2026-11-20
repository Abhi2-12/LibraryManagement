
package com.library.api.config;

// Java utility
import java.util.List;

// Spring configuration
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// HTTP methods
import org.springframework.http.HttpMethod;

// Spring Security
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// CORS imports - added for React frontend communication
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            // Disabled CSRF for our stateless API using HTTP Basic.
            // We will need to review this before production deployment.
            .csrf(csrf -> csrf.disable())

            // Allows Spring Security to use our CORS configuration below.
            // This is needed because React and Spring Boot use different ports.
            .cors(Customizer.withDefaults())

            .authorizeHttpRequests(auth -> auth

                // Anyone can view the book list and book details.
                .requestMatchers(
                    HttpMethod.GET,
                    "/api/books",
                    "/api/books/**"
                ).permitAll()

                // Regular users and admins can borrow and return books.
                .requestMatchers(
                    HttpMethod.POST,
                    "/api/books/*/borrow",
                    "/api/books/*/return"
                ).hasAnyRole("USER", "ADMIN")

                // Only admins can add new books.
                .requestMatchers(
                    HttpMethod.POST,
                    "/api/books"
                ).hasRole("ADMIN")

                // Only admins can update existing books.
                .requestMatchers(
                    HttpMethod.PUT,
                    "/api/books/**"
                ).hasRole("ADMIN")

                // Only admins can delete books.
                .requestMatchers(
                    HttpMethod.DELETE,
                    "/api/books/**"
                ).hasRole("ADMIN")

                // Any other request requires authentication.
                .anyRequest().authenticated()
            )

            // Enables username and password authentication.
            .httpBasic(Customizer.withDefaults())

            // Keeps the built-in login form enabled.
            .formLogin(Customizer.withDefaults());

        return http.build();
    }


    // CORS CONFIGURATION
    // This method allows our React frontend to communicate with Spring Boot.
    // Without this, the browser may block requests from port 5173 to port 8080.
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        // Allow requests coming from our local React development server.
        configuration.setAllowedOrigins(
            List.of("http://localhost:5173")
        );

        // Allow the HTTP methods our frontend will use.
        configuration.setAllowedMethods(
            List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
        );

        // Allow JSON data and the Authorization header for login credentials.
        configuration.setAllowedHeaders(
            List.of("Authorization", "Content-Type")
        );

        // Allow credentialed requests from the approved React origin.
        configuration.setAllowCredentials(true);

        // Apply this CORS configuration to all API endpoints.
        UrlBasedCorsConfigurationSource source =
            new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }


    // Password encoder
    // BCrypt hashes passwords instead of storing plain text in the encoder.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // User accounts
    // For now, we are using in-memory accounts for learning.
    // These accounts are not stored in MySQL.
    @Bean
    public UserDetailsService userDetailsService(
            PasswordEncoder passwordEncoder) {

        // Regular user: can view, borrow and return books.
        UserDetails regularUser = User.builder()
            .username("libraryuser")
            .password(passwordEncoder.encode("TestPass123!"))
            .roles("USER")
            .build();

        // Admin: can manage the book inventory.
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
