package com.MyProject.Ecom.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "user")

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private boolean enabled ;
    private String email;
    private String password;
    private String rpassword;
    private String firstname;
    private String lastname;

    @Enumerated(EnumType.STRING)
    private UserRole role;




   @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }


    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

  /*  public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }*/

    @Override
    public boolean isAccountNonExpired() {
        return true; // Adjust according to your requirements
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Adjust according to your requirements
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Adjust according to your requirements
    }


    @Override
    public boolean isEnabled() {
        return true;
    }
}
