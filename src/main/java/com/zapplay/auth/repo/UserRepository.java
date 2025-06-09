package com.zapplay.auth.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zapplay.auth.model.User;



public interface UserRepository extends JpaRepository<User, Long> {
	
    User findByEmail(String username);
    User findByUsername(String username);
    User findByPhone(String username);

    
}
