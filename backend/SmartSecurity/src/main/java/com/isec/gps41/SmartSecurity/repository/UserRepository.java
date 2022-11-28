package com.isec.gps41.SmartSecurity.repository;

import com.isec.gps41.SmartSecurity.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndActiveIsTrue(String email);

    User findByEmailAndActive(String email, boolean active);


    boolean existsByEmail(String email);


}
