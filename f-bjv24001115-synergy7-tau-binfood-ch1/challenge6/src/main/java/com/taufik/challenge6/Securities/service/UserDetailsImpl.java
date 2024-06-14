package com.taufik.challenge6.Securities.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import com.taufik.challenge6.Models.Entities.Auth.User;

import java.util.List;

@Setter
@Getter
public class UserDetailsImpl implements UserDetails {

    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserDetailsImpl(String username, String password, List<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public UserDetailsImpl(String username, List<GrantedAuthority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    public static UserDetails build(User user) {
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName().name());
        return new UserDetailsImpl(user.getUsername(), user.getPassword(), List.of(authority));
    }

    public static UserDetailsImpl build(OidcUser oidcUser) {
        List<GrantedAuthority> authorities = oidcUser.getAuthorities().stream()
                .map(GrantedAuthority.class::cast)
                .toList();
        return new UserDetailsImpl(oidcUser.getEmail(), authorities);
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
        return true;
    }
}
