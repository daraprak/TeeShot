package com.daraprak.TeeShot.security;

import com.daraprak.TeeShot.models.AuthGroup;
import com.daraprak.TeeShot.models.Player;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppUserPrincipal implements UserDetails {

    Player player;
    List<AuthGroup> authGroup;

    public AppUserPrincipal(Player player, List<AuthGroup> authGroup) {
        this.player = player;
        this.authGroup = authGroup;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authGroup == null) return Collections.emptyList();
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authGroup.forEach(auth -> authorities.add(new SimpleGrantedAuthority(auth.getAAuthGroup())));
        return authorities;
    }

    @Override
    public String getPassword() {
        return player.getPassword();
    }

    @Override
    public String getUsername() {
        return player.getEmail();
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
