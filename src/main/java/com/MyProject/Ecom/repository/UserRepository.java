package com.MyProject.Ecom.repository;


import java.util.List;
import java.util.Optional;

import com.MyProject.Ecom.entity.User;
import com.MyProject.Ecom.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);
    List<User> findByRole(UserRole role);
}
