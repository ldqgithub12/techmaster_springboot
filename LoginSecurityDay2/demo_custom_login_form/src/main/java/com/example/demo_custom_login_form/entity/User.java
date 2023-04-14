package com.example.demo_custom_login_form.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class User implements UserDetails {
    @Id
    private Integer id;
    private String name;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    private String password;
    @Type(JsonType.class)
    @Column(name = "roles", columnDefinition = "json")
    private List<String>roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        this.roles.forEach(role -> {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
            authorities.add(authority);
        });
        return authorities;
    }
    @Override
    public String getPassword() {
        return this.password;
    }


    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
