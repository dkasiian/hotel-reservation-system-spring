package com.dkasiian.model.repositories;

import com.dkasiian.model.entities.Role;
import com.dkasiian.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
    Optional<User> findByIdAndRole(Long id, Role role);
}
