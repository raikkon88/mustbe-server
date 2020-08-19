package com.mustbe.mustbe.services;

import com.mustbe.mustbe.entities.Game;
import com.mustbe.mustbe.exceptions.ServiceException;
import com.mustbe.mustbe.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Iterable<Game> getGames(){
        return gameRepository.findAll();
    }

    @Transactional
    public Game createNewGame(Game game) throws ServiceException {
        Optional<Game> g = gameRepository.findByName(game.getName());
        if(g.isPresent()){
            throw new ServiceException("A game with name " + game.getName() + " already exists.", HttpStatus.CONFLICT);
        }
        gameRepository.save(game);
        return game;
    }

}
