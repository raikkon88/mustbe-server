package com.mustbe.mustbe.repositories;

import com.mustbe.mustbe.entities.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GameRepository extends CrudRepository<Game, Long> {

    Optional<Game> findByName(String name);

}
