package com.mustbe.mustbe.services;

import com.mustbe.mustbe.entities.Player;
import com.mustbe.mustbe.entities.UserLogin;
import com.mustbe.mustbe.exceptions.ServiceException;
import com.mustbe.mustbe.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Player findByPhone(String phone) {
        Optional<Player> player = playerRepository.findByPhone(phone);
        if(player.isPresent()) {
            return player.get();
        }
        return null;
    }

    public Player register(Player player) throws ServiceException {
        Optional<Player> existentPlayer = playerRepository.findByPhone(player.getPhone());
        if(existentPlayer.isPresent()){
            throw new ServiceException("This user already exists", HttpStatus.CONFLICT);
        }
        playerRepository.save(player);
        return player;
    }

}
