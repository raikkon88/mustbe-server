package com.mustbe.mustbe.controllers;
import com.fasterxml.jackson.annotation.JsonView;
import com.mustbe.mustbe.entities.Player;
import com.mustbe.mustbe.services.PlayerService;
import com.mustbe.mustbe.views.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;


    @GetMapping("/self")
    @JsonView(Views.Player.class)
    public ResponseEntity<?> getSelf(UsernamePasswordAuthenticationToken token) {
        return new ResponseEntity<>(playerService.findByPhone(((Player)token.getPrincipal()).getPhone()), HttpStatus.OK);
    }

}
