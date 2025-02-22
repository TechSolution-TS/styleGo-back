package com.techsolution.stylego.repository;

import com.techsolution.stylego.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User getReferenceByUuid(String uuid);
    Optional<User> findByUuid(String uuid);

    Optional<User> findByEmail(String email);
}
