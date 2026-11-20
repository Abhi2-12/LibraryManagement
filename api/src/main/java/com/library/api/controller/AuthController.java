
package com.library.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/me")
    public Map<String, Object> getCurrentUser(
            Authentication authentication) {

        // Get the roles assigned to the authenticated user.
        List<String> roles = authentication.getAuthorities()
                .stream()
                .filter(authority ->
                    authority.getAuthority().startsWith("ROLE_"))
            .map(GrantedAuthority::getAuthority)
            .toList();

        // Return the username and roles to the frontend.
        return Map.of(
                "username", authentication.getName(),
                "roles", roles
        );
    }
}
