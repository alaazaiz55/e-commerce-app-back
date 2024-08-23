package com.MyProject.Ecom.repository;

import java.util.Optional;

import com.MyProject.Ecom.entity.Role;
import com.MyProject.Ecom.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRole name);
}
