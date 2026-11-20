
package com.library.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.api.model.LibraryUser;

public interface LibraryUserRepository
        extends JpaRepository<LibraryUser, Long> {

    Optional<LibraryUser> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
