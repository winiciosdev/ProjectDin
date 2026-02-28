package com.winicios.dev.din.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;



public interface UserRepository extends JpaRepository <User, String> {

    UserDetails findByName(String username);

    UserDetails findByEmail(String email);

    boolean existsByEmail(String email);
}
