package com.taufik.challenge6.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taufik.challenge6.Models.Entities.Auth.RoleEnum;
import com.taufik.challenge6.Models.Entities.Auth.Role;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByName(RoleEnum name);
    boolean existsByName(RoleEnum name);
}
