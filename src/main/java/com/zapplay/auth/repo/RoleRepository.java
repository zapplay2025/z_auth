package com.zapplay.auth.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.zapplay.auth.model.Roles;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Long> {
	Roles findByName(String name);
}