
package com.library.api.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.library.api.model.LibraryUser;
import com.library.api.repository.LibraryUserRepository;

@Service
public class LibraryUserDetailsService implements UserDetailsService {

    private final LibraryUserRepository userRepository;

    public LibraryUserDetailsService(
            LibraryUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        LibraryUser libraryUser = userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                    new UsernameNotFoundException("User not found"));

        return User.builder()
                .username(libraryUser.getUsername())
                .password(libraryUser.getPassword())
                .roles(libraryUser.getRole())
                .build();
    }
}
