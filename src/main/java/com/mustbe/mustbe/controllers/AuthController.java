package com.mustbe.mustbe.controllers;

import com.mustbe.mustbe.entities.Player;
import com.mustbe.mustbe.entities.UserLogin;
import com.mustbe.mustbe.exceptions.ServiceException;
import com.mustbe.mustbe.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserLogin user) {
        try {
            Player p = playerService.register(new Player(user.phone, bCryptPasswordEncoder.encode(user.password)));
            return new ResponseEntity<>(p, HttpStatus.OK);
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
        }

    }



}

