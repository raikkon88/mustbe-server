package com.mustbe.mustbe.controllers;

import com.mustbe.mustbe.entities.Game;
import com.mustbe.mustbe.exceptions.ServiceException;
import com.mustbe.mustbe.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<?> getGames(UsernamePasswordAuthenticationToken token) {
        return new ResponseEntity<>(gameService.getGames(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> newGame(@RequestBody Game game){
        try {
            Game g = gameService.createNewGame(game);
            return new ResponseEntity<>(g, HttpStatus.OK);
        }
        catch (ServiceException e){
            return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
        }
    }

}
