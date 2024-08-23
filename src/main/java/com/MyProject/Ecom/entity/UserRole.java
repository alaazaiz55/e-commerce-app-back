package com.MyProject.Ecom.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.nio.file.FileStore;
import java.security.Permission;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



public enum UserRole {


    ADMIN(Collections.singletonList("ADMIN")),
    CUSTOMER(Collections.singletonList("CUSTOMER"));




    private final List<String> authorities;

    UserRole(List<String> authorities) {
        this.authorities = authorities;
    }


   // private final Set<Permission> permissions;

    public List<GrantedAuthority> getAuthorities() {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


}
