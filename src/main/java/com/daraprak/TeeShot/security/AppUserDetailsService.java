package com.daraprak.TeeShot.security;

import com.daraprak.TeeShot.dao.AuthGroupRepository;
import com.daraprak.TeeShot.models.AuthGroup;
import com.daraprak.TeeShot.models.Player;
import com.daraprak.TeeShot.services.PlayerService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AppUserDetailsService implements UserDetailsService {

    AuthGroupRepository authGroupRepository;
    PlayerService playerService;

    @Autowired
    public AppUserDetailsService(AuthGroupRepository authGroupRepository, PlayerService playerService) {
        this.authGroupRepository = authGroupRepository;
        this.playerService = playerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Player player = null;
        try {
            player = playerService.findByEmail(username);
        } catch (NoSuchElementException ex) {
            log.warn("Couldn't find email: " + username);
            ex.printStackTrace();
        } catch (UsernameNotFoundException e) {
            log.warn("Couldn't find email: " + username);
            e.printStackTrace();
        }
        List<AuthGroup> authGroups = authGroupRepository.findByaEmail(username);
        return new AppUserPrincipal(player, authGroups);
    }
}
