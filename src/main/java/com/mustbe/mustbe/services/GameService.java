package com.mustbe.mustbe.services;

import com.mustbe.mustbe.entities.Event;
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

    @Transactional
    public Game addEvent(long gameId, Event event) throws ServiceException {
        Game game = findById(gameId);
        if(game == null) {
            throw new ServiceException("Game not found", HttpStatus.NOT_FOUND);
        }
        game.addEvent(event);
        gameRepository.save(game);
        return game;
    }

    @Transactional
    public Game findById(long gameId){
        Optional<Game> game = gameRepository.findById(gameId);
        if(game.isPresent()){
            return game.get();
        }
        return null;
    }

}
