package com.mustbe.mustbe.services;

import com.mustbe.mustbe.entities.Game;
import com.mustbe.mustbe.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Iterable<Game> getGames(){
        return gameRepository.findAll();
    }

}
