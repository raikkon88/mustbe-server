package com.mustbe.mustbe.security;


import com.mustbe.mustbe.entities.Player;
import com.mustbe.mustbe.services.PlayerService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private PlayerService playerService;

    public UserDetailsServiceImpl(PlayerService playerService){
        this.playerService = playerService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Player p = playerService.findByPhone(email);
        if (p == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(p.getPhone(), p.getPassword(), Collections.emptyList());
    }


}
