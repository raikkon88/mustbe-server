package com.mustbe.mustbe.repositories;

import com.mustbe.mustbe.entities.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {
}
